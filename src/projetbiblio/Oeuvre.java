package projetbiblio;

public class Oeuvre {

    // Attributs
    private String numISBN; // {unique}
    private String titre;
    private String nomEditeur;
    private int dateParution; // type date Date (voir type calendrier grégorien)
    private String nomAuteur;

    private enum EnumPublic {
        enfant, adolescent, adulte
    }; // EnumPublic
    
    private int dernierExemplaire;
    private int nombreExemplaires;
    private HashMap<int, Exemplaire> exemplaires;
    
    
   // constructeur
    public Oeuvre(String numISBN, String titre, String nomEditeur, int dateParution, String nomAuteur) {
        this.numISBN = numISBN;
        this.titre = titre;
        this.nomEditeur = nomEditeur;
        this.dateParution = dateParution;
        this.nomAuteur = nomAuteur;
        this.dernierExemplaire = 1; // cet atttribut sera appelé quand on crée un nouvel exemplaire
        this.nombreExemplaires = 0;
        this.exemplaires = new HashMap<int, Exemplaire>();
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

    public int getDateParution() {
        return dateParution;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public int getNombreExemplaires() {
        return nombreExemplaires;
    }
    
    
    /**
     * Affiche le titre et l'ISBN de l'oeuvre
     */
    public void afficherReduit() {
        EntreesSorties.afficherMessage("--- Titre : "+ this.getTitre() + " ---");
        EntreesSorties.afficherMessage("N°ISBN : "+ this.getNumISBN());
    }
    
    public void afficherDetails() {
        EntreesSorties.afficherMessage("--- Auteur : "+ this.getNomAuteur() + " ---");
        EntreesSorties.afficherMessage("--- Editeur : "+ this.getNomEditeur() + " ---");
        EntreesSorties.afficherMessage("Date de Parution : "+ this.getDateParution());
    }
    
    public void afficherExemplaire() {
        for(Exemplaire e : exemplaires){
            e.afficher();
        }
    }

}
