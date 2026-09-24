package com.aidana.game.entities;

import com.aidana.game.enums.ItemType;
import com.aidana.game.interfaces.Tradeable;

public class SecretCache implements Tradeable {
    private boolean isLocked = true;

    @Override
    public void openShop(Hero hero) {
        if (isLocked && hero.getGold() >= 10) {
            hero.changeGold(-10);
            hero.addItem(new Item("Старинная монета", ItemType.TREASURE, 50));
            isLocked = false;
            System.out.println("🔑 Вы заплатили 10 монет взломщику и открыли тайник!");
        } else if (!isLocked) {
            System.out.println("Тайник уже пуст.");
        } else {
            System.out.println("Нужно 10 монет, чтобы взломать замок.");
        }
    }

    @Override
    public boolean hasItemsForSale() {
        return isLocked;
    }
}
