package com.aidana.game.interfaces;

import com.aidana.game.entities.Hero;

public interface Tradeable {
    void openShop(Hero hero);
    boolean hasItemsForSale();
}
