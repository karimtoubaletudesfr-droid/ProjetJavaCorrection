package vue;

import java.util.List;
import java.util.Scanner;

import modele.inventaire.Item;
import modele.personnages.Hero;
import modele.personnages.Pnj;
import modele.theme.Theme;

public class Ihm {
    private final Scanner scanner = new Scanner(System.in);

    public String demanderNomHero() {
        while (true) {
            System.out.print("Nom du heros: ");
            String heroName = scanner.nextLine().trim();
            if (!heroName.isEmpty()) {
                return heroName;
            }
            System.out.println("Veuillez entrer un nom de heros valide.");
        }
    }

    public String demanderClasseHero(List<String> classesDisponibles) {
        System.out.println("Choisissez une classe:");
        for (int i = 0; i < classesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + classesDisponibles.get(i));
        }
        int choix = demanderIndex("Classe", classesDisponibles.size());
        return classesDisponibles.get(choix - 1);
    }

    public Theme demanderTheme() {
        System.out.println("Choisissez un theme:");
        System.out.println("1. Medieval");
        System.out.println("2. Futuriste");
        System.out.println("3. Horreur fantastique");
        int choix = demanderIndex("Theme", 3);
        return switch (choix) {
            case 2 -> Theme.FUTURISTE;
            case 3 -> Theme.HORREUR;
            default -> Theme.MEDIEVAL;
        };
    }

    public int choixMenuPrincipal() {
        System.out.println("Menu principal:");
        System.out.println("1. Inventaire");
        System.out.println("2. Entrer dans le donjon");
        System.out.println("3. Quitter");
        return demanderIndex("Choix", 3);
    }

    public void afficherSalle(int numero, List<Pnj> ennemis, List<Item> items) {
        System.out.println("=== Salle " + numero + " ===");
        System.out.println("Ennemis:");
        if (ennemis.isEmpty()) {
            System.out.println("  Aucun");
        } else {
            for (int i = 0; i < ennemis.size(); i++) {
                Pnj p = ennemis.get(i);
                System.out.println("  " + (i + 1) + ") " + p.getNom() + " (" + p.getPv() + " pv)");
            }
        }
        System.out.println("Objets au sol:");
        if (items.isEmpty()) {
            System.out.println("  Aucun");
        } else {
            for (Item i : items) {
                System.out.println("  " + iconeItem(i) + " " + i.getNom());
            }
        }
    }

    public int choixActionSalle() {
        System.out.println("1. Attaquer");
        System.out.println("2. Ramasser objets");
        System.out.println("3. Inventaire");
        return demanderIndex("Choix", 3);
    }

    public int choixActionCombat(boolean aDeuxClasses) {
        if (aDeuxClasses) {
            System.out.println("1. Attaque classe principale");
            System.out.println("2. Attaque classe secondaire");
            System.out.println("3. Utiliser un objet");
            return demanderIndex("Choix", 3);
        }
        System.out.println("1. Attaquer");
        System.out.println("2. Utiliser un objet");
        return demanderIndex("Choix", 2);
    }

    public void afficherEtatCombat(Hero hero, List<Pnj> ennemis) {
        System.out.println(hero.getNom() + " : " + hero.getPv() + " / " + hero.getPvMax() + " pv");
        System.out.println("Ennemis:");
        for (int i = 0; i < ennemis.size(); i++) {
            Pnj p = ennemis.get(i);
            System.out.println((i + 1) + ". " + p.getNom() + " - " + p.getPv() + " pv");
        }
    }

    public void afficherInventaire(List<Item> items) {
        System.out.println("Inventaire:");
        if (items.isEmpty()) {
            System.out.println("  Vide");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + iconeItem(items.get(i)) + " " + items.get(i).getNom());
            }
        }
    }

    public int demanderIndex(String message, int max) {
        while (true) {
            System.out.print(message + " (1-" + max + "): ");
            String line = scanner.nextLine();
            try {
                int val = Integer.parseInt(line.trim());
                if (val >= 1 && val <= max) {
                    return val;
                }
            } catch (NumberFormatException ignored) { }
            System.out.println("Entree invalide.");
        }
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }

    private String iconeItem(Item item) {
        if (item instanceof modele.inventaire.Consommable) return "*";
        if (item instanceof modele.inventaire.Arme) return "+";
        if (item instanceof modele.inventaire.Armure) return "#";
        if (item instanceof modele.inventaire.Casque) return "~";
        if (item instanceof modele.inventaire.Chaussures) return "-";
        if (item instanceof modele.inventaire.ItemLegendaire) return "!";
        return "?";
    }
}
