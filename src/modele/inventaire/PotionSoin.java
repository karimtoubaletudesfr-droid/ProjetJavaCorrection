package modele.inventaire;

import modele.personnages.Hero;

public class PotionSoin extends Consommable {
    private final int soin;

    public PotionSoin(int soin) {
        super("Potion de soin");
        this.soin = soin;
    }

    @Override
    public void utiliser(Hero hero) {
        hero.soigner(soin);
    }
}
