package com.example.wiremockdemoproject.service;

import com.example.wiremockdemoproject.model.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final RestClient client;

    public Model getStatus() {
        return client.get()
                .uri("http://localhost:8081/")
                .retrieve()
                .body(Model.class);
    }

}
