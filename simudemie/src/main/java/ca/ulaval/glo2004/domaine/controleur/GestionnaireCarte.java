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
public class GestionnaireCarte extends GestionnaireOnglet<Carte> {
    protected final String RELATIVE_PATH = "Cartes\\cartes.ser";
    
    private static GestionnaireCarte instance;
    
    public static GestionnaireCarte getInstance() {
        if(instance == null) {
            instance = new GestionnaireCarte();
        }
        return instance;
    }
    
    private GestionnaireCarte() {
        fileHelper = new FileHelper(RELATIVE_PATH);
        charger();
    }
    
    @Override
    public Carte creer(Object... arguments) {
        String nom = (String) arguments[0];
        Carte carte = new Carte(nom);
        ajouter(carte);
        return carte;
    }
    
    public Pays getPays(int indexCarte, int indexPays) {
        return getElement(indexCarte).getPays(indexPays);
    }
    
    public Pays getPays(int indexCarte, Polygon polygon) {
        return getElement(indexCarte).getPays(polygon);
    }

    @Override
    protected Carte getElement(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
