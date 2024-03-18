package com.example.wiremockdemoproject.integrationTests;

import com.example.wiremockdemoproject.model.Model;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@WireMockTest(httpPort = 8081)
@AutoConfigureMockMvc
public class ControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void success_response() throws Exception {
        Model model = Model.builder()
                .status(200)
                .message("Success")
                .build();

//        JsonNode jsonNode = mapper.valueToTree(model);
        String jsonNode = mapper.writeValueAsString(model);

        stubFor(WireMock.get(urlEqualTo("/"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonNode)));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", equalTo("Success")));
    }

}
