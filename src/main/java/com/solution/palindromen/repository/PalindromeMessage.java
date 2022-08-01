package com.solution.palindromen.repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class PalindromeMessage {
    @Id
    private String messageId;
    private String message;
    private LocalDateTime timestamp;
    private long longestPalindromeSubstring;

    public String getMessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public long getLongestPalindromeSubstring() {
        return longestPalindromeSubstring;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setLongestPalindromeSubstring(long longestPalindromeSubstring) {
        this.longestPalindromeSubstring = longestPalindromeSubstring;
    }
}
