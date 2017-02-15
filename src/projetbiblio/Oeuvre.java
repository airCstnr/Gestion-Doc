import java.util.GregorianCalendar;
import java.util.HashMap;

public class Oeuvre {
    
    // Attributs
    private String numISBN; // {unique}
    private String titre;
    private String nomEditeur;
    private GregorianCalendar dateParution;
    private String nomAuteur;
    private int dernierExemplaire;
    private int nombreExemplaires;
    private EnumPublic pub;
    private HashMap<Integer, Exemplaire> exemplaires;

    // constructeur
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
    
    // méthodes publiques
    public void setExemplaire(Exemplaire e, int numExemplaire){
        exemplaires.put(numExemplaire, e);
    }

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
    
    /**
     * Ajoute un exemplaire en appelant le constructeur d'exemplaire
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
        EntreesSorties.afficherMessage("Date de Parution : " + this.getDateParution());
    }

    /**
     * Affiche pour chaque exemplaires son N°
     */
    public void afficherExemplaire() {
        for (Exemplaire e : exemplaires.values()) {
            e.afficher();
        }
    }

}
