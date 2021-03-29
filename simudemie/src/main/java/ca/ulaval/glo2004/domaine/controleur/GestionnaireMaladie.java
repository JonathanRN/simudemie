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
    
    private GestionnaireMaladie() {
        fileHelper = new FileHelper(RELATIVE_PATH);
        charger();
    }
    
    @Override
    public Maladie creer(Object... arguments) {
        String nom = (String) arguments[0];
        double tauxInf = (double) arguments[1];
        double tauxDead = (double) arguments[2];
        double tauxGue = (double) arguments[3];
        
        Maladie maladie = getElement(nom);
        
        if(maladie == null) {
            maladie = new Maladie(nom, tauxInf, tauxDead, tauxGue);
            ajouter(maladie);
        } else {
            maladie.setNom(nom);
            maladie.setTauxInfection(tauxInf);
            maladie.setTauxGuerison(tauxGue);
            maladie.setTauxMortalite(tauxDead);
            sauvegarder();
        }
        
        return maladie;
    }
    
    public Maladie getElement(String nom) {
        Maladie maladie = null;
        for(Maladie m : getList()) {
            if(m.getNom().equals(nom)) {
                maladie = m;
            }
        }
        return maladie;
    }
}
