/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine.controleur;

import ca.ulaval.glo2004.domaine.Maladie;
import ca.ulaval.glo2004.domaine.helper.FileHelper;

/**
 *
 * @author Mick
 */
public class GestionnaireMaladie extends GestionnaireOnglet<Maladie> {
    
    protected final String RELATIVE_PATH = "Maladies\\maladies.ser";
    
    private static GestionnaireMaladie instance;
    
    public static GestionnaireMaladie getInstance() {
        if(instance == null) {
            instance = new GestionnaireMaladie();
        }
        return instance;
    }
    
    private GestionnaireMaladie() {fileHelper = new FileHelper(RELATIVE_PATH); charger();}
    
    @Override
    public Maladie creer(Object... arguments) {
        int index = (int) arguments[0];
        String nom = (String) arguments[1];
        double tauxInf = (double) arguments[2];
        double tauxDead = (double) arguments[3];
        double tauxGue = (double) arguments[4];
        int incubation = (int) arguments[5];
        boolean immunitePossible = (boolean) arguments[6];
        
        Maladie maladie = getElement(index);
        
        if(maladie == null) {
            maladie = new Maladie(nom, tauxInf, tauxDead, tauxGue, incubation, immunitePossible);
            ajouter(maladie);
        } else {
            maladie.setNom(nom);
            maladie.setTauxInfection(tauxInf);
            maladie.setTauxGuerison(tauxGue);
            maladie.setTauxMortalite(tauxDead);
            maladie.setIncubation(incubation);
            maladie.setImmunitePossible(immunitePossible);
            sauvegarder();
        }
        
        return maladie;
    }
}
