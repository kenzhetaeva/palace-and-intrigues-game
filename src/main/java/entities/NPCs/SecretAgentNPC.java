package entities.NPCs;

import entities.Hero;
import entities.Item;
import enums.ItemType;

import java.util.Random;
import java.util.Scanner;

public class SecretAgentNPC extends NPC {

    public SecretAgentNPC(String name, int initialRelationship) {
        super(name, "Тайный агент", initialRelationship);
    }

    @Override
    public void interact(Hero hero, Scanner scanner, Random rand) {
        Item secretPapers = new Item(
                "Секретные бумаги",
                "Документ, порочащий честное имя",
                ItemType.COMPROMAT,
                0
        );

        System.out.println("\n\uD83E\uDD2B️ " + title + " " + name + " кланяется вам в знак приветствия.");
        if (relationship >= 30) {
            System.out.println(title + " " + name +  " донес до вашего сведения все данные по делу, " +
                    "на которое вы его наняли.");
            hero.addItem(secretPapers);

            return;
        }

        if (relationship >= 0) {
            System.out.println(title + " " + name + " скрыл от вас некоторые сведения по делу, " +
                    "на которое вы его наняли");
            return;
        }

        System.out.println(title + " " + name + " вместо выполнения вашего приказа, объединился с вашими врагами!" +
                " (-10 к влиянию)");
        hero.changeInfluence(-10);

    }
}
