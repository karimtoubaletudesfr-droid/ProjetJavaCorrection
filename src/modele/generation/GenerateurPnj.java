package modele.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modele.personnages.Pnj;
import modele.theme.ThemeFactory;
import modele.theme.ThemeFactoryFuturiste;
import modele.theme.ThemeFactoryMedieval;
import modele.theme.TypePnj;

public class GenerateurPnj {
    private final ThemeFactory factory;
    private final Random rng = new Random();

    public GenerateurPnj(ThemeFactory factory) {
        this.factory = factory;
    }

    public List<Pnj> genererPourSalle() {
        int nb = 1 + rng.nextInt(4);
        List<Pnj> res = new ArrayList<>();
        for (int i = 0; i < nb; i++) {
            TypePnj type = tirerType();
            res.add(factory.creerPnj(type));
        }
        return res;
    }

    public Pnj creerBoss() {
        return factory.creerBoss();
    }

    private TypePnj tirerType() {
        if (factory instanceof ThemeFactoryMedieval) {
            int r = rng.nextInt(100);
            if (r < 30) return TypePnj.CHEVALIER_ERRANT;
            if (r < 55) return TypePnj.SORCIERE;
            if (r < 75) return TypePnj.VAUTOUR;
            if (r < 90) return TypePnj.RAT_ENRAGE;
            return TypePnj.GOBELIN;
        }
        if (factory instanceof ThemeFactoryFuturiste) {
            int r = rng.nextInt(100);
            if (r < 30) return TypePnj.SOLDAT_CYBER;
            if (r < 55) return TypePnj.ROBOT_SENTINELLE;
            if (r < 80) return TypePnj.ALIEN;
            return TypePnj.DRONE;
        }
        return TypePnj.CHEVALIER_ERRANT;
    }
}
