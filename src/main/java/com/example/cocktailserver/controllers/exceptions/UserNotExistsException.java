package com.example.cocktailserver.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User is not exist")
public class UserNotExistsException extends Exception{
}
