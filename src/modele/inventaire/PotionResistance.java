package modele.inventaire;

import modele.combat.Effet;
import modele.personnages.Hero;

public class PotionResistance extends Consommable {
    private final int bonusConst;
    private final int dureeTours;

    public PotionResistance(int bonusConst, int dureeTours) {
        super("Potion de resistance");
        this.bonusConst = bonusConst;
        this.dureeTours = dureeTours;
    }

    @Override
    public void utiliser(Hero hero) {
        hero.appliquerEffet(new Effet("Resistance accrue", 0, 0, bonusConst, 0, dureeTours));
    }
}
