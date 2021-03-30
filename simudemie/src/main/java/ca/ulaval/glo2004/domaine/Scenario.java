/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mick
 */
public class Scenario implements Serializable {
    private String nom;
    private final List<Carte> cartes = new ArrayList<>();;
    private int indexCourant;
    private boolean estCommence;
    
    public Scenario(String nom, Carte carte, Maladie maladie) {
        this.nom = nom;
        
        // Ajoute la carte en parametre comme jour 0
        carte.setMaladie(maladie);
        cartes.add(new Carte(carte));
    }
    
    public boolean estCommence() {
        return estCommence;
    }
    
    public Carte getCarteJourCourant() {
        return cartes.get(indexCourant);
    }
    
    public int getIndexJourCourant() {
        return indexCourant;
    }
    
    public void chargerJour(int index) {
        indexCourant = index;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void demarrer() {
        estCommence = true;
        retourEnDirect();
    }
    
    public void retourEnDirect() {
        indexCourant = cartes.size() - 1;
    }
    
    public int avancerJour() {
        // Ajoute une nouvelle journee selon les nouvelles donnees modifies de la carte courante
        cartes.add(new Carte(getCarteJourCourant()));
        
        indexCourant++;
        getCarteJourCourant().avancerJour();
        
        return indexCourant;
    }
}
