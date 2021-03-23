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
public class GestionnaireCreationMaladie extends GestionnaireCreation<Maladie> {
    // TODO: Mettre constantes dans un fichier de param .ini peut-être ?
    protected final String PATH = "C:\\test\\maladies.ser";
    
    private static GestionnaireCreationMaladie instance;
    
    public static GestionnaireCreationMaladie getInstance() {
        if (instance == null) {
            instance = new GestionnaireCreationMaladie();
        }
        return instance;
    }
    
    private GestionnaireCreationMaladie() {
        fileHelper = new FileHelper(PATH);
        charger();
    }
    
    @Override
    protected Maladie creer(Object... parametre) {
        return null;
    }
    
    public Maladie creerMaladie(String nom, double tauxInf, double tauxMort, double tauxGue) {
        Maladie maladie = new Maladie(nom, tauxInf, tauxMort, tauxGue);
        ajouter(maladie);
        return maladie;
    }

    @Override
    protected Maladie getElement(String nom) {
        Maladie retMal = null;
        for(Maladie maladie : getCollection()) {
            if(maladie.getNom().equals(nom)) {
                retMal = maladie;
            }
        }
        return retMal;
    }
}
