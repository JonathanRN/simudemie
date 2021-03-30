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
    private List<Carte> cartes;
    private Carte carteJourCourant;
    
    public Scenario(String nom, Carte carte, Maladie maladie) {
        this.nom = nom;
        cartes = new ArrayList<>();
        carteJourCourant = new Carte(carte);
        carteJourCourant.setMaladie(maladie);
    }
    
    public Carte getCarteJourCourant() {
        return carteJourCourant;
    }
    
    public int getIndexJourCourant() {
        return cartes.size() + 1;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void avancerJour() {
        carteJourCourant.avancerJour();
        cartes.add(carteJourCourant);
        carteJourCourant = new Carte(carteJourCourant);
    }
}
