package com.chucky.school.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Test", description = "The Test API")
@RestController
public class TestController {

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> home() {
        return ResponseEntity.ok().body(
                Map.of(
                        "message", "Welcome to School Management API",
                        "description", "This API exposes endpoints to school management api."));
    }
}