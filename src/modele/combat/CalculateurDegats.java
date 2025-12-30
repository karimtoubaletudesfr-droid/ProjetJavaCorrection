package modele.combat;

import modele.utils.De;

public final class CalculateurDegats {
    private CalculateurDegats() { }

    public static int lancerD6() { return De.d6(); }

    public static int degatsPhysiques(int forceAtt, int bonusArme, int constitutionDef) {
        return Math.max(1, (forceAtt + bonusArme) - (constitutionDef / 2));
    }

    public static int degatsMagiques(int intelAtt, int bonusSort, int constitutionDef) {
        return Math.max(1, (intelAtt + bonusSort) - (constitutionDef / 3));
    }

    public static int reductionDegats(int dex, int cons) {
        return (dex + cons) / 4;
    }
}
