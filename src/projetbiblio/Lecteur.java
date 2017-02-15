import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;

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
	
	
	
	// -----------------------------------------------
		//Constructeur
	// -----------------------------------------------
		
		public Lecteur(String nom, String prenom, Integer numLecteur, GregorianCalendar dateNaiss, String adresse, String tel)
		{
			this.setNom(nom);
			this.setPrenom(prenom);
			this.setNumLecteur(numLecteur);
			this.setDateNaiss(dateNaiss);
			this.setAdresse(adresse);
			this.setTel(tel);
		}
		
// -----------------------------------------------
	// Public
// -----------------------------------------------
		
		// -----------------------------------------------
			//Getters
		// -----------------------------------------------
	
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
		public void afficherLecteur()
		{
			System.out.println("Numero lecteur : " + this.getNumLecteur());
			System.out.println("Nom et prenom du lecteur: " + this.getNom() + " " + this.getPrenom());
			System.out.println("Age : " + this.calculAge() + " ans");
			System.out.println("Adresse : " + this.getAdresse());
			System.out.println("Telephone : " + this.getTel());
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
			if(dateNaissComp.before(dateActuelle)){
				age=dateActuelle.get(GregorianCalendar.YEAR)-_dateNaiss.get(GregorianCalendar.YEAR);
			}
			else{
				age=dateActuelle.get(GregorianCalendar.YEAR)-_dateNaiss.get(GregorianCalendar.YEAR)-1;
			}
			return age;
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