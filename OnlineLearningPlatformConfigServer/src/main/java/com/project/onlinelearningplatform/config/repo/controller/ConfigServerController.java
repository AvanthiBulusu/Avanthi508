package com.project.onlinelearningplatform.config.repo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigServerController {

    @Value("${message}")
    private String message;

    @GetMapping("/")
    public String showMessage() {
        return message;
    }
}
