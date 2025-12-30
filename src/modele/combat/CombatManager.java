package modele.combat;

import java.util.ArrayList;
import java.util.List;

import modele.inventaire.Arme;
import modele.personnages.Hero;
import modele.personnages.Pnj;
import vue.Ihm;

public class CombatManager {
    private final Ihm ihm;

    public CombatManager(Ihm ihm) {
        this.ihm = ihm;
    }

    public int attaquer(Hero hero, Pnj cible) {
        int pvAvant = cible.getPv();
        Arme arme = hero.getEquipement().getArme();
        int bonus = (arme != null) ? arme.getBonusDegats() : 0;
        int base = hero.getAttaqueStrategy().calculerDegats(hero, cible);
        cible.recevoirDegats(base);
        int pvApres = cible.getPv();
        StringBuilder res = new StringBuilder();
        res.append(hero.getNom()).append(" attaque ").append(cible.getNom());
        res.append(" pour ").append(base).append(" degats");
        res.append(" (force ").append(hero.getForce()).append(", bonus arme ").append(bonus).append("). ");
        res.append("PV ennemi: ").append(pvAvant).append(" -> ").append(pvApres).append(".");
        ihm.afficherMessage(res.toString());
        if (!cible.estVivant()) {
            ihm.afficherMessage(cible.getNom() + " est vaincu.");
        }
        return base;
    }

    public int attaquer(Pnj pnj, Hero hero) {
        int pvAvant = hero.getPv();
        int base = pnj.getAttaqueStrategy().calculerDegats(pnj, hero);
        int jet = CalculateurDegats.lancerD6();
        int reductionBase = CalculateurDegats.reductionDegats(hero.getDexterite(), hero.getConstitution());
        int reductionTotale = reductionBase + jet;
        int subi = Math.max(1, base - reductionTotale);
        hero.recevoirDegats(subi);
        int pvApres = hero.getPv();
        StringBuilder res = new StringBuilder();
        res.append(pnj.getNom()).append(" attaque ").append(hero.getNom());
        res.append(" pour ").append(base).append(" degats bruts.");
        res.append(" Defense (dex+const)/4=").append(reductionBase).append(" + d6=").append(jet);
        res.append(" => degats subis ").append(subi).append(".");
        res.append(" PV heros: ").append(pvAvant).append(" -> ").append(pvApres).append(".");
        ihm.afficherMessage(res.toString());
        if (!hero.estVivant()) {
            ihm.afficherMessage(hero.getNom() + " est tombe au combat.");
        }
        return subi;
    }

    public void appliquerEffets(Hero hero) {
        if (hero.getEffets().isEmpty()) {
            return;
        }
        for (Effet effet : hero.getEffets()) {
            ihm.afficherMessage(effet.getDetails());
        }
        hero.decrementerEffets();
    }
}
