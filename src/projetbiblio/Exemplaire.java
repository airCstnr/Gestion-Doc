package projetbiblio;

public class Exemplaire {

    private int numExemplaire;
    private date dateReception;
    private boolean empruntable;
    private Oeuvre o;

    public Exemplaire(int numExemplaire, date dateReception, boolean empruntable, Oeuvre o) {
        this.numExemplaire = numExemplaire;
        this.dateReception = dateReception;
        this.empruntable = empruntable;
        this.o = o;
    }

    public int getNumExemplaire() {
        return numExemplaire;
    }

    /**
     * Affiche le numéro d'exemplaire
     */
    public void afficher() {
        EntreesSorties.afficherMessage("Num Ex : " + this.getNumExemplaire());
    }

}
