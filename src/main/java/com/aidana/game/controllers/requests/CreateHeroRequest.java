package com.aidana.game.controllers.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Запрос на создание нового героя")
public record CreateHeroRequest(
        @Schema(description = "Имя персонажа", example = "Хван Хенджин", minLength = 2, maxLength = 50)
        @NotBlank(message = "Имя героя не может быть пустым")
        @Size(min = 2, max = 50, message = "Имя должно содержать от 2 до 50 символов")
        String name
) {}