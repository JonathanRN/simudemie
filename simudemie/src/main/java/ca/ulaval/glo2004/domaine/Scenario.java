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
    
    public Scenario(String nom, Carte carte, Maladie maladie) {
        this.nom = nom;
        
        // Ajoute la carte en parametre comme jour 1
        carte.setMaladie(maladie);
        cartes.add(new Carte(carte));
        indexCourant = 0;
    }
    
    public Carte getCarteJourCourant() {
        return cartes.get(indexCourant);
    }
    
    public void chargerJour(int index) {
        indexCourant = index;
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
        getCarteJourCourant().avancerJour();
        
        // Ajoute une nouvelle journee selon les nouvelles donnees modifies de la carte courante
        cartes.add(new Carte(getCarteJourCourant()));
    }
}
