/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.io.Serializable;



public class Maladie implements Serializable {

    private String nom;
    private double tauxInfection;
    private double tauxMortalite;
    private double tauxGuerison;
    
    public Maladie(String nom, double tauxInfection, double tauxMortalite, double tauxGuerison)
    {
        this.nom = nom;
        this.tauxInfection = tauxInfection;
        this.tauxMortalite = tauxMortalite;
        this.tauxGuerison = tauxGuerison;
    }
    
    public String getNom(){return nom;}
    
    public double getTauxInfection(){return tauxInfection;}
    
    public double getTauxMortalite(){return tauxMortalite;}
    
    public double getTauxGuerison(){return tauxGuerison;}
    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    public void setTauxInfection(double tauxInfection)
    {
        this.tauxInfection = tauxInfection;
    }
        
    public void setTauxMortalite(double tauxMortalite)
    {
        this.tauxMortalite = tauxMortalite;
    }
    
    public void setTauxGuerison(double tauxGuerison)
    {
        this.tauxGuerison = tauxGuerison;
    }
    
     
}
