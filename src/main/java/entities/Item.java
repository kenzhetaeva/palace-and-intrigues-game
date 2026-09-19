package entities;

import enums.ItemType;

public class Item {
    private String name;
    private String description;
    private ItemType type;
    private int value; // Сила эффекта (например, сколько здоровья восстанавливает)

    public Item(String name, String description, ItemType type, int value) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.value = value;
    }

    public void printInfo() {
        System.out.println(
                "\uD83D\uDCE6 [" + this.name + "] - " + this.description
                        + " (Тип: " + this.type + ", Эффект: " + this.value + ")"
        );
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
