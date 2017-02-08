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
    private int nombreExemplaire;

}
