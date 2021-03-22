/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine.controleur;

import ca.ulaval.glo2004.afficheur.onglets.OngletCarte;
import ca.ulaval.glo2004.afficheur.onglets.OngletMaladie;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Maladie;
import ca.ulaval.glo2004.domaine.helper.FileHelper;
import java.util.Collection;

/**
 *
 * @author Mick
 */
public class GestionnaireCreation {
    // TODO: Mettre constantes dans un fichier de param .ini peut-être ?
    private final String MALADIE_PATH = "C:\\test\\maladies.ser";
    private final String CARTE_PATH = "C:\\test\\cartes.ser";
            
    private FileHelper<Maladie> fileHelperMaladie;
    private FileHelper<Carte> fileHelperCarte;
    private Collection<Maladie> maladies;
    
    
    private static GestionnaireCreation instance;
    
    public static GestionnaireCreation getInstance() {
        if (instance == null) {
            instance = new GestionnaireCreation();
        }
        return instance;
    }
    
    private GestionnaireCreation()
    {
        fileHelperMaladie = new FileHelper(MALADIE_PATH);
        fileHelperCarte = new FileHelper(CARTE_PATH);
        chargerMaladies();
    }
    /* ONGLET MALADIE */
    
    public Maladie creerMaladie(String nom, double tauxInf, double tauxMort, double tauxGuerison)
    {   
        Maladie maladie = new Maladie(nom, tauxInf, tauxMort, tauxGuerison);
        maladies.add(maladie);
        sauvegarderMaladies();
        
        return maladie;
    }
    
    public void chargerMaladies() {
        maladies = fileHelperMaladie.charger();
    }
    
    public void supprimerMaladie(String nom) {
        maladies.removeIf(m -> m.getNom().equals(nom));
        sauvegarderMaladies();
    }
    
    private void sauvegarderMaladies() {
        fileHelperMaladie.sauvegarder(maladies);
    }
    
    public void importerMaladie() {
        Maladie maladie = fileHelperMaladie.importer();
        maladies.add(maladie);
        sauvegarderMaladies();
    }
    
    public void exporterMaladie(String nom) {
        for(Maladie maladie : maladies) {
            if(maladie.getNom().equals(nom)) {
                fileHelperMaladie.exporter(maladie);
            }
        }
    }
    
    public Collection<Maladie> getMaladies() {
        return maladies;
    }
    
    /* ONGLET CARTE */
}
