package com.javatechie.service;

import com.javatechie.dto.LlamaResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.ai.chat.messages.Media;
import java.io.IOException;
import java.util.List;

@Service
public class LlamaAiServiceImpl implements LlamaAiService {

    @Autowired
    private OllamaChatModel chatModel;


    @Override
    public LlamaResponse generateMessage(String promptMessage) {

        ChatResponse response = chatModel.call(
                new Prompt(
                        promptMessage,
                        OllamaOptions.create()
                                .withModel("llama2")
                ));

        return new LlamaResponse().setMessage(response.getResult().getOutput().getContent());
    }
//this visual AI can be done using  llava model not llama2 model
  @Override
  public LlamaResponse loadImageAndAnalyse() {

      ClassPathResource resource = new ClassPathResource("/multimodal.test.png");
      var userMessage = new UserMessage("Explain what do you see on this picture?",
              List.of(new Media(MimeType.valueOf("image/png"),resource )));

      ChatResponse response = chatModel.call(
              new Prompt(List.of(userMessage), OllamaOptions.create().withModel("llama2")));
      return new LlamaResponse().setMessage(response.getResult().getOutput().getContent());
  }
}