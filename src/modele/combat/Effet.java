package modele.combat;

public class Effet {
    private final String nom;
    private final int bonusForce;
    private final int bonusDex;
    private final int bonusCons;
    private final int bonusIntel;
    private int toursRestants;

    public Effet(String nom, int bonusForce, int bonusDex, int bonusCons, int bonusIntel, int duree) {
        this.nom = nom;
        this.bonusForce = bonusForce;
        this.bonusDex = bonusDex;
        this.bonusCons = bonusCons;
        this.bonusIntel = bonusIntel;
        this.toursRestants = duree;
    }

    public boolean decrementer() {
        return --toursRestants > 0;
    }

    public String getNom() { return nom; }
    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        if (bonusForce != 0) sb.append("Bonus force: ").append(bonusForce).append("\n");
        if (bonusCons != 0) sb.append("Bonus constitution: ").append(bonusCons).append("\n");
        if (bonusDex != 0) sb.append("Bonus dexterite: ").append(bonusDex).append("\n");
        if (bonusIntel != 0) sb.append("Bonus intelligence: ").append(bonusIntel).append("\n");
        if (sb.length() == 0) return "Pas de bonus.";
        sb.append("Tours restants: ").append(toursRestants);
        return sb.toString();
    }
    public int getBonusForce() { return bonusForce; }
    public int getBonusDex() { return bonusDex; }
    public int getBonusCons() { return bonusCons; }
    public int getBonusIntel() { return bonusIntel; }
    public int getToursRestants() { return toursRestants; }
}
