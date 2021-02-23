/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;


public class Mesure {
    
    private String nom;
    private float tauxAdhesion;
    private float tauxReductionProp;
    
    Mesure()
    {
        //TODO Constructeur Mesure
    }
    
    public String getNom(){return nom;}
    
    public float getTauxAdhesion(){return tauxAdhesion;}
    
    public float getTauxReductionProp(){return tauxReductionProp;}
    
    private void setNom(String nom)
    {
        this.nom = nom;
    }
    
    private void setTauxAdhesion(float tauxAdhesion)
    {
        this.tauxAdhesion = tauxAdhesion;
    }
        
    private void setTauxReductionProp(float tauxReductionProp)
    {
        this.tauxReductionProp = tauxReductionProp;
    }
}
