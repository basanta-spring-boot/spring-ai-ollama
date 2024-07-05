package com.javatechie.controller;

import com.javatechie.dto.LlamaResponse;
import com.javatechie.service.LlamaAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LlamaRestController {

    private final LlamaAiService llamaAiService;

    @Autowired
    public LlamaRestController(LlamaAiService llamaAiService) {
        this.llamaAiService = llamaAiService;
    }

    @GetMapping("api/v1/ai/generate")
    public ResponseEntity<LlamaResponse> generate(
            @RequestParam(value = "promptMessage", defaultValue = "Why is the sky blue?")
            String promptMessage) {
        final LlamaResponse aiResponse = llamaAiService.generateMessage(promptMessage);
        return ResponseEntity.status(HttpStatus.OK).body(aiResponse);
    }

    @GetMapping("/ai/generateStream")
    public ResponseEntity<LlamaResponse> generateStream() {
        final LlamaResponse aiResponse = llamaAiService.loadImageAndAnalyse();
        return ResponseEntity.status(HttpStatus.OK).body(aiResponse);
    }
}