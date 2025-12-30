package modele.inventaire;

import modele.classe.ClasseHero;
import modele.combat.PassiveSkill;
import java.util.List;

public class ItemLegendaire extends Item {
    private final ClasseHero classeOctroyee;
    private final List<PassiveSkill> passifs;

    public ItemLegendaire(String nom, ClasseHero classeOctroyee, List<PassiveSkill> passifs) {
        super(nom);
        this.classeOctroyee = classeOctroyee;
        this.passifs = passifs;
    }

    public ClasseHero getClasseOctroyee() { return classeOctroyee; }
    public List<PassiveSkill> getPassifs() { return passifs; }
}
