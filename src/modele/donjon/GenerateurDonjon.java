package modele.donjon;

import java.util.ArrayList;
import java.util.List;

import modele.generation.GenerateurObjets;
import modele.generation.GenerateurPnj;

public class GenerateurDonjon {
    private final GenerateurPnj genPnj;
    private final GenerateurObjets genObj;

    public GenerateurDonjon(GenerateurPnj genPnj, GenerateurObjets genObj) {
        this.genPnj = genPnj;
        this.genObj = genObj;
    }

    public Donjon generer(int nbSalles, boolean bossFinal) {
        List<Salle> salles = new ArrayList<>();
        int normales = bossFinal ? nbSalles - 1 : nbSalles;
        for (int i = 0; i < normales; i++) {
            salles.add(new Salle(genPnj.genererPourSalle(), genObj.genererPourSalle()));
        }
        if (bossFinal) {
            List<modele.personnages.Pnj> bossList = new ArrayList<>();
            bossList.add(genPnj.creerBoss());
            salles.add(new Salle(bossList, genObj.genererPourSalle()));
        }
        return new Donjon(salles);
    }
}
