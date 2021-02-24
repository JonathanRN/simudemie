/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.awt.Polygon;
import java.util.List;

public class Region {
    
    private String nom;
    private int populationSaine;
    private int populationImmune;
    private int populationInfectee;
    private int populationDecedee;
    private List<Region> regionVoisines;
    private Polygon polygone;
    
    public Region(Polygon polygone) 
    {
        this.polygone = polygone;
        //TODO Constructeur
    }
    
    public void DeplacementRegions()
    {
        //TODO Déplacement des populations entre les régions
    }
    
    public void Contaminer()
    {
        //TODO Appliquer le taux d'infection sur la populationSaine
        //Gestion des Mesures
    }
    
    public void EliminerPopulation()
    {
        //TODO Appliquer le taux de mortalite sur la populationInfectee
    }
    
    public void GuerirPop()
    {
        //TODO Appliquer le taux de guérison sur la populationInfectee
    }
    
    public void AjouterVoisin(Region nouvelleRegion)
    {
        //TODO Ajouter une region à sa liste de voisin
    }
    
    public void RetirerVoisin(Region ancienneRegion)
    {
           //TODO Retirer une région de la liste de region voisines
    }
    
    public String getNom(){return nom;}
    
    public int getPopSaine(){return populationSaine;}
    
    public int getPopImmunisee(){return populationImmune;}
    
    public int getPopInfectee(){return populationInfectee;}
    
    public int getPopDecedee(){return populationDecedee;}
    
    private void setNom(String nom)
    {
        this.nom = nom;
    }
    
    private void setPopSaine(int populationSaine)
    {
        this.populationSaine = populationSaine;
    }
    
    private void setPopImmune(int populationImmune)
    {
        this.populationImmune = populationImmune;
    }
        
    private void setPopInfectee(int populationInfectee)
    {
        this.populationInfectee = populationInfectee;
    }
            
    private void setPopDecedee(int populationDecedee)
    {
        this.populationDecedee = populationDecedee;
    }
    
}
