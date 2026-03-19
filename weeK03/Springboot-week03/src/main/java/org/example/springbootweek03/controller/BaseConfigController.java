package org.example.springbootweek03.controller;

import jakarta.annotation.Resource;
import org.example.springbootweek03.config.AppConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/config")
public class BaseConfigController {
    @Value("${spring.application.name}")
        private String applicationName;
@Value("${app.appName}")
private String appName;
    @Value("${server.port}")
    private Integer port;
    @Value("${app.version}")
    private String Version;
    @Value("${app.description}")
    private String description;

    @Resource
    private AppConfig appConfig;

    @GetMapping("/info")
    public Map<String,Object> getConfig() {
        return Map.of(
                "applicationName", applicationName,
                "port", port,
                "appVersion", Version,
                "appDescription", description
        );

    }

    @GetMapping("/info2")
    public Map<String,Object> getConfig2() {
        return Map.of(
                "applicationName", appConfig.getAppName(),
                "appVersion", appConfig.getVersion(),
                "appDescription", appConfig.getDescription()

        );

    }
}
