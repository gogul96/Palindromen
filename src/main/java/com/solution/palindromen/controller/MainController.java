package com.solution.palindromen.controller;

import com.solution.palindromen.service.PalindromeService;
import com.solution.palindromen.utils.PalindromeInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class MainController {

    @Autowired
    private PalindromeService palindromeService;

    @PostMapping("/pushLongestPalindrome")
    public ResponseEntity pushLongestPalindrome(@RequestBody PalindromeInput palindromeInput) {
        try {
            if (palindromeService.findAndPushLongestPalindrome(palindromeInput)) {
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.badRequest().body("Invalid Content "+ palindromeInput.getContent());
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/getPublishedPalindromes")
    public ResponseEntity<List<String>> getPublishedPalindromes() {
        try {
            return ResponseEntity.ok(palindromeService.getStoredPalindromes());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @MessageMapping("/palindrome")
    @SendTo("/topic/messages")
    public String send(final PalindromeInput palindromeInput) throws Exception {
        try {
            return palindromeService.findAndStoreLongestPalindrome(palindromeInput).toString();
        } catch (Exception e) {
            throw new Exception("Internal Server Error");
        }
    }
}
