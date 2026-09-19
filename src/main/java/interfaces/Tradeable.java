package interfaces;

import entities.Hero;

public interface Tradeable {
    void openShop(Hero hero);
    boolean hasItemsForSale();
}
