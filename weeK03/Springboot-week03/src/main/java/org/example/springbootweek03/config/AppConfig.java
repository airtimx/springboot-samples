package org.example.springbootweek03.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Configuration;

@Component
@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfig {
    private String appName;
    private String description;
    private String version;
    private String[] envs;
    private Author author;

    @Data
    public static class Author {
        private String name;
        private String email;
    }
}
