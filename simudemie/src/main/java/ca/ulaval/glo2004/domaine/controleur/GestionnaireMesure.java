/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine.controleur;

import ca.ulaval.glo2004.domaine.Mesure;
import ca.ulaval.glo2004.domaine.helper.FileHelper;

/**
 *
 * @author Mick
 */
public class GestionnaireMesure extends GestionnaireOnglet<Mesure> {
    protected final String RELATIVE_PATH = "Mesures\\mesures.ser";
    
    public GestionnaireMesure() {
        fileHelper = new FileHelper(RELATIVE_PATH);
        charger();
    }
    
    @Override
    public Mesure creer(Object... arguments) {
        String nom = (String) arguments[0];
        float tauxAdhesion = (float) arguments[1];
        float tauxReductionProp = (float) arguments[2];
        
        Mesure mesure = new Mesure(nom, tauxAdhesion, tauxReductionProp);
        ajouter(mesure);
        return mesure;
    }

    @Override
    protected Mesure getElement(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
