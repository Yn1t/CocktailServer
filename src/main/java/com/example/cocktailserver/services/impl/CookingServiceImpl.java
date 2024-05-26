package com.example.cocktailserver.services.impl;

import com.example.cocktailserver.controllers.exceptions.CookingAlreadyExistsException;
import com.example.cocktailserver.controllers.models.CookingRequest;
import com.example.cocktailserver.database.entities.Cooking;
import com.example.cocktailserver.database.entities.Period;
import com.example.cocktailserver.database.repositories.CocktailUserRepository;
import com.example.cocktailserver.database.repositories.CookingRepository;
import com.example.cocktailserver.services.CookingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CookingServiceImpl implements CookingService {

    private final CookingRepository cookingRepository;
    private final CocktailUserRepository userRepository;
    private final ModelMapper mapper;

    public CookingServiceImpl(CookingRepository cookingRepository, CocktailUserRepository userRepository, ModelMapper mapper) {
        this.cookingRepository = cookingRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public String addCooking(CookingRequest request) throws CookingAlreadyExistsException {
        Optional<Cooking> existedCooking = cookingRepository.findOptionalByName(request.getName());

        if (existedCooking.isPresent())
            throw new CookingAlreadyExistsException();

        Cooking new_cooking = mapper.map(request, Cooking.class);

        cookingRepository.save(new_cooking);
        return new_cooking.getId();
    }

    @Override
    public Map<String, List<String>> addCookings(List<CookingRequest> requests) throws CookingAlreadyExistsException {
        List<Cooking> newCookings = new ArrayList<>();
        Map<String, List<String>> cookingsId = new HashMap<>();
        cookingsId.put("cookings", new ArrayList<>());

        for(CookingRequest request : requests) {
            Optional<Cooking> existedPeriod = cookingRepository.findOptionalByName(request.getName());

            if (existedPeriod.isPresent())
                throw new CookingAlreadyExistsException();

            Cooking newCooking = mapper.map(request, Cooking.class);
            newCookings.add(newCooking);
        }

        cookingRepository.saveAll(newCookings);
        newCookings.forEach(cooking -> cookingsId.get("cookings").add(cooking.getId()));
        return cookingsId;
    }
}
