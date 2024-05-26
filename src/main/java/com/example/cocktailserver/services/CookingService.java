package com.example.cocktailserver.services;

import com.example.cocktailserver.controllers.exceptions.CookingAlreadyExistsException;
import com.example.cocktailserver.controllers.models.CookingRequest;

import java.util.List;
import java.util.Map;

public interface CookingService {
    String addCooking(CookingRequest request) throws CookingAlreadyExistsException;
    Map<String, List<String>> addCookings(List<CookingRequest>  requests) throws CookingAlreadyExistsException;
}
