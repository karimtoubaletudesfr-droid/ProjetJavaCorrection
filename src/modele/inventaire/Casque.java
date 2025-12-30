package modele.inventaire;

public class Casque extends Equipable {
    private final int bonusPv;
    private final int bonusDex;

    public Casque(String nom, int bonusPv, int bonusDex) {
        super(nom);
        this.bonusPv = bonusPv;
        this.bonusDex = bonusDex;
    }

    public int getBonusPv() { return bonusPv; }
    public int getBonusDex() { return bonusDex; }
}
