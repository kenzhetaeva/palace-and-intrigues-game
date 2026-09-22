package com.example.demo.entities;

import com.example.demo.enums.ItemType;
import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private ItemType type;

    @Column(name = "effect_value")
    private int effectValue; // Сила эффекта (например, сколько здоровья восстанавливает)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hero_id")
    private Hero hero;

    public Item() {}

    public Item(String name, ItemType type, int effectValue) {
        this.name = name;
        this.type = type;
        this.effectValue = effectValue;
    }

    public void printInfo() {
        System.out.println(
                "\uD83D\uDCE6 [" + this.name + "] " + " (Тип: " + this.type + ", Эффект: " + this.effectValue + ")"
        );
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public int getEffectValue() {
        return effectValue;
    }
}
