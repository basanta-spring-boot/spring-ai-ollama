package com.javatechie.service;

import com.javatechie.dto.LlamaResponse;

public interface LlamaAiService {

  LlamaResponse generateMessage(String prompt);
  LlamaResponse loadImageAndAnalyse();
}