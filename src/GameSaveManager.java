import java.io.*;

public class GameSaveManager {
    private static final String SAVE_FILE = "save.txt";

    public static void saveGame(Hero hero) {
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            writer.write(hero.getName() + "\n");
            writer.write(hero.getInfluence() + "\n");
            writer.write(hero.getGold() + "\n");
            writer.write(hero.getHealth() + "\n");

            for (Item item : hero.getInventory()) {
                writer.write(item.getName() + ";" + item.getDescription() + ";" +
                        item.getType() + ";" + item.getValue() + "\n");
            }
            System.out.println("💾 Игра успешно сохранена в файл " + SAVE_FILE + "!");
        } catch (IOException e) {
            System.out.println("⚠️ Ошибка при сохранении игры: " + e.getMessage());
        }
    }

    public static Hero loadGame() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String name = reader.readLine();
            int influence = Integer.parseInt(reader.readLine());
            int gold = Integer.parseInt(reader.readLine());
            int health = Integer.parseInt(reader.readLine());

            Hero hero = new Hero(name);
            hero.getInventory().clear();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String itemName = parts[0];
                    String description = parts[1];
                    ItemType type = ItemType.valueOf(parts[2]);
                    int value = Integer.parseInt(parts[3]);

                    hero.addItem(new Item(itemName, description, type, value));
                }
            }

            hero.changeInfluence(influence - hero.getInfluence());
            hero.changeGold(gold - hero.getGold());
            hero.changeHealth(health - hero.getHealth());

            System.out.println("\uD83D\uDCC2 Сохранение успешно загружено! С возвращением, " + name + "!");
            return hero;
        } catch (IOException e) {
            System.out.println("⚠️ Не удалось найти файл сохранения. Начинаем новую игру.\n");
            return null;
        }
    }
}
