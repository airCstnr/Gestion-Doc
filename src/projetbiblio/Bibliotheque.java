
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
    private HashMap<String, Oeuvre> _dicoOeuvres;

    private int numLecteur;

    // -----------------------------------------------
    //Constructeur
    // -----------------------------------------------
    public Bibliotheque() {
        this.setLecteurs(new HashMap<Integer, Lecteur>());
        this._dicoOeuvres = new HashMap<>();
        this.numLecteur = 0;

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
        Integer numLecteur = dernierLecteur();

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

        Lecteur L = new Lecteur(nom, prenom, numLecteur, dateNaiss, adresse, tel, age);
        lierLecteur(L, numLecteur);
        System.out.println("N° de Lecteur attribué : " + numLecteur);

    }

    /*
    * La méthode consulterLecteur permet d'afficher l'ensemble des informations relatives à
    * un lecteur, par la saisie de son identifiant (numéro de lecteur).
    * Si le numéro de lecteur n'est pas dans la base de données de bibliotheque un message d'erreur est
    * renvoyé a l'utilisateur.
     */
    public int dernierLecteur() {
        numLecteur++;
        return numLecteur;
    }

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
     * La méthode consulterListeLecteurs permet d'afficher la liste de tous les
     * lecteurs de la base.
     */
    public void consulterListeLecteurs() {
        EntreesSorties.afficherTitre("Liste des lecteurs : ");
        for (Lecteur l : _dicoLecteur.values()) {
            EntreesSorties.afficherMessage("Lecteur : " + l.getNumLecteur());
        }
    }

    public void nouvelExemplaire() {
        String nISBN = EntreesSorties.lireChaine("N° ISBN : ");
        Oeuvre o = getOeuvre(nISBN);
        if (o != null) {
            GregorianCalendar dateR = EntreesSorties.lireDate("Date de Récéption : ");
            o.ajoutExemplaire(dateR, true);
            EntreesSorties.afficherMessage("La création de l'exemplaire est réussie.");
        }
    }

    public Oeuvre getOeuvre(String isbn) {
        return _dicoOeuvres.get(isbn);
    }

    public void nouvelOuvrage() {
        String nISBN = EntreesSorties.lireChaine("N° ISBN : ");
        Oeuvre o = getOeuvre(nISBN);
        if (o == null) {
            String titre = EntreesSorties.lireChaine("Titre : ");
            String auteur = EntreesSorties.lireChaine("Auteur : ");
            String editeur = EntreesSorties.lireChaine("Editeur : ");
            GregorianCalendar dateP = EntreesSorties.lireDate("Date de Parution : ");
            int s_pub = EntreesSorties.lireEntier("Public (0 enfant, 1 ado, 2 adulte) : ");
            EnumPublic pub;
            switch (s_pub) {
                case 0:
                    // enfant
                    pub = EnumPublic.ENFANT;
                    break;
                case 1:
                    // ado
                    pub = EnumPublic.ADOLESCENT;
                    break;
                case 2:
                    // adulte
                    pub = EnumPublic.ADULTE;
                    break;
                default:
                    // erreur
                    EntreesSorties.afficherMessage("Le public saisi n'est pas correct !!!!! Erreur (dead).");
                    return;
            }
            o = new Oeuvre(nISBN, titre, editeur, dateP, auteur, pub);
            lierOeuvre(o, nISBN);
        } else {
            System.out.println("N° existe déjà.");
        }
    }

    /**
     * Affiche la liste des oeuvres enregistrées dans la base.
     */
    public void consulterListeOuvrages() {
        EntreesSorties.afficherTitre("Liste des Oeuvres : ");
        for (Oeuvre o : _dicoOeuvres.values()) {
            EntreesSorties.afficherMessage("Oeuvre : " + o.getTitre() + "\tN° ISBN : " + o.getNumISBN());
        }
    }

    /**
     * Affiche les détails d'un ouvrage
     */
    public void consulterOuvrage() {
        String nISBN = EntreesSorties.lireChaine("N° ISBN : ");
        Oeuvre o = getOeuvre(nISBN);
        if (o != null) {
            o.afficherReduit();
            o.afficherDetails();
        } else {
            EntreesSorties.afficherMessage("L'oeuvre demandée n'existe pas.");
        }
    }

    /**
     * Affiche les détails des exemplaires d'un ouvrage
     */
    public void consulterExemplaireOuvrage() {
        String nISBN = EntreesSorties.lireChaine("N° ISBN : ");
        Oeuvre o = getOeuvre(nISBN);
        if (o != null) {
            o.afficherReduit();
            o.afficherExemplaire();
        } else {
            EntreesSorties.afficherMessage("L'oeuvre demandée n'existe pas.");
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

    public void lierOeuvre(Oeuvre o, String numISBN) {
        this._dicoOeuvres.put(numISBN, o);
    }

    /*
	 * La méthode lesLecteurs permet de créer un iterator sur les lecteurs, dans le but de les parcourir
	 * pour eventuellement les relancer.
     */
    private Iterator<Lecteur> lesLecteurs() {
        return _dicoLecteur.values().iterator();
    }

}
