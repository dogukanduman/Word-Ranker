package com.schibsted.ranker.helper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Helper for read test set metadata
 */
public class TestFileHelper {

    private static List<FileInfo> fileInfoList;

    public static Optional<List<FileInfo>> getFileInfoList() {

        if (fileInfoList == null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Path path = getLocalFilePath("/fileInfo.json").orElseThrow(IOException::new);
                File file = new File(path.toUri());
                fileInfoList = Arrays.asList(objectMapper.readValue(file, FileInfo[].class));
            } catch (IOException e) {
                return Optional.empty();
            }

        }
        return Optional.of(fileInfoList);
    }

    public static Optional<Path> getLocalFilePath(String fileName) {
        URL url = TestFileHelper.class.getResource(fileName);
        try {
            return Optional.of(Paths.get(url.toURI()));
        } catch (URISyntaxException e) {
            return Optional.empty();
        }

    }

}
