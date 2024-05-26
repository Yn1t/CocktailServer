package com.example.cocktailserver.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Base not exists")
public class BaseNotExistException extends Exception{
}
