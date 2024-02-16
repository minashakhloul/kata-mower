package com.mowitnow.infrastructure.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileUtils {
    public static List<String> readFileLinesFromResource(String fileLocation) throws URISyntaxException {
        File file = new File(Objects.requireNonNull(FileUtils.class.getClassLoader().getResource(fileLocation)).toURI());
        return getLines(file);
    }

    private static List<String> getLines(File file) {
        Path filePath = Paths.get(file.getPath());
        Charset charset = StandardCharsets.UTF_8;
        try {
            return Files.lines(filePath, charset)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            System.err.format("I/O error: %s%n", ex);
        }
        return null;
    }
}
