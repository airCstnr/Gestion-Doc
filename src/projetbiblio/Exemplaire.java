
import java.io.Serializable;
import java.util.GregorianCalendar;

public class Exemplaire implements Serializable {

    // -----------------------------------------------
    //Attributs
    // -----------------------------------------------
    private int numExemplaire;
    private GregorianCalendar dateReception;
    private boolean empruntable;
    private Oeuvre oeuvre;
    private Emprunt emprunt;

    // -----------------------------------------------
    //Constructeur
    // -----------------------------------------------
    /**
     * Permet de fabriquer un nouvel exemplaire d'une oeuvre o. Son numéro
     * d'exemplaire est unique pour une oeuvre donnée. On conserva sa date de
     * réception et son caractère empruntable (ou non). On initialise son
     * emprunt à null.
     *
     * @param numExemplaire
     * @param dateReception
     * @param empruntable
     * @param o
     */
    public Exemplaire(int numExemplaire, GregorianCalendar dateReception, boolean empruntable, Oeuvre o) {
        this.numExemplaire = numExemplaire;
        this.dateReception = dateReception;
        this.empruntable = empruntable;
        this.oeuvre = o;
        this.emprunt = null; // il n'est pas emprunté lors de sa création
    }

// -----------------------------------------------
// Public
// -----------------------------------------------
    // -----------------------------------------------
    // Getters
    // -----------------------------------------------
    /**
     * Renvoie le numéro de l'exemplaire.
     *
     * @return numéro d'exemplaire
     */
    public int getNumExemplaire() {
        return numExemplaire;
    }

    /**
     * Renvoie l'emprunt de l'exemplaire s'il est emprunté, null sinon.
     *
     * @return emprunt
     */
    public Emprunt getEmprunt() {
        return emprunt;
    }
    
    /**
     * Renvoie true si l'exemplaire est empruntable, false sinon.
     * @return empruntable
     */
    public boolean getEmpruntable() {
        return empruntable;
    }

    // -----------------------------------------------
    // Methodes
    // -----------------------------------------------
    /**
     * Affiche le numéro d'exemplaire.
     */
    public void afficherNumExemplaire() {
        EntreesSorties.afficherMessage("Num Ex : " + this.getNumExemplaire());
    }

    /**
     * Affiche les détails de l'exemplaire.
     *
     * Affiche la date de réception, s'il est empruntable et s'il est emprunté.
     */
    public void afficheDetails() {
        EntreesSorties.afficherMessage("Date de réception : " + dateReception.toString());
        EntreesSorties.afficherMessage("Empruntable : " + (empruntable?"oui":"non"));
        EntreesSorties.afficherMessage("Emprunté : " + (emprunt!=null?"oui":"non"));
    }

    // -----------------------------------------------
    // Setters
    // -----------------------------------------------
    /**
     * Permet de modifier l'emprunt.
     *
     * Donner null pour que l'exemplaire soit disponible.
     *
     * @param emprunt
     */
    public void setEmprunt(Emprunt emprunt) {
        this.emprunt = emprunt;
    }

}
