package com.schibsted.ranker;

import com.schibsted.ranker.helper.FileInfo;
import com.schibsted.ranker.helper.TestFileHelper;
import com.schibsted.ranker.service.FileReader;
import com.schibsted.ranker.service.imp.FileReaderImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileReaderTest {

    /**
     * Metadata for test file set
     */
    private List<FileInfo> fileInfoList;

    private FileReader fileReader = new FileReaderImp();

    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        fileInfoList = TestFileHelper.getFileInfoList().orElseThrow(Exception::new);
    }

    @Test
    public void readTest() throws IOException {

        /**Get test set path */
        Path path = TestFileHelper.getLocalFilePath("/").orElseThrow(IOException::new);

        String testData = "test test";

        /** Create test file*/
        Files.write(Paths.get(path.toString(), "abc.test"), testData.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

        /** Get content of the test file */
        Optional<String> content = fileReader.read(Paths.get(path.toString(), "abc.test"));

        assertEquals(content.get(), testData);
    }


}
