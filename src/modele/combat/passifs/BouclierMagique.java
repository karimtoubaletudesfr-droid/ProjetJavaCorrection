package modele.combat.passifs;

import modele.combat.EventContext;
import modele.combat.EventType;
import modele.combat.PassiveSkill;
import modele.personnages.Personnage;
import vue.Ihm;

public class BouclierMagique implements PassiveSkill {
    private boolean utilise = false;

    @Override
    public void onEvent(EventType type, Personnage self, Personnage autre, EventContext ctx, Ihm ihm) {
        if (type == EventType.ATTAQUE_SUBIE && ctx != null && !utilise) {
            ctx.annulerDegats();
            utilise = true;
            if (ihm != null) ihm.afficherMessage(self.getNom() + " est protege par un Bouclier Magique (degats annules).");
        }
    }

    @Override
    public String getNom() { return "Bouclier Magique"; }
}
