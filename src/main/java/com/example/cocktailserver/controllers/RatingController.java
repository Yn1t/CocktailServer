package com.example.cocktailserver.controllers;

import com.example.cocktailserver.controllers.exceptions.CocktailNotExistsException;
import com.example.cocktailserver.controllers.exceptions.RatingBadValueException;
import com.example.cocktailserver.controllers.exceptions.UserNotExistsException;
import com.example.cocktailserver.controllers.models.RatingRequest;
import com.example.cocktailserver.services.RatingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/rate")
    public String rate(@RequestBody RatingRequest request) throws RatingBadValueException, UserNotExistsException,
            CocktailNotExistsException {
        return ratingService.rateByNames(request);
    }
}
