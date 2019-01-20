package com.schibsted.ranker;

import com.schibsted.ranker.helper.FileInfo;
import com.schibsted.ranker.helper.TestFileHelper;
import com.schibsted.ranker.service.FileReader;
import com.schibsted.ranker.service.WordParser;
import com.schibsted.ranker.service.imp.FileReaderImp;
import com.schibsted.ranker.service.imp.WordParserImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordParserTest {

    /**
     * Metadata for test file set
     */
    private List<FileInfo> fileInfoList;

    private WordParser wordParser = new WordParserImp();

    private FileReader fileReader = new FileReaderImp();

    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        fileInfoList = TestFileHelper.getFileInfoList().orElseThrow(Exception::new);
    }


    @Test
    public void parseTest() throws IOException {


        for( FileInfo fileInfo : fileInfoList){
            /**Get test set path */
            Path path = TestFileHelper.getLocalFilePath(fileInfo.getPath()).orElseThrow(IOException::new);

            String content = fileReader.read(path).get();
            int size =wordParser.parse(content).size();
            assertEquals(size, fileInfo.getWordCount());
        }
    }
}
