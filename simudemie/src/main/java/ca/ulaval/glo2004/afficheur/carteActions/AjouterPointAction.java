/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.carteActions;

import java.awt.Polygon;

/**
 *
 * @author Jonathan
 */
public class AjouterPointAction extends ActionCarte {

    private Polygon polygon;
    private int x, y;
    
    public AjouterPointAction(Polygon polygon, int x, int y) {
        this.polygon = polygon;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void Executer() {
        polygon.addPoint(x, y);
    }

    @Override
    public void Undo() {        
        Polygon nouveau = new Polygon();
        // Ajoute tous les points, sauf le precedent
        for (int i = 0; i < polygon.npoints - 1; i++) {
            nouveau.addPoint(polygon.xpoints[i], polygon.ypoints[i]);
        }
        
        polygon.reset();
        for (int i = 0; i < nouveau.npoints; i++) {
            polygon.addPoint(nouveau.xpoints[i], nouveau.ypoints[i]);
        }
    }
}
