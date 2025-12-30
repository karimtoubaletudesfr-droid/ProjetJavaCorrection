package modele.combat;

import modele.personnages.Personnage;
import vue.Ihm;

public interface PassiveSkill {
    void onEvent(EventType type, Personnage self, Personnage autre, EventContext ctx, Ihm ihm);
    String getNom();
}
