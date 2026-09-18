import java.util.Random;
import java.util.Scanner;

public class CaptainNPC extends NPC {

    public CaptainNPC(String name, int initialRelationship) {
        super(name, "Капитан Императорской Гвардии", initialRelationship);
    }

    @Override
    public void interact(Hero hero, Scanner scanner, Random rand) {
        Item secretPapers = new Item(
                "Секретные бумаги",
                "Документ, порочащий честное имя",
                ItemType.COMPROMAT,
                0
        );

        System.out.println("\n⚔️ " + title + " " + name + " отдает вам честь.");

        if (rand.nextBoolean()) {
            System.out.println("Гуляя по дворцовому саду, вы нашли тайник в дупле древнего дуба!");
            System.out.println("1. Забрать содержимое себе");
            System.out.println("2. Ничего не трогать");
            System.out.println("> ");

            int choice = scanner.nextInt();
            if (choice == 1) {
                hero.addItem(secretPapers);
                System.out.println("Вы получили секретные бумаги!");
            } else {
                System.out.println("Вы не стали трогать чужие вещи и узнавать чужие тайны");
            }
        }
    }
}
