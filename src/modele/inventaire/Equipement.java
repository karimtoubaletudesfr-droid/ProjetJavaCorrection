package modele.inventaire;

public class Equipement {
    private Arme arme;
    private Armure armure;
    private Casque casque;
    private Chaussures chaussures;

    public Equipable equiper(Equipable item) {
        Equipable ancien = null;
        if (item instanceof Arme) {
            ancien = arme;
            arme = (Arme) item;
            return ancien;
        }
        if (item instanceof Armure) {
            ancien = armure;
            armure = (Armure) item;
            return ancien;
        }
        if (item instanceof Casque) {
            ancien = casque;
            casque = (Casque) item;
            return ancien;
        }
        if (item instanceof Chaussures) {
            ancien = chaussures;
            chaussures = (Chaussures) item;
            return ancien;
        }
        return null;
    }

    public int getBonusForce() {
        int b = 0;
        if (armure != null) b += armure.getBonusForce();
        return b;
    }

    public int getBonusDex() {
        int b = 0;
        if (casque != null) b += casque.getBonusDex();
        if (chaussures != null) b += chaussures.getBonusDex();
        return b;
    }

    public int getBonusCons() {
        int b = 0;
        if (armure != null) b += armure.getBonusPv();
        if (casque != null) b += casque.getBonusPv();
        return b;
    }

    public Arme getArme() { return arme; }
    public Armure getArmure() { return armure; }
    public Casque getCasque() { return casque; }
    public Chaussures getChaussures() { return chaussures; }

    public Equipable desequiperArme() {
        Equipable ancien = arme;
        arme = null;
        return ancien;
    }

    public Equipable desequiperArmure() {
        Equipable ancien = armure;
        armure = null;
        return ancien;
    }

    public Equipable desequiperCasque() {
        Equipable ancien = casque;
        casque = null;
        return ancien;
    }

    public Equipable desequiperChaussures() {
        Equipable ancien = chaussures;
        chaussures = null;
        return ancien;
    }
}
