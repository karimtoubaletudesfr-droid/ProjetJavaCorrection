package modele.personnages;

import modele.classe.ClasseHero;
import modele.inventaire.Equipable;
import modele.inventaire.Equipement;
import modele.inventaire.Inventaire;

public class Hero extends Personnage {
    private final Inventaire inventaire = new Inventaire();
    private final Equipement equipement = new Equipement();
    private final ClasseHero classe;

    public Hero(String nom, ClasseHero classe) {
        this.nom = nom;
        this.classe = classe;
        this.pvMax = this.pv = classe.getPv();
        this.force = classe.getForce();
        this.dexterite = classe.getDexterite();
        this.constitution = classe.getConstitution();
        this.intelligence = classe.getIntelligence();
        this.attaqueStrategy = classe.getAttaqueStrategy();
        Equipable equipInit = classe.getEquipementInitial();
        if (equipInit != null) {
            equipement.equiper(equipInit);
        }
        if (classe.getItemsInitial() != null) {
            for (modele.inventaire.Item item : classe.getItemsInitial()) {
                inventaire.ajouter(item);
            }
        }
    }

    @Override
    public int getForce() {
        return super.getForce() + equipement.getBonusForce();
    }

    @Override
    public int getDexterite() {
        return super.getDexterite() + equipement.getBonusDex();
    }

    @Override
    public int getConstitution() {
        return super.getConstitution() + equipement.getBonusCons();
    }

    @Override
    public int getPvMax() {
        return super.getPvMax() + equipement.getBonusCons();
    }

    public Inventaire getInventaire() { return inventaire; }
    public Equipement getEquipement() { return equipement; }
    public ClasseHero getClasse() { return classe; }
}
