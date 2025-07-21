package com.maestria.platform.maestriaplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MaestriaPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaestriaPlatformApplication.class, args);
    }

}
