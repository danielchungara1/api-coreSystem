package com.tplate.coresystem.shared.store.fileManger;

import com.tplate.coresystem.shared.store.exceptions.FileMangerException;

import java.io.InputStream;
import java.nio.file.Path;

public interface IFileManager {

    Boolean existsDirectory(String directory) throws FileMangerException;
    Path createDirectory(String directory) throws FileMangerException;
    Path createDirectories(String directories) throws FileMangerException;
    Path getDirectory(String directory) throws FileMangerException;
    <T extends InputStream> Object createFile(Path directory, T data, String fullName) throws FileMangerException;
    void deleteDirectoyRecursively(Path directory) throws FileMangerException;
    Path pathBase();

}
