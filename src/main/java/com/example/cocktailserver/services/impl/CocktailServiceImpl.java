package com.example.cocktailserver.services.impl;


import com.example.cocktailserver.controllers.exceptions.*;
import com.example.cocktailserver.controllers.models.CocktailRequest;
import com.example.cocktailserver.database.entities.*;
import com.example.cocktailserver.database.repositories.*;
import com.example.cocktailserver.services.CocktailService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CocktailServiceImpl implements CocktailService {
    private final CocktailRepository cocktailRepository;
    private final PeriodRepository periodRepository;
    private final StyleRepository styleRepository;
    private final CookingRepository cookingRepository;
    private final BaseRepository baseRepository;
    private final ModelMapper mapper;

    public CocktailServiceImpl(CocktailRepository cocktailRepository, PeriodRepository periodRepository,
                               StyleRepository styleRepository, CookingRepository cookingRepository,
                               BaseRepository baseRepository, ModelMapper mapper)
    {
        this.baseRepository = baseRepository;
        this.cocktailRepository = cocktailRepository;
        this.styleRepository = styleRepository;
        this.cookingRepository = cookingRepository;
        this.periodRepository = periodRepository;
        this.mapper = mapper;
    }

    @Override
    public String createCocktail(CocktailRequest request) throws BaseNotExistException, StyleNotExistException,
            CookingNotExistException, PeriodNotExistException, CocktailAlreadyExistException {
        Optional<Period> existedPeriod = periodRepository.findOptionalByName(request.getPeriod());
        Optional<Style> existedStyle = styleRepository.findOptionalByName(request.getStyle());
        Optional<Cooking> existedCooking = cookingRepository.findOptionalByName(request.getCooking());
        Optional<Base> existedBase = baseRepository.findOptionalByName(request.getBase());

        existedBase.orElseThrow(BaseNotExistException::new);
        existedPeriod.orElseThrow(PeriodNotExistException::new);
        existedCooking.orElseThrow(CookingNotExistException::new);
        existedStyle.orElseThrow(StyleNotExistException::new);

        //Optional<Cocktail> existedName = cocktailRepository.findOptionalByNameAndSubname(request.getName(), request.getSubname());
        //Optional<Cocktail> existedSub_name = cocktailRepository.findOptionalBySub_name(request.getSub_name());

        //if (existedName.isPresent())
            //throw new CocktailAlreadyExistException();

        Cocktail cocktail = mapper.map(request, Cocktail.class);
        cocktail.setBase(existedBase.get());
        cocktail.setCooking(existedCooking.get());
        cocktail.setPeriod(existedPeriod.get());
        cocktail.setStyle(existedStyle.get());

        cocktailRepository.save(cocktail);
        return cocktail.getId();
    }
}
