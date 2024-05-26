package com.example.cocktailserver.services;

import com.example.cocktailserver.controllers.exceptions.BaseAlreadyExistsException;
import com.example.cocktailserver.controllers.models.BaseDto;
import com.example.cocktailserver.controllers.models.BaseRequest;

import java.util.List;

public interface BaseService {
    String addBase(BaseRequest request) throws BaseAlreadyExistsException;
    List<String> addBases(List<String> requestList) throws BaseAlreadyExistsException;
}
