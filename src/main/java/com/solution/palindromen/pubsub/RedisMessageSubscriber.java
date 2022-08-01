package com.solution.palindromen.pubsub;

import com.solution.palindromen.repository.PalindromeMessage;
import com.solution.palindromen.repository.PalindromeMessageRepo;
import com.solution.palindromen.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RedisMessageSubscriber implements MessageListener {

    @Autowired
    private PalindromeMessageRepo palindromeMessageRepo;

    public RedisMessageSubscriber() {
        palindromeMessageRepo = (PalindromeMessageRepo) BeanUtil.getBean(PalindromeMessageRepo.class);
    }

    public void onMessage(final Message message, final byte[] pattern) {
        System.out.println("Message received is " + message.toString());

        try {
            addMessageToRepository(message.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // addMessageToWebSocketClients();
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
}
