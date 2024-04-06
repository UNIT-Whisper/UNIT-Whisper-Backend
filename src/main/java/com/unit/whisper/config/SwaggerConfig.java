package com.unit.whisper.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .servers(getServers())
                .info(getInfo())
                .components(
                        new Components().addSecuritySchemes("user_token", tokenSecuritySchema()));
    }

    private List<Server> getServers() {
        return List.of(new Server().url("/").description("백엔드 api 서버"));
    }

    private Info getInfo() {
        return new Info().title("Whisper API").description("나만의 감정 별마당").version("v1");
    }

    public SecurityScheme tokenSecuritySchema() {
        return new SecurityScheme()
                .name("Authorization")
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP);
    }
}
