package com.schibsted.ranker;

import com.schibsted.ranker.helper.FileInfo;
import com.schibsted.ranker.helper.TestFileHelper;
import com.schibsted.ranker.service.DirectoryScanner;
import com.schibsted.ranker.service.imp.DirectoryScannerImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectoryScannerTest {

    /**
     * Metadata for test file set
     */
    private List<FileInfo> fileInfoList;

    private DirectoryScanner directoryScanner = new DirectoryScannerImp();

    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        fileInfoList = TestFileHelper.getFileInfoList().orElseThrow(Exception::new);
    }

    @Test
    public void scanTest() throws IOException {

        /**Get test set path */
        Path path = TestFileHelper.getLocalFilePath("/").orElseThrow(IOException::new);

        /**Scan given directories  which have txt extension*/
        List<Path> txtFileList = directoryScanner.scan(path, ".txt");

        assertEquals(txtFileList.size(), fileInfoList.size());
    }
}
