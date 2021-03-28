/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.carteActions;

import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.VoieLiaison;

/**
 *
 * @author Jonathan
 */
public class AjouterLienAction extends ActionCarte {

    private final VoieLiaison voie;
    private final Carte carte;

    public AjouterLienAction(VoieLiaison voie, Carte carte) {
        this.voie = voie;
        this.carte = carte;
    }
    
    @Override
    public void Executer() {
        carte.ajouterVoie(voie);
    }

    @Override
    public void Undo() {
        carte.retirerVoie(voie);
    }
}
