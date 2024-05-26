package com.example.cocktailserver.services.impl;

import com.example.cocktailserver.controllers.exceptions.StyleAlreadyExistException;
import com.example.cocktailserver.controllers.models.StyleRequest;
import com.example.cocktailserver.database.entities.Style;
import com.example.cocktailserver.database.repositories.CocktailUserRepository;
import com.example.cocktailserver.database.repositories.StyleRepository;
import com.example.cocktailserver.services.StyleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;
    private final CocktailUserRepository cocktailUserRepository;
    private final ModelMapper mapper;

    public StyleServiceImpl(StyleRepository styleRepository, CocktailUserRepository cocktailUserRepository,
                            ModelMapper mapper) {
        this.styleRepository = styleRepository;
        this.cocktailUserRepository = cocktailUserRepository;
        this.mapper = mapper;
    }


    @Override
    public String addStyle(StyleRequest styleRequest) throws StyleAlreadyExistException {
        Optional<Style> existedStyle = styleRepository.findOptionalByName(styleRequest.getName());

        if (existedStyle.isPresent())
            throw new StyleAlreadyExistException();

        Style newStyle = mapper.map(styleRequest, Style.class);
        styleRepository.save(newStyle);

        return newStyle.getId();
    }

    @Override
    public Map<String, List<String>> addStyles(List<StyleRequest> requests) throws StyleAlreadyExistException {
        List<Style> newStyles = new ArrayList<>();
        Map<String, List<String>> stylesId = new HashMap<>();
        stylesId.put("styles", new ArrayList<>());

        for(StyleRequest styleRequest : requests) {
            Optional<Style> existedStyle = styleRepository.findOptionalByName(styleRequest.getName());

            if (existedStyle.isPresent())
                throw new StyleAlreadyExistException();

            newStyles.add(mapper.map(styleRequest, Style.class));
        }

        styleRepository.saveAll(newStyles);
        newStyles.forEach(style -> stylesId.get("styles").add(style.getId()));
        return stylesId;
    }
}
