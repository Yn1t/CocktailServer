package com.example.cocktailserver.services;

import com.example.cocktailserver.controllers.exceptions.StyleAlreadyExistException;
import com.example.cocktailserver.controllers.models.StyleRequest;

import java.util.List;
import java.util.Map;

public interface StyleService {
    public String addStyle(StyleRequest styleRequest) throws StyleAlreadyExistException;
    public Map<String, List<String>> addStyles(List<StyleRequest> requests) throws StyleAlreadyExistException;
}
