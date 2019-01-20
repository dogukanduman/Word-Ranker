package com.schibsted.ranker.service;

import java.util.List;

public interface WordParser {

    /**
     * WordParser parses the content and create list of word splitting content with pre-given regex
     *
     * @param content
     * @return
     */
    List<String> parse(String content);
}
