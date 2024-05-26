package com.example.cocktailserver.services.impl;

import com.example.cocktailserver.controllers.exceptions.BaseAlreadyExistsException;
import com.example.cocktailserver.controllers.models.BaseRequest;
import com.example.cocktailserver.database.entities.Base;
import com.example.cocktailserver.database.repositories.BaseRepository;
import com.example.cocktailserver.database.repositories.CocktailRepository;
import com.example.cocktailserver.services.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BaseServiceImpl implements BaseService {

    private final BaseRepository baseRepository;
    private final CocktailRepository cocktailRepository;
    private final ModelMapper mapper;

    public BaseServiceImpl(BaseRepository baseRepository, CocktailRepository cocktailRepository, ModelMapper mapper) {
        this.baseRepository = baseRepository;
        this.cocktailRepository = cocktailRepository;
        this.mapper = mapper;
    }

    @Override
    public String addBase(BaseRequest request) throws BaseAlreadyExistsException {
        Optional<Base> existedBase = baseRepository.findOptionalByName(request.getName());

        if (existedBase.isPresent()) {
            throw new BaseAlreadyExistsException();
        }

        Base newBase = mapper.map(request, Base.class);
        baseRepository.save(newBase);

        return newBase.getId();
    }

    @Override
    public List<String> addBases(List<String> requestList) throws BaseAlreadyExistsException {
        List<Base> newBases = new ArrayList<>();

        for (String request : requestList) {
            Optional<Base> existedBase = baseRepository.findOptionalByName(request);

            if (existedBase.isPresent())
                throw new BaseAlreadyExistsException();
            Base newBase = new Base();
            newBase.setName(request);
            newBases.add(newBase);
        }

        baseRepository.saveAll(newBases);
        List<String> ids = new ArrayList<>();
        newBases.forEach(base -> ids.add(base.getId()));
        return ids;
    }
}
