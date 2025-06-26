package com.example.aruba.senddocumentsystem.receivermanager.rest.controller.advice;

import com.example.aruba.senddocumentsystem.receivermanager.domain.exception.InvalidUsernameException;
import com.example.aruba.senddocumentsystem.receivermanager.domain.exception.ReceiverAlreadyExistsException;
import com.example.aruba.senddocumentsystem.receivermanager.domain.exception.ReceiverNotFoundException;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, InvalidUsernameException.class})
    public Map<String, String> handleIllegalArgumentException(IllegalArgumentException e){
        Map<String, String> error = new HashMap<>();
        error.put("Missing or invalid Arguments", e.getMessage());
        log.error(error.toString());
        return error;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ReceiverAlreadyExistsException.class)
    public Map<String, String> handleReceiverAlreadyExistsException(ReceiverAlreadyExistsException e){
        Map<String, String> error = new HashMap<>();
        error.put("Can't create the receiver", "The receiver that you are tying to create is already existing");
        log.error(error.toString());
        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReceiverNotFoundException.class)
    public Map<String, String> handleReceiverNotFoundException(ReceiverNotFoundException e){
        Map<String, String> error = new HashMap<>();
        error.put("Can't update the receiver", "The receiver does not exist");
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
