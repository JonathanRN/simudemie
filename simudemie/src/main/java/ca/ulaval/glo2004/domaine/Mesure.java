/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.io.Serializable;


public class Mesure implements Serializable{
    
    private String nom;
    private float tauxAdhesion;
    private float tauxReductionProp;
    
    public Mesure(String nom, float tauxAdhesion, float tauxReductionProp)
    {
        this.nom = nom; 
        this.tauxAdhesion = tauxAdhesion;
        this.tauxReductionProp = tauxReductionProp;
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
