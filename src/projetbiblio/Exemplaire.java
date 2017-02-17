import java.io.Serializable;
import java.util.GregorianCalendar;

public class Exemplaire implements Serializable{

    private int numExemplaire;
    private GregorianCalendar dateReception;
    private boolean empruntable;
    private Oeuvre o;

    public Exemplaire(int numExemplaire, GregorianCalendar dateReception, boolean empruntable, Oeuvre o) {
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
