package com.jokenponline.api.exceptions;

import com.jokenponline.domain.exceptions.AlreadySavedException;
import com.jokenponline.domain.exceptions.InvalidChoiceException;
import com.jokenponline.domain.exceptions.NotFoundException;
import com.jokenponline.api.exceptions.exceptionsDTO.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleIdNotFound (NotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDTO(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        "Not encountered resource",
                        e.getMessage()
                ));
    }

    @ExceptionHandler(InvalidChoiceException.class)
    public ResponseEntity<ExceptionDTO> handleInvalidChoice (InvalidChoiceException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ExceptionDTO(
                        LocalDateTime.now(),
                        HttpStatus.NOT_ACCEPTABLE.value(),
                        "This choice is not valid",
                        e.getMessage()
                ));
    }

    @ExceptionHandler(AlreadySavedException.class)
    public ResponseEntity<ExceptionDTO> handleAlreadySaved (AlreadySavedException e) {
        return ResponseEntity
                .status(HttpStatus.ALREADY_REPORTED)
                .body(new ExceptionDTO(
                        LocalDateTime.now(),
                        HttpStatus.ALREADY_REPORTED.value(),
                        "The object that you are trying to save is already saved",
                        e.getMessage()
                ));
    }
}
