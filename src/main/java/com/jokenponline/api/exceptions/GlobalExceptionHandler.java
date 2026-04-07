package com.jokenponline.api.exceptions;

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
}
