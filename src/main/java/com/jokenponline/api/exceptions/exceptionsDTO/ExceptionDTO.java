package com.jokenponline.api.exceptions.exceptionsDTO;

import java.time.LocalDateTime;


public record ExceptionDTO(
    LocalDateTime timestamp,
    int status,
    String error,
    String message
) {}
