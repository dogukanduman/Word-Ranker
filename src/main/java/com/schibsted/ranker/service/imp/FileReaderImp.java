package com.schibsted.ranker.service.imp;

import com.schibsted.ranker.service.FileReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderImp implements FileReader {

    @Override
    public Optional<String> read(Path path) {

        String data;
        try (Stream<String> stream = Files.lines(path, StandardCharsets.ISO_8859_1)) {
            data = stream.collect(Collectors.joining(" "));
        } catch (IOException e) {
            return Optional.empty();
        }
        return Optional.of(data);
    }
}

