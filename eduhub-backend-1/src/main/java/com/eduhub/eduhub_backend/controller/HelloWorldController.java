package com.eduhub.eduhub_backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HelloWorldController {

    private final Environment environment;

    @Value("${spring.application.name}")
    private String appName;

    public HelloWorldController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/env")
    public String getEnvironmentVariables() {

        String port = environment.getProperty("server.port");

        return "App Name: " + appName + ", Port: " + port;
    }
}