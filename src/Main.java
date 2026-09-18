import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Добро пожаловать в игру «Дворец и Интриги»!");

        Hero hero = null;

        System.out.println("1. Начать новую игру");
        System.out.println("2. Загрузить сохранение");
        System.out.println("> ");
        int startChoice = scanner.nextInt();
        scanner.nextLine();

        if (startChoice == 2) {
            hero = GameSaveManager.loadGame();
        }

        if (hero == null) {
            System.out.println("Введите имя вашего персонажа: ");
            String name = scanner.nextLine();
            hero = new Hero(name);
        }

        List<NPC> palaceNPCs = new ArrayList<>();
        palaceNPCs.add(new AdvisorNPC("Кван Джи Хун", -20));
        palaceNPCs.add(new CaptainNPC("Ян Чонин", -80));
        palaceNPCs.add(new GuardNPC("Чхве Джин Сан", 100));

        boolean isRunning = true;

        System.out.println("\nПриветствуем при дворе, " + hero.getName() + "!");

        while (isRunning) {
            hero.printStatus();
            for(NPC npc : palaceNPCs) {
                npc.printInfo();
            }

            System.out.println("Выберите действие:");
            System.out.println("1. Посетить аудиенцию (+10 к влиянию, -10 к энергии)");
            System.out.println("2. Собрать налоги (+20 золота, -15 к энергии)");
            System.out.println("3. Отдохнуть (+30 к энергии)");
            System.out.println("4. 🎒 Открыть инвентарь");
            System.out.println("5. 💾 Сохранить игру");
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
                case 4:
                    openInventoryMenu(hero, scanner);
                    continue;
                case 5:
                    GameSaveManager.saveGame(hero);
                    continue;
                case 0:
                    System.out.println("\nВы покинули игру.");
                    isRunning = false;
                    continue;
                default:
                    System.out.println("\nНеверный выбор. Попробуйте снова.");
            }
            if (rand.nextBoolean()) {
                int randomIndex = rand.nextInt(palaceNPCs.size());
                NPC randomNpc = palaceNPCs.get(randomIndex);
                triggerRandomEvent(hero, randomNpc, scanner, rand);
            }
        }
        scanner.close();
    }

    private static void triggerRandomEvent(Hero hero, NPC npc, Scanner scanner, Random rand) {
        System.out.println("\n🎲 --- СОБЫТИЕ ВО ДВОРЦЕ ---");
        System.out.println("К вам подходит " + npc.getTitle() + " " + npc.getName() + ".");
        npc.interact(hero, scanner, rand);
    }

    private static void openInventoryMenu(Hero hero, Scanner scanner) {
        List<Item> items = hero.getInventory();

        System.out.println("\n--- \uD83C\uDF92 ВАШ ИНВЕНТАРЬ ---");
        if (items.isEmpty()) {
            System.out.println("Инвентарь пуст.");
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            System.out.print((i + 1) + ". ");
            items.get(i).printInfo();
        }
        System.out.println("0. Назад в главное меню");
        System.out.print("Выберите номер предмета для использования: ");

        int itemChoice = scanner.nextInt();

        if (itemChoice > 0 && itemChoice <= items.size()) {
            hero.useItem(itemChoice - 1);
        } else if (itemChoice != 0) {
            System.out.println("⚠️ Неверный номер предмета.");
        }
    }
}
