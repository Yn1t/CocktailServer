package com.example.cocktailserver.services;

import com.example.cocktailserver.controllers.exceptions.UserAlreadyExistException;
import com.example.cocktailserver.controllers.models.RegistrationParamsRequest;
import com.example.cocktailserver.controllers.models.UserDto;

public interface RegistrationService {
    UserDto signup(RegistrationParamsRequest registrationParamsRequest) throws UserAlreadyExistException;
}
