package modele.inventaire;

import java.util.ArrayList;
import java.util.List;

public class Inventaire {
    private final List<Item> items = new ArrayList<>();

    public void ajouter(Item item) { items.add(item); }
    public boolean retirer(Item item) { return items.remove(item); }
    public List<Item> getItems() { return items; }

    public List<Consommable> getConsommables() {
        List<Consommable> res = new ArrayList<>();
        for (Item i : items) {
            if (i instanceof Consommable c) { res.add(c); }
        }
        return res;
    }

    public List<Equipable> getEquipables() {
        List<Equipable> res = new ArrayList<>();
        for (Item i : items) {
            if (i instanceof Equipable e) { res.add(e); }
        }
        return res;
    }
}
