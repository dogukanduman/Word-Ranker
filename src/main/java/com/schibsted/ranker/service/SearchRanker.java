package com.schibsted.ranker.service;

import com.schibsted.ranker.domain.Searchable;

public interface SearchRanker {

    /**
     * Search input in indexed content and
     * calculate percentage of found words
     *
     * @param searchable
     * @param input
     * @return
     */
    Long calculatePercentage(Searchable searchable, String input);

}
