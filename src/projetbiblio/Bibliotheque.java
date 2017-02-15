
import projetbiblio.EntreesSorties;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

// Classe de gestion de la Bibliotheque
public class Bibliotheque implements Serializable {

    private static final long serialVersionUID = 262L;

    // -----------------------------------------------
    //Attributs
    // -----------------------------------------------
    /*
    * Le dictionnaire de lecteur permet à bibliotheque de 
    * garantir l'unicité de ces derniers, et facilitent les recherches et créations.
     */
    private HashMap<Integer, Lecteur> _dicoLecteur;
    
    
    
    
    
    // -----------------------------------------------
    //Constructeur
    // -----------------------------------------------
    public Bibliotheque() {
        this.setLecteurs(new HashMap<Integer, Lecteur>());

    }

// -----------------------------------------------
    // Public
// -----------------------------------------------	
    // -----------------------------------------------
    // Méthodes
    // -----------------------------------------------
    
    /*
    * La méthode nouveauLecteur permet de créé un lecteur en demandant la saisie de son numéro
    * nom, prénom, date de naissance, adresse et numéro de téléphone.
    * L'age doit être compris entre 3 et 110 ans
    * Le lecteur est identifié par son numéro, si celui ci existe déjà dans le dictionnaire
    * de bibliothèque, un message d'erreur est affiché.
    * Une fois le nouveau lecteur créé, il est ajouté au dictionnaire de lecteur
    * afin de garantir la cohérence des données.
     */
    public void nouveauLecteur() {
        Integer numLecteur = EntreesSorties.lireEntier("Entrez le numero de lecteur :");

        Lecteur L = getLecteur(numLecteur);

        if (L == null) {
            String nom = EntreesSorties.lireChaine("Entrez le nom :");
            String prenom = EntreesSorties.lireChaine("Entrez le prenom :");
            Integer age;
            GregorianCalendar dateNaiss, dateNaissComp;
            GregorianCalendar dateActuelle = new GregorianCalendar();
            do {
                dateNaiss = EntreesSorties.lireDate("Entrez la date de naissance du lecteur :");
                dateNaissComp = new GregorianCalendar(dateActuelle.get(GregorianCalendar.YEAR), dateNaiss.get(GregorianCalendar.MONTH), dateNaiss.get(GregorianCalendar.DATE));
                if (dateNaissComp.before(dateActuelle)) {
                    age = dateActuelle.get(GregorianCalendar.YEAR) - dateNaiss.get(GregorianCalendar.YEAR);
                } else {
                    age = dateActuelle.get(GregorianCalendar.YEAR) - dateNaiss.get(GregorianCalendar.YEAR) - 1;
                }
                if ((age <= 3) | (age >= 110)) {
                    EntreesSorties.afficherMessage("Age incorrecte (" + age + "), veuillez recommencer.");
                } else {
                    EntreesSorties.afficherMessage("Age du lecteur : " + age + " ans");
                }
            } while ((age <= 3) | (age >= 110));
            String adresse = EntreesSorties.lireChaine("Entrez l'adresse :");
            String tel = EntreesSorties.lireChaine("Entrez le numero de telephone :");
            EntreesSorties.afficherMessage("Fin de saisie");

            L = new Lecteur(nom, prenom, numLecteur, dateNaiss, adresse, tel);
            lierLecteur(L, numLecteur);
        } else {
            EntreesSorties.afficherMessage("Ce numero de lecteur existe deja.");
        }

    }

    /*
    * La méthode consulterLecteur permet d'afficher l'ensemble des informations relatives à
    * un lecteur, par la saisie de son identifiant (numéro de lecteur).
    * Si le numéro de lecteur n'est pas dans la base de données de bibliotheque un message d'erreur est
    * renvoyé a l'utilisateur.
    */
    public void consulterLecteur() {
        Integer numLecteur = EntreesSorties.lireEntier("Entrez le numero du lecteur : ");

        Lecteur L = getLecteur(numLecteur);

        if (L != null) {
            L.afficherLecteur();
        } else {
            EntreesSorties.afficherMessage("Aucun lecteur n'est associe a ce numero.");
        }
    }
    
    /**
     * La méthode consulterListeLecteurs permet d'afficher la liste de tous les lecteurs de la base.
     */
    public void consulterListeLecteurs() {
        EntreesSorties.afficherTitre("Liste des lecteurs : ");
        for (Lecteur l : _dicoLecteur.values()) {
            EntreesSorties.afficherMessage("Lecteur : " + l.getNumLecteur());
        }
    }

// -----------------------------------------------
    // Private
// -----------------------------------------------
    // -----------------------------------------------
    // Setters
    // -----------------------------------------------
    private void setLecteurs(HashMap<Integer, Lecteur> dicoLecteur) {
        _dicoLecteur = dicoLecteur;
    }

    // -----------------------------------------------
    // Méthodes
    // -----------------------------------------------
    /*
	 * La méthode getLecteur permet de rechercher dans la base de donnée de bibliotheque un objet 
	 * lecteur identifié par son numéro, et de renvoyer l'objet. (ou la donnée null s'il n'est pas trouvé)
     */
    private Lecteur getLecteur(Integer numLecteur) {
        return _dicoLecteur.get(numLecteur);
    }

    /*
	 * La méthode lierLecteur permet d'ajouter un lecteur a la base de donnée de bibliotheque.
     */
    private void lierLecteur(Lecteur L, Integer numLecteur) {
        _dicoLecteur.put(numLecteur, L);
    }

    /*
	 * La méthode lesLecteurs permet de créer un iterator sur les lecteurs, dans le but de les parcourir
	 * pour eventuellement les relancer.
     */
    private Iterator<Lecteur> lesLecteurs() {
        return _dicoLecteur.values().iterator();
    }

}
