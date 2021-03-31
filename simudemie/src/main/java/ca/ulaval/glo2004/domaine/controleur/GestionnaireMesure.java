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
        int index = (int) arguments[0];
        String nom = (String) arguments[1];
        float tauxAdhesion = (float) arguments[2];
        float tauxReduction = (float) arguments[3];
        
        Mesure mesure = getElement(index);
        
        if(mesure == null) {
            mesure = new Mesure(nom, tauxAdhesion, tauxReduction);
            ajouter(mesure);
        } else {
            mesure.setNom(nom);
            mesure.setTauxAdhesion(tauxAdhesion);
            mesure.setTauxReductionProp(tauxReduction);
            sauvegarder();
        }
        
        
        return mesure;
    }
}
