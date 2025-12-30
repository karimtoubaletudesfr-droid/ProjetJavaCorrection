package modele.combat.passifs;

import modele.combat.EventContext;
import modele.combat.EventType;
import modele.combat.PassiveSkill;
import modele.personnages.Personnage;
import vue.Ihm;

public class ContreAttaque implements PassiveSkill {
    private final int degats;

    public ContreAttaque(int degats) { this.degats = degats; }

    @Override
    public void onEvent(EventType type, Personnage self, Personnage autre, EventContext ctx, Ihm ihm) {
        if (type == EventType.ATTAQUE_SUBIE && ctx != null && autre != null && autre.estVivant()) {
            ctx.setRiposteDegats(degats);
            if (ihm != null) ihm.afficherMessage(self.getNom() + " riposte avec Contre-Attaque (" + degats + " degats). ");
        }
    }

    @Override
    public String getNom() { return "Contre-Attaque"; }
}
