package com.example.demo.services;

import com.example.demo.entities.Hero;
import com.example.demo.repositories.HeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private HeroRepository heroRepository;

    @InjectMocks
    private GameService gameService;

    private Hero testHero;

    @BeforeEach
    void setUp() {
        testHero = new Hero("Хван Хенджин");
        testHero.setGold(100);
    }

    @Test
    @DisplayName("Проверка сохранения нового героя с предметами")
    void createNewGame_ShouldSaveHeroWithItems() {
        when(heroRepository.save(any(Hero.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Hero createdHero = gameService.createNewGame("Хван Хенджин");

        assertThat(createdHero).isNotNull();
        assertThat(createdHero.getName()).isEqualTo("Хван Хенджин");
        assertThat(createdHero.getInventory()).hasSize(2);

        ArgumentCaptor<Hero> heroCaptor = ArgumentCaptor.forClass(Hero.class);
        verify(heroRepository).save(heroCaptor.capture());

        Hero capturedHero = heroCaptor.getValue();
        assertThat(capturedHero.getName()).isEqualTo("Хван Хенджин");
    }
}