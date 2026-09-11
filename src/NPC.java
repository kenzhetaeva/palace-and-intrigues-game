public class NPC {
    private String name;
    private String title; // Должность
    private int relationship; // Отношение к герою (от -100 до 100)

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
