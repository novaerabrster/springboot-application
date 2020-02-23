package com.freenow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "This car is already in use.")
public class CarAlreadyInUseException extends Exception
{

    private static final long serialVersionUID = -2395704695098044185L;

    public CarAlreadyInUseException(String message)
    {
        super(message);
    }

}
