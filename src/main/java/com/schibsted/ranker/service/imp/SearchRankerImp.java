package com.schibsted.ranker.service.imp;

import com.schibsted.ranker.domain.Searchable;
import com.schibsted.ranker.service.SearchRanker;
import com.schibsted.ranker.service.WordParser;

import java.util.List;

public class SearchRankerImp implements SearchRanker {

    /**
     * WordParser parses the content and create list of word splitting content with pre-given regex
     */
    private WordParser wordParser = new WordParserImp();

    @Override
    public Long calculatePercentage(Searchable searchable, String input) {

        /** Split input data into words */
        List<String> wordList = wordParser.parse(input);

        int numberOfWords = wordList.size();

        /** Calculate number of words which found in file content */
        long numberOfFoundWords = wordList.stream().filter(word -> searchable.search(word)).count();

        /** Calculate rank */
        return (numberOfFoundWords* 100 / numberOfWords) ;
    }
}
