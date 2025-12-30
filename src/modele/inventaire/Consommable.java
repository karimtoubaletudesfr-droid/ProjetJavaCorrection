package modele.inventaire;

import modele.personnages.Hero;

public abstract class Consommable extends Item {
    public Consommable(String nom) { super(nom); }
    public abstract void utiliser(Hero hero);
}
