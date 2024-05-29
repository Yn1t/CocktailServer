package com.example.cocktailserver.services;

import com.example.cocktailserver.controllers.exceptions.CocktailNotExistsException;
import com.example.cocktailserver.controllers.exceptions.RatingBadValueException;
import com.example.cocktailserver.controllers.exceptions.UserNotExistsException;
import com.example.cocktailserver.controllers.models.RatingRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface RatingService {
    public String rateByNames(@RequestBody RatingRequest request) throws CocktailNotExistsException, UserNotExistsException, RatingBadValueException;
}
