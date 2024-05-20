package com.example.cocktailserver.controllers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationParamsRequest {
    private String name;
    private String email;
    private String password;
}
