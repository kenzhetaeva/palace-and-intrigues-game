package com.aidana.game.controllers;

import com.aidana.game.controllers.requests.CreateHeroRequest;
import com.aidana.game.controllers.responses.ErrorResponse;
import com.aidana.game.controllers.responses.HeroResponse;
import com.aidana.game.entities.Hero;
import com.aidana.game.mappers.HeroMapper;
import com.aidana.game.services.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/heroes")
@Tag(name = "Герои", description = "Управление персонажами и их характеристиками")
public class HeroRestController {

    private final GameService gameService;
    private final HeroMapper heroMapper;

    public HeroRestController(GameService gameService, HeroMapper heroMapper) {
        this.gameService = gameService;
        this.heroMapper = heroMapper;
    }

    @PostMapping
    @Operation(summary = "Создать нового героя", description = "Создает персонажа с базовым инвентарем и набором стартового золота.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Герой успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<HeroResponse> createHero(@Valid @RequestBody CreateHeroRequest request) {
        Hero createdHero = gameService.createNewGame(request.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(heroMapper.toHeroResponse(createdHero));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить героя по ID", description = "Возвращает полную информацию о герое и его инвентаре.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Герой найден"),
            @ApiResponse(responseCode = "404", description = "Герой с указанным ID не найден",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<HeroResponse> getHeroById(@PathVariable Integer id) {
        Hero hero = gameService.findHeroById(id);
        return ResponseEntity.ok(heroMapper.toHeroResponse(hero));
    }
}
