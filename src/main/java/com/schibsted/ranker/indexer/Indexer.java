package com.schibsted.ranker.indexer;

public interface Indexer {

    void add(String word);

    boolean search(String word);
}
