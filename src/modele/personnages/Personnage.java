package modele.personnages;

import java.util.ArrayList;
import java.util.List;

import modele.combat.AttaqueStrategy;
import modele.combat.Effet;
import modele.combat.EventContext;
import modele.combat.EventType;
import modele.combat.PassiveSkill;
import vue.Ihm;

public abstract class Personnage {
    protected String nom;
    protected int pvMax;
    protected int pv;
    protected int force;
    protected int dexterite;
    protected int constitution;
    protected int intelligence;
    protected AttaqueStrategy attaqueStrategy;
    protected List<Effet> effets = new ArrayList<>();
    protected List<PassiveSkill> passifs = new ArrayList<>();

    public boolean estVivant() { return pv > 0; }

    public void recevoirDegats(int degats) { this.pv = Math.max(0, pv - degats); }

    public void soigner(int points) { this.pv = Math.min(pvMax, pv + points); }

    public void appliquerEffet(Effet effet) { effets.add(effet); }

    public void decrementerEffets() { effets.removeIf(e -> !e.decrementer()); }

    public void ajouterPassif(PassiveSkill passif) { passifs.add(passif); }

    public void notifierPassifs(EventType type, Personnage autre, EventContext ctx, Ihm ihm) {
        for (PassiveSkill p : passifs) {
            p.onEvent(type, this, autre, ctx, ihm);
        }
        if (ctx != null && ctx.getSoin() > 0) {
            soigner(ctx.getSoin());
        }
    }

    public String getNom() { return nom; }
    public int getPv() { return pv; }
    public int getPvMax() { return pvMax; }
    public int getForce() { return force + bonusForce(); }
    public int getDexterite() { return dexterite + bonusDex(); }
    public int getConstitution() { return constitution + bonusCons(); }
    public int getIntelligence() { return intelligence + bonusIntel(); }
    public AttaqueStrategy getAttaqueStrategy() { return attaqueStrategy; }
    public List<Effet> getEffets() { return effets; }
    public List<PassiveSkill> getPassifs() { return passifs; }

    protected void setAttaqueStrategy(AttaqueStrategy strat) { this.attaqueStrategy = strat; }

    private int bonusForce() { int b = 0; for (Effet e : effets) { b += e.getBonusForce(); } return b; }
    private int bonusDex() { int b = 0; for (Effet e : effets) { b += e.getBonusDex(); } return b; }
    private int bonusCons() { int b = 0; for (Effet e : effets) { b += e.getBonusCons(); } return b; }
    private int bonusIntel() { int b = 0; for (Effet e : effets) { b += e.getBonusIntel(); } return b; }
}
