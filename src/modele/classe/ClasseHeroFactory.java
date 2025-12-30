package modele.classe;

import modele.combat.strategy.AttaqueMagique;
import modele.combat.strategy.AttaquePhysique;
import modele.inventaire.Arme;
import modele.inventaire.PotionSoin;

public class ClasseHeroFactory {
    public ClasseHero creerBarbare() {
        return new ClasseHero(
                "Barbare",
                150, 20, 10, 15, 5,
                new AttaquePhysique(10),
                new Arme("Hache", 10),
                java.util.List.of(new PotionSoin(30))
        );
    }

    public ClasseHero creerSorcier() {
        return new ClasseHero(
                "Sorcier",
                100, 5, 8, 10, 25,
                new AttaqueMagique(10),
                null,
                java.util.List.of(new PotionSoin(30))
        );
    }

    public ClasseHero creerArcher() {
        return new ClasseHero(
                "Archer",
                120, 12, 18, 12, 10,
                new AttaquePhysique(8),
                new Arme("Arc", 8),
                java.util.List.of(new PotionSoin(30))
        );
    }

    public ClasseHero creerAssassin() {
        return new ClasseHero(
                "Assassin",
                110, 15, 20, 10, 8,
                new AttaquePhysique(6),
                new Arme("Dague", 6),
                java.util.List.of(new PotionSoin(30))
        );
    }
}
