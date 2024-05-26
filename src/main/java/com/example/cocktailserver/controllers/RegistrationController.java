package com.example.cocktailserver.controllers;

import com.example.cocktailserver.controllers.exceptions.UserAlreadyExistException;
import com.example.cocktailserver.controllers.exceptions.UserNotExistsException;
import com.example.cocktailserver.controllers.models.RegistrationParamsRequest;
import com.example.cocktailserver.controllers.models.UserDto;
import com.example.cocktailserver.controllers.models.UserRequest;
import com.example.cocktailserver.services.RegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody RegistrationParamsRequest params) throws UserAlreadyExistException {
        return registrationService.signup(params);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody UserRequest request) throws UserNotExistsException {
        return registrationService.login(request);
    }
}
