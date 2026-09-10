import java.util.Scanner;

public class Main {
    public static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в игру «Дворец и Интриги»!");
        System.out.println("Введите имя вашего персонажа: ");
        String name = scanner.nextLine();

        Hero hero = new Hero(name);
        boolean isRunning = true;

        System.out.println("\nПриветствуем при дворе, " + name + "!");

        while (isRunning) {
            hero.printStatus();
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
                    break;
                default:
                    System.out.println("\nНеверный выбор. Попробуйте снова.");
            }
        }
        scanner.close();
    }
}
