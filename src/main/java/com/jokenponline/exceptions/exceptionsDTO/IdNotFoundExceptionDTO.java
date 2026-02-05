package com.jokenponline.exceptions.exceptionsDTO;

import java.time.LocalDateTime;


public record IdNotFoundExceptionDTO(
    LocalDateTime timestamp,
    int status,
    String error,
    String message
) {}
