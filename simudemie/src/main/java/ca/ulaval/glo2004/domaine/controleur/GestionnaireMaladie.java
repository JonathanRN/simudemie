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
    // TODO: Mettre constantes dans un fichier de param .ini peut-être ?
    protected final String PATH = "C:\\test\\maladies.ser";
    
    public GestionnaireMaladie() {
        fileHelper = new FileHelper(PATH);
        charger();
    }
    
    @Override
    public Maladie creer(Object... arguments) {
        String nom = (String) arguments[0];
        double tauxInf = (double) arguments[1];
        double tauxDead = (double) arguments[2];
        double tauxGue = (double) arguments[3];
        Maladie maladie = new Maladie(nom, tauxInf, tauxDead, tauxGue);
        ajouter(maladie);
        return maladie;
    }
}
