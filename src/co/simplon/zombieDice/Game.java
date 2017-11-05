package co.simplon.zombieDice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {

    boolean gameOver;

    int nombreDeJoueur;

    List<Player> players = new ArrayList<Player>();

    Player joueurActif;
    int changementJoueur;

    Tour nouveauTour;
    Tour tourPrecedent;

    Game() {
        changementJoueur = 0;
    }

    void initialisation() {
        gameOver = false;

    }

    void lancerPartie() {
        System.out.println("ZOMBIE DICE");

        Scanner scanNbJoueur = new Scanner(System.in);
        System.out.println("Entrer le nombre de joueurs:");
        nombreDeJoueur = scanNbJoueur.nextInt();

        for (int i = 1; i <= nombreDeJoueur; i++) {
            Scanner scanNomJoueur = new Scanner(System.in);
            System.out.println("Entrer le nom du joueur " + i);
            String nom = scanNomJoueur.next();
            Player p = new Player(nom);
            players.add(p);
        }

        joueurActif = players.get(changementJoueur);
        afficherScore();

        Scanner scanLireEntreeClavier = new Scanner(System.in);
        String entreeUtilisateur;
        while (!gameOver) {
            joueurActif.compteurPas = 0;
            System.out.println("_______________________________________________________________________");
            System.out.println("C'est à " + joueurActif.nom + " de jouer");
            System.out.println("\nAppuyer sur P pour piocher les Dé.");
            entreeUtilisateur = scanLireEntreeClavier.next();
            if (entreeUtilisateur.equalsIgnoreCase("P")) {
                boolean mort = false;
                boolean allrunner = false;
                nouveauTour = joueurActif.choisirDe();
                System.out.println("\nVos Dé pour ce tour sont: ");
                afficherDePiocher();
                while (!entreeUtilisateur.equalsIgnoreCase("L")) {
                    System.out.println("\nAppuyer sur L pour lancer les Dé. ");
                    entreeUtilisateur = scanLireEntreeClavier.next();
                    if (entreeUtilisateur.equalsIgnoreCase("L")) {
                        joueurActif.jeterDe(nouveauTour);
                        afficherDeValeur();
                        joueurActif.miseAJourScore();

                        mort = checkBangEncaisse();
                      

                        gameOver = checkGameOver();
                       

                        allrunner = checkCompteurPas();
                       
                        afficherScore();
                          if (mort) {
                            joueurSuivant();
                        }
                           if (allrunner) {
                            joueurSuivant();
                        }
                            if (gameOver) {
                            System.out.println("GAME OVER");
                            System.out.println(joueurActif.nom + " est le gagnant.");
                        }
                        Scanner continueOption = new Scanner(System.in);
                        if (!mort) {
                            while (true) {
                                System.out.println("\nVoulez-vous continuer? O / N");
                                String continueTurn = continueOption.next();
                                if (continueTurn.equalsIgnoreCase("N")) {
                                    joueurSuivant();
                                    break;
                                }
                                if (continueTurn.equalsIgnoreCase("O")) {
                                    break;
                                }
                                
                            }
                        }
                    }
                }
            }
        }

    }

    void afficherScore() {
        System.out.println("\n\n\t\t\tT A B L E A U    D E    S C O R E\n");
        System.out.println("Players  \t\tTotal De Cerveaux Mangés \t\tBang encaissés");
        for (int i = 0; i < nombreDeJoueur; i++) {
            System.out.println(players.get(i).nom + "\t\t\t\t" + players.get(i).compteurCerveaux + "\t\t\t\t" + players.get(i).compteurBang);

        }
        System.out.println("\n");
    }

    private void afficherDePiocher() {
        for (int i = 0; i < 3; i++) {
            System.out.println(joueurActif.nouveauTour.dc.get(i).getCouleur());
        }
        System.out.print("\n");
    }

    private void afficherDeValeur() {

        for (int i = 0; i < 3; i++) {
            System.out.println(joueurActif.nouveauTour.dc.get(i).getCouleur() + "\t" + joueurActif.nouveauTour.dc.get(i).getTirageValeur());
        }
        System.out.println("\n");

    }

    private boolean checkBangEncaisse() {
        if (joueurActif.compteurBang >= 3) {
            System.out.println("\nTu t'es fais tirer 3 fois dessus. Tu es mort. Changement de joueur.");
            joueurActif.compteurCerveaux = 0;
            return true;
        }
        return false;
    }

    private void joueurSuivant() {

        joueurActif.compteurBang = 0;

        changementJoueur = (changementJoueur + 1) % nombreDeJoueur;
        joueurActif = players.get(changementJoueur);
    }

    private boolean checkCompteurPas() {
        if (joueurActif.compteurPas >= 3) {
            System.out.println("Toutes les cibles ont fuit. Changement de joueur.");
            return true;
        }
        return false;
    }

    private boolean checkGameOver() {
        if (joueurActif.compteurCerveaux >= 13) {
            return true;
        }
        return false;
    }

}
