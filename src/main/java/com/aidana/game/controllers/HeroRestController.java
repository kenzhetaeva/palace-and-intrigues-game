package com.aidana.game.controllers;

import com.aidana.game.controllers.requests.CreateHeroRequest;
import com.aidana.game.controllers.responses.HeroResponse;
import com.aidana.game.entities.Hero;
import com.aidana.game.mappers.HeroMapper;
import com.aidana.game.services.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/heroes")
public class HeroRestController {

    private final GameService gameService;
    private final HeroMapper heroMapper;

    public HeroRestController(GameService gameService, HeroMapper heroMapper) {
        this.gameService = gameService;
        this.heroMapper = heroMapper;
    }

    @PostMapping
    public ResponseEntity<HeroResponse> createHero(@Valid @RequestBody CreateHeroRequest request) {
        Hero createdHero = gameService.createNewGame(request.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(heroMapper.toHeroResponse(createdHero));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroResponse> getHeroById(@PathVariable Integer id) {
        Hero hero = gameService.findHeroById(id);
        return ResponseEntity.ok(heroMapper.toHeroResponse(hero));
    }
}
