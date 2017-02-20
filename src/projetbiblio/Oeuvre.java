
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;

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
        this.exemplaires = new HashMap<Integer, Exemplaire>();
    }

// -----------------------------------------------
// Public
// -----------------------------------------------
    // -----------------------------------------------
    // Getters
    // -----------------------------------------------
    public String getNumISBN() {
        return numISBN;
    }

    public String getTitre() {
        return titre;
    }

    public String getNomEditeur() {
        return nomEditeur;
    }

    public GregorianCalendar getDateParution() {
        return dateParution;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public int getNombreExemplaires() {
        return nombreExemplaires;
    }

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
        EntreesSorties.afficherMessage("Date de Parution : ");
        String date = EntreesSorties.ecrireDate(this.getDateParution());
        System.out.println(date);
    }

    /**
     * Affiche pour chaque exemplaires son numéro d'exemplaire
     */
    public void afficherExemplaire() {
        for (Exemplaire e : exemplaires.values()) {
            e.afficherNumExemplaire();
        }
    }

}
