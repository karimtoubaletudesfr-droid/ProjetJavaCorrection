package modele.combat.passifs;

import modele.combat.EventContext;
import modele.combat.EventType;
import modele.combat.PassiveSkill;
import modele.personnages.Personnage;
import vue.Ihm;

public class Regeneration implements PassiveSkill {
    private final int soin;

    public Regeneration(int soin) { this.soin = soin; }

    @Override
    public void onEvent(EventType type, Personnage self, Personnage autre, EventContext ctx, Ihm ihm) {
        if (type == EventType.DEBUT_TOUR) {
            self.soigner(soin);
            if (ihm != null) ihm.afficherMessage(self.getNom() + " recupere " + soin + " PV grace a Regeneration.");
        }
    }

    @Override
    public String getNom() { return "Regeneration"; }
}
