
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Classe d'Oeuvre de la Bibliothèque
 *
 * @author castanir
 */
public class Oeuvre implements Serializable {

    // -----------------------------------------------
    // Attributs
    // -----------------------------------------------
    private String numISBN; // {unique}
    private String titre;
    private String nomEditeur;
    private GregorianCalendar dateParution;
    private String nomAuteur;
    private int dernierExemplaire;
    private int nombreExemplaires;
    private EnumPublic pub;
    private HashMap<Integer, Exemplaire> exemplaires;

    // -----------------------------------------------
    // Constructeur
    // -----------------------------------------------
    /**
     * Permet de créer une nouvelle oeuvre de numéro ISBN. Il a un titre, un nom
     * d'éditeur, un nom d'auteur, une date de parution qui doit être antrieure
     * à la date actuelle, un pubic auquel elle est adressée.
     *
     * @param numISBN
     * @param titre
     * @param nomEditeur
     * @param dateParution
     * @param nomAuteur
     * @param pub
     */
    public Oeuvre(String numISBN, String titre, String nomEditeur, GregorianCalendar dateParution, String nomAuteur, EnumPublic pub) {
        this.numISBN = numISBN;
        this.titre = titre;
        this.nomEditeur = nomEditeur;
        this.dateParution = dateParution;
        this.nomAuteur = nomAuteur;
        this.dernierExemplaire = 1; // cet atttribut sera appelé quand on crée un nouvel exemplaire
        this.nombreExemplaires = 0;
        this.pub = pub;
        this.exemplaires = new HashMap<>();
    }

// -----------------------------------------------
// Public
// -----------------------------------------------
    // -----------------------------------------------
    // Getters
    // -----------------------------------------------
    /**
     * Renvoie le numéro ISBN.
     *
     * @return numISBN
     */
    public String getNumISBN() {
        return numISBN;
    }

    /**
     * Renvoie le titre.
     *
     * @return titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Renvoie le nume de l'éditeur.
     *
     * @return numEditeur
     */
    public String getNomEditeur() {
        return nomEditeur;
    }

    /**
     * Renvoie la date de parution.
     *
     * @return dateParution
     */
    public GregorianCalendar getDateParution() {
        return dateParution;
    }

    /**
     * Renvoie le nom de l'auteur
     *
     * @return nomAuteur
     */
    public String getNomAuteur() {
        return nomAuteur;
    }

    /**
     * Renvoie le nombre d'exemplaires présents dans la bibliothèque.
     *
     * @return nombreExemplaires
     */
    public int getNombreExemplaires() {
        return nombreExemplaires;
    }

    /**
     * Renvoie le public qui peut emprunter l'oeuvre
     * 
     * @return public
     */
    public EnumPublic getPub() {
        return pub;
    }

    /**
     * Retourne l'exemplaire de numEx, null sinon.
     *
     * @param numEx
     *
     * @return Exemplaire ou null
     */
    public Exemplaire getExemplaire(int numEx) {
        return exemplaires.get(numEx);
    }

    // -----------------------------------------------
    // Méthodes Publiques
    // -----------------------------------------------
    /**
     * Ajoute un exemplaire e de numéro numExemplaire à l'oeuvre
     *
     * @param e
     * @param numExemplaire
     */
    public void setExemplaire(Exemplaire e, int numExemplaire) {
        exemplaires.put(numExemplaire, e);
    }

    /**
     * Ajoute un exemplaire en appelant le constructeur d'exemplaire
     *
     * @param dateReception
     * @param empruntable
     */
    public void ajoutExemplaire(GregorianCalendar dateReception, boolean empruntable) {
        int numExemplaire = this.dernierExemplaire++;
        Exemplaire e = new Exemplaire(numExemplaire, dateReception, empruntable, this);
        setExemplaire(e, numExemplaire);
    }

    /**
     * Affiche le titre et l'ISBN de l'oeuvre
     */
    public void afficherReduit() {
        EntreesSorties.afficherMessage("--- Titre : " + this.getTitre() + " ---");
        EntreesSorties.afficherMessage("N°ISBN : " + this.getNumISBN());
    }

    /**
     * Affiche l'auteur, l'éditeur et la date de parution
     */
    public void afficherDetails() {
        EntreesSorties.afficherMessage("Auteur : " + this.getNomAuteur());
        EntreesSorties.afficherMessage("Editeur : " + this.getNomEditeur());
        EntreesSorties.afficherMessage("Date de Parution : " + EntreesSorties.ecrireDate(this.getDateParution()));
        EntreesSorties.afficherMessage("Public : " + this.getPub());
    }

    /**
     * Affiche pour chaque exemplaires son numéro d'exemplaire
     */
    public void afficherExemplaire() {
        for (Exemplaire e : exemplaires.values()) {
            EntreesSorties.afficherMessage("-------------------------");
            e.afficherNumExemplaire();
            e.afficheDetails();
            EntreesSorties.afficherMessage("-------------------------");
        }
    }

}
