package entities.NPCs;

import entities.Hero;

import java.util.Random;
import java.util.Scanner;

public class CaptainNPC extends NPC {

    public CaptainNPC(String name, int initialRelationship) {
        super(name, "Капитан Императорской Гвардии", initialRelationship);
    }

    @Override
    public void interact(Hero hero, Scanner scanner, Random rand) {
        System.out.println("\n⚔️ " + title + " " + name + " отдает вам честь.");

        if (relationship >= 20) {
            hero.changeInfluence(20);
            System.out.println("Капитан добросовестно служит для вашего блага. " +
                    "Благодаря ему в городе нет преступлений (+20 к влиянию)!");
        } else {
            System.out.println("Кажется капитан что-то задумал. Он пристально следит за каждым вашим шагом.");
        }
    }
}
