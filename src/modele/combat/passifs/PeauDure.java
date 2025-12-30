package modele.combat.passifs;

import modele.combat.EventContext;
import modele.combat.EventType;
import modele.combat.PassiveSkill;
import modele.personnages.Personnage;
import vue.Ihm;

public class PeauDure implements PassiveSkill {
    private final int reduction;

    public PeauDure(int reduction) { this.reduction = reduction; }

    @Override
    public void onEvent(EventType type, Personnage self, Personnage autre, EventContext ctx, Ihm ihm) {
        if (type == EventType.ATTAQUE_SUBIE && ctx != null) {
            ctx.reduireDegats(reduction);
            if (ihm != null) ihm.afficherMessage(self.getNom() + " subit moins de degats grace a Peau Dure (-" + reduction + ").");
        }
    }

    @Override
    public String getNom() { return "Peau Dure"; }
}
