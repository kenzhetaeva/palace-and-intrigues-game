package com.aidana.game.controllers.responses;

import java.util.List;

public record HeroResponse(
        Integer id,
        String name,
        int gold,
        List<ItemResponse> items
) {}
