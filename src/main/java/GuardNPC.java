import java.util.Random;
import java.util.Scanner;

public class GuardNPC extends NPC {

    public GuardNPC(String name, int initialRelationship) {
        super(name, "Командир Императорской Стражи", initialRelationship);
    }

    @Override
    public void interact(Hero hero, Scanner scanner, Random rand) {
        System.out.println("\n⚔️ " + title + " " + name + " отдает вам честь.");
        if (relationship >= 20) {
            hero.changeHealth(20);
            System.out.println("Стража обеспечила вам безопасный отдых (+20 к энергии)!");
        } else {
            System.out.println("Стража подозрительно следит за каждым вашим шагом.");
        }
    }
}
