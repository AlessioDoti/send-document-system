package com.example.aruba.senddocumentsystem.deliverytracker.rest.controller.advice;


import com.example.aruba.senddocumentsystem.deliverytracker.domain.exception.DeliveryNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DeliveryNotFoundException.class)
    public Map<String, String> handleDeliveryNotFoundException(DeliveryNotFoundException e){
        Map<String, String> error = new HashMap<>();
        error.put("Resource not found", e.getMessage());
        log.error(error.toString());
        return error;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleReceiverNotFoundException(Exception e){
        Map<String, String> error = new HashMap<>();
        error.put("An error occured", e.getMessage());
        log.error(error.toString());
        return error;
    }
}
