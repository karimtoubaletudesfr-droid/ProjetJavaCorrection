package controleur;

import java.util.ArrayList;
import java.util.List;

import modele.classe.ClasseHero;
import modele.classe.ClasseHeroFactory;
import modele.combat.CombatManager;
import modele.donjon.Donjon;
import modele.donjon.GenerateurDonjon;
import modele.donjon.Salle;
import modele.generation.GenerateurObjets;
import modele.generation.GenerateurPnj;
import modele.inventaire.Consommable;
import modele.inventaire.Equipable;
import modele.inventaire.Item;
import modele.personnages.Hero;
import modele.personnages.Pnj;
import modele.theme.Theme;
import modele.theme.ThemeFactory;
import modele.theme.ThemeFactoryFuturiste;
import modele.theme.ThemeFactoryMedieval;
import vue.Ihm;

public class Controleur {
    private final Ihm ihm;
    private CombatManager combatManager;
    private GenerateurDonjon generateurDonjon;
    private ThemeFactory themeFactory;

    private Hero hero;
    private Donjon donjon;
    private Theme themeChoisi;

    public Controleur(Ihm ihm) {
        this.ihm = ihm;
    }

    public void lancerJeu() {
        ClasseHeroFactory classeFactory = new ClasseHeroFactory();
        String nomHero = ihm.demanderNomHero();
        List<String> classes = List.of("Barbare", "Sorcier", "Archer", "Assassin");
        String choixClasse = ihm.demanderClasseHero(classes);
        ClasseHero classeHero = switch (choixClasse.toLowerCase()) {
            case "barbare" -> classeFactory.creerBarbare();
            case "sorcier" -> classeFactory.creerSorcier();
            case "archer" -> classeFactory.creerArcher();
            case "assassin" -> classeFactory.creerAssassin();
            default -> classeFactory.creerBarbare();
        };
        this.hero = new Hero(nomHero, classeHero);
        this.themeChoisi = ihm.demanderTheme();
        this.themeFactory = (themeChoisi == Theme.FUTURISTE) ? new ThemeFactoryFuturiste() : new ThemeFactoryMedieval();
        GenerateurPnj genPnj = new GenerateurPnj(themeFactory);
        GenerateurObjets genObj = new GenerateurObjets(themeFactory);
        this.generateurDonjon = new GenerateurDonjon(genPnj, genObj);
        this.combatManager = new CombatManager(ihm);
        this.donjon = generateurDonjon.generer(10, true); // boss final inclus
        menuPrincipal();
    }

    private void menuPrincipal() {
        boolean quitter = false;
        while (!quitter) {
            int choix = ihm.choixMenuPrincipal();
            switch (choix) {
                case 1 -> afficherInventaireEtActions();
                case 2 -> {
                    explorerDonjon();
                    if (!hero.estVivant() || !donjon.aEncoreDesSalles()) {
                        quitter = true;
                    }
                }
                case 3 -> quitter = true;
                default -> ihm.afficherMessage("Choix invalide");
            }
        }
        if (hero != null && hero.estVivant() && !donjon.aEncoreDesSalles()) {
            ihm.afficherMessage("Felicitations, donjon termine !");
        } else if (hero != null && !hero.estVivant()) {
            ihm.afficherMessage("Game Over.");
        }
    }

    private void explorerDonjon() {
        int numeroSalle = 1;
        while (hero.estVivant() && donjon.aEncoreDesSalles()) {
            Salle salle = donjon.salleCourante();
            gererSalle(salle, numeroSalle);
            if (hero.estVivant()) {
                donjon.passerSalleSuivante();
                numeroSalle++;
            }
        }
    }

    private void gererSalle(Salle salle, int numeroSalle) {
        while (hero.estVivant() && contientEnnemisVivant(salle.getEnnemis())) {
            ihm.afficherSalle(numeroSalle, salle.getEnnemis(), salle.getItemsAuSol());
            int choix = ihm.choixActionSalle();
            switch (choix) {
                case 1 -> gererCombat(salle);
                case 2 -> ramasserObjets(salle);
                case 3 -> afficherInventaireEtActions();
                default -> ihm.afficherMessage("Choix invalide");
            }
        }
        if (hero.estVivant()) {
            ihm.afficherMessage("Salle nettoyee.");
        }
    }

    private void ramasserObjets(Salle salle) {
        List<Item> items = salle.getItemsAuSol();
        if (items.isEmpty()) {
            ihm.afficherMessage("Aucun objet a ramasser.");
            return;
        }
        for (Item item : new ArrayList<>(items)) {
            hero.getInventaire().ajouter(item);
        }
        items.clear();
        ihm.afficherMessage("Objets ajoutes a l'inventaire.");
    }

