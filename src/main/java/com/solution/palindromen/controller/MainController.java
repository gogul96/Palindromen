package com.solution.palindromen.controller;

import com.solution.palindromen.service.PalindromeService;
import com.solution.palindromen.service.ReversalService;
import com.solution.palindromen.utils.PalindromeInput;
import com.solution.palindromen.utils.PalindromeOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import com.solution.palindromen.utils.ReversalInput;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class MainController {

    @Autowired
    private ReversalService reversalService;
    @Autowired
    private PalindromeService palindromeService;

    @RequestMapping("/reverse")
    public ResponseEntity<String> doReversal(@RequestBody ReversalInput reversalInput) {
        return ResponseEntity.ok(reversalService.doStringReverse(reversalInput));
    }

    @PostMapping("/addLongestPalindrome")
    public ResponseEntity<PalindromeOutput> getLongestPalindrome(@RequestBody PalindromeInput palindromeInput) {
        try {
            return ResponseEntity.ok(palindromeService.getLongestPalindrome(palindromeInput));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/pushLongestPalindrome")
    public ResponseEntity pushLongestPalindrome(@RequestBody PalindromeInput palindromeInput) {
        try {
            palindromeService.findAndPushLongestPalindrome(palindromeInput);
            return ResponseEntity.accepted().build();
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
}
