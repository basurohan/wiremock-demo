package com.example.wiremockdemoproject.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Model {
    private int status;
    private String message;
}
