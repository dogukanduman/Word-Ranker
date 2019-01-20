package com.schibsted.ranker.service.imp;

import com.schibsted.ranker.service.WordParser;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordParserImp implements WordParser {


    private final static String wordTokenizer = "[\\s]+";

    private final static String punc = "\\p{Punct}";

    private final static Pattern wordPattern = Pattern.compile(wordTokenizer);

    @Override
    public List<String> parse(String content) {

        List<String> wordList = Arrays.stream(wordPattern.split(content))
                .map(word -> word.replaceAll(punc, ""))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toList());
        return wordList;
    }
}
