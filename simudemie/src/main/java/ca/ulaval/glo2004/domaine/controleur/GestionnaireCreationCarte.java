/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine.controleur;

import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.helper.FileHelper;
import java.awt.Polygon;

/**
 *
 * @author Mick
 */
public class GestionnaireCreationCarte extends GestionnaireCreation<Carte> {
    // TODO: Mettre constantes dans un fichier de param .ini peut-être ?
    protected final String PATH = "C:\\test\\cartes.ser";
    
    private static GestionnaireCreationCarte instance;
    
    public static GestionnaireCreationCarte getInstance() {
        if(instance == null) {
            instance = new GestionnaireCreationCarte();
        }
        return instance;
    }
    
    private GestionnaireCreationCarte() {
        fileHelper = new FileHelper(PATH);
        charger();
    }
    
    @Override
    public Carte creer(Object... arguments) {
        String nom = (String) arguments[0];
        Carte carte = new Carte(nom);
        ajouter(carte);
        return carte;
    }
   
    public void creerPays(int index, Pays pays) {
        getElement(index).ajouterPays(pays);
    }
    
    public Pays getPays(int indexCarte, int indexPays) {
        return getElement(indexCarte).getPays(indexPays);
    }
    
    public Pays getPays(int indexCarte, Polygon polygon) {
        return getElement(indexCarte).getPays(polygon);
    }
}
