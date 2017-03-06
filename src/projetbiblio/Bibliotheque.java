
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Classe de gestion de la Bibliotheque. Interface de Gestion Documentaire.
 *
 * @author castanir
 */
public class Bibliotheque implements Serializable {

    private static final long serialVersionUID = 262L; // paramètre utile (ou pas) pour la sérialisation

    // -----------------------------------------------
    //Attributs
    // -----------------------------------------------
    /**
     * Le dictionnaire de lecteur permet à bibliotheque de garantir l'unicité de
     * ces derniers, et facilitent les recherches et créations.
     */
    private HashMap<Integer, Lecteur> _dicoLecteur;

    /**
     * Idem pour les oeuvres
     */
    private HashMap<String, Oeuvre> _dicoOeuvres;

    /**
     * Numéro du dernier lecteur enregistré dans la base.
     */
    private int numLecteur;

    // -----------------------------------------------
    //Constructeur
    // -----------------------------------------------
    /**
     * Initialise les dictionnaires de lecteurs et d'oeuvres. Initialise à 0 le
     * numéro du dernier lecteur.
     */
    public Bibliotheque() {
        this.setLecteurs(new HashMap<>());
        this._dicoOeuvres = new HashMap<>();
        this.numLecteur = 0;

    }

// -----------------------------------------------
// Public
// -----------------------------------------------
    // -----------------------------------------------
    // Méthodes
    // -----------------------------------------------
    /**
     * Permet de créer un nouveau lecteur. La méthode nouveauLecteur permet de
     * créé un lecteur en demandant la saisie de son numéro nom, prénom, date de
     * naissance, adresse et numéro de téléphone. L'age doit être compris entre
     * 3 et 110 ans Le lecteur est identifié par son numéro, si celui ci existe
     * déjà dans le dictionnaire de bibliothèque, un message d'erreur est
     * affiché. Une fois le nouveau lecteur créé, il est ajouté au dictionnaire
     * de lecteur afin de garantir la cohérence des données.
     */
    public void nouveauLecteur() {
        EntreesSorties.afficherTitre("-- Nouveau lecteur --");

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
                EntreesSorties.afficherMessage("Age incorrect (" + age + "), veuillez recommencer.");
            } else {
                EntreesSorties.afficherMessage("Age du lecteur : " + age + " ans");
            }
        } while ((age <= 3) | (age >= 110));
        String adresse = EntreesSorties.lireChaine("Entrez l'adresse :");
        String tel = EntreesSorties.lireChaine("Entrez le numero de telephone :");
        EntreesSorties.afficherMessage("Fin de saisie");

        Lecteur L = new Lecteur(nom, prenom, numLecteur, dateNaiss, adresse, tel);
        lierLecteur(L, numLecteur);
        System.out.println("N° de Lecteur attribué : " + numLecteur);
    }

    /**
     * Dernier lecteur incrémente le numéro du dernier lecteur et le renvoie
     *
     * @return numéro du dernier lecteur incrémenté
     */
    public int dernierLecteur() {
        numLecteur++;
        return numLecteur;
    }

    /**
     * Permet d'afficher l'ensemble des informations relatives à un lecteur. La
     * méthode consulterLecteur permet d'afficher l'ensemble des informations
     * relatives à un lecteur, par la saisie de son identifiant (numéro de
     * lecteur). Si le numéro de lecteur n'est pas dans la base de données de
     * bibliotheque un message d'erreur est renvoyé a l'utilisateur.
     */
    public void consulterLecteur() {
        EntreesSorties.afficherTitre("-- Consulter lecteur --");
        Integer numLect = EntreesSorties.lireEntier("Entrez le numero du lecteur : ");
        Lecteur l = getLecteur(numLect);
        if (l != null) {
            l.afficherLecteur();
        } else {
            EntreesSorties.afficherMessage("Aucun lecteur n'est associe a ce numero.");
        }
    }

    /**
     * La méthode consulterListeLecteurs permet d'afficher la liste de tous les
     * lecteurs de la base.
     */
    public void consulterListeLecteurs() {
        EntreesSorties.afficherTitre("-- Liste des lecteurs --");
        for (Lecteur l : _dicoLecteur.values()) {
            EntreesSorties.afficherMessage("Lecteur : " + l.getNumLecteur() + "\tNom : " + l.getNom() + "\tPrénom : " + l.getPrenom());
        }
    }

    /**
     * Permet d'ajouter un exemplaire d'une oeuvre de numéro ISBN et de saisir
     * sa date de réception.
     */
    public void nouvelExemplaire() {
        EntreesSorties.afficherTitre("-- Nouvel exemplaire --");
        String nISBN = EntreesSorties.lireChaine("N° ISBN : ");
        Oeuvre o = getOeuvre(nISBN);
        if (o != null) {
            GregorianCalendar dateR = EntreesSorties.lireDate("Date de Récéption : ");
            boolean empruntabilite = EntreesSorties.lireEntier("Exemplaire Empruntable (0 pour oui / autre pour non) : ") == 0;
            o.ajoutExemplaire(dateR, empruntabilite);
            EntreesSorties.afficherMessage("La création de l'exemplaire est réussie.");
        }
    }

    /**
     * Retourne l'oeuvre de numéro ISBN.
     *
     * @param numISBN
     *
     * @return oeuvre de numISBN
     */
    public Oeuvre getOeuvre(String numISBN) {
        return _dicoOeuvres.get(numISBN);
    }

    /**
     * Permet de créer un nouvel ouvrage. Paramètres : numéro ISBN, titre,
     * auteur, éditeur, date de réception, le public Prérequis : l'oeuvre
     * n'existe pas encore, le public existe
     */
    public void nouvelOuvrage() {
        EntreesSorties.afficherTitre("-- Nouvel ouvrage --");
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
                    EntreesSorties.afficherMessage("Erreur : le public saisi n'est pas correct.");
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
     *
     * Pour chaque oeuvre de la base, consulterListeOuvrages affiche son titre
     * et son numéro ISBN.
     */
    public void consulterListeOuvrages() {
        EntreesSorties.afficherTitre("-- Liste des oeuvres --");
        for (Oeuvre o : _dicoOeuvres.values()) {
            EntreesSorties.afficherMessage("Oeuvre : " + o.getTitre() + "\tN° ISBN : " + o.getNumISBN());
        }
    }

    /**
     * Affiche les détails d'un ouvrage.
     *
     * consulterOuvrage demande son numéro ISBN, puis affiche l'affichage réduit
     * et l'affichage détaillé. Si l'oeuvre n'existe pas, il affiche un message
     * d'erreur.
     */
    public void consulterOuvrage() {
        EntreesSorties.afficherTitre("-- Consulter ouvrage --");
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
     * Affiche les détails des exemplaires d'un ouvrage.
     *
     * consulterExemplaireOuvrage demande le numéro ISBN d'un ouvrage, puis
     * affiche l'affichage réduit de l'oeuvre et les exemplaires de l'ouvrage.
     * Si l'oeuvre n'existe pas, il affiche un message d'erreur.
     */
    public void consulterExemplaireOuvrage() {
        EntreesSorties.afficherTitre("-- Consulter exemplaire --");
        String nISBN = EntreesSorties.lireChaine("N° ISBN : ");
        Oeuvre o = getOeuvre(nISBN);
        if (o != null) {
            //o.afficherReduit();
            o.afficherExemplaire();
        } else {
            EntreesSorties.afficherMessage("L'oeuvre demandée n'existe pas.");
        }
    }

    /**
     * Permet à un lecteur d'emprunter un exemplaire d'une oeuvre.
     *
     * Demande le numero ISBN, le numéro d'exemplaire et le numéro de lecteur.
     * Vérifie si l'oeuvre existe, si l'exemplaire de l'oeuvre existe, si
     * l'exemplaire est empruntable et si il est disponible. Vérifie si le
     * public de l'oeuvre est compatible avec le public du lecteur. Vérifie si
     * le lecteur peut emprunter un nouvel exemplaire.
     *
     * Si tout est vérifié, crée l'emprunt et le signale. Sinon affiche le
     * message d'erreur adéquat et abandonne.
     */
    public void emprunterExemplaire() {
        EntreesSorties.afficherTitre("-- Emprunter exemplaire --");
        String isbn = EntreesSorties.lireChaine("N° ISBN : ");
        Oeuvre oeuvre = _dicoOeuvres.get(isbn);
        if (oeuvre == null) {
            EntreesSorties.afficherMessage("L'oeuvre de n°ISBN " + isbn + " n'existe pas.");
            return;
        }
        int numEx = EntreesSorties.lireEntier("Numéro d'exemplaire : ");
        Exemplaire exemplaire = oeuvre.getExemplaire(numEx);
        if (exemplaire == null) {
            EntreesSorties.afficherMessage("L'exemplaire de numéro " + numEx + " n'existe pas.");
            return;
        }
        int numLect = EntreesSorties.lireEntier("Numéro de lecteur : ");
        Lecteur lecteur = _dicoLecteur.get(numLect);
        if (lecteur == null) {
            EntreesSorties.afficherMessage("Le lecteur de numéro " + numLect + " n'existe pas.");
            return;
        }

        // vérifications
        if (!exemplaire.getEmpruntable()) {
            EntreesSorties.afficherMessage("L'exemplaire n'est pas empruntable.");
            return;
        }
        if (exemplaire.getEmprunt() != null) {
            EntreesSorties.afficherMessage("L'exemplaire n'est pas disponible.");
            return;
        }
        if (!compatibilitePublic(oeuvre, lecteur)) {
            EntreesSorties.afficherMessage("Le public de l'oeuvre n'est pas adapté à l'age du lecteur.");
            return;
        }
        if (lecteur.estSature()) {
            EntreesSorties.afficherMessage("Le lecteur ne peut pas emprunter un nouvel exemplaire.");
            return;
        }

        // création de l'emprunt
        Emprunt emprunt = new Emprunt(new GregorianCalendar(), lecteur, exemplaire);
        lecteur.setEmprunt(emprunt);
        exemplaire.setEmprunt(emprunt);
        EntreesSorties.afficherMessage("Création de l'emprunt réussie. L'exemplaire est emprunté!");
    }

    /**
     * Permet de rendre un exemplaire d'une oeuvre qui a été empruntée par un
     * lecteur.
     *
     * Demande la saisie des détails de l'exemplaire, puis effectue les
     * modifications nécessaires sur l'emprunt, le lecteur et l'exemplaire.
     */
    public void rendreExemplaire() {
        EntreesSorties.afficherTitre("-- Rendre exemplaire --");
        String isbn = EntreesSorties.lireChaine("N° ISBN : ");
        Oeuvre oeuvre = _dicoOeuvres.get(isbn);
        if (oeuvre == null) {
            EntreesSorties.afficherMessage("L'oeuvre de n°ISBN " + isbn + " n'existe pas.");
            return;
        }
        int numEx = EntreesSorties.lireEntier("Numéro d'exemplaire : ");
        Exemplaire exemplaire = oeuvre.getExemplaire(numEx);
        if (exemplaire == null) {
            EntreesSorties.afficherMessage("L'exemplaire de numéro " + numEx + " n'existe pas.");
            return;
        }
        
        Emprunt emprunt = exemplaire.getEmprunt();
        if (emprunt==null) {
            EntreesSorties.afficherMessage("L'exemplaire de numéro " + numEx + " n'est pas emprunté.");
            return;
        }
        emprunt.deleteEmprunt();
        EntreesSorties.afficherMessage("L'exemplaire est rendu!");
    }

    /**
     * Permet d'afficher les emprunts d'un lecteur.
     *
     * Demande le numéro de lecteur puis affiche l'ensemble de ses emprunts.
     * Vérifie si le lecteur existe.
     */
    public void consulterEmpruntsLecteur() {
        EntreesSorties.afficherTitre("-- Consulter emprunts lecteur --");
        int numLect = EntreesSorties.lireEntier("Numéro de lecteur : ");
        Lecteur lecteur = _dicoLecteur.get(numLect);
        if (lecteur == null) {
            EntreesSorties.afficherMessage("Le lecteur de numéro " + numLect + " n'existe pas.");
            return;
        }
        if (lecteur.getNbEmprunts() != 0) {
            for (Emprunt e : lecteur.getEmprunts()) {
                EntreesSorties.afficherMessage(e.toString());
            }
        }
        else
        {
            EntreesSorties.afficherMessage(" Ce lecteur n'a emprunté aucun livre pour le moment ");
        }
    }

    /**
     * Permet d'afficher les lecteurs dont les emprunts ont dépassé la date de
     * retour prévue.
     *
     * Vérifie pour chaque lecteur s'il a des emprunts dont la date de retour
     * dépasse la date courante.
     *
     */
    public void relancerLecteur() {
        EntreesSorties.afficherTitre("-- Relancer lecteur --");
        for (Lecteur l : _dicoLecteur.values()) {
            if (l.getNbEmprunts() != 0) {
                for (Emprunt e : l.getEmprunts()) {
                    GregorianCalendar today = new GregorianCalendar(); // on prend la date d'aujourd'hui
                    today.add(GregorianCalendar.DAY_OF_MONTH, -15); // on lui retire 15 jours
                    if (e.getDateRetour().before(today)) { // on la compare avec la date de retour attendue
                        e.afficherDetails();
                    }
                }
            }            
        }
    }

    /**
     * Permet d'afficher l'ensemble de tous les emprunts des lecteurs.
     *
     * Recherche pour chaque lecteur s'il a des emprunts et les affiche.
     */
    public void consulterListeEmprunts() {
        EntreesSorties.afficherTitre("-- Liste emprunts --");
        for (Lecteur l : _dicoLecteur.values()) {
            if (l.getNbEmprunts() != 0) {
                for (Emprunt e : l.getEmprunts()) {
                    e.afficherDetails();
                }
            }
        }
    }

// -----------------------------------------------
    // Private
// -----------------------------------------------
    // -----------------------------------------------
    // Setters
    // -----------------------------------------------
    /**
     * Le dictionnaire de lecteur devient dicoLecteur.
     *
     * @param dicoLecteur
     */
    private void setLecteurs(HashMap<Integer, Lecteur> dicoLecteur) {
        _dicoLecteur = dicoLecteur;
    }

    // -----------------------------------------------
    // Méthodes
    // -----------------------------------------------
    /**
     * Renvoie le lecteur de numLecteur.
     *
     * Permet de rechercher dans la base de donnée de bibliotheque un objet
     * lecteur identifié par son numéro, et de renvoyer l'objet, null si non
     * trouvé.
     *
     * @param numLecteur
     *
     * @return lecteur dont le numéro est numLecteur
     */
    private Lecteur getLecteur(Integer numLecteur) {
        return _dicoLecteur.get(numLecteur);
    }

    /**
     * Permet d'ajouter un lecteur a la base de donnée de bibliotheque.
     */
    private void lierLecteur(Lecteur L, Integer numLecteur) {
        _dicoLecteur.put(numLecteur, L);
    }

    /**
     * Permet d'ajouter une Oeuvre a la base de donnée de bibliotheque.
     *
     * @param o
     * @param numISBN
     */
    public void lierOeuvre(Oeuvre o, String numISBN) {
        this._dicoOeuvres.put(numISBN, o);
    }

    /**
     * La méthode lesLecteurs permet de créer un iterator sur les lecteurs, dans
     * le but de les parcourir pour eventuellement les relancer.
     */
    /*private Iterator<Lecteur> lesLecteurs() {
        return _dicoLecteur.values().iterator();
    }*/
    /**
     * Renvoie true si l'age du lecteur est compatible avec le public de
     * l'oeuvre, false sinon.
     *
     * @param o
     * @param l
     * @return true si l'age du lecteur correspond au public de l'oeuvre
     */
    private boolean compatibilitePublic(Oeuvre o, Lecteur l) {
        if (o.getPub() == EnumPublic.ENFANT) {
            return true;
        }
        if (o.getPub() == EnumPublic.ADOLESCENT && l.getAge() > 10) {
            return true;
        }
        if (o.getPub() == EnumPublic.ADULTE && l.getAge() > 16) {
            return true;
        }
        return false;
    }

}
