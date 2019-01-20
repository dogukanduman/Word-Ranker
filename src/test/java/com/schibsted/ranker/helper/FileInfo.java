package com.schibsted.ranker.helper;

public class FileInfo {

    private String name;
    private String path;
    private long wordCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getWordCount() {
        return wordCount;
    }

    public void setWordCount(long wordCount) {
        this.wordCount = wordCount;
    }
}
