package com.example.cocktailserver.services.impl;

import com.example.cocktailserver.controllers.exceptions.UserAlreadyExistException;
import com.example.cocktailserver.controllers.models.RegistrationParamsRequest;
import com.example.cocktailserver.controllers.models.UserDto;
import com.example.cocktailserver.services.RegistrationService;

public class RegistrationServiceImpl implements RegistrationService {



    @Override
    public UserDto signup(RegistrationParamsRequest registrationParamsRequest) throws UserAlreadyExistException {
        return null;
    }
}
