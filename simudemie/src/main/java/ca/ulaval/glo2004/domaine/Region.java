/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.awt.Polygon;

public class Region {
    
    private String nom;
    private int populationSaine;
//    private int populationImmune;
    private int populationInfectee;
    private int populationDecedee;
    private Polygon polygone;
    
    public Region(Polygon polygone)
    {
        this.polygone = polygone;
    }
    
//    public void deplacementRegions()
//    {
//        //TODO Déplacement des populations entre les régions
//    }
    
    public void contaminer(float taux)
    {
        int nouveauxInfectes  = (int)(populationSaine * taux);
        setPopSaine(populationSaine - nouveauxInfectes);
        setPopInfectee(populationInfectee + nouveauxInfectes);
    }
    
    public void eliminerPopulation(float taux)
    {
        int deces  = (int)(populationInfectee * taux);
        setPopInfectee(populationInfectee - deces);
        setPopDecedee(populationDecedee + deces);
    }
    
    public void guerirPop(float taux)
    {
        int gueris = (int)(populationInfectee * taux);
        setPopInfectee(populationInfectee - gueris);
        setPopSaine(populationSaine + gueris);
    }
    
    public String getNom(){return nom;}
    
    public int getPopTotale() { return populationSaine + populationInfectee; }
    
    public int getPopSaine(){return populationSaine;}
    
//    public int getPopImmunisee(){return populationImmune;}
    
    public int getPopInfectee(){return populationInfectee;}
    
    public int getPopDecedee(){return populationDecedee;}
    
    public Polygon getPolygone() {
        return polygone;
    }
    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    public void setPopSaine(int populationSaine)
    {
        this.populationSaine = populationSaine;
    }
    
//    private void setPopImmune(int populationImmune)
//    {
//        this.populationImmune = populationImmune;
//    }
        
    private void setPopInfectee(int populationInfectee)
    {
        this.populationInfectee = populationInfectee;
    }
            
    private void setPopDecedee(int populationDecedee)
    {
        this.populationDecedee = populationDecedee;
    }
    
}
