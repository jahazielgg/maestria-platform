package com.maestria.platform.maestriaplatform.config;

import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.OpenAiChatModel;


@Configuration
public class GroqConfig {

    @Bean
    public OpenAiApi groqApi(
            @Value("${spring.ai.openai.base-url}") String baseUrl,
            @Value("${spring.ai.openai.api-key}") String apiKey
    ) {
        return OpenAiApi.builder()
                .baseUrl(baseUrl)
                .apiKey(apiKey)
                .build();
    }

    @Bean
    public OpenAiChatModel groqChatModel(
            OpenAiApi groqApi,
            @Value("${spring.ai.openai.chat.options.model}") String model,
            @Value("${spring.ai.openai.chat.options.temperature}") Double temperature
    ) {
        return OpenAiChatModel.builder()
                .openAiApi(groqApi)
                .defaultOptions(OpenAiChatOptions.builder()
                        .model(model)
                        .temperature(temperature)
                        .build())
                .build();
    }
}