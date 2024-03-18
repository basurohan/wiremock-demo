package com.example.wiremockdemoproject.controller;

import com.example.wiremockdemoproject.model.Model;
import com.example.wiremockdemoproject.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeService service;

    @GetMapping("/")
    public ResponseEntity<Model> getStatus() {
        return ResponseEntity.ok(service.getStatus());
    }

}
