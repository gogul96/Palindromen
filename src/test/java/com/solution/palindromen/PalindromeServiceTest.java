package com.solution.palindromen.service;

import com.solution.palindromen.utils.PalindromeInput;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeServiceTest {

    private static PalindromeService palindromeService;

    @BeforeAll
    static void initialize() {
        palindromeService = new PalindromeService();
    }

    @Test
    void validateInputString() {
        assertEquals(palindromeService.validateInputString("abc"), true);
        assertEquals(palindromeService.validateInputString("abc12"), true);
        assertEquals(palindromeService.validateInputString("12abc"), true);
        assertEquals(palindromeService.validateInputString("ABD"), true);
        assertEquals(palindromeService.validateInputString("ABcd"), true);
        assertEquals(palindromeService.validateInputString("123"), false);
        assertEquals(palindromeService.validateInputString("#45"), false);
        assertEquals(palindromeService.validateInputString("%$#"), false);
        assertEquals(palindromeService.validateInputString("abd#45"), true);
        assertEquals(palindromeService.validateInputString(null), false);
    }


    @Test
    void getLongestPalindrome() throws Exception {
        PalindromeInput palindromeInput = new PalindromeInput("abccba", null);
        assertEquals(palindromeService.getLongestPalindrome(palindromeInput).getLongest_timestamp_size(), 6);
        palindromeInput = new PalindromeInput("", null);
        assertEquals(palindromeService.getLongestPalindrome(palindromeInput).getLongest_timestamp_size(), 0);
        palindromeInput = new PalindromeInput("abc", null);
        assertEquals(palindromeService.getLongestPalindrome(palindromeInput).getLongest_timestamp_size(), 1);
        palindromeInput = new PalindromeInput("tattarrattat", null);
        assertEquals(palindromeService.getLongestPalindrome(palindromeInput).getLongest_timestamp_size(), 12);
        palindromeInput = new PalindromeInput("TaTTarrAttAt", null);
        assertEquals(palindromeService.getLongestPalindrome(palindromeInput).getLongest_timestamp_size(), 12);
        palindromeInput = new PalindromeInput("abc11abc", null);
        assertEquals(palindromeService.getLongestPalindrome(palindromeInput).getLongest_timestamp_size(), 1);
        palindromeInput = new PalindromeInput("aaa11abc", null);
        assertEquals(palindromeService.getLongestPalindrome(palindromeInput).getLongest_timestamp_size(), 3);
        palindromeInput = new PalindromeInput("abc11aaa", null);
        assertEquals(palindromeService.getLongestPalindrome(palindromeInput).getLongest_timestamp_size(), 3);
        palindromeInput = new PalindromeInput("aaa11aaa", null);
        assertEquals(palindromeService.getLongestPalindrome(palindromeInput).getLongest_timestamp_size(), 3);
        palindromeInput = new PalindromeInput("abc11cba", null);
        assertEquals(palindromeService.getLongestPalindrome(palindromeInput).getLongest_timestamp_size(), 1);
        palindromeInput = new PalindromeInput("abc12abc", null);
        assertEquals(palindromeService.getLongestPalindrome(palindromeInput).getLongest_timestamp_size(), 1);
        palindromeInput = new PalindromeInput("123aa321", null);
        assertEquals(palindromeService.getLongestPalindrome(palindromeInput).getLongest_timestamp_size(), 2);
    }
}