/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.carteActions;

import ca.ulaval.glo2004.afficheur.CreationCarte.CreationCartePanel;
import java.awt.Polygon;

/**
 *
 * @author Jonathan
 */
public class AjouterPointAction extends ActionCarte {

    private int x, y;
    private CreationCartePanel cc;
    
    public AjouterPointAction(int x, int y, CreationCartePanel cc) {
        this.x = x;
        this.y = y;
        this.cc = cc;
    }
    
    @Override
    public void Executer() {
        cc.getCourant().addPoint(x, y);
    }

    @Override
    public void Undo() {
        // Ajoute tous les points, sauf le precedent
        Polygon courantSaufPrecedent = new Polygon();
        for (int i = 0; i < cc.getCourant().npoints - 1; i++) {
            courantSaufPrecedent.addPoint(cc.getCourant().xpoints[i], cc.getCourant().ypoints[i]);
        }
        cc.setCourant(courantSaufPrecedent);
    }
}
