/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.io.Serializable;


public class Mesure implements Serializable{
    
    private String nom;
    private double tauxAdhesion;
    private double tauxReductionProp;
    private boolean active;
    
    public Mesure(String nom, double tauxAdhesion, double tauxReductionProp)
    {
        this.nom = nom; 
        this.tauxAdhesion = tauxAdhesion;
        this.tauxReductionProp = tauxReductionProp;
        active = false;
    }
    
    public Mesure(Mesure mesure)
    {
        this.nom = mesure.nom; 
        this.tauxAdhesion = mesure.tauxAdhesion;
        this.tauxReductionProp = mesure.tauxReductionProp;
        active = false;
    }
    
    public String getNom(){return nom;}
    
    public double getTauxAdhesion(){return tauxAdhesion;}
    
    public double getTauxReductionProp(){return tauxReductionProp;}
    
    public boolean getActive() {
        return active;
    }
    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    public void setTauxAdhesion(double tauxAdhesion)
    {
        this.tauxAdhesion = tauxAdhesion;
    }
        
    public void setTauxReductionProp(double tauxReductionProp)
    {
        this.tauxReductionProp = tauxReductionProp;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
}
