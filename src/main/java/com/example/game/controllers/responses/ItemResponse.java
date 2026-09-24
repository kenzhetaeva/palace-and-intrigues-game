package com.example.game.controllers.responses;

public record ItemResponse(
        Integer id,
        String name,
        String type,
        int effectValue
) {}
