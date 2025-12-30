package modele.combat;

import modele.personnages.Personnage;

public interface AttaqueStrategy {
    int calculerDegats(Personnage attaquant, Personnage defenseur);
    String nomAttaque();
}
