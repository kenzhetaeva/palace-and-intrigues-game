package com.aidana.game.entities.NPCs;

import com.aidana.game.entities.Hero;
import com.aidana.game.entities.Item;
import com.aidana.game.enums.ItemType;
import com.aidana.game.interfaces.Bribable;

import java.util.Random;
import java.util.Scanner;

public class AdvisorNPC extends NPC implements Bribable {

    public AdvisorNPC(String name, int initialRelationship) {
        super(name, "Первый Министр", initialRelationship);
    }

    @Override
    public void interact(Hero hero, Scanner scanner, Random rand) {
        Item secretPapers = new Item("Секретные бумаги", ItemType.COMPROMAT, 0);

        if (rand.nextBoolean()) {
            System.out.println(title + " " + name + " распускает о вас слухи!");
            System.out.println("1. Подкупить его");
            System.out.println("2. Игнорировать (Потерять 15 влияния)");

            if (hero.hasItemByName(secretPapers.getName())) {
                System.out.println("3. [Использовать компромат] Заставить молчать");
            }
            System.out.println("> ");

            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("Сколько хотите заплатить как взятку");
                System.out.println("> ");
                int amount = scanner.nextInt();
                if (takeBribe(hero, amount)) {
                    System.out.println("Слухи замяты.");
                } else {
                    System.out.println("Слухи распространились.");
                    hero.changeInfluence(-15);
                }
            } else if (choice == 2) {
                hero.changeInfluence(-15);
                changeRelationship(-10);
                System.out.println("Вы проигнорировали выпад. Ваше влияние упало.");
            } else if (choice == 3 && hero.hasItemByName(secretPapers.getName())) {
                System.out.println("\nВы показали документ с его тайной. Он бледнеет и умолкает!\n");
                hero.removeItemByName(secretPapers.getName());
                changeRelationship(-10);
            }
        } else {
            System.out.println("Вы случайно узнали секрет " + title + " " + name + "!");
            System.out.println("1. Шантажировать его (+20 золота, испортить отношения)");
            System.out.println("2. Сохранить тайну (+25 к отношениям)");
            System.out.println("> ");

            int choice = scanner.nextInt();
            if (choice == 1) {
                hero.changeGold(20);
                changeRelationship(-30);
                System.out.println(name + " заплатил вам, но теперь он вас ненавидит.");
            } else {
                changeRelationship(25);
                System.out.println(name + " благодарен за ваше молчание.");
            }
        }
    }

    @Override
    public boolean takeBribe(Hero hero, int amount) {
        if (hero.getGold() < amount) {
            System.out.println("❌ У вас недостаточно золота для взятки!");
            return false;
        }

        hero.changeGold(-amount);

        int relationshipBonus = amount / 5;
        changeRelationship(relationshipBonus);

        System.out.println("💰 " + title + " " + name + " незаметно спрятал " + amount + " монет в рукав.");
        System.out.println("Отношения улучшились на +" + relationshipBonus + "!");
        return true;
    }
}
