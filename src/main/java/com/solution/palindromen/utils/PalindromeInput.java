package com.solution.palindromen.utils;

public class PalindromeInput {

    private String content;
    private String timestamp;

    public PalindromeInput(String content, String timeStamp) {
        this.content = content;
        this.timestamp = timeStamp;
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
