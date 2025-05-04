package com.ai.gemini.GeminiChatApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class GeminiChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeminiChatApplication.class, args);


	}
	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}

}
