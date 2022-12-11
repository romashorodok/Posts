package com.example.server.controllers;

import java.util.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler{
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, List<HashMap<String, String>>> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            HashMap<String, String> message = new HashMap<>() {{
                put("message", error.getDefaultMessage());
            }};
            errors.computeIfPresent(fieldName,(key, val) -> {
                val.add(message);
                return val;
            });
            errors.putIfAbsent(fieldName, new ArrayList<>(List.of(message)));
        });
        return new ResponseEntity<>(new HashMap<>() {{
            put("error", errors);
        }}, HttpStatus.BAD_REQUEST);
    }
}
