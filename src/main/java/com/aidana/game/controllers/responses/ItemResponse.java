package com.aidana.game.controllers.responses;

public record ItemResponse(
        Integer id,
        String name,
        String type,
        int effectValue
) {}
