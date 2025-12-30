package modele.inventaire;

public class Arme extends Equipable {
    private final int bonusDegats;

    public Arme(String nom, int bonusDegats) {
        super(nom);
        this.bonusDegats = bonusDegats;
    }

    public int getBonusDegats() { return bonusDegats; }
}
