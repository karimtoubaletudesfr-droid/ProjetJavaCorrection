package modele.inventaire;

import modele.personnages.Hero;

public class Aliment extends Consommable {
    private final int soin;

    public Aliment(String nom, int soin) {
        super(nom);
        this.soin = soin;
    }

    @Override
    public void utiliser(Hero hero) {
        hero.soigner(soin);
    }
}
