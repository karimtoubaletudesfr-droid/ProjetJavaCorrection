package modele.theme;

import modele.inventaire.Consommable;
import modele.inventaire.Equipable;
import modele.inventaire.Item;
import modele.personnages.Pnj;

public interface ThemeFactory {
    Pnj creerPnj(TypePnj type);
    Consommable creerConsommableAleatoire();
    Equipable creerEquipableAleatoire();
    Pnj creerBoss();
    Item creerItemLegendaire();
}
