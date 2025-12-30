package modele.inventaire;

public abstract class Item {
    protected final String nom;

    public Item(String nom) { this.nom = nom; }

    public String getNom() { return nom; }
}
