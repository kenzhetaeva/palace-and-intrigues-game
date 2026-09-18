import java.util.ArrayList;
import java.util.List;

public class Hero {
    private int id;
    private String name;
    private int influence; // Влияние во дворце (0-100)
    private int gold;      // Золото
    private int health;    // Здоровье/Энергия (0-100)
    private List<Item> inventory; // Список предметов в инвентаре

    public Hero(String name) {
        this.name = name;

        // Initial values
        this.influence = 50;
        this.gold = 100;
        this.health = 100;
        this.inventory = new ArrayList<>();

        inventory.add(new Item("Целительный отвар", "Восстанавливает 30 ед. энергии", ItemType.HEALTH, 30));
    }

    public void changeInfluence(int amount) {
        this.influence += amount;
        if (influence < 0) {
            influence = 0;
        }
        if (influence > 100) {
            influence = 100;
        }
    }

    public void changeGold(int amount) {
        this.gold += amount;
        if (this.gold < 0) {
            this.gold = 0;
        }
    }

    public void changeHealth(int amount) {
        this.health += amount;
        if (this.health < 0) {
            this.health = 0;
        }
        if (this.health > 100) {
            this.health = 100;
        }
    }

    public boolean hasItemByName(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void removeItemByName(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                inventory.remove(item);
                System.out.println("❌ Предмет использован: [" + item.getName() + "]");
                return;
            }
        }
    }

    public void addItem(Item item) {
        this.inventory.add(item);
        System.out.println("✨ Вы получили предмет: [" + item.getName() + "]");
    }

    public void useItem(int index) {
        if (index < 0 || index >= inventory.size()) {
            System.out.println("⚠️ Предмета с таким номером нет!");
            return;
        }
        Item item = inventory.get(index);

        if (item.getType() == ItemType.HEALTH) {
            changeHealth(item.getValue());
            System.out.println("\n\uD83E\uDDEA Вы использовали ["
                    + item.getName() + "] и восстановили " + item.getValue() + "энергии!");
            inventory.remove(index);
        } else if (item.getType() == ItemType.COMPROMAT) {
            System.out.println("\n\uD83D\uDCDC Этот предмет нельзя использовать просто так - приберегите его для дворцовых интриг!");
        } else {
            System.out.println("\nЭтот предмет нельзя применить прямо сейчас.");
        }
    }

    public void printStatus() {
        System.out.println("\n===============СТАТУС ПЕРСОНАЖА===============");
        System.out.println("Имя: " + name);
        System.out.println("Влияние: " + influence + "/100");
        System.out.println("Казна: " + gold + " монет");
        System.out.println("Энергия: " + health + "/100");
        System.out.println("===============================================\n");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getInfluence() {
        return influence;
    }

    public int getGold() {
        return gold;
    }

    public int getHealth() {
        return health;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfluence(int influence) {
        this.influence = influence;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
