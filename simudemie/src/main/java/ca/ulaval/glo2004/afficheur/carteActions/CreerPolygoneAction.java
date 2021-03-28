/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.carteActions;

import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Pays;

/**
 *
 * @author Jonathan
 */
public class CreerPolygoneAction extends ActionCarte {
    
    private final Carte carte;
    private final Pays pays;
    
    public CreerPolygoneAction(Carte carte, Pays pays) {
        this.carte = carte;
        this.pays = pays;
    }
    
    @Override
    public void Executer() {
        pays.ajouterRegion(new ca.ulaval.glo2004.domaine.Region(pays.getPolygone()));
        carte.ajouterPays(pays);
    }

    @Override
    public void Undo() {
        // TODO
    }
}
