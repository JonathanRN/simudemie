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
        carte.setMaladie(new Maladie(maladie));
        cartes.add(new Carte(carte));
    }
    public void initialisePopInit()
    {
        cartes.get(0).initiliserPopInit();
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
    
    public int getTotalJours() {
        return cartes.size() - 1;
    }
    
    public Carte getCarteDernierJour() {
        return cartes.get(getTotalJours());
    }
    
    public void chargerJour(int index) {
        indexCourant = index;
    }
    
    public void stepJour(int step) {
        if (step == -1 && indexCourant > 0) {
            chargerJour(indexCourant - 1);
        }
        else if (step == 1 && indexCourant < cartes.size() - 1) {
            chargerJour(indexCourant + 1);
        }
    }
    
    public List<Carte> getListeCartes() {
        return cartes;
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
        indexCourant = getTotalJours();
    }
    
    public int avancerJour() {
        // Ajoute une nouvelle journee selon les nouvelles donnees modifies de la carte courante
        cartes.add(new Carte(getCarteJourCourant()));
        
        indexCourant++;
        getCarteJourCourant().avancerJour(indexCourant);
        
        return indexCourant;
    }
}
