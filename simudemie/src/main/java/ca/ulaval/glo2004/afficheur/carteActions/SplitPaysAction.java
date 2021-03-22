/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.carteActions;

import ca.ulaval.glo2004.afficheur.CreationCarte.CreationCartePanel;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.Region;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireScenario;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class SplitPaysAction extends ActionCarte {

    private final CreationCartePanel panel;
    private final Polygon polygon, gauche, droit;
    private Pays pays;
    private ArrayList<Polygon> polygones;
    
    private Region regionGauche, regionDroite;

    public SplitPaysAction(Polygon p, Polygon gauche, Polygon droit, CreationCartePanel panel) {
        this.polygon = p;
        this.gauche = gauche;
        this.droit = droit;
        this.panel = panel;
    }
    
    @Override
    public void Executer() {
        polygones = panel.getPolygones();
        pays = GestionnaireScenario.GetInstance().getPays(panel.getIndexCarte(), polygon);
        int index = polygones.indexOf(polygon);
        
        polygones.remove(index);

        polygones.add(index, gauche);
        polygones.add(index + 1, droit);

        regionGauche = new Region(gauche);
        regionDroite = new Region(droit);
        
        pays.ajouterRegion(regionGauche);
        pays.ajouterRegion(regionDroite);
        
        pays.retirerRegion(pays.getRegions().stream().filter(x -> x.getPolygone().equals(polygon)).findFirst().get());
    }

    @Override
    public void Undo() {
        pays.retirerRegion(regionGauche);
        pays.retirerRegion(regionDroite);
        
        polygones.add(polygones.indexOf(gauche), pays.getPolygone());
        
        polygones.remove(gauche);
        polygones.remove(droit);
    }
}
