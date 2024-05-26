package com.example.cocktailserver.services;

import com.example.cocktailserver.controllers.exceptions.PeriodAlreadyExistsException;
import com.example.cocktailserver.controllers.models.PeriodRequest;

import java.util.List;
import java.util.Map;

public interface PeriodService {
    String addPeriod(PeriodRequest periodRequest) throws PeriodAlreadyExistsException;
    Map<String, List<String>> addPeriods(List<PeriodRequest> periods) throws PeriodAlreadyExistsException;
}
