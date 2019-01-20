package com.schibsted.ranker.service.imp;

import com.schibsted.ranker.service.DirectoryScanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DirectoryScannerImp implements DirectoryScanner {

    @Override
    public List<Path> scan(Path path, String extension) throws IOException {
        List<Path> pathList = Files.walk(path)
                .filter(p -> p.toString().endsWith(extension))
                .collect(toList());
        return pathList;
    }
}
