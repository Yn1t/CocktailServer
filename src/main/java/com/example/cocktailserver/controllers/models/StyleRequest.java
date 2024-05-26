package com.example.cocktailserver.controllers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StyleRequest {
    private String name;
    private String glass;
    private String icon_link;
}
