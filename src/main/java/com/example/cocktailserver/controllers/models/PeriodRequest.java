package com.example.cocktailserver.controllers.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodRequest {
    private String name;
    private int start_;
    private int end_;
}
