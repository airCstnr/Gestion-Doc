import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;


public class Main {
	
	public Main() {

		Bibliotheque bibliotheque = new Bibliotheque();
                
                /*---------------------------------------------------------------------------------------------
                    Menu de gestion des fichiers de tests
                    vidange sert a identifier le fichier vide
                    brouillon sert a identifier le fichier de brouillon
                */
                
                
		String nomFich;
                int vidange,brouillon,backup;
                nomFich="default.ser";
                vidange=brouillon=backup=0;
                                
                Integer menufich;

                
                do {
                    EntreesSorties.afficherMessage(" ========================================================");
                    EntreesSorties.afficherMessage("|            Selection Du fichier test:                  |");
                    EntreesSorties.afficherMessage("| Fichier Vide :                                    0    |");
                    EntreesSorties.afficherMessage("| Fichier Brouillon :                               1    |");
                    EntreesSorties.afficherMessage("| Fichier de test (Ne peut pas etre modifé) :       2    |");
                    EntreesSorties.afficherMessage("| Enregistrement dans la base (Attention mécréant): 3    |");
                    EntreesSorties.afficherMessage(" ========================================================");
                    menufich = EntreesSorties.lireEntier();

                    switch (menufich) {
                        case 1: {
                            nomFich="brouillon.ser";
                            brouillon=1;
                            break;
                        }
                        case 2: {
                            nomFich="fichierTest.ser";
                            break;
                        }
                        case 3: {
                            nomFich="backup.ser";
                            backup=1;
                            break;
                        }
                        default: {
                            vidange=1;
                            break;
                        }
                    }
                } while (menufich != 0 && menufich != 2 && menufich != 1 && menufich != 3);

		
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
                
                
                menuSortie(nomFich,vidange,brouillon,backup,bibliotheque);
		
		
            }
                

	public static void main(String args[]){
	new Main();
	}
        
                
        private void serialization(Bibliotheque bibliotheque,String nomFich)
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
        
        
        private void menuSortie (String nomFich, int vidange,int brouillon,int backup, Bibliotheque bibliotheque) {
            /*---------------------------------------------------------------------------------------------
		 * Sérialisation dans le fichier du nom de "save.ser".
		 * L'utilisateur est informé de la réussite ou non de la sauvegarde des données.
		*/
                
                if ( vidange == 1 )
                {
                    EntreesSorties.afficherMessage(" *** Start :  Merci d'avoir testé A bientôt !");
                    EntreesSorties.afficherMessage(" *** Start :  Réinitialisation du Fichier " + nomFich);
                }
                
                if ( brouillon == 1 )
                {
                    Integer menuBrouillon;
                        
                    do {
                        EntreesSorties.afficherMessage(" ========================================================");
                        EntreesSorties.afficherMessage("|            Reinitialisation du brouillon:              |");
                        EntreesSorties.afficherMessage("| Remise à zero :    1                                   |");
                        EntreesSorties.afficherMessage("| Sauvegarde des modifications:   2                      |");
                        EntreesSorties.afficherMessage("| Quitter :         0                                    |");
                        EntreesSorties.afficherMessage(" ========================================================");
                        menuBrouillon = EntreesSorties.lireEntier();
                        switch (menuBrouillon) {
                            /* Suppression du fichier brouillon*/
                            case 1: {
                                new File("./brouillon.ser").delete();
                                EntreesSorties.afficherMessage(" *** Start :  Réinitialisation du brouillon         !");
                                EntreesSorties.afficherMessage(" *** Start :  Merci d'avoir fais vos test A bientôt !");
                                break;
                            }
                            /* Sauvegarde du fichier brouillon*/
                            case 2: {
                                serialization(bibliotheque,nomFich);
                                EntreesSorties.afficherMessage(" *** Start :  Sauvegarde du brouillon               !");
                                EntreesSorties.afficherMessage(" *** Start :  Merci d'avoir fais vos test A bientôt !");
                                break;
                            }
                            /* Sauvegarde par default du brouillon */
                            default: {                                
                                serialization(bibliotheque,nomFich);
                                EntreesSorties.afficherMessage(" *** Start :  Sauvegarde du brouillon par defaut            !");
                                EntreesSorties.afficherMessage(" *** Start :  Merci d'avoir fais vos test A bientôt         !");
                                break;
                            }
                        }
                    } while (menuBrouillon != 0 && menuBrouillon != 2 && menuBrouillon != 1);
                    
                }
                
                if ( backup ==1 )
                {
                    Integer savebackup=0;
                    do {
                        EntreesSorties.afficherMessage(" ========================================================");
                        EntreesSorties.afficherMessage("|            Sauvegarde du fichier backup :              |");
                        EntreesSorties.afficherMessage("| Sauvegarder :                          1               |");
                        EntreesSorties.afficherMessage("| Quitter et sauver dans brouillon :     2               |");
                        EntreesSorties.afficherMessage(" ========================================================");
                        savebackup = EntreesSorties.lireEntier();
                        
                    } while ( savebackup != 1 && savebackup != 2);
                    
                    if ( savebackup.equals(1) )
                    {
                        serialization(bibliotheque,nomFich);
                        EntreesSorties.afficherMessage(" *** Start :  Sauvegarde du fichier backup              !");
                        EntreesSorties.afficherMessage(" *** Start :  Pensez à le copié dans le fichier de test !");
                    }
                    if ( savebackup.equals(2) )
                    {
                        nomFich = "brouillon.ser";
                        serialization(bibliotheque,nomFich);
                        EntreesSorties.afficherMessage(" *** Start :  Sauvegarde du fichier backup dans brouillon  !");
                        EntreesSorties.afficherMessage(" *** Start :  Merci d'avoir fais vos test A bientôt        !");
                    }
                    
                    
                }
        }
        
        
}

 
