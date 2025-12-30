package modele.theme;

import java.util.Random;

import modele.combat.strategy.AttaqueMagique;
import modele.combat.strategy.AttaquePhysique;
import modele.inventaire.Aliment;
import modele.inventaire.Armure;
import modele.inventaire.Casque;
import modele.inventaire.Consommable;
import modele.inventaire.Equipable;
import modele.inventaire.Item;
import modele.inventaire.PotionForce;
import modele.inventaire.PotionResistance;
import modele.inventaire.PotionSoin;
import modele.inventaire.ItemLegendaire;
import modele.personnages.Pnj;
import modele.classe.ClasseHeroFactory;

public class ThemeFactoryHorreur implements ThemeFactory {
    private final Random rng = new Random();
    private final ClasseHeroFactory classeFactory = new ClasseHeroFactory();

    @Override
    public Pnj creerPnj(TypePnj type) {
        return switch (type) {
            case VAMPIRE -> new Pnj(type, new AttaquePhysique(3), 130, 16, 12, 14, 8);
            case LOUP_GAROU -> new Pnj(type, new AttaquePhysique(4), 140, 18, 14, 12, 6);
            case SPECTRE -> new Pnj(type, new AttaqueMagique(5), 90, 8, 16, 10, 18);
            case GOULE -> new Pnj(type, new AttaquePhysique(2), 80, 12, 10, 10, 5);
            case BOSS -> creerBoss();
            default -> throw new IllegalArgumentException("Type non horreur: " + type);
        };
    }

    @Override
    public Consommable creerConsommableAleatoire() {
        int r = rng.nextInt(5);
        return switch (r) {
            case 0 -> new PotionSoin(35);
            case 1 -> new PotionForce(6, 3);
            case 2 -> new PotionResistance(5, 2);
            case 3 -> new Aliment("Sang maudit", 25);
            default -> new Aliment("Viande corrompue", 20);
        };
    }

    @Override
    public Equipable creerEquipableAleatoire() {
        int r = rng.nextInt(3);
        return switch (r) {
            case 0 -> new Armure("Plastron runique", 8, 0);
            case 1 -> new Casque("Masque de terreur", 4, 2);
            default -> null;
        };
    }

    @Override
    public Pnj creerBoss() {
        return new Pnj(TypePnj.BOSS, new AttaqueMagique(7), 260, 20, 15, 18, 20);
    }

    @Override
    public Item creerItemLegendaire() {
        return new ItemLegendaire("Relique impie", classeFactory.creerSorcier(), java.util.List.of());
    }
}
