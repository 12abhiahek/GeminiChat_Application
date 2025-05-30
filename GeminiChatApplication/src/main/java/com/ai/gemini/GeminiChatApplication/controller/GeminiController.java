package com.ai.gemini.GeminiChatApplication.controller;

import com.ai.gemini.GeminiChatApplication.service.QnAService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ai/qna")
public class GeminiController {
    private final QnAService qnaService;

    public GeminiController(QnAService qnaService) {
        this.qnaService=qnaService;
    }
    @PostMapping("/ask")
    public ResponseEntity<String> askMe(@RequestBody Map<String,String> payload){
        String question = payload.get("question");
        String answer = qnaService.getAnswer(question);
        return ResponseEntity.ok(answer);
    }

}
