package com.example.game.controllers.responses;

public record ErrorResponse(
        int status,
        String message,
        Object details,
        long timestamp
) {}
