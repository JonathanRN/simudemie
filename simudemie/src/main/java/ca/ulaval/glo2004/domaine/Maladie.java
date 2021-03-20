/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.io.Serializable;


public class Maladie implements Serializable {

    private String nom;
    private float tauxInfection;
    private float tauxMortalite;
    private float tauxGuerison;
    
    public Maladie(String nom, float tauxInfection, float tauxMortalite, float tauxGuerison)
    {
        this.nom = nom;
        this.tauxInfection = tauxInfection;
        this.tauxMortalite = tauxMortalite;
        this.tauxGuerison = tauxGuerison;
    }
    
    public String getNom(){return nom;}
    
    public float getTauxInfection(){return tauxInfection;}
    
    public float getTauxMortalite(){return tauxMortalite;}
    
    public float getTauxGuerison(){return tauxGuerison;}
    
    private void setNom(String nom)
    {
        this.nom = nom;
    }
    
    private void setTauxInfection(float tauxInfection)
    {
        this.tauxInfection = tauxInfection;
    }
        
    private void setTauxMortalite(float tauxMortalite)
    {
        this.tauxMortalite = tauxMortalite;
    }
    
    private void setTauxGuerison(float tauxGuerison)
    {
        this.tauxGuerison = tauxGuerison;
    }
        
}
