package com.schibsted.ranker;

import com.schibsted.ranker.domain.FileContent;
import com.schibsted.ranker.domain.Searchable;
import com.schibsted.ranker.helper.FileInfo;
import com.schibsted.ranker.helper.TestFileHelper;
import com.schibsted.ranker.service.SearchRanker;
import com.schibsted.ranker.service.imp.SearchRankerImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchRankerTest {

    /**
     * Metadata for test file set
     */
    private List<FileInfo> fileInfoList;

    private SearchRanker searchRanker = new SearchRankerImp();

    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        fileInfoList = TestFileHelper.getFileInfoList().orElseThrow(Exception::new);
    }

    @Test
    public void calculatePercentageTest() throws IOException {


        String content = "Lorem Ipsum is simply dummy text of the printing";

        String input = "simply Ipsum printing";

        Searchable searchable = new FileContent("testFile", content);

        Long result = searchRanker.calculatePercentage(searchable, input);

        assertEquals(result, new Long(100L));

    }

}
