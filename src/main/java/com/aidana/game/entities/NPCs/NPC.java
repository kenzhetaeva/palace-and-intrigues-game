package com.aidana.game.entities.NPCs;

import com.aidana.game.entities.Hero;
import jakarta.persistence.*;

import java.util.Random;
import java.util.Scanner;

@Entity
@Table(name = "npcs")
public abstract class NPC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "title", nullable = false)
    protected String title; // Должность

    @Column(name = "relationship")
    protected int relationship; // Отношение к герою (от -100 до 100)

    public NPC(String name, String title, int initialRelationship) {
        this.name = name;
        this.title = title;
        this.relationship = initialRelationship;
    }

    public void changeRelationship(int amount) {
        this.relationship += amount;
        if (this.relationship > 100) {
            this.relationship = 100;
        }
        if (this.relationship < -100) {
            this.relationship = -100;
        }
    }

    public abstract void interact(Hero hero, Scanner scanner, Random rand);

    public void printInfo() {
        String status = relationship >= 0 ? "Лоялен" : "Враждебен";
        System.out.println("-> " + title + " " + name + " [" + status + " | Отношение: " + relationship + "]\n");
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public int getRelationship() {
        return relationship;
    }
}
