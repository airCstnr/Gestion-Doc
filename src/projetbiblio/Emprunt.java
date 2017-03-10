
import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Classe Emprunt de bibliothèque.
 * 
 * Association entre un lecteur et un exemplaire.
 * 
 * @author raphael
 */
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
    /**
     * Constructeur de Emprunt.
     * 
     * Nécessite de connaitre le lecteur et l'exemplaire.
     * 
     * @param dateEmprunt
     * @param lecteur
     * @param exemplaire 
     */
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
    /**
     * Renvoie le lecteur qui a emprunté l'exemplaire.
     * 
     * @return lecteur
     */
    public Lecteur getLecteur() {
        return lecteur;
    }

    /**
     * Renvoie l'exemplaire emprunté par le lecteur.
     * 
     * @return exemplaire
     */
    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    /**
     * Renvoie la date d'emprunt.
     *
     * @return dateEmprunt
     */
    public GregorianCalendar getDateEmprunt() {
        return dateEmprunt;
    }

    /**
     * Renvoie la date de retour attendue.
     *
     * @return dateRetour
     */
    public GregorianCalendar getDateRetour() {
        GregorianCalendar dateRetour = (GregorianCalendar) dateEmprunt.clone();
        dateRetour.add(GregorianCalendar.DAY_OF_MONTH, 8);
        return dateRetour;
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
        this.getLecteur().deleteEmprunt(this);
        this.getExemplaire().setEmprunt(null);
        this.setExemplaire(null);
        this.setLecteur(null);
    }

    /**
     * Affiche les détails du lecteur, de l'exemplaire et de l'emprunt
     *
     * @return 
     */
    @Override
    public String toString() {
        return lecteur.getNumLecteur().toString() + "\t " + lecteur.getNomComplet() + "\t"
                + exemplaire.getOeuvre().getNumISBN() + "\t" + exemplaire.getOeuvre().getTitre() + "\t" + exemplaire.getNumExemplaire() + "\t"
                + EntreesSorties.ecrireDate(dateEmprunt) + "\t" + EntreesSorties.ecrireDate(getDateRetour());
    }
    
    
    /**
     * Affiche les détails de l'emprunt dans une mise en page particulière.
     */
    public void afficherDetails() {
        
        EntreesSorties.afficherMessage("--- Lecteur : " + lecteur.getNomComplet());
        EntreesSorties.afficherMessage("Num Lecteur : " + lecteur.getNumLecteur().toString() );
        EntreesSorties.afficherMessage("--- N° ISBN : " + exemplaire.getOeuvre().getNumISBN());
        EntreesSorties.afficherMessage("Titre :" + exemplaire.getOeuvre().getTitre() );
        EntreesSorties.afficherMessage("Numero d'exemplaire : " + exemplaire.getNumExemplaire() );
        EntreesSorties.afficherMessage("-- Dates --");
        EntreesSorties.afficherMessage("Date d'emprunt : " + EntreesSorties.ecrireDate(dateEmprunt) );
        EntreesSorties.afficherMessage("Date de retour : " + EntreesSorties.ecrireDate(getDateRetour()) );
        EntreesSorties.afficherMessage("-------------------------");
    }
    
    
    /**
     * Affiche les détails minimaux de l'emprunt mis en page (lecteur, dates)
     */
    public void afficherDetailsPourOeuvre() {
        EntreesSorties.afficherMessage("--- Emprunté par le lecteur numéro : " + lecteur.getNumLecteur().toString());
        EntreesSorties.afficherMessage("Date d'emprunt : " + EntreesSorties.ecrireDate(dateEmprunt) );
        EntreesSorties.afficherMessage("Date de retour : " + EntreesSorties.ecrireDate(getDateRetour()) );        
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
