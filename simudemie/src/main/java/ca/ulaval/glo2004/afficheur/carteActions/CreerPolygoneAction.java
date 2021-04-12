/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.carteActions;

import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Pays;
import java.awt.Polygon;

/**
 *
 * @author Jonathan
 */
public class CreerPolygoneAction extends ActionCarte {
    
    private final Carte carte;
    private final Pays pays;
    private final Polygon courant;
    
    public CreerPolygoneAction(Carte carte, Pays pays, Polygon courant) {
        this.carte = carte;
        this.pays = pays;
        this.courant = courant;
    }
    
    @Override
    public void Executer() {
        pays.ajouterRegion(new ca.ulaval.glo2004.domaine.Region(pays.getPolygone()));
        carte.ajouterPays(pays);
    }

    @Override
    public void Undo() {
        pays.retirerRegion(pays.getRegion(pays.getPolygone()));
        carte.retirerPays(pays);
        
        // Rajout des points dans le polygone
        Polygon p = pays.getPolygone();
        courant.reset();
        for (int i = 0; i < p.npoints; i++) {
            courant.addPoint(p.xpoints[i], p.ypoints[i]);
        }
    }
}
