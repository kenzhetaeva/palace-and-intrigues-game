package com.example.game.interfaces;

import com.example.game.entities.Hero;

public interface Tradeable {
    void openShop(Hero hero);
    boolean hasItemsForSale();
}
