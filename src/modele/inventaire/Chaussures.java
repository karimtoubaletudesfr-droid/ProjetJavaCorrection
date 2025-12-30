package modele.inventaire;

public class Chaussures extends Equipable {
    private final int bonusDex;

    public Chaussures(String nom, int bonusDex) {
        super(nom);
        this.bonusDex = bonusDex;
    }

    public int getBonusDex() { return bonusDex; }
}
