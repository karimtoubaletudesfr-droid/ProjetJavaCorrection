package modele.inventaire;

import modele.combat.Effet;
import modele.personnages.Hero;

public class PotionForce extends Consommable {
    private final int bonus;
    private final int dureeTours;

    public PotionForce(int bonus, int dureeTours) {
        super("Potion de force");
        this.bonus = bonus;
        this.dureeTours = dureeTours;
    }

    @Override
    public void utiliser(Hero hero) {
        hero.appliquerEffet(new Effet("Bonus force", bonus, 0, 0, 0, dureeTours));
    }
}
