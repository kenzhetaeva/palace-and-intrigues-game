package com.example.demo.services;

import com.example.demo.entities.Hero;
import com.example.demo.entities.Item;
import com.example.demo.enums.ItemType;
import com.example.demo.repositories.HeroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    private final HeroRepository heroRepository;

    public GameService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Transactional
    public Hero createNewGame(String heroName) {
        Hero hero = new Hero(heroName);
        hero.addItem(new Item("Нефритовый амулет", ItemType.ACCESSORY, 15));
        hero.addItem(new Item("Стальной кинжал", ItemType.WEAPON, 10));

        return heroRepository.save(hero);
    }

    @Transactional(readOnly = true)
    public void printHeroInfo(int heroId) {
        Hero hero = heroRepository.findById(heroId).orElse(null);

        if (hero == null) {
            System.out.println("❌ Герой не найден.");
            return;
        }

        System.out.println("\n=== ИНФОРМАЦИЯ О ГЕРОЕ ===");
        System.out.println("ID: " + hero.getId());
        System.out.println("Имя: " + hero.getName());
        System.out.println("Золото: " + hero.getGold());
        System.out.println("Предметы в инвентаре:");

        for (Item item : hero.getInventory()) {
            System.out.println(" - " + item.getName() + " (" + item.getType() + "), Эффект: +" + item.getEffectValue());
        }
        System.out.println("===========================\n");
    }
}