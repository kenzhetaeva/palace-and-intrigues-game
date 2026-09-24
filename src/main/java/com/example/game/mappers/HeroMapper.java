package com.example.game.mappers;

import com.example.game.controllers.responses.HeroResponse;
import com.example.game.controllers.responses.ItemResponse;
import com.example.game.entities.Hero;
import com.example.game.entities.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HeroMapper {

    public HeroResponse toHeroResponse(Hero hero) {
        List<ItemResponse> itemDtos = hero.getInventory() == null ? List.of() :
                hero.getInventory().stream()
                        .map(this::toItemResponse)
                        .toList();

        return new HeroResponse(
                hero.getId(),
                hero.getName(),
                hero.getGold(),
                itemDtos
        );
    }

    public ItemResponse toItemResponse(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getType().name(),
                item.getEffectValue()
        );
    }
}
