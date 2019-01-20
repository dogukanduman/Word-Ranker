package com.schibsted.ranker.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface DirectoryScanner {

    /**
     * Scan directories and find path of files which have txt extension
     *
     * @param path
     * @param extension
     * @return
     * @throws IOException
     */
    List<Path> scan(Path path, String extension) throws IOException;
}
