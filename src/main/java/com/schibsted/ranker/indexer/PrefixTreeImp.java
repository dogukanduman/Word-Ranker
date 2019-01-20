package com.schibsted.ranker.indexer;


import java.util.Map;

public class PrefixTreeImp implements Indexer {

    private Node root = new Node();

    @Override
    public void add(String word) {

        word = word.toUpperCase();

        Map<Character, Node> children = root.getChildren();

        Node node;

        for (char c : word.toCharArray()) {

            if (children.containsKey(c)) {
                node = children.get(c);
            } else {
                node = new Node(c);
                children.put(c, node);
            }
            children = node.getChildren();
        }

    }

    @Override
    public boolean search(String word) {

        word = word.toUpperCase();

        Map<Character, Node> children = root.getChildren();

        Node node;

        for (char c : word.toCharArray()) {

            if (children.containsKey(c)) {
                node = children.get(c);
            } else {
                return false;
            }
            children = node.getChildren();
        }
        /**Check input word just the subset of the a word. */
        if(children.isEmpty()){
            return true;
        }else{
            return false;
        }

    }

}
