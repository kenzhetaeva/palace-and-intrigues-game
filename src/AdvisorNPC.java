import java.util.Random;
import java.util.Scanner;

public class AdvisorNPC extends NPC {

    public AdvisorNPC(String name, int initialRelationship) {
        super(name, "Первый Министр", initialRelationship);
    }

    @Override
    public void interact(Hero hero, Scanner scanner, Random rand) {

        Item secretPapers = new Item(
                "Секретные бумаги",
                "Документ, порочащий честное имя",
                ItemType.COMPROMAT,
                0
        );

        if (rand.nextBoolean()) {
            System.out.println(this.getTitle() + " " + this.getName() + " распускает о вас слухи!");
            System.out.println("1. Подкупить его (Потратить 30 монет)");
            System.out.println("2. Игнорировать (Потерять 15 влияния)");

            if (hero.hasItemByName(secretPapers.getName())) {
                System.out.println("3. [Использовать компромат] Заставить молчать");
            }
            System.out.println("> ");

            int choice = scanner.nextInt();
            if (choice == 1) {
                if (hero.getGold() >= 30) {
                    hero.changeGold(-30);
                    this.changeRelationship(15);
                    System.out.println("Вы передали кошель с золотом. Слухи замяты.");
                } else {
                    System.out.println("У вас недостаточно золота! Слухи распространились.");
                    hero.changeInfluence(-15);
                }
            } else if (choice == 2) {
                hero.changeInfluence(-15);
                this.changeRelationship(-10);
                System.out.println("Вы проигнорировали выпад. Ваше влияние упало.");
            } else if (choice == 3 && hero.hasItemByName(secretPapers.getName())) {
                System.out.println("\nВы показали документ с его тайной. Он бледнеет и умолкает!\n");
                hero.removeItemByName(secretPapers.getName());
                this.changeRelationship(-10);
            }
        } else {
            System.out.println("Вы случайно узнали секрет " + getTitle() + " " + getName() + "!");
            System.out.println("1. Шантажировать его (+20 золота, испортить отношения)");
            System.out.println("2. Сохранить тайну (+25 к отношениям)");
            System.out.println("> ");

            int choice = scanner.nextInt();
            if (choice == 1) {
                hero.changeGold(20);
                changeRelationship(-30);
                System.out.println(getName() + " заплатил вам, но теперь он вас ненавидит.");
            } else {
                changeRelationship(25);
                System.out.println(getName() + " благодарен за ваше молчание.");
            }
        }
    }
}
