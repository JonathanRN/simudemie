/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine.controleur;

import ca.ulaval.glo2004.afficheur.onglets.OngletMaladie;
import ca.ulaval.glo2004.domaine.Maladie;
import ca.ulaval.glo2004.domaine.helper.FileHelper;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Mick
 */
public class GestionnaireCreation {
    // TODO: Mettre constantes dans un fichier de param .ini peut-être ?
    private final String MALADIE_PATH = "C:\\test\\maladies.ser";
            
    private FileHelper<Maladie> fileHelper;
    private Collection<Maladie> maladies;
    
    public GestionnaireCreation()
    {
        fileHelper = new FileHelper(MALADIE_PATH);
        chargerMaladies();
    }
    
    public void creerCarte(String nom)
    {
        //TODO Appel au constructeur de carte
    }
    
    public void creerPays(String nom)
    {
        //TODO Appel au constructeur 
    }
    
    public void creerRegion(String nom, int popSaine)
    {
        //TODO Appel au constructeur 
    }
            
    public void creerMaladie(String nom, float tauxInf, float tauxMort, float tauxGuerison)
    {
        Maladie maladie = new Maladie(nom, tauxInf, tauxMort, tauxGuerison);
        maladies.add(maladie);
        sauvegarderMaladies();
    }
    
    public void chargerMaladies() {
        maladies = fileHelper.charger();
    }
    
    public void supprimerMaladie(String nom) {
        maladies.removeIf(m -> m.getNom().equals(nom));
        sauvegarderMaladies();
    }
    
    private void sauvegarderMaladies() {
        fileHelper.sauvegarder(maladies);
    }
    
    public void importerMaladie() {
        Maladie maladie = fileHelper.importer();
        maladies.add(maladie);
        sauvegarderMaladies();
    }
    
    public void exporterMaladie(String nom) {
        for(Maladie maladie : maladies) {
            if(maladie.getNom().equals(nom)) {
                fileHelper.exporter(maladie);
            }
        }
    }
    
    /*  Liaison avec la vue   */
    
    
    /*
    
    Il va falloir faire des méthodes d'ajout de 
    
    Pays -> Carte
    VoieLiaison -> Pays
    Region -> Pays
    Region -> listeDeVoisins
    
    Et la même chose pour les retraits
    
    */
}
