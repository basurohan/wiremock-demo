package com.example.wiremockdemoproject.integrationTests;

import com.example.wiremockdemoproject.model.Model;
import com.example.wiremockdemoproject.service.HomeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@WireMockTest(httpPort = 8081)
public class ServiceIntegrationTest {

    @Autowired
    HomeService service;
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void success_response() throws JsonProcessingException {
        Model model = Model.builder()
                .status(200)
                .message("Success")
                .build();

//        JsonNode jsonNode = mapper.valueToTree(model);
        String jsonNode = mapper.writeValueAsString(model);

        stubFor(get(urlEqualTo("/"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonNode)));

        Model response = service.getStatus();

        assertEquals(200, response.getStatus());
    }

}
