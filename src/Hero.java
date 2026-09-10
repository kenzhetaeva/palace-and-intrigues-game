public class Hero {
    private String name;
    private int influence; // Влияние во дворце (0-100)
    private int gold;      // Золото
    private int health;    // Здоровье/Энергия (0-100)

    public Hero(String name) {
        this.name = name;

        // Initial values
        this.influence = 50;
        this.gold = 100;
        this.health = 100;
    }

    public void changeInfluence(int amount) {
        this.influence += amount;
        if (influence < 0) {
            influence = 0;
        }
        if (influence > 100) {
            influence = 100;
        }
    }

    public void changeGold(int amount) {
        this.gold += amount;
        if (this.gold < 0) {
            this.gold = 0;
        }
    }

    public void changeHealth(int amount) {
        this.health += amount;
        if (this.health < 0) {
            this.health = 0;
        }
        if (this.health > 100) {
            this.health = 100;
        }
    }

    public void printStatus() {
        System.out.println("\n=====СТАТУС ПЕРСОНАЖА=====");
        System.out.println("Имя: " + name);
        System.out.println("Влияние: " + influence + "/100");
        System.out.println("Казна: " + gold + " монет");
        System.out.println("Энергия: " + health + "/100");
        System.out.println("===========================\n");
    }

    public String getName() {
        return name;
    }

    public int getInfluence() {
        return influence;
    }

    public int getGold() {
        return gold;
    }

    public int getHealth() {
        return health;
    }
}
