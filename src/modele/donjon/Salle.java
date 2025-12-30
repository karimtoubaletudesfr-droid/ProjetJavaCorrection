package modele.donjon;

import java.util.List;

import modele.inventaire.Item;
import modele.personnages.Pnj;

public class Salle {
    private final List<Pnj> ennemis;
    private final List<Item> itemsAuSol;

    public Salle(List<Pnj> ennemis, List<Item> itemsAuSol) {
        this.ennemis = ennemis;
        this.itemsAuSol = itemsAuSol;
    }

    public List<Pnj> getEnnemis() { return ennemis; }
    public List<Item> getItemsAuSol() { return itemsAuSol; }
}
