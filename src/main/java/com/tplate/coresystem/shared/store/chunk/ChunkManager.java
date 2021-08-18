package com.tplate.coresystem.shared.store.chunk;

import com.tplate.coresystem.shared.store.exceptions.FileMangerException;
import com.tplate.coresystem.shared.store.fileManger.FileManager;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;

@Service
public class ChunkManager extends FileManager {

    public ChunkManager(String pathBase) {
        super(pathBase);
    }

    public ChunkManager() {
    }

    public Object createFileFromChuks(Path chunkDirectory, String fullName){
        InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        Path pathFile = (Path) this.createFile(chunkDirectory.getParent(), inputStream, fullName);

        //Leo chunks de forma ordenada y Append cada chunk en el finalFile  creado
        try {
            Files.walk(chunkDirectory)
                    .sorted(Comparator.naturalOrder())
                    .map(Path::toFile)
                    .forEach(file -> {
                        try {
                            if (file.isFile()){
                                Files.write(pathFile, Files.readAllBytes(Paths.get(file.getPath())), StandardOpenOption.APPEND);
                            }
                        }catch (Exception e){
                            throw new FileMangerException("Ocurrio un error al crear al crear el archivo final: " + fullName + ".\nException: "+ e.getClass().getCanonicalName());
                        }
                    });
        }catch (Exception e){
            throw new FileMangerException("Ocurrio un error al crear al crear el archivo final: " + fullName + ".\nException: "+ e.getClass().getCanonicalName());
        }
        return pathFile;
    }

    public void uploadFile(String idInscripcion, String idFile, Chunk chunk, String mime){
        String chunkDirectory = idInscripcion + File.separator + idFile;
        Path chunkPath;

        if (!this.existsDirectory(chunkDirectory)){
            chunkPath = this.createDirectory(chunkDirectory);
        }else{
            chunkPath = this.getDirectory(chunkDirectory);
        }

        this.createChunk(chunkPath, chunk);

        if (this.isLastChunk(chunkPath, chunk)){
            this.createFileFromChuks(chunkPath, idFile + "." + mime);
            this.deleteDirectoyRecursively(chunkPath);
        }
    }

    private boolean isLastChunk(Path chunkPath, Chunk chunk) {
        return (new File(chunkPath.toString())).list().length == chunk.getNroChunk();
    }

    public Object createChunk(Path chunkDirectory, Chunk chunk){
        return super.createFile(chunkDirectory, chunk.getData(), chunk.getNroChunk().toString());
    }
}
