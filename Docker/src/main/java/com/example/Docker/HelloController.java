package com.example.Docker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/")
    public Map<String, Object> api() {
        return Map.of(
                "status", "success",
                "message", "Docker Spring Boot API 🟢",
                "version", "1.0",
                "timestamp", "2026-04-24T19:13:00+05:30",
                "endpoints", new String[]{"/", "/health"},
                "author", "Rocky (Ludhiana)"
        );
    }
}