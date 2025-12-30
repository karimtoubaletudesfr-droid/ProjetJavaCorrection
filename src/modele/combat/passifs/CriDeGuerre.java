package modele.combat.passifs;

import modele.combat.EventContext;
import modele.combat.EventType;
import modele.combat.PassiveSkill;
import modele.combat.Effet;
import modele.personnages.Personnage;
import vue.Ihm;

public class CriDeGuerre implements PassiveSkill {
    private boolean applique = false;
    private final int bonusForce;
    private final int bonusCons;

    public CriDeGuerre(int bonusForce, int bonusCons) {
        this.bonusForce = bonusForce;
        this.bonusCons = bonusCons;
    }

    @Override
    public void onEvent(EventType type, Personnage self, Personnage autre, EventContext ctx, Ihm ihm) {
        if (type == EventType.DEBUT_TOUR && !applique) {
            self.appliquerEffet(new Effet("Cri de Guerre", bonusForce, 0, bonusCons, 0, 99));
            applique = true;
            if (ihm != null) ihm.afficherMessage(self.getNom() + " pousse un Cri de Guerre (+" + bonusForce + " force, +" + bonusCons + " constitution). ");
        }
    }

    @Override
    public String getNom() { return "Cri de Guerre"; }
}
