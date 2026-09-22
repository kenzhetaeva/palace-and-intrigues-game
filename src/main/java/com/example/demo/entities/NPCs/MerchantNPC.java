package com.example.demo.entities.NPCs;

import com.example.demo.entities.Hero;
import com.example.demo.entities.Item;
import com.example.demo.enums.ItemType;
import com.example.demo.interfaces.Tradeable;

import java.util.Random;
import java.util.Scanner;

public class MerchantNPC extends NPC implements Tradeable {
    private int itemPrice;
    private boolean hasItem;

    public MerchantNPC(String name, int initialRelationship, int itemPrice) {
        super(name, "Придворный Торговец", initialRelationship);
        this.itemPrice = itemPrice;
        this.hasItem = true;
    }

    @Override
    public void interact(Hero hero, Scanner scanner, Random rand) {
        System.out.println("\n⚖️ Торговец " + name + " приветствует вас.");
        openShop(hero);
    }

    @Override
    public void openShop(Hero hero) {
        if (!hasItem) {
            System.out.println("У торговца закончились товары.");
            return;
        }

        if (hero.getGold() >= itemPrice) {
            hero.changeGold(-itemPrice);
            hero.addItem(new Item("Эликсир сил", ItemType.HEALTH, 30));
            hasItem = false;
            System.out.println("Вы успешно купили эликсир!");
        } else {
            System.out.println("Недостаточно золота для покупки.");
        }
    }

    @Override
    public boolean hasItemsForSale() {
        return hasItem;
    }
}
