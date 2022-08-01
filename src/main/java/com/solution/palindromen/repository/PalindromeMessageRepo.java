package com.solution.palindromen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalindromeMessageRepo extends JpaRepository<PalindromeMessage, String> {
}
