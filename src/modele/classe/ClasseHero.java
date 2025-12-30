package modele.classe;

import modele.combat.AttaqueStrategy;
import modele.inventaire.Equipable;
import vue.Ihm;
public class ClasseHero {
    private final String nom;
    private final int pv;
    private final int force;
    private final int dexterite;
    private final int constitution;
    private final int intelligence;
    private final AttaqueStrategy attaqueStrategy;
    private final Equipable equipementInitial;
    private final java.util.List<modele.inventaire.Item> itemsInitial;

    public ClasseHero(String classeName, int pv, int force, int dexterite, int constitution, int intelligence, AttaqueStrategy attaqueStrategy, Equipable equipementInitial, java.util.List<modele.inventaire.Item> itemsInitial) {
        this.nom =classeName;
        this.pv = pv;
        this.force = force;
        this.dexterite = dexterite;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.attaqueStrategy = attaqueStrategy;
        this.equipementInitial = equipementInitial;
        this.itemsInitial = itemsInitial;
    }

    public String getNom() { return nom; }
    public int getPv() { return pv; }
    public int getForce() { return force; }
    public int getDexterite() { return dexterite; }
    public int getConstitution() { return constitution; }
    public int getIntelligence() { return intelligence; }
    public AttaqueStrategy getAttaqueStrategy() { return attaqueStrategy; }
    public Equipable getEquipementInitial() { return equipementInitial; }
    public java.util.List<modele.inventaire.Item> getItemsInitial() { return itemsInitial; }
}
