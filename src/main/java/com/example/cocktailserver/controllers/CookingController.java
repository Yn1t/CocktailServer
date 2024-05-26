package com.example.cocktailserver.controllers;

import com.example.cocktailserver.controllers.exceptions.CookingAlreadyExistsException;
import com.example.cocktailserver.controllers.models.CookingRequest;
import com.example.cocktailserver.services.CookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cooking")
public class CookingController {

    private final CookingService cookingService;

    public CookingController(CookingService cookingService) {
        this.cookingService = cookingService;
    }

    @PostMapping("/add")
    public String addCooking(@RequestBody CookingRequest cookingRequest) throws CookingAlreadyExistsException {
        return cookingService.addCooking(cookingRequest);
    }

    @PostMapping("/add_cookings")
    public @ResponseBody Map<String,List<String>> addCookings(@RequestBody Map<String,List<CookingRequest>> cookings)
            throws CookingAlreadyExistsException {
        return cookingService.addCookings(cookings.get("cookings"));
    }
}
