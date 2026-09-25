package com.aidana.game.controllers.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Информация о герое")
public record HeroResponse(
        @Schema(description = "Уникальный идентификатор", example = "1")
        Integer id,

        @Schema(description = "Имя персонажа", example = "Хван Хенджин")
        String name,

        @Schema(description = "Количество золота в кошельке", example = "150")
        int gold,

        @Schema(description = "Инвентарь персонажа")
        List<ItemResponse> items
) {}
