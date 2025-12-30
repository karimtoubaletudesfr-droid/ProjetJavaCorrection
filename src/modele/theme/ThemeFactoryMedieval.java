package modele.theme;

import java.util.Random;

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

public class ThemeFactoryMedieval implements ThemeFactory {
    private final Random rng = new Random();

    @Override
    public Pnj creerPnj(TypePnj type) {
        return switch (type) {
            case CHEVALIER_ERRANT -> new Pnj(type, new AttaquePhysique(1), 120, 15, 8, 12, 5);
            case SORCIERE -> new Pnj(type, new AttaquePhysique(0), 90, 5, 8, 8, 18);
            case VAUTOUR -> new Pnj(type, new AttaquePhysique(0), 70, 8, 14, 6, 4);
            case RAT_ENRAGE -> new Pnj(type, new AttaquePhysique(0), 40, 6, 12, 5, 2);
            case GOBELIN -> new Pnj(type, new AttaquePhysique(0), 60, 7, 10, 6, 3);
            case BOSS -> creerBoss();
            default -> throw new IllegalArgumentException("Type non medieval: " + type);
        };
    }

    @Override
    public Consommable creerConsommableAleatoire() {
        int r = rng.nextInt(5);
        return switch (r) {
            case 0 -> new PotionSoin(30);
            case 1 -> new PotionForce(5, 3);
            case 2 -> new PotionResistance(4, 2);
            case 3 -> new Aliment("Pain", 15);
            default -> new Aliment("Viande sechee", 20);
        };
    }

    @Override
    public Equipable creerEquipableAleatoire() {
        int r = rng.nextInt(3);
        return switch (r) {
            case 0 -> new Armure("Armure de cuir", 5, 0);
            case 1 -> new Casque("Casque en fer", 3, 1);
            default -> null;
        };
    }

    @Override
    public Pnj creerBoss() {
        return new Pnj(TypePnj.BOSS, new AttaquePhysique(5), 220, 22, 14, 18, 8);
    }
}
