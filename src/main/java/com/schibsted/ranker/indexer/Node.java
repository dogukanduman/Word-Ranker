package com.schibsted.ranker.indexer;

import java.util.HashMap;


public class Node {

    private char character;
    private HashMap<Character, Node> children = new HashMap<Character, Node>();

    public Node() {

    }

    public Node(char character) {
        this.character = character;
    }

    public HashMap<Character, Node> getChildren() {
        return children;
    }
}
