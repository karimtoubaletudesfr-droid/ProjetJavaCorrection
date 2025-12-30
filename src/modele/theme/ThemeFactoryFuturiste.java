package modele.theme;

import java.util.Random;

import modele.combat.strategy.AttaqueMagique;
import modele.combat.strategy.AttaquePhysique;
import modele.inventaire.Aliment;
import modele.inventaire.Armure;
import modele.inventaire.Casque;
import modele.inventaire.Consommable;
import modele.inventaire.Equipable;
import modele.inventaire.PotionForce;
import modele.inventaire.PotionResistance;
import modele.inventaire.PotionSoin;
import modele.personnages.Pnj;

public class ThemeFactoryFuturiste implements ThemeFactory {
    private final Random rng = new Random();

    @Override
    public Pnj creerPnj(TypePnj type) {
        return switch (type) {
            case SOLDAT_CYBER -> new Pnj(type, new AttaquePhysique(2), 110, 14, 10, 10, 6);
            case ROBOT_SENTINELLE -> new Pnj(type, new AttaquePhysique(3), 100, 12, 9, 12, 5);
            case ALIEN -> new Pnj(type, new AttaqueMagique(4), 80, 10, 12, 8, 14);
            case DRONE -> new Pnj(type, new AttaquePhysique(2), 60, 5, 15, 5, 6);
            case BOSS -> creerBoss();
            default -> throw new IllegalArgumentException("Type non futuriste: " + type);
        };
    }

    @Override
    public Consommable creerConsommableAleatoire() {
        int r = rng.nextInt(5);
        return switch (r) {
            case 0 -> new PotionSoin(25);
            case 1 -> new PotionForce(6, 3);
            case 2 -> new PotionResistance(5, 2);
            case 3 -> new Aliment("Gel energisant", 20);
            default -> new Aliment("Ration synthetique", 15);
        };
    }

    @Override
    public Equipable creerEquipableAleatoire() {
        int r = rng.nextInt(3);
        return switch (r) {
            case 0 -> new Armure("Plastron composite", 7, 0);
            case 1 -> new Casque("Visiere holographique", 4, 2);
            default -> null;
        };
    }

    @Override
    public Pnj creerBoss() {
        return new Pnj(TypePnj.BOSS, new AttaqueMagique(8), 240, 18, 15, 16, 18);
    }
}
