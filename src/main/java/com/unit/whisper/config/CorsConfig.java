package com.unit.whisper.config;


import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        ArrayList<String> allowedOriginPatterns = new ArrayList<>();
        allowedOriginPatterns.add("http://localhost:3000");
        allowedOriginPatterns.add("https://whisper.smalljoy.co.kr");
        allowedOriginPatterns.add("http://whisper.smalljoy.co.kr");

        String[] patterns = allowedOriginPatterns.toArray(String[]::new);
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins(patterns)
                .exposedHeaders("Set-Cookie")
                .allowCredentials(true);
    }
}
