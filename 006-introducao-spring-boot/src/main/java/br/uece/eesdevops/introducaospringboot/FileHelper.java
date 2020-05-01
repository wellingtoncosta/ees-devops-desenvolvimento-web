package br.uece.eesdevops.introducaospringboot;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileHelper {

    public File readFile(String path) {
        Path filePath = Paths.get(path);
        if (Files.exists(filePath)) {
            return filePath.toFile();
        } else {
            throw new IllegalArgumentException("File path " + path + " is not a valid path.");
        }
    }

}
