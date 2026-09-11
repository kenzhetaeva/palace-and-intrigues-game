import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Добро пожаловать в игру «Дворец и Интриги»!");
        System.out.println("Введите имя вашего персонажа: ");
        String name = scanner.nextLine();

        Hero hero = new Hero(name);

        NPC advisor = new NPC("Кван Джи Хун", "Первый Министр", -80);
        NPC captain = new NPC("Ян Чонин", "Капитан Императорской Гвардии", -20);
        NPC guard = new NPC("Чхве Джин Сан", "Командир Императорской Стражи", 100);

        boolean isRunning = true;

        System.out.println("\nПриветствуем при дворе, " + name + "!");

        while (isRunning) {
            hero.printStatus();
            advisor.printInfo();
            captain.printInfo();
            guard.printInfo();

            System.out.println("Выберите действие:");
            System.out.println("1. Посетить аудиенцию (+10 к влиянию, -10 к энергии)");
            System.out.println("2. Собрать налоги (+20 золота, -15 к энергии)");
            System.out.println("3. Отдохнуть (+30 к энергии)");
            System.out.println("0. Выйти из игры");
            System.out.println("> ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nВы успешно выступили на аудиенции!");
                    hero.changeInfluence(10);
                    hero.changeHealth(-10);
                    break;
                case 2:
                    System.out.println("\nКазна пополнена!");
                    hero.changeGold(20);
                    hero.changeHealth(-15);
                    break;
                case 3:
                    System.out.println("\nВы отдохнули в своих покоях.");
                    hero.changeHealth(30);
                    break;
                case 0:
                    System.out.println("\nВы покинули игру.");
                    isRunning = false;
                    continue;
                default:
                    System.out.println("\nНеверный выбор. Попробуйте снова.");
            }
            if (rand.nextInt(100) < 50) {
                int npc = rand.nextInt(3);
                NPC chosenNPC;
                if (npc == 0) {
                    chosenNPC = advisor;
                } else if (npc == 1) {
                    chosenNPC = captain;
                } else {
                    chosenNPC = guard;
                }
                triggerRandomEvent(hero, chosenNPC, scanner, rand);
            }
        }
        scanner.close();
    }

    private static void triggerRandomEvent(Hero hero, NPC npc, Scanner scanner, Random rand) {
        System.out.println("\n⚠️ [ДВОРЦОВАЯ ИНТРИГА!] ⚠️");

        int eventType = rand.nextInt(2);

        if (eventType == 0) {
            System.out.println(npc.getTitle() + " " + npc.getName() + " распускает о вас слухи!");
            System.out.println("1. Подкупить его (Потратить 30 монет)");
            System.out.println("2. Игнорировать (Потерять 15 влияния)");
            System.out.println("> ");

            int choice = scanner.nextInt();
            if (choice == 1) {
                if (hero.getGold() >= 30) {
                    hero.changeGold(-30);
                    npc.changeRelationship(15);
                    System.out.println("Вы передали кошель с золотом. Слухи замяты.");
                } else {
                    System.out.println("У вас недостаточно золота! Слухи распространились.");
                    hero.changeInfluence(-15);
                }
            } else {
                hero.changeInfluence(-15);
                npc.changeRelationship(-10);
                System.out.println("Вы проигнорировали выпад. Ваше влияние упало.");
            }
        } else {
            System.out.println("Вы случайно узнали секрет " + npc.getTitle() + " " + npc.getName() + "!");
            System.out.println("1. Шантажировать его (+20 золота, испортить отношения)");
            System.out.println("2. Сохранить тайну (+25 к отношениям)");
            System.out.println("> ");

            int choice = scanner.nextInt();
            if (choice == 1) {
                hero.changeGold(20);
                npc.changeRelationship(-30);
                System.out.println(npc.getName() + " заплатил вам, но теперь он вас ненавидит.");
            } else {
                npc.changeRelationship(25);
                System.out.println(npc.getName() + " благодарен за ваше молчание.");
            }
        }
    }
}
