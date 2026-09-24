package com.example.game;

import com.example.game.services.GameService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner run(GameService gameService) {
        return args -> {
            System.out.println("🚀 Игра запускается...");

//            Hero savedHero = gameService.createNewGame("Ли Минхо");
//            int heroId = savedHero.getId();
//
//            gameService.printHeroInfo(heroId);
        };
    }
}