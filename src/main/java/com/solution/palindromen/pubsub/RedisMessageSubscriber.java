package com.solution.palindromen.pubsub;

import com.solution.palindromen.service.PalindromeService;
import com.solution.palindromen.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageSubscriber implements MessageListener {
    @Autowired
    private PalindromeService palindromeService;

    public RedisMessageSubscriber() {
        palindromeService = (PalindromeService) BeanUtil.getBean(PalindromeService.class);
    }

    public void onMessage(final Message message, final byte[] pattern) {
        System.out.println("Message received is " + message.toString());

        try {
            palindromeService.addMessageToRepository(message.toString());
            palindromeService.sendMessageToWebSocketClient(message.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
