package com.parking.garage.excepiton;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class GarageException extends RuntimeException{

    public GarageException(String message) {
        super(message);
    }
}
