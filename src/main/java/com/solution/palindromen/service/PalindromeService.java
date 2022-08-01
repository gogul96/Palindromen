package com.solution.palindromen.service;

import com.solution.palindromen.pubsub.RedisMessagePublisher;
import com.solution.palindromen.utils.PalindromeInput;
import com.solution.palindromen.utils.PalindromeOutput;
import com.solution.palindromen.repository.PalindromeMessage;
import com.solution.palindromen.repository.PalindromeMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PalindromeService {

    @Autowired
    private PalindromeMessageRepo palindromeMessageRepo;

    @Autowired
    private RedisMessagePublisher publisher;

    @Autowired
    private SimpMessagingTemplate template;

    public PalindromeOutput getLongestPalindrome(PalindromeInput palindromeInput) throws Exception {
        try {
            PalindromeOutput palindromeOutput = new PalindromeOutput(palindromeInput.getContent(), palindromeInput.getTimestamp());
            palindromeOutput.setLongest_timestamp_size(getLongestPalindromeLength(palindromeInput.getContent().toLowerCase()));
            return palindromeOutput;
        } catch (Exception e) {
            throw new Exception("Invalid Input");
        }
    }

    private Long getLongestPalindromeLength(String content) {
        long max = 0L;
        if (content!= null && content.length() != 0) {
            int left = 0, right = 0;
            for (int i = 0; i < content.length(); i++) {
                int lengthFirst = getPalindromeSubString(content, i, i);
                int lengthSecond = getPalindromeSubString(content, i, i+1);
                int maxLength = Math.max(lengthFirst, lengthSecond);
                if (maxLength > (right - left)) {
                    left = i - (maxLength - 1) / 2;
                    right = i + maxLength / 2;
                }
            }
            max = content.substring(left, right+1).length();
        }
        return max;
    }

    private int getPalindromeSubString(String content, int start, int end) {
        while (start >= 0 && end < content.length()
                && (Character.isAlphabetic(content.charAt(start)) && (Character.isAlphabetic(content.charAt(end))))
                && (content.charAt(start) == content.charAt(end))
        ) {
            start--;
            end++;
        }
        return end - start - 1;
    }

    public List<String> getStoredPalindromes() {
        return palindromeMessageRepo.findAll()
                .stream().map(PalindromeMessage::getMessage).collect(Collectors.toList());
    }

    public boolean findAndPushLongestPalindrome(PalindromeInput palindromeInput) throws Exception {
        try {
            if (!validateInputString(palindromeInput.getContent())) {
                return false;
            }
            PalindromeOutput palindromeOutput = getLongestPalindrome(palindromeInput);
            publisher.publish(palindromeOutput.toString());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return true;
    }

    public PalindromeOutput findAndStoreLongestPalindrome(PalindromeInput palindromeInput) throws Exception{
        try {
            if (!validateInputString(palindromeInput.getContent())) {
                throw new Exception("Invalid Input");
            }
            PalindromeOutput palindromeOutput = getLongestPalindrome(palindromeInput);
            addMessageToRepository(palindromeOutput.toString());
            return palindromeOutput;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void addMessageToRepository(String message) {
        try {
            String messageId = UUID.randomUUID().toString();
            PalindromeMessage palindromeMessage = new PalindromeMessage();
            palindromeMessage.setMessageId(messageId);
            palindromeMessage.setMessage(message);
            palindromeMessage.setTimestamp(LocalDateTime.now());
            palindromeMessageRepo.saveAndFlush(palindromeMessage);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to Save message to repository");
        }
    }

    public boolean validateInputString(String content) {
        return content != null && content.matches(".*[a-zA-Z]+.*");
    }

    public void sendMessageToWebSocketClient(String message) {
        template.convertAndSend("/topic/messages", message);
    }

}
