/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine.controleur;

import ca.ulaval.glo2004.domaine.Maladie;
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
import java.util.List;

/**
 *
 * @author Mick
 */
public class GestionnaireCreation {
    // TODO: Mettre constantes dans un fichier de param .ini peut-être ?
    private final String MALADIE_PATH = "C:\\test";
            
    private List<Maladie> maladies;
    
    public GestionnaireCreation()
    {
        maladies = new ArrayList<>();
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
        sauvegarderMaladie(maladie);
    }
    
    public void chargerMaladies() throws ClassNotFoundException {
        Maladie maladie;
        try {
            File folder = new File(MALADIE_PATH);
            FilenameFilter filter = (File f, String name) -> name.endsWith("_mal.ser");
            
            for(File file : folder.listFiles(filter)) {
                try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
                    maladie = (Maladie) ois.readObject();
                    maladies.add(maladie);
                }
            }
                
        } catch(IOException | ClassNotFoundException e) {
             // TODO: Afficher erreur de chargement avec petite boîte cute
        }
    }
    
    private void sauvegarderMaladie(Maladie maladie) {
        try {
            // TODO probleme de compilation avec Path.of
            //Path path = Path.resolve(MALADIE_PATH, maladie.getNom() + "_mal.ser");
            String path = String.format(MALADIE_PATH + "\\%s", maladie.getNom() + "_mal.ser");
            System.out.println(path.toString());
            try (FileOutputStream fos = new FileOutputStream(path.toString()); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(maladie);
            }
        } catch(IOException ioe) {
            // TODO: Afficher erreur de sauvegarde avec petite boîte cute
        }
    }
    
    /*
    
    Il va falloir faire des méthodes d'ajout de 
    
    Pays -> Carte
    VoieLiaison -> Pays
    Region -> Pays
    Region -> listeDeVoisins
    
    Et la même chose pour les retraits
    
    */
}
