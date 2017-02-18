import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;


public class Main {
	
	public Main() {

		Bibliotheque bibliotheque = new Bibliotheque();
		
                
                Integer menufich,vidange,brouillon;
                String nomFich="default.ser";
                vidange=brouillon=0;
                
                do {
                    EntreesSorties.afficherMessage(" ========================================================");
                    EntreesSorties.afficherMessage("|            Selection Du fichier test:                  |");
                    EntreesSorties.afficherMessage("| Fichier Vide :                          1              |");
                    EntreesSorties.afficherMessage("| Fichier Brouillon :                     2              |");
                    EntreesSorties.afficherMessage("| Fichier de test (Ne pas le modifer) :   3              |");
                    EntreesSorties.afficherMessage("| Ficher par défaut (default.ser) :       0              |");
                    EntreesSorties.afficherMessage(" ========================================================");
                    menufich = EntreesSorties.lireEntier();

                    switch (menufich) {
                        case 1: {
                            nomFich="save.ser";
                            vidange=1;
                            break;
                        }
                        case 2: {
                            nomFich="brouillon.ser";
                            brouillon=1;
                            break;
                        }
                        case 3: {
                            nomFich="fichierTest.ser";
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                } while (menufich != 0 && menufich != 2 && menufich != 1 && menufich != 3);
                
                
                
		//String nomFich="backup.ser";
		
		/*---------------------------------------------------------------------------------------------
		 * Récupération des serialisation précédentes dans le fichier du nom de "save.ser".
		 * L'utilisateur est informé de la réussite ou non de la récupération des données.
		*/
		try {
			FileInputStream fichier = new FileInputStream(nomFich);
			ObjectInputStream in = new ObjectInputStream(fichier);

			bibliotheque = (Bibliotheque) in.readObject();
			
			fichier.close();
			in.close();

			EntreesSorties.afficherMessage(" $$$ Restauration du fichier " + nomFich + " realisee");
		} catch (Exception e) {
			EntreesSorties.afficherMessage(" *** Start : Pbs de Restauration / fichier " + nomFich);
		}
		//----------------------------------------------------------------------------------------------
		
		MenuBiblio menu = new MenuBiblio(bibliotheque);
		menu.menuPrincipal();
		
		/*---------------------------------------------------------------------------------------------
		 * Sériaisation dans le fichier du nom de "save.ser".
		 * L'utilisateur est informé de la réussite ou non de la sauvegarde des données.
		*/
                
                if (vidange == 1)
                {
                    EntreesSorties.afficherMessage(" *** Start :  Merci d'avoir testé A bientôt !");
                    EntreesSorties.afficherMessage(" *** Start :  Réinitialisation du Fichier " + nomFich);
                }
                else{
                    if (brouillon == 1)
                    {
                        Integer menuBrouillon,zero;
                        zero=0;
                        
                        do {
                            EntreesSorties.afficherMessage(" ========================================================");
                            EntreesSorties.afficherMessage("|            Reinitialisation du brouillon:              |");
                            EntreesSorties.afficherMessage("| Remise à zero :    1                                   |");
                            EntreesSorties.afficherMessage("| Sauvegarde des modifications:   2                      |");
                            EntreesSorties.afficherMessage("| Quitter :         0                                    |");
                            EntreesSorties.afficherMessage(" ========================================================");
                            menuBrouillon = EntreesSorties.lireEntier();

                            switch (menuBrouillon) {
                                case 1: {
                                    zero=1;
                                    break;
                                }
                                case 2: {
                                    zero=2;
                                    break;
                                }
                                default: {
                                    break;
                                }
                            }
                        } while (menufich != 0 && menufich != 2 && menufich != 1);
                        
                        /* Suppression du fichier brouillon*/
                        
                        if (zero == 1)
                        {
                            new File("./brouillon.ser").delete();                            
                        }
                        
                        /* Sauvegarde du fichier brouillon*/
                        
                        /* Revenir sur la partie apres car bug a verfif */
                        
                        if (zero == 2)
                        {
                            try {
                                FileOutputStream f = new FileOutputStream(nomFich);
                                ObjectOutputStream out = new ObjectOutputStream(f);

                                out.writeObject(bibliotheque);

                                EntreesSorties.afficherMessage(" $$$ Sauvegarde dans le fichier " + nomFich + " realisee");
			
                                out.close();
                                f.close();
                            } catch (Exception e) {
                                EntreesSorties.afficherMessage(" *** Start :Pbs de Sauvegarde dans le fichier " + nomFich);
                            }
                        }
                        
                        /* Sauvegarde en cas de soucis avec brouillon*/
                        
                        else
                        {
                            try {
                                FileOutputStream f = new FileOutputStream(nomFich);
                                ObjectOutputStream out = new ObjectOutputStream(f);

                                out.writeObject(bibliotheque);

                                EntreesSorties.afficherMessage(" $$$ Sauvegarde dans le fichier " + nomFich + " realisee");
			
                                out.close();
                                f.close();
                            } catch (Exception e) {
                                EntreesSorties.afficherMessage(" *** Start :Pbs de Sauvegarde dans le fichier " + nomFich);
                            }
                        }
                    }
                    
                    /* Sauvegarde POur le cas ou on utilise le fichier : fichierTest*/
                    
                    else
                    { 
                        try {
                                FileOutputStream f = new FileOutputStream(nomFich);
                                ObjectOutputStream out = new ObjectOutputStream(f);

                                out.writeObject(bibliotheque);

                                EntreesSorties.afficherMessage(" $$$ Sauvegarde dans le fichier " + nomFich + " realisee");
			
                                out.close();
                                f.close();
                            } catch (Exception e) {
                                EntreesSorties.afficherMessage(" *** Start :Pbs de Sauvegarde dans le fichier " + nomFich);
                            }
                    }
                       
                        
                        
                }
            }
                

	public static void main(String args[]){
	new Main();
	}
}

 
