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
    
    private ArrayList<Polygon> polygons;
    
    public DessinerPolygoneAction(ArrayList<Polygon> polygons) {
        this.polygons = polygons;
    }
    
    @Override
    public void Executer() {
        polygons.add(new Polygon());
    }

    @Override
    public void Undo() {
        polygons.remove(polygons.get(polygons.size() - 1));
    }
}
