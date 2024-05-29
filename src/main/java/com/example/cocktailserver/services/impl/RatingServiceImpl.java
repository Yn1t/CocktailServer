package com.example.cocktailserver.services.impl;

import com.example.cocktailserver.controllers.exceptions.CocktailNotExistsException;
import com.example.cocktailserver.controllers.exceptions.RatingBadValueException;
import com.example.cocktailserver.controllers.exceptions.UserNotExistsException;
import com.example.cocktailserver.controllers.models.RatingRequest;
import com.example.cocktailserver.database.entities.Cocktail;
import com.example.cocktailserver.database.entities.CocktailUser;
import com.example.cocktailserver.database.entities.Rating;
import com.example.cocktailserver.database.repositories.CocktailRepository;
import com.example.cocktailserver.database.repositories.CocktailUserRepository;
import com.example.cocktailserver.database.repositories.RatingRepository;
import com.example.cocktailserver.services.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    private final CocktailUserRepository userRepository;
    private final CocktailRepository cocktailRepository;
    private final RatingRepository ratingRepository;
    private final ModelMapper mapper;

    public RatingServiceImpl(CocktailUserRepository cocktailUserRepository, CocktailRepository cocktailRepository,
                             RatingRepository ratingRepository, ModelMapper mapper) {
        this.userRepository = cocktailUserRepository;
        this.cocktailRepository = cocktailRepository;
        this.ratingRepository = ratingRepository;
        this.mapper = mapper;
    }
    @Override
    public String rateByNames(RatingRequest request) throws CocktailNotExistsException, UserNotExistsException,
            RatingBadValueException {
        Optional<Cocktail> existedCocktail = cocktailRepository.findOptionalByNameAndSubname(
                request.getCocktail_name(), request.getCocktail_subname() );
        Optional<CocktailUser> existedUser = userRepository.findOptionalByName(request.getUser_name());

        Cocktail cocktail = existedCocktail.orElseThrow(CocktailNotExistsException::new);
        CocktailUser user = existedUser.orElseThrow(UserNotExistsException::new);


        if (request.getRating() > 5 || request.getRating() < 0)
            throw new RatingBadValueException();

        Optional<Rating> existedRating = ratingRepository.findOptionalByCocktailAndCocktailUser(cocktail, user);

        Rating rating;

        if (existedRating.isPresent())
        {
            rating = existedRating.get();
            cocktail.setRating(
                    cocktail.getRating() + (request.getRating() - rating.getRate()) / (double)cocktail.getRating_amount()
                    );
            rating.setRate(request.getRating());
        }
        else {
            rating = new Rating();
            rating.setRate(request.getRating());
            rating.setCocktail(cocktail);
            rating.setCocktailUser(user);
            cocktail.setRating((cocktail.getRating_amount() * cocktail.getRating() + (double) request.getRating()) /
                    (cocktail.getRating_amount() + 1));
            cocktail.setRating_amount(cocktail.getRating_amount() + 1);
        }

        ratingRepository.save(rating);
        return rating.getId();
    }
}
