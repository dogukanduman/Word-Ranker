package com.schibsted.ranker.service;

import java.nio.file.Path;
import java.util.Optional;

public interface FileReader {

    /**
     * Read file and retrieve its content
     *
     * @param path
     * @return
     */
    Optional<String> read(Path path);
}
