package modele.donjon;

import java.util.List;

public class Donjon {
    private final List<Salle> salles;
    private int index = 0;

    public Donjon(List<Salle> salles) {
        this.salles = salles;
    }

    public boolean aEncoreDesSalles() {
        return index < salles.size();
    }

    public Salle salleCourante() {
        return salles.get(index);
    }

    public void passerSalleSuivante() {
        index++;
    }
}
