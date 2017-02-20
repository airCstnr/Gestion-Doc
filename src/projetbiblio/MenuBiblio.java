public class MenuBiblio {

    private Bibliotheque _bibliotheque;

    public MenuBiblio(Bibliotheque bibliotheque) {
        _bibliotheque = bibliotheque;
    }

    /*
     * menuPrincipal permet à l'utilisateur de selectionner un type de sous menu (Lecteur, Ouvrage ou Exemplaire) 
     * où il effectuera par la suite l'action désirée. Si l'utilisateur a fini d'utiliuser le programme, il choisit l'option Quitter.
     */
    public void menuPrincipal() {
        Integer menu;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("|                   Menu Principal                       |");
            EntreesSorties.afficherMessage("| Menu Lecteur :    1                                    |");
            EntreesSorties.afficherMessage("| Menu Oeuvre :     2                                    |");
            EntreesSorties.afficherMessage("| Menu Emprunt :    3                                    |");
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
                case 3: {
                    this.menuEmprunt();
                    break;
                }
                default: {
                    break;
                }
            }
        } while (menu != 0);
    }

    /* 
     * menuLect permet d'effectuer une série d'action concernant les utilisateur (lecteurs) de la bibliothèque.
     * Une fois une action effectuée, l'utilisateur sera rediriger vers ce même menu afin de pouvoir selectionner
     * une nouvelle fois une action concernant les lecteurs.
     * "Retour Menu Principal" renvoie l'utilisateur au menu principal.
     */
    public void menuLecteur() {
        Integer menuLect;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("|                   Menu Lecteur                         |");
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

    /**
     * menuOeuvre permet à l'utilisateur d'effectuer plusieurs opération sur les oeuvres et leurs exemplaires
     */
    private void menuOeuvre() {
        Integer menuOeuvre;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("|                   Menu Oeuvre                          |");
            EntreesSorties.afficherMessage("| Nouvel Ouvrage :                   1                   |");
            EntreesSorties.afficherMessage("| Consulter Ouvrage :                2                   |");
            EntreesSorties.afficherMessage("| Consulter Liste Ouvrages :         3                   |");
            EntreesSorties.afficherMessage("| Nouvel Exemplaires :               4                   |");
            EntreesSorties.afficherMessage("| Consulter Exemplaires Ouvrage :    5                   |");
            EntreesSorties.afficherMessage("| Retour Menu Principal :            0                   |");
            EntreesSorties.afficherMessage(" ========================================================");
            menuOeuvre = EntreesSorties.lireEntier();

            switch (menuOeuvre) {
                case 1: {
                    _bibliotheque.nouvelOuvrage();
                    break;
                }
                case 2: {
                    _bibliotheque.consulterOuvrage();
                    break;
                }
                case 3: {
                    _bibliotheque.consulterListeOuvrages();
                    break;
                }
                case 4: {
                    _bibliotheque.nouvelExemplaire();
                    break;
                }
                case 5: {
                    _bibliotheque.consulterExemplaireOuvrage();
                    break;
                }
                default: {
                    break;
                }
            }
        } while (menuOeuvre != 0);
    }
    
    /**
     * menuEmprunt permet à l'utilisateur d'effectuer plusieurs opération sur les emprunts
     */
    private void menuEmprunt() {
        Integer menuEmprunt;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("|                   Menu Emprunt                         |");
            EntreesSorties.afficherMessage("| Emprunter Exemplaire :             1                   |");
            EntreesSorties.afficherMessage("| Rendre Exemplaire :                2                   |");
            EntreesSorties.afficherMessage("| Consulter Emprunts Lecteur :       3                   |");
            EntreesSorties.afficherMessage("| Relancer Lecteur :                 4                   |");
            EntreesSorties.afficherMessage("| Consulter Liste Emprunts :         5                   |");
            EntreesSorties.afficherMessage("| Retour Menu Principal :            0                   |");
            EntreesSorties.afficherMessage(" ========================================================");
            menuEmprunt = EntreesSorties.lireEntier();

            switch (menuEmprunt) {
                case 1: {
                    _bibliotheque.emprunterExemplaire();
                    break;
                }
                case 2: {
                    _bibliotheque.rendreExemplaire();
                    break;
                }
                case 3: {
                    _bibliotheque.consulterEmpruntsLecteur();
                    break;
                }
                case 4: {
                    _bibliotheque.relancerLecteur();
                    break;
                }
                case 5: {
                    _bibliotheque.consulterListeEmprunts();
                    break;
                }
                default: {
                    EntreesSorties.afficherTitre("Pas un choix.");
                    break;
                }
            }
        } while (menuEmprunt != 0);
    }

}
