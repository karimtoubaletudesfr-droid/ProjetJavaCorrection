package modele.personnages;

import modele.combat.AttaqueStrategy;
import modele.theme.TypePnj;

public class Pnj extends Personnage {
    private final TypePnj type;

    public Pnj(TypePnj type, AttaqueStrategy strat, int pv, int force, int dex, int cons, int intel) {
        this.type = type;
        this.attaqueStrategy = strat;
        this.pvMax = this.pv = pv;
        this.force = force;
        this.dexterite = dex;
        this.constitution = cons;
        this.intelligence = intel;
        this.nom = type.name();
    }

    public TypePnj getType() { return type; }
}
