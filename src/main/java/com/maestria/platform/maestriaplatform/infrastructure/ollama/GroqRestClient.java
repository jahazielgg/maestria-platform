package com.maestria.platform.maestriaplatform.infrastructure.ollama;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Component;

@Component
public class GroqRestClient implements OllamaClient {

    private final OpenAiChatModel chatModel;

    public GroqRestClient(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String generateMaterial(String prompt) {
        try {
            var response = chatModel.call(new Prompt(prompt));
            return response.getResult().getOutput().getText();
        } catch (Exception e) {
            throw new RuntimeException("Error generating material: " + e.getMessage());
        }
    }
}
