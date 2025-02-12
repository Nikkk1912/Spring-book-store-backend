package com.springbookserver.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContentToFileWriter {

    public static Path saveStringToFile(String savePath, String content) throws IOException {
        Path path = Paths.get(savePath, "books.xml");
        Path parentDir = path.getParent();

        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        try (java.io.FileWriter fileWriter = new java.io.FileWriter(path.toFile())) {
            fileWriter.write(content);
        }

        return path;
    }
}
