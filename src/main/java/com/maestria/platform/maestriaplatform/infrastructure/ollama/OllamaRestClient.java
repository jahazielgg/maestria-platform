package com.maestria.platform.maestriaplatform.infrastructure.ollama;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Component;

@Component
public class OllamaRestClient implements OllamaClient {

    private final OllamaChatModel chatModel;

    public OllamaRestClient(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String generateMaterial (String prompt) {
        try {
            var response = chatModel.call(
                    new Prompt(prompt,
                            OllamaOptions.builder()
                                    .model("mistral")
                                    .temperature(0.8)
                                    .build()
                    )
            );

            return response.getResult().getOutput().getText();
        } catch (Exception e) {
            throw new RuntimeException("Error generating material: " + e.getMessage());
        }
    }
}