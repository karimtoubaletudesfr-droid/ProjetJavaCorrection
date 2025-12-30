package modele.combat.passifs;

import modele.combat.EventContext;
import modele.combat.EventType;
import modele.combat.PassiveSkill;
import modele.combat.Effet;
import modele.personnages.Personnage;
import vue.Ihm;

public class Adrenaline implements PassiveSkill {
    private final int bonusForce;

    public Adrenaline(int bonusForce) { this.bonusForce = bonusForce; }

    @Override
    public void onEvent(EventType type, Personnage self, Personnage autre, EventContext ctx, Ihm ihm) {
        if (type == EventType.ATTAQUE_EFFECTUEE) {
            self.appliquerEffet(new Effet("Adrenaline", bonusForce, 0, 0, 0, 1));
            if (ihm != null) ihm.afficherMessage(self.getNom() + " entre en Adrenaline (+" + bonusForce + " force pour 1 tour).");
        }
    }

    @Override
    public String getNom() { return "Adrenaline"; }
}
