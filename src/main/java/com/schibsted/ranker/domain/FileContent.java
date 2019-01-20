package com.schibsted.ranker.domain;

import com.schibsted.ranker.indexer.Indexer;
import com.schibsted.ranker.indexer.PrefixTreeImp;
import com.schibsted.ranker.service.WordParser;
import com.schibsted.ranker.service.imp.WordParserImp;

import java.util.List;

public class FileContent implements Searchable {

    private String name;
    private String content;


    private WordParser wordParser = new WordParserImp();

    /**
     * Indexing algorithm for easing searching
     */
    private Indexer indexer = new PrefixTreeImp();

    public FileContent(String name, String content) {
        this.name = name;
        this.content = content;
        List<String> wordList = wordParser.parse(content);
        wordList.forEach(word -> indexer.add(word));

    }

    @Override
    public boolean search(String word) {
        return indexer.search(word);
    }

    public String getName() {
        return name;
    }
}
