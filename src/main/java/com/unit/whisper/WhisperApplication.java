package com.unit.whisper;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class WhisperApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhisperApplication.class, args);
    }
}
