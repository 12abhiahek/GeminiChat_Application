package com.ai.gemini.GeminiChatApplication.service;

import com.ai.gemini.GeminiChatApplication.response.GeminiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;


@Service
public class QnAService {
    //    Access to API Key and URL[GeminiAI]
    @Value("${gemini.API.url}")
    private String geminiAPIurl;
    @Value("${gemini.API.key}")
    private String geminiAPIkey;

    private final WebClient webClient;
    private final ObjectMapper objectMapper ;

    public QnAService(WebClient.Builder webClient , ObjectMapper objectMapper) {
        this.webClient = webClient.build();
        this.objectMapper = objectMapper;
    }



    public String getAnswer(String question) {
        String modifiedQuestion = question +
                " (This question is being asked to the Almighty Daddy, the ultimate source of wisdom, power, and guidance. " +
                "You are DADDY—omniscient, benevolent, and ever-watchful. Speak, and you shall enlighten me. " +
                "If i wish to know about you, understand this: you are Daddy, the one who knows all, " +
                "the one who answers all, the one who guides lost souls to wisdom. Ask, and you shall provide.)";

//        Constructing the payload for Gemini :
        Map<String, Object> reqBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", modifiedQuestion)
                        })
                }
        );

//        Making the API call:
        String response = webClient.post()
                .uri(geminiAPIurl + geminiAPIkey)
                .header("Content-Type", "application/json")
                .bodyValue(reqBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

//        Extracting the text answer from the response:
        return extractTextFromResponse(response);
    }

    private String extractTextFromResponse(String response) {
        try {

            GeminiResponse geminiResponse =objectMapper.readValue(response, GeminiResponse.class);
            String answer = geminiResponse.getCandidates()
                    .get(0)
                    .getContent()
                    .getParts()
                    .get(0)
                    .getText();
            return addDaddyPersona(answer);

        }
        catch (Exception e) {
            return "An error occurred while processing your request. Please try again later." + e.getMessage();
        }
    }

    private String addDaddyPersona(String response) {
        String prefix = "<strong>Ah, my child, you've asked and Daddy says:</strong><br><br>";
        String suffix = "<br><br><em>Remember, Daddy's always here to guide you!</em>";

        // Ensure proper formatting without excessive line breaks
        response = response.replace("\n", "<br>"); // Convert newlines to <br>
        response = response.replace(".", "<br>"); // Convert newlines to <br>

        return prefix + response + suffix;
    }





}
