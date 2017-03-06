import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Classe de gestion des EntréesSorties (affichages et saisies)
 * @author IUT, refactoré par E. Ceret
 * @version 2.0
 *
 */
public class EntreesSorties  {

	// ************************************************************************************************************
	// Constantes
	// ************************************************************************************************************

	private static final long serialVersionUID = 1L;
	
	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************

	private static BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));

	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************

	/**
	 * Constructeur. Aucun traitement requis.
	 */
	public EntreesSorties() {
		
	}
	
	// ************************************************************************************************************
	// Méthodes privées
	// ************************************************************************************************************

	// ------------------------------------------------------------------------------------------------------------
	// Traitements internes

	/**
	 * @return un entier représentant l'année courante.
	 */
	private static int anneeCourante() {
		// Instancie une variable date
		GregorianCalendar dateCourante = new GregorianCalendar();
		
		// Affecte la date courante à cette variable
		dateCourante.setTime(new Date());
		
		// Extrait l'année de cette variable et la renvoie
		return dateCourante.get(GregorianCalendar.YEAR);
	}
	
	/**
	 * @return un entier représentant le mois courant.
	 */
	private static int moisCourant() {
		// Instancie une variable date
		GregorianCalendar dateCourante = new GregorianCalendar(); 
		
		// Affecte la date courante à cette variable
		dateCourante.setTime(new Date());

		// Extrait le mois de cette variable et la renvoie
		return dateCourante.get(GregorianCalendar.MONTH);
	}	
	
	/**
	 * Affichage d'un texte explicatif avant demande d'une saisie.
	 * @param message le texte à afficher
	 */
	private static void afficherLibelle(String libelle) {
		System.out.print(libelle);		
	}

	// ************************************************************************************************************
	// Méthodes publiques
	// ************************************************************************************************************

	//	---------------------------------------------------------------------------------------------------------------------
	//	Gestion des chaines de caractère
	
	/**
	 * permet la saisie d'une chaine. Pas de contrôle du format saisi.
	 * 
	 * @return chaine saisie par l'utilisateur
	 */
	public static String lireChaine()   {
		try {
			return entree.readLine();
		}
		catch (IOException e)  {
	        System.out.println(" ERREUR d'E/S : IO.LIRE_CHAINE");
	        return "";
		}
	}  // Fin lireChaine
	
	/**
	 * Affiche un libellé et permet la saisie d'une chaine.
	 * 
	 * @param libelle : libellé à afficher en début pour indiquer quelle saisie effectuer
	 * @return String : chaine saisie par l'utilisateur
	 */
	public static String lireChaine(String libelle)   {
		EntreesSorties.afficherLibelle(libelle);
		return lireChaine();
	}  // Fin lireChaine

	//	---------------------------------------------------------------------------------------------------------------------
	//	Gestion des entiers
	
	/**
	 * permet la saisie d'un entier. Si l'utilisateur saisit une valeur non-entière, affichage d'une erreur et demande 
	 * d'une autre saisie. 
	 * 
	 * @return int : l'entier saisi.
	 * 
	 */
	public static int lireEntier()   {
		boolean ok = false;
		int valentiere = 0;
		
		try  {
		   do {
		        try  {
		        	valentiere = Integer.parseInt(entree.readLine());
		        	ok = true;
		        }
		        catch (NumberFormatException e)
		                {System.out.println("Non un entier. Recommencez.");}
		   } while (!ok);
		
		   return  valentiere;
		}
		catch (IOException e)  {
		        System.out.println(" ERREUR d'E/S : IO.LIRE_ENTIER");
		        return 0;
		}
	} // Fin de lireEntier

	/**
	 * Affiche un libellé puis permet la saisie d'un entier. Si l'utilisateur saisit une valeur non-entière, affichage 
	 * d'une erreur et demande d'une autre saisie. 
	 * 
	 * @param libelle : libellé à afficher en début pour indiquer quelle saisie effectuer
	 * @return int : l'entier saisi.
	 * 
	 */
	public static int lireEntier(String libelle)   {
		EntreesSorties.afficherLibelle(libelle);
		return lireEntier();
	}  // Fin de lireEntier

	//	---------------------------------------------------------------------------------------------------------------------
	//	Gestion des flottants
	/**
	 * permet la saisie d'un réel. Si l'utilisateur saisit une valeur non-réelle, affichage d'une erreur et demande 
	 * d'une autre saisie. 
	 * 
	 * @return float : la valeur flottante saisie.
	 */
	public static float lireFlottant()   {
		boolean ok = false;
		float valflottant = 0;
		Float flot;
		try  {
		   do {
		        try  {
			        flot = Float.valueOf(entree.readLine());
			        valflottant = flot.floatValue();
			        ok = true;
			    }
		        catch (NumberFormatException e)
		                {System.out.println("Non un flottant. Recommencez.");}
		   } while (!ok);
		   return  valflottant;
		}
		catch (IOException e)  {
		        System.out.println(" ERREUR d'E/S : IO.LIRE_ENTIER");
		    return 0;
		}
	} // Fin de lireFlottant
	
	/**
	 * Affiche un libellé puis permet la saisie d'un réel. Si l'utilisateur saisit une valeur non-réelle, 
	 * affichage d'une erreur et demande d'une autre saisie. 
	 * 
	 * @param libelle : libellé à afficher en début pour indiquer quelle saisie effectuer
	 * @return float : la valeur flottante saisie.
	 */
	public static float lireFlottant(String libelle)   {
		EntreesSorties.afficherLibelle(libelle);
		return lireFlottant();
	}  // Fin de lireFlottant
	
	
	//	---------------------------------------------------------------------------------------------------------------------
	//	Gestion des dates
	/**
	 * permet la saisie d'une date au format JJ/MM/AAAA. Le jour doit être compris entre 1 et 31, le 
	 * mois doit être entre 1 et 12 et l'année entre 1900 et 2100. Le contrôle de cohérence entre les différents champs
	 * est effectué grâce à une conversion de la saisie en date. Le contrôle du format de champs champ est réalisé par l'appel
	 * à la fonction lireEntier
	 * 
	 * @return GregorianCalendat : la date saisie
	 */
	public static GregorianCalendar lireDate()   {
		boolean ok = false;
		String date ;
		String[] champsDate ;
		int jour = 0;
		int mois = 0;
		int an = 0;
		GregorianCalendar dateSaisie = null ;
		String message = "";
		
		do {
        	// On initialise ok à vrai. Toute erreur détectée le remettra à faux, ce qui
        	// fait qu'on restera dans la boucle ET qu'on aura l'information qu'une erreur a été trouvée.
        	ok = true ;
        	
			// Saisie de la date sous la forme d'une chaîne qui sera ensuite convertie en date
		    date = lireChaine();
		    
		    // Conversion de la chaîne en 3 variables entières jour, mois, an. 
		    champsDate = date.split("/");
		    
		    // Calcul du jour saisi
		    if (champsDate.length >= 1) {
		    	try{
			    	jour = Integer.parseInt(champsDate[0]);
		    	} catch (Exception e) {
		    		message += "Le jour saisi est invalide." ;
		        	ok = false ;
		    	}
		    }
		    
		    // Calcul du mois saisi (si pas saisi : valorisé avec le mois courant)
		    if (champsDate.length >= 2) {
		    	try{
		    		mois = Integer.parseInt(champsDate[1]);
		    	} catch (Exception e) {
		    		message += "Le mois saisi est invalide." ;
		        	ok = false ;
		    	}
		    } else {
		    	mois = moisCourant();
		    }
		    
		    // Calcul de l'année saisie (si pas saisie : valorisée avec l'année courante)
		    if (champsDate.length == 3) {
		    	try{
		    		an = Integer.parseInt(champsDate[2]);
		    	} catch (Exception e) {
		    		message += "L'année saisie est invalide." ;
		        	ok = false ;
		    	}
		    } else {
		    	an = anneeCourante();
		    }

		    // Conversion des trois champs saisis en date
		    if (ok) {
		    	try {
		    		dateSaisie = new GregorianCalendar(an, mois-1, jour);
		    	}
		    	catch (Exception e) {
		    		message += "La date saisie est invalide." ;
		    		ok = false;
		    	}
		    }
		} while (!ok);
		
		EntreesSorties.afficherMessage("Vous avez saisi : " + EntreesSorties.ecrireDate(dateSaisie));
		
		// Renvoi du résultat
		return  dateSaisie;
	} // Fin lireDate

	/**
	 * Affiche un libellé puis permet la saisie d'une date au format JJ/MM/AAAA. Le jour doit être compris entre 1 et 31, le 
	 * mois doit être entre 1 et 12 et l'année entre 1900 et 2100. Le contrôle de cohérence entre les différents champs
	 * est effectué grâce à une conversion de la saisie en date. Le contrôle du format de champs champ est réalisé par l'appel
	 * à la fonction lireEntier
	 * 
	 * @param libelle : libellé à afficher en début pour indiquer quelle saisie effectuer
	 * @return GregorianCalendat : la date saisie
	 */
	public static GregorianCalendar lireDate(String libelle)   {
		EntreesSorties.afficherLibelle(libelle);
		return lireDate();
	}  // Fin de lireFlottant

	
	//	---------------------------------------------------------------------------------------------------------------------
	//	Affichages d'une date
	
	/**
	 * Convertit une date en chaine au format JJ/MM/AAAA. Si la date passée vaut null, la chaine résultante est "nul".
	 * @param date date à convertir
	 * @return chaine au format JJ/MM/AAAA
	 */
	public static String ecrireDate(GregorianCalendar date)   {
		if (date == null) {
		    return "nul";
		} else {
	        return date.get(Calendar.DAY_OF_MONTH) + "/" + (date.get(Calendar.MONTH)+1) + "/" + date.get(Calendar.YEAR);
		}
	} // Fin ecrireDate
	
	/**
	 * Convertit une date en chaine au format "JJ/MM/AAAA hhH mnMN ssSEC msMILLISEC". Si la date passée vaut null, 
	 * la chaine résultante est "nul".
	 * @param date date à convertir
	 * @return chaine au format "JJ/MM/AAAA hhH mnMN ssSEC msMILLISEC"
	 */
	 public static String ecrireDateComplete(GregorianCalendar date)   {
	
		if (date==null) {
	        return "nul";
		} else {
			return (EntreesSorties.ecrireDate(date) + "  " +
					EntreesSorties.heure(date) + "H " +
					EntreesSorties.minute(date) + "mn " +
					EntreesSorties.seconde(date) + "sec " + 
					EntreesSorties.milliSeconde(date) + "millisec");
		}
	} // Fin ecrireDateComplete
	
	/**
	 * Donne le n° du jour dans la semaine d'une date donnée. 
	 * @param date date dont on veut le jour dans la semaine
	 * @return n° du jour dans la semaine de 0 (dimanche) à 6 (samedi)
	 */
	public static int jourSemaine(GregorianCalendar date)   {
		if (date==null) {
			return -1;
		} else {
			return date.get(Calendar.DAY_OF_WEEK) ;
		}
	} // Fin JourSemaine
	
	/**
	 * Donne l'heure contenue dans une date. 
	 * @param date date dont on veut extraire l'heure
	 * @return un entier représentant l'heure de la date, entre 0 et 23.
	 */
	public static int heure(GregorianCalendar date)   {
		if (date==null) {
			return -1;
		} else {
		   return date.get(Calendar.HOUR_OF_DAY) ;
		}
	} // Fin heure
	
	/**
	 * Donne les minutes contenue dans une date. 
	 * @param date date dont on veut extraire les minutes
	 * @return un entier représentant les minutes de la date, entre 0 et 59.
	 */
	public static int minute(GregorianCalendar date)   {
		if (date==null) {
	    	return -1;
		} else {
			return date.get(Calendar.MINUTE) ;
		}
	} // Fin minute

	/**
	 * Donne les secondes contenue dans une date. 
	 * @param date date dont on veut extraire les secondes
	 * @return un entier représentant les secondes de la date, entre 0 et 59.
	 */
	public static int seconde(GregorianCalendar date)   {
		if (date==null) {
	    	return -1;
		} else {
			return date.get(Calendar.SECOND) ;
		}
	} // Fin seconde
	
	/**
	 * Donne les millisecondes contenue dans une date. 
	 * @param date date dont on veut extraire les millisecondes
	 * @return un entier représentant les millisecondes de la date, entre 0 et 999.
	 */
	public static int milliSeconde(GregorianCalendar date)   {
		if (date==null) {
	    	return -1;
		} else {
			return date.get(Calendar.MILLISECOND) ;
		}
	} // Fin milliSeconde
	
	//	---------------------------------------------------------------------------------------------------------------------
	//	Affichage d'informations
	
	/**
	 * Affichage d'un titre. On fait précéder et suivre le texte reçu par une ligne blanche.
	 * @param titre titre à afficher
	 */
	public static void afficherTitre(String titre) {
		System.out.println();
		System.out.println(titre);
                System.out.println();
	}
        
        /**
	 * Affichage d'un titre. On fait précéder et suivre le texte reçu par une ligne blanche.
	 * @param titre titre à afficher
	 */
	public static void afficherTitreMenu(String titre) {
		System.out.println();
                System.out.println("----------------------------------------");
		System.out.println(titre);
		System.out.println("----------------------------------------");
                System.out.println();
	}

	/**
	 * Affichage d'un message. On revient à la ligne après le message.
	 * @param message message à afficher.
	 */
	public static void afficherMessage(String message) {
		System.out.println(message);		
	}
	
} // Fin de classe IO