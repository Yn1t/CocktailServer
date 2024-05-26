package com.example.cocktailserver.services.impl;

import com.example.cocktailserver.controllers.exceptions.UserAlreadyExistException;
import com.example.cocktailserver.controllers.exceptions.UserNotExistsException;
import com.example.cocktailserver.controllers.models.RegistrationParamsRequest;
import com.example.cocktailserver.controllers.models.UserDto;
import com.example.cocktailserver.controllers.models.UserRequest;
import com.example.cocktailserver.database.entities.CocktailUser;
import com.example.cocktailserver.database.repositories.CocktailUserRepository;
import com.example.cocktailserver.services.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final CocktailUserRepository cocktailUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public RegistrationServiceImpl(ModelMapper mapper, CocktailUserRepository cocktailUserRepository, PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.cocktailUserRepository = cocktailUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto signup(RegistrationParamsRequest registrationParamsRequest) throws UserAlreadyExistException {
        Optional<CocktailUser> existedUser = cocktailUserRepository.findOptionalByEmail(registrationParamsRequest.getEmail());
        if (existedUser.isPresent()) {
            throw new UserAlreadyExistException();
        }

        CocktailUser cocktailUser = mapper.map(registrationParamsRequest, CocktailUser.class);
        String password = passwordEncoder.encode(cocktailUser.getPassword() + "sada");

        // todo default values
        cocktailUser.setPassword(password);
        cocktailUser.setName(registrationParamsRequest.getName());
        cocktailUserRepository.save(cocktailUser);

        return mapper.map(cocktailUser, UserDto.class);
    }

    @Override
    public UserDto login(UserRequest userRequest) throws UserNotExistsException {
        Optional<CocktailUser> existedUser = cocktailUserRepository.findOptionalByEmail(userRequest.getEmail());

        if (existedUser.isEmpty())
            throw new UserNotExistsException();

        CocktailUser cocktailUser = existedUser.get();

        if (passwordEncoder.matches(userRequest.getPassword() + "sada", cocktailUser.getPassword()))
            return mapper.map(cocktailUser, UserDto.class);
        else
            throw new UserNotExistsException();
    }
}
