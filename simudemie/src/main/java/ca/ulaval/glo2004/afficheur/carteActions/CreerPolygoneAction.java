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
    private Pays pays;
    
    public CreerPolygoneAction(int index, Pays pays, ArrayList<Polygon> polygons) {
        this.index = index;
        this.pays = pays;
        this.polygons = polygons;
    }
    
    @Override
    public void Executer() {
        GestionnaireScenario.GetInstance().creerPays(index, pays);
        polygons.add(new Polygon());
    }

    @Override
    public void Undo() {
        polygons.remove(polygons.get(polygons.size() - 1));
    }
}
