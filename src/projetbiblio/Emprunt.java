import java.io.Serializable;
import java.util.GregorianCalendar;

public class Emprunt implements Serializable {

    // -----------------------------------------------
    // Attributs
    // -----------------------------------------------
    private GregorianCalendar dateEmprunt;
    private Lecteur lecteur;
    private Exemplaire exemplaire;

    // -----------------------------------------------
    // Constructeur
    // -----------------------------------------------
    public Emprunt(GregorianCalendar dateEmprunt, Lecteur lecteur, Exemplaire exemplaire) {
        this.dateEmprunt = dateEmprunt;
        this.lecteur = lecteur;
        this.exemplaire = exemplaire;
    }

// -----------------------------------------------
// Public
// -----------------------------------------------
    // -----------------------------------------------
    // Getters
    // -----------------------------------------------
    public Lecteur getLecteur() {
        return lecteur;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    /**
     * Renvoie la date d'emprunt de l'exemplaire.
     *
     * @return dateEmprunt
     */
    public GregorianCalendar getDateEmprunt() {
        return dateEmprunt;
    }

    /**
     * Renvoie la date de retour attendue de l'exemaplire.
     *
     * ATTENTION : La date retournée est la date d'emprunt
     * ATTENTION : Il faut trouver comment renvoyer une date + 8 jours
     * 
     * @return dateRetour
     */
    public GregorianCalendar getDateRetour() {
        return dateEmprunt;
    }

    // -----------------------------------------------
    // Methodes
    // -----------------------------------------------
    /**
     * Permet du supprimer l'emprunt.
     *
     * Supprime le lien du lecteur vers l'emprunt, le lien de l'exemplaire vers
     * l'emprunt et met à NULL lecteur et exemaplire.
     */
    public void deleteEmprunt() {
        System.out.println("Pas encore développé!");
    }

// -----------------------------------------------
// Private
// -----------------------------------------------
    // -----------------------------------------------
    //Setters
    // -----------------------------------------------
    private void setLecteur(Lecteur lecteur) {
        this.lecteur = lecteur;
    }

    private void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

}
