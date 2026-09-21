package com.example.demo.interfaces;

import com.example.demo.entities.Hero;

public interface Tradeable {
    void openShop(Hero hero);
    boolean hasItemsForSale();
}
