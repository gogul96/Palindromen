package com.solution.palindromen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class PalindromenApplication {

	public static void main(String[] args) {
		SpringApplication.run(PalindromenApplication.class, args);
	}

}
