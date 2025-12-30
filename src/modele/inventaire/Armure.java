package modele.inventaire;

public class Armure extends Equipable {
    private final int bonusPv;
    private final int bonusForce;

    public Armure(String nom, int bonusPv, int bonusForce) {
        super(nom);
        this.bonusPv = bonusPv;
        this.bonusForce = bonusForce;
    }

    public int getBonusPv() { return bonusPv; }
    public int getBonusForce() { return bonusForce; }
}
