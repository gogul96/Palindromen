package com.solution.palindromen.utils;

public class PalindromeOutput {

    private String content;
    private String timestamp;
    private Long longest_timestamp_size;

    public PalindromeOutput(String content, String timeStamp) {
        this.content = content;
        this.timestamp = timeStamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getLongest_timestamp_size() {
        return longest_timestamp_size;
    }

    public void setLongest_timestamp_size(Long longest_timestamp_size) {
        this.longest_timestamp_size = longest_timestamp_size;
    }

    @Override
    public String toString() {
        return "{ \"content\": \"" + this.content +"\", \"timestamp\": \"" + this.timestamp
                + "\", \"longest_timestamp_size\": "+this.longest_timestamp_size + " }";
    }
}
