/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.carteActions;

import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireScenario;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class CreerPolygoneAction extends ActionCarte {
    
    private ArrayList<Polygon> polygons;
    private int index;
    
    public CreerPolygoneAction(int index, ArrayList<Polygon> polygons) {
        this.index = index;
        this.polygons = polygons;
    }
    
    @Override
    public void Executer() {
        GestionnaireScenario.GetInstance().creerPays(index, new Pays("test carte", 0, polygons.get(polygons.size() - 1)));
        polygons.add(new Polygon());
    }

    @Override
    public void Undo() {
        polygons.remove(polygons.get(polygons.size() - 1));
    }
}
