package com.tplate.coresystem.shared.store.fileManger;

import com.tplate.coresystem.shared.store.exceptions.FileMangerException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;

@Service
public class FileManager implements IFileManager{

    private Path pathBase;

    public FileManager() {
    }

    public FileManager(String pathBase) {
        try {
            this.pathBase = Paths.get(pathBase).toAbsolutePath().normalize();
            Files.createDirectories(this.pathBase);
        } catch (Exception e) {
            throw new FileMangerException("Hubo un error al crear el diretorio base. " + "\n Excepcion: " + e.getClass().getCanonicalName());
        }
    }

    @Override
    public Boolean existsDirectory(String directory) {
        return null;
    }

    @Override
    public Path createDirectory(String directory){
        try {
            return this.createDirectories(directory);
        }catch (Exception e){
            throw new FileMangerException("Ocurrio un error al crear el directorio: " + directory + ".\nException: "+ e.getClass().getCanonicalName());
        }
    }
    @Override
    public Path createDirectories(String directories){
        Path path = this.pathBase.resolve(directories);
        try {
            Files.createDirectories(path);
            return path;
        }catch (Exception e){
            throw new FileMangerException("Ocurrio un error al crear al crear los directorios: " + directories + ".\nException: "+ e.getClass().getCanonicalName());
        }
    }

    @Override
    public Path getDirectory(String directory) {
        return this.pathBase.resolve(directory);
    }

    @Override
    public Object createFile(Path directory, InputStream data, String fullName) {
        try {
            Path path = directory.resolve(fullName);
            Files.copy(data, path , StandardCopyOption.REPLACE_EXISTING);
            return path;
        }catch (Exception e){
            throw new FileMangerException("Ocurrio un error al crear al crear el archivo: " + fullName + ".\nException: "+ e.getClass().getCanonicalName());
        }
    }

    @Override
    public void deleteDirectoyRecursively(Path directory) {
        try {
            Files.walk(directory)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (Exception e) {
            throw new FileMangerException("Ocurrio un error al eliminar los directorios: " + directory + ".\nException: "+ e.getClass().getCanonicalName());
        }
    }

    @Override
    public Path pathBase() {
        return this.pathBase;
    }

    public Path getPathBase() {
        return pathBase;
    }

    public void setPathBase(Path pathBase) {
        this.pathBase = pathBase;
    }


}
