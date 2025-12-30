package modele.combat;

public class EventContext {
    private int degats; // degats en cours
    private boolean annulerDegats;
    private int riposteDegats;
    private int soin;

    public EventContext(int degats) {
        this.degats = degats;
    }

    public int getDegats() { return degats; }
    public void setDegats(int degats) { this.degats = degats; }
    public void reduireDegats(int delta) { this.degats = Math.max(0, this.degats - delta); }

    public boolean isAnnulerDegats() { return annulerDegats; }
    public void annulerDegats() { this.annulerDegats = true; }

    public int getRiposteDegats() { return riposteDegats; }
    public void setRiposteDegats(int riposteDegats) { this.riposteDegats = riposteDegats; }

    public int getSoin() { return soin; }
    public void ajouterSoin(int soin) { this.soin += soin; }
}
