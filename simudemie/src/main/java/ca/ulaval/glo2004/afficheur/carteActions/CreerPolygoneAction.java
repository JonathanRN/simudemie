/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.carteActions;

import ca.ulaval.glo2004.afficheur.CreationCarte.panels.CreationCartePanel;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Pays;
import java.awt.Polygon;

/**
 *
 * @author Jonathan
 */
public class CreerPolygoneAction extends ActionCarte {
    
    private final Carte carte;
    private final CreationCartePanel cc;
    private Pays pays;
    
    public CreerPolygoneAction(Carte carte, CreationCartePanel cc) {
        this.carte = carte;
        this.cc = cc;
    }
    
    @Override
    public void Executer() {
        pays = new Pays(cc.getCourant());
        pays.ajouterRegion(new ca.ulaval.glo2004.domaine.Region(pays.getPolygone()));
        carte.ajouterPays(pays);
        
        // On cree un nouveau tout de suite pour y ajouter des points eventuellement
        cc.setCourant(new Polygon());
    }

    @Override
    public void Undo() {
        pays.retirerRegion(pays.getRegion(pays.getPolygone()));
        carte.retirerPays(pays);
        
        cc.setCourant(pays.getPolygone());
    }
}
