import java.io.Serializable;
import java.util.GregorianCalendar;

public class Exemplaire implements Serializable {

    // -----------------------------------------------
    //Attributs
    // -----------------------------------------------
    
    private int numExemplaire;
    private GregorianCalendar dateReception;
    private boolean empruntable;
    private Oeuvre o;

    // -----------------------------------------------
    //Constructeur
    // -----------------------------------------------
    
    /**
     * Permet de fabriquer un nouvel exemplaire d'une oeuvre o.
     * Son numéro d'exemplaire est unique pour une oeuvre donnée.
     * On conserva sa date de réception et son caractère empruntable (ou non).
     * @param numExemplaire
     * @param dateReception
     * @param empruntable
     * @param o 
     */
    public Exemplaire(int numExemplaire, GregorianCalendar dateReception, boolean empruntable, Oeuvre o) {
        this.numExemplaire = numExemplaire;
        this.dateReception = dateReception;
        this.empruntable = empruntable;
        this.o = o;
    }

    /**
     * Renvoie le numéro de l'exemplaire.
     * @return numéro d'exemplaire
     */
    public int getNumExemplaire() {
        return numExemplaire;
    }

    /**
     * Affiche le numéro d'exemplaire.
     */
    public void afficher() {
        EntreesSorties.afficherMessage("Num Ex : " + this.getNumExemplaire());
    }

}
