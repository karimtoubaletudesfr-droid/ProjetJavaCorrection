package modele.combat;

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
        return attaquer(hero, cible, hero.getAttaqueStrategy());
    }

    public int attaquer(Hero hero, Pnj cible, AttaqueStrategy strat) {
        int pvAvant = cible.getPv();
        Arme arme = hero.getEquipement().getArme();
        int bonus = (arme != null) ? arme.getBonusDegats() : 0;
        int base = strat.calculerDegats(hero, cible);
        EventContext ctxSubie = new EventContext(base);
        cible.notifierPassifs(EventType.ATTAQUE_SUBIE, hero, ctxSubie, ihm);
        int degatsFinal = ctxSubie.isAnnulerDegats() ? 0 : Math.max(1, ctxSubie.getDegats());
        cible.recevoirDegats(degatsFinal);
        if (ctxSubie.getRiposteDegats() > 0 && hero.estVivant()) {
            hero.recevoirDegats(ctxSubie.getRiposteDegats());
            ihm.afficherMessage(cible.getNom() + " riposte et inflige " + ctxSubie.getRiposteDegats() + " degats.");
        }
        int pvApres = cible.getPv();
        StringBuilder res = new StringBuilder();
        res.append(hero.getNom()).append(" attaque ").append(cible.getNom());
        res.append(" pour ").append(degatsFinal).append(" degats");
        res.append(" (force ").append(hero.getForce()).append(", bonus arme ").append(bonus).append("). ");
        res.append("PV ennemi: ").append(pvAvant).append(" -> ").append(pvApres).append(".");
        ihm.afficherMessage(res.toString());
        if (!cible.estVivant()) {
            ihm.afficherMessage(cible.getNom() + " est vaincu.");
        }
        hero.notifierPassifs(EventType.ATTAQUE_EFFECTUEE, cible, null, ihm);
        return degatsFinal;
    }

    public int attaquer(Pnj pnj, Hero hero) {
        int pvAvant = hero.getPv();
        int base = pnj.getAttaqueStrategy().calculerDegats(pnj, hero);
        int jet = CalculateurDegats.lancerD6();
        int reductionBase = CalculateurDegats.reductionDegats(hero.getDexterite(), hero.getConstitution());
        int reductionTotale = reductionBase + jet;
        int subi = Math.max(1, base - reductionTotale);
        EventContext ctx = new EventContext(subi);
        hero.notifierPassifs(EventType.ATTAQUE_SUBIE, pnj, ctx, ihm);
        int finalDegats = ctx.isAnnulerDegats() ? 0 : Math.max(1, ctx.getDegats());
        hero.recevoirDegats(finalDegats);
        int pvApres = hero.getPv();
        StringBuilder res = new StringBuilder();
        res.append(pnj.getNom()).append(" attaque ").append(hero.getNom());
        res.append(" pour ").append(base).append(" degats bruts.");
        res.append(" Defense (dex+const)/4=").append(reductionBase).append(" + d6=").append(jet);
        res.append(" => degats subis ").append(finalDegats).append(".");
        res.append(" PV heros: ").append(pvAvant).append(" -> ").append(pvApres).append(".");
        ihm.afficherMessage(res.toString());
        if (ctx.getRiposteDegats() > 0 && pnj.estVivant()) {
            pnj.recevoirDegats(ctx.getRiposteDegats());
            ihm.afficherMessage(hero.getNom() + " riposte et inflige " + ctx.getRiposteDegats() + " degats a " + pnj.getNom());
        }
        if (!hero.estVivant()) {
            ihm.afficherMessage(hero.getNom() + " est tombe au combat.");
        }
        return finalDegats;
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
