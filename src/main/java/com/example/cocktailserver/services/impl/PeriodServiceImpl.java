package com.example.cocktailserver.services.impl;

import com.example.cocktailserver.controllers.exceptions.PeriodAlreadyExistsException;
import com.example.cocktailserver.controllers.models.PeriodRequest;
import com.example.cocktailserver.database.entities.Period;
import com.example.cocktailserver.database.repositories.CocktailUserRepository;
import com.example.cocktailserver.database.repositories.PeriodRepository;
import com.example.cocktailserver.services.PeriodService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PeriodServiceImpl implements PeriodService {

    private final PeriodRepository periodRepository;
    private final CocktailUserRepository userRepository;
    private final ModelMapper mapper;

    public PeriodServiceImpl(PeriodRepository periodRepository, CocktailUserRepository cocktailUserRepository,
                             ModelMapper mapper) {
        this.periodRepository = periodRepository;
        this.userRepository = cocktailUserRepository;
        this.mapper = mapper;
    }


    @Override
    public String addPeriod(PeriodRequest periodRequest) throws PeriodAlreadyExistsException {
        Optional<Period> existedPeriod = periodRepository.findOptionalByName(periodRequest.getName());

        if (existedPeriod.isPresent())
            throw new PeriodAlreadyExistsException();

        Period newPeriod = mapper.map(periodRequest, Period.class);
        periodRepository.save(newPeriod);
        return newPeriod.getId();
    }

    @Override
    public Map<String, List<String>> addPeriods(List<PeriodRequest> periods) throws PeriodAlreadyExistsException {
        List<Period> newPeriods = new ArrayList<>();
        Map<String, List<String>> periodsId = new HashMap<>();
        periodsId.put("periods", new ArrayList<>());

        for(PeriodRequest request : periods) {
            Optional<Period> existedPeriod = periodRepository.findOptionalByName(request.getName());

            if (existedPeriod.isPresent())
                throw new PeriodAlreadyExistsException();

            Period newPeriod = mapper.map(request, Period.class);
            newPeriods.add(newPeriod);
        }

        periodRepository.saveAll(newPeriods);
        newPeriods.forEach(period -> periodsId.get("periods").add(period.getId()));

        return periodsId;
    }
}
