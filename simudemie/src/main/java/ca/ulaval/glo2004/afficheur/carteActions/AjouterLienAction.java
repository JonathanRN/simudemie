/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.carteActions;

import ca.ulaval.glo2004.domaine.VoieLiaison;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireCarte;

/**
 *
 * @author Jonathan
 */
public class AjouterLienAction extends ActionCarte {

    private final VoieLiaison voie;
    private final int carteIndex;

    public AjouterLienAction(VoieLiaison voie, int carteIndex) {
        this.voie = voie;
        this.carteIndex = carteIndex;
    }
    
    @Override
    public void Executer() {
        GestionnaireCarte.getInstance().getElement(carteIndex).ajouterVoie(voie);
    }

    @Override
    public void Undo() {
        GestionnaireCarte.getInstance().getElement(carteIndex).retirerVoie(voie);
    }
}
