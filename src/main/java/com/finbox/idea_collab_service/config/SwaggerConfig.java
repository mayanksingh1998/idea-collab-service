package com.finbox.idea_collab_service.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI ideaCollabOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Idea Collaboration Service API")
                        .description("API documentation for Finbox's Idea Collaboration platform.")
                        .version("v1.0")
                        .contact(new Contact().name("Finbox Dev Team").email("devs@finbox.com")));
    }
}
