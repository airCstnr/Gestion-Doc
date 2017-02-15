
import projetbiblio.EntreesSorties;

public class MenuBiblio {

    private Bibliotheque _bibliotheque;

    public MenuBiblio(Bibliotheque bibliotheque) {
        _bibliotheque = bibliotheque;
    }

    /*
     * menuPrincipal permet � l'utilisateur de selectionner un type de sous menu (Lecteur, Ouvrage ou Exemplaire) 
     * o� il effectuera par la suite l'action d�sir�e. Si l'utilisateur a fini d'utiliuser le programme, il choisit l'option Quitter.
     */
    public void menuPrincipal() {
        Integer menu;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("|                   Menu Principal                       |");
            //EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
            EntreesSorties.afficherMessage("| Menu Lecteur :    1                                    |");
            EntreesSorties.afficherMessage("| Menu Oeuvre :     2                                    |");
            EntreesSorties.afficherMessage("| Quitter :         0                                    |");
            EntreesSorties.afficherMessage(" ========================================================");
            menu = EntreesSorties.lireEntier();

            switch (menu) {
                case 1: {
                    this.menuLecteur();
                    break;
                }
                case 2: {
                    this.menuOeuvre();
                    break;
                }
                default: {
                    break;
                }
            }
        } while (menu != 0);
    }

    /* menuLect permet d'effectuer une s�rie d'action concernant les utilisateur (lecteurs) de la biblioth�que.
	 * Une fois une action effectu�e, l'utilisateur sera rediriger vers ce m�me menu afin de pouvoir selectionner
	 * une nouvelle fois une action concernant les lecteurs.
	 * "Retour Menu Principal" renvoi l'utilisateur au menu principal.
     */
    public void menuLecteur() {
        Integer menuLect;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("|                   Menu Lecteur                         |");
            //EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
            EntreesSorties.afficherMessage("| Nouveau Lecteur :              1                       |");
            EntreesSorties.afficherMessage("| Consulter Lecteur :            2                       |");
            EntreesSorties.afficherMessage("| Consulter Liste Lecteurs :     3                       |");
            EntreesSorties.afficherMessage("| Retour Menu Principal :        0                       |");
            EntreesSorties.afficherMessage(" ========================================================");
            menuLect = EntreesSorties.lireEntier();

            switch (menuLect) {
                case 1: {
                    _bibliotheque.nouveauLecteur();
                    break;
                }
                case 2: {
                    _bibliotheque.consulterLecteur();
                    break;
                }
                case 3: {
                    _bibliotheque.consulterListeLecteurs();
                    break;
                }
                default: {
                    break;
                }
            }
        } while (menuLect != 0);
    }

    private void menuOeuvre() {
        Integer menuOeuvre;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("|                   Menu Oeuvre                          |");
            EntreesSorties.afficherMessage("| Nouvel Ouvrage :                   1                   |");
            EntreesSorties.afficherMessage("| Consulter Ouvrage :                2                   |");
            EntreesSorties.afficherMessage("| Consulter Exemplaires Ouvrage :    3                   |");
            EntreesSorties.afficherMessage("| Retour Menu Principal :            0                   |");
            EntreesSorties.afficherMessage(" ========================================================");
            menuOeuvre = EntreesSorties.lireEntier();

            switch (menuOeuvre) {
                case 1: {
                    // nouvelOuvrage();
                    break;
                }
                case 2: {
                    // consulterOuvrage();
                    break;
                }
                case 3: {
                    // consulterExemplairesOuvrage();
                    break;
                }
                default: {
                    break;
                }
            }
        } while (menuOeuvre != 0);
    }

}
