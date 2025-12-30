package modele.combat.strategy;

import modele.combat.AttaqueStrategy;
import modele.combat.CalculateurDegats;
import modele.inventaire.Arme;
import modele.personnages.Hero;
import modele.personnages.Personnage;

public class AttaquePhysique implements AttaqueStrategy {
    private final int bonusArme;

    public AttaquePhysique(int bonusArme) {
        this.bonusArme = bonusArme;
    }

    @Override
    public int calculerDegats(Personnage attaquant, Personnage defenseur) {
        int bonus = bonusArme;
        if (attaquant instanceof Hero hero) {
            Arme arme = hero.getEquipement().getArme();
            if (arme != null) {
                bonus = arme.getBonusDegats();
            }
        }
        return CalculateurDegats.degatsPhysiques(attaquant.getForce(), bonus, defenseur.getConstitution());
    }

    @Override
    public String nomAttaque() {
        return "attaque physique";
    }
}
