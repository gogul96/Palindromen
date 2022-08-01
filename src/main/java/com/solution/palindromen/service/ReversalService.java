package com.solution.palindromen.service;

import com.solution.palindromen.utils.ReversalInput;

@org.springframework.stereotype.Service
public class ReversalService {

    public String doStringReverse(ReversalInput reversalInput) {
        return new StringBuilder(reversalInput.getContent()).reverse().toString();
    }
}
