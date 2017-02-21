
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

// Classe de gestion de Lecteur
public class Lecteur implements Serializable {

    private static final long serialVersionUID = 422L;

    // -----------------------------------------------
    //Attributs
    // -----------------------------------------------
    private String _nom;
    private String _prenom;
    private Integer _numLecteur;
    private GregorianCalendar _dateNaiss;
    private String _adresse;
    private String _tel;
    private ArrayList<Emprunt> emprunts;

    // -----------------------------------------------
    //Constructeur
    // -----------------------------------------------
    public Lecteur(String nom, String prenom, Integer numLecteur, GregorianCalendar dateNaiss, String adresse, String tel, Integer ageLect) {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setNumLecteur(numLecteur);
        this.setDateNaiss(dateNaiss);
        this.setAdresse(adresse);
        this.setTel(tel);
        this.emprunts = new ArrayList<>(); // la liste des emprunts est vide
    }

// -----------------------------------------------
    // Public
// -----------------------------------------------
    // -----------------------------------------------
    //Getters
    // -----------------------------------------------
    /**
     * Renvoie true si le lecteur a emprunté 5 exemplaires, false sinon
     *
     * @return
     */
    public boolean estSature() {
        return (this.emprunts.size() == 5);
    }

    /**
     * Renoive le nombre d'emprunts actuels du lecteur.
     *
     * @return nbEmprunts
     */
    public int getNbEmprunts() {
        return this.emprunts.size();
    }

    /**
     * Renvoie la liste des emprunts.
     *
     * @return emprunts
     */
    public ArrayList<Emprunt> getEmprunts() {
        return emprunts;
    }

    /**
     * Renvoie le nom et le prénom du lecteur
     *
     * @return prenom nom
     */
    public String getNomComplet() {
        return _prenom + " " + _nom.toUpperCase();
    }

    /**
     * Renvoie le type de public auquel appartient le lecteur.
     *
     * Calcule en fonction de l'age du lecteur le public auquel il appartient.
     *
     * @return
     */
    public EnumPublic getPublic() {
        return null;
    }

    /**
     * Renvoie l'age du lecteur.
     *
     * Utilise la date de naissance du lecteur pour renvoyer son age. ATTENTION
     * : L'age est déterminé uniquement par l'année de naissance, et non par son
     * anniversaire.
     *
     * @return age
     */
    public int getAge() {
        return 0;
    }

    public String getNom() {
        return _nom;
    }

    public String getPrenom() {
        return _prenom;
    }

    public Integer getNumLecteur() {
        return _numLecteur;
    }

    public GregorianCalendar getDateNaiss() {
        return _dateNaiss;
    }

    public String getAdresse() {
        return _adresse;
    }

    public String getTel() {
        return _tel;
    }

    // -----------------------------------------------
    // Methodes
    // -----------------------------------------------

    /*
     * La méthode afficherLecteur affiche l'ensemble des informations relatives à un lecteur.
     */
    public void afficherLecteur() {
        EntreesSorties.afficherMessage("Numero lecteur : " + this.getNumLecteur());
        EntreesSorties.afficherMessage("Nom et prenom du lecteur: " + this.getNom() + " " + this.getPrenom());
        EntreesSorties.afficherMessage("Age : " + this.calculAge() + " ans");
        EntreesSorties.afficherMessage("Adresse : " + this.getAdresse());
        EntreesSorties.afficherMessage("Telephone : " + this.getTel());
        EntreesSorties.afficherMessage("");
    }

    /*
     * la méthode calculAge permet de déterminer l'age des lecteurs grace a leur date de naissance
     * et la date actuelle. De cette façon, il n'y a pas de mise a jour a faire sur l'age des lecteurs.
     */
    public Integer calculAge() {
        Integer age;
        GregorianCalendar dateNaissComp;
        GregorianCalendar dateActuelle = new GregorianCalendar();
        dateNaissComp = new GregorianCalendar(dateActuelle.get(GregorianCalendar.YEAR), _dateNaiss.get(GregorianCalendar.MONTH), _dateNaiss.get(GregorianCalendar.DATE));
        if (dateNaissComp.before(dateActuelle)) {
            age = dateActuelle.get(GregorianCalendar.YEAR) - _dateNaiss.get(GregorianCalendar.YEAR);
        } else {
            age = dateActuelle.get(GregorianCalendar.YEAR) - _dateNaiss.get(GregorianCalendar.YEAR) - 1;
        }
        return age;
    }

    /**
     * Permet d'ajouter un emprunt au lecteur.
     *
     * @param emprunt
     */
    public void setEmprunt(Emprunt emprunt) {
        this.emprunts.add(emprunt);
    }

    /**
     * Permet d'enlever l'emprunt au lecteur.
     *
     * @param emprunt
     */
    public void deleteEmprunt(Emprunt emprunt) {
        this.emprunts.remove(emprunt);
    }

// -----------------------------------------------
// Private
// -----------------------------------------------
    // -----------------------------------------------
    //Setters
    // -----------------------------------------------
    private void setNom(String nom) {
        this._nom = nom;
    }

    private void setPrenom(String prenom) {
        this._prenom = prenom;
    }

    private void setNumLecteur(Integer numLecteur) {
        this._numLecteur = numLecteur;
    }

    private void setDateNaiss(GregorianCalendar dateNaiss) {
        this._dateNaiss = dateNaiss;
    }

    private void setAdresse(String adresse) {
        this._adresse = adresse;
    }

    private void setTel(String tel) {
        this._tel = tel;
    }

}