    private void gererCombat(Salle salle) {
        List<Pnj> ennemis = salle.getEnnemis();
        while (hero.estVivant() && contientEnnemisVivant(ennemis)) {
            ennemis.removeIf(p -> !p.estVivant());
            if (ennemis.isEmpty()) {
                break;
            }
            Pnj cible = ennemis.get(0); // on attaque toujours le premier vivant
            ihm.afficherEtatCombat(hero, ennemis);
            int choix = ihm.choixActionCombat();
            if (choix == 1) {
                combatManager.appliquerEffets(hero);
                combatManager.attaquer(hero, cible);
            } else if (choix == 2) {
                afficherInventaireEtActions();
            } else {
                ihm.afficherMessage("Choix invalide");
            }
            if (cible.estVivant() && hero.estVivant()) {
                combatManager.attaquer(cible, hero);
            }
        }
        if (!hero.estVivant()) {
            ihm.afficherMessage("Le heros est vaincu.");
        } else {
            ihm.afficherMessage("Tous les ennemis sont vaincus.");
        }
    }

    private void afficherInventaireEtActions() {
        ihm.afficherInventaire(hero.getInventaire().getItems());
        afficherSlotsEquipes();
        ihm.afficherMessage("1. Consommer un objet\n2. S'equiper\n3. Se desequiper\n4. Retour");
        int choix = ihm.demanderIndex("Votre choix", 4);
        switch (choix) {
            case 1 -> utiliserConsommable();
            case 2 -> equiperObjet();
            case 3 -> desequiperObjet();
            case 4 -> { /* retour */ }
            default -> ihm.afficherMessage("Choix invalide");
        }
    }

    private void utiliserConsommable() {
        List<Consommable> conso = hero.getInventaire().getConsommables();
        if (conso.isEmpty()) {
            ihm.afficherMessage("Aucun consommable.");
            return;
        }
        ihm.afficherMessage("Choisir un consommable:");
        for (int i = 0; i < conso.size(); i++) {
            ihm.afficherMessage((i + 1) + ". " + conso.get(i).getNom());
        }
        int idx = ihm.demanderIndex("Choix", conso.size());
        Consommable choisi = conso.get(idx - 1);
        choisi.utiliser(hero);
        hero.getInventaire().retirer(choisi);
    }

    private void equiperObjet() {
        List<Equipable> equipables = hero.getInventaire().getEquipables();
        if (equipables.isEmpty()) {
            ihm.afficherMessage("Aucun equipable.");
            return;
        }
        ihm.afficherMessage("Choisir un equipement:");
        for (int i = 0; i < equipables.size(); i++) {
            ihm.afficherMessage((i + 1) + ". " + equipables.get(i).getNom());
        }
        int idx = ihm.demanderIndex("Choix", equipables.size());
        Equipable eq = equipables.get(idx - 1);
        Equipable ancien = hero.getEquipement().equiper(eq);
        hero.getInventaire().retirer(eq);
        if (ancien != null) {
            hero.getInventaire().ajouter(ancien);
        }
    }

    private void desequiperObjet() {
        ihm.afficherMessage("Choisir le slot a desequiper:");
        ihm.afficherMessage("1. Arme");
        ihm.afficherMessage("2. Armure");
        ihm.afficherMessage("3. Casque");
        ihm.afficherMessage("4. Chaussures");
        ihm.afficherMessage("5. Retour");
        int choix = ihm.demanderIndex("Slot", 5);
        Equipable rendu = null;
        switch (choix) {
            case 1 -> rendu = hero.getEquipement().desequiperArme();
            case 2 -> rendu = hero.getEquipement().desequiperArmure();
            case 3 -> rendu = hero.getEquipement().desequiperCasque();
            case 4 -> rendu = hero.getEquipement().desequiperChaussures();
            case 5 -> { return; }
            default -> ihm.afficherMessage("Choix invalide");
        }
        if (rendu != null) {
            hero.getInventaire().ajouter(rendu);
            ihm.afficherMessage(rendu.getNom() + " remis dans l'inventaire.");
        } else {
            ihm.afficherMessage("Rien a desequiper dans ce slot.");
        }
    }

    private boolean contientEnnemisVivant(List<Pnj> ennemis) {
        for (Pnj p : ennemis) {
            if (p.estVivant()) return true;
        }
        return false;
    }
    private void afficherSlotsEquipes() {
        var eq = hero.getEquipement();
        ihm.afficherMessage("Slots equipes:");
        ihm.afficherMessage("  Arme: " + (eq.getArme() != null ? eq.getArme().getNom() : "aucune"));
        ihm.afficherMessage("  Armure: " + (eq.getArmure() != null ? eq.getArmure().getNom() : "aucune"));
        ihm.afficherMessage("  Casque: " + (eq.getCasque() != null ? eq.getCasque().getNom() : "aucun"));
        ihm.afficherMessage("  Chaussures: " + (eq.getChaussures() != null ? eq.getChaussures().getNom() : "aucune"));
    }
}
