package co.simplon.zombieDice;

import java.util.ArrayList;
import java.util.List;




public class Player {
    
    int compteurCerveaux;
    boolean gamestatus;
    String nom;
    Tour nouveauTour;
    Tour tourPrecedent;
    
    Coupe c;
    int compteurBang;
    int compteurPas;
    
    
    Tour jeterDe(Tour r) {
        for (int i = 0; i < 3; i++) {
            r.dc.get(i).valeur = r.dc.get(i).tirageDe();
        }
        return r;
    }

    Tour choisirDe() {
        
        nouveauTour = new Tour();

        if (tourPrecedent != null) {
            for (int i = 0; i < 3; i++) {
                if (tourPrecedent.dc.get(i).isPas()) {
                    nouveauTour.dc.add(tourPrecedent.dc.get(i));
                }

            }
        }

        for (int i = 0; i < 3; i++) {
            if (nouveauTour.dc.size() < 3) {
                nouveauTour.dc.add(c.piocherDe());
            }

        }

        return nouveauTour;

    }
    

    void miseAJourScore()
    {
        for(int i=0;i<3;i++)
        {
            if (nouveauTour.dc.get(i).isCerveaux())
                compteurCerveaux++;
            else if (nouveauTour.dc.get(i).isBang())
                compteurBang++;
            else 
                compteurPas++;
                
            
        }
        tourPrecedent = nouveauTour;
    }
    
    
    
    Player(String nm)
    {
        nom = nm;
        c = new Coupe();
        tourPrecedent = null;
        nouveauTour = new Tour();
    }
    

    
   
    
}
