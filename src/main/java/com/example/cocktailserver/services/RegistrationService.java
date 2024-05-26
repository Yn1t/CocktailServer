package com.example.cocktailserver.services;

import com.example.cocktailserver.controllers.exceptions.UserAlreadyExistException;
import com.example.cocktailserver.controllers.exceptions.UserNotExistsException;
import com.example.cocktailserver.controllers.models.RegistrationParamsRequest;
import com.example.cocktailserver.controllers.models.UserDto;
import com.example.cocktailserver.controllers.models.UserRequest;

public interface RegistrationService {
    UserDto signup(RegistrationParamsRequest registrationParamsRequest) throws UserAlreadyExistException;
    UserDto login(UserRequest userRequest) throws UserNotExistsException;
}
