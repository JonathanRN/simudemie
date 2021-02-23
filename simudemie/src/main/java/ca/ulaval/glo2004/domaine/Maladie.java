/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;


public class Maladie {

    private String nom;
    private float tauxInfection;
    private float tauxMortalite;
    private float tauxGuerison;
    
    public Maladie()
    {
        //TODO Constructeur Maladie
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
