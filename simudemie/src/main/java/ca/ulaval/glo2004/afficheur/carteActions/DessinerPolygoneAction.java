/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.carteActions;

import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class DessinerPolygoneAction extends ActionCarte {

    private Polygon polygon;
    private ArrayList<Polygon> polygons;
    
    public DessinerPolygoneAction(Polygon polygon, ArrayList<Polygon> polygons) {
        this.polygon = polygon;
        this.polygons = polygons;
    }
    
    @Override
    public void Executer() {
        // Cree une nouvelle copie a inserer
        Polygon nouveau = new Polygon();
        for (int i = 0; i < polygon.npoints; i++) {
            nouveau.addPoint(polygon.xpoints[i], polygon.ypoints[i]);
        }
        polygons.add(nouveau);
    }

    @Override
    public void Undo() {
        polygons.remove(polygons.get(polygons.size() - 1));
    }
}
