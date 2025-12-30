package modele.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modele.inventaire.Item;
import modele.theme.ThemeFactory;

public class GenerateurObjets {
    private final ThemeFactory factory;
    private final Random rng = new Random();

    public GenerateurObjets(ThemeFactory factory) {
        this.factory = factory;
    }

    public List<Item> genererPourSalle() {
        int nb = rng.nextInt(3); // 0,1,2
        List<Item> res = new ArrayList<>();
        // 10% chance of a legendary item
        if (rng.nextInt(100) < 10) {
            Item leg = factory.creerItemLegendaire();
            if (leg != null) res.add(leg);
        }
        for (int i = 0; i < nb; i++) {
            boolean prendreEquip = rng.nextInt(100) < 40; // 60% consommable, 40% equipable
            if (prendreEquip) {
                var eq = factory.creerEquipableAleatoire();
                if (eq != null) res.add(eq);
            } else {
                res.add(factory.creerConsommableAleatoire());
            }
        }
        return res;
    }
}
