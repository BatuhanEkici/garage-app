package com.parking.garage.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    @ExceptionHandler(GarageException.class)
    public String garageExceptionHandler(GarageException exception) {
        logger.error("GarageException handling..");
        return exception.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public String genericExceptionHandler() {
        logger.error("Exception handling..");
        return "İşleminiz sırasında hata gerçekleşti lütfen daha sonra tekrar deneyiniz.";
    }
}
