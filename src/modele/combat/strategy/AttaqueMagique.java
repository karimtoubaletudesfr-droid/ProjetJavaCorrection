package modele.combat.strategy;

import modele.combat.AttaqueStrategy;
import modele.combat.CalculateurDegats;
import modele.personnages.Personnage;

public class AttaqueMagique implements AttaqueStrategy {
    private final int bonusSort;

    public AttaqueMagique(int bonusSort) {
        this.bonusSort = bonusSort;
    }

    @Override
    public int calculerDegats(Personnage attaquant, Personnage defenseur) {
        return CalculateurDegats.degatsMagiques(attaquant.getIntelligence(), bonusSort, defenseur.getConstitution());
    }

    @Override
    public String nomAttaque() {
        return "attaque magique";
    }
}
