package modele.classe;

import java.util.List;

import modele.combat.PassiveSkill;
import modele.combat.passifs.Adrenaline;
import modele.combat.passifs.BouclierMagique;
import modele.combat.passifs.ContreAttaque;
import modele.combat.passifs.CriDeGuerre;
import modele.combat.passifs.PeauDure;
import modele.combat.passifs.Regeneration;
import modele.combat.strategy.AttaqueMagique;
import modele.combat.strategy.AttaquePhysique;
import modele.inventaire.Arme;
import modele.inventaire.PotionSoin;

public class ClasseHeroFactory {
    public ClasseHero creerBarbare() {
        List<PassiveSkill> passifs = List.of(new PeauDure(3), new ContreAttaque(10), new CriDeGuerre(2, 4), new Regeneration(4));
        return new ClasseHero(
                "Barbare",
                150, 20, 10, 15, 5,
                new AttaquePhysique(10),
                new Arme("Hache", 10),
                List.of(new PotionSoin(30)),
                passifs
        );
    }

    public ClasseHero creerSorcier() {
        List<PassiveSkill> passifs = List.of(new BouclierMagique(), new Regeneration(3));
        return new ClasseHero(
                "Sorcier",
                100, 5, 8, 10, 25,
                new AttaqueMagique(10),
                null,
                List.of(new PotionSoin(30)),
                passifs
        );
    }

    public ClasseHero creerArcher() {
        List<PassiveSkill> passifs = List.of(new PeauDure(2), new Adrenaline(5), new Regeneration(3));
        return new ClasseHero(
                "Archer",
                120, 12, 18, 12, 10,
                new AttaquePhysique(8),
                new Arme("Arc", 8),
                List.of(new PotionSoin(30)),
                passifs
        );
    }

    public ClasseHero creerAssassin() {
        List<PassiveSkill> passifs = List.of(new ContreAttaque(8), new Adrenaline(5), new Regeneration(3));
        return new ClasseHero(
                "Assassin",
                110, 15, 20, 10, 8,
                new AttaquePhysique(6),
                new Arme("Dague", 6),
                List.of(new PotionSoin(30)),
                passifs
        );
    }
}
