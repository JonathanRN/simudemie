/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.awt.Polygon;
import java.util.List;

public class Pays {
    
    private String nom;
    private int populationTotale;
    private List<VoieLiaison> frontieres;
    private List<Region> listeRegions;
    private Polygon polygone;
    
    public Pays(Polygon polygone) 
    {
        this.polygone = polygone;
        //TODO Constructeur
    }
    
    private void ajouterRegion(Region nouvelleRegion)
    {
        //TODO Ajout d'une region à la liste
    }
    
    private void retirerRegion(Region ancienneRegion)
    {
        //TODO Retrait d'une region à la liste
    }
    
    public void ajouterVoie(VoieLiaison nouvelleVoie)
    {
        //TODO Ajout d'une region à la liste
    }
    
    public void retirerVoie(VoieLiaison ancienneVoie)
    {
        //TODO Retrait d'une region à la liste
    }
    
    public void deplacementInterPays()
    {
    
    }
        
    public String getNom(){return nom;}
    
    public int getPopTotale(){return populationTotale;}
    
    private void setNom(String nom)
    {
        this.nom = nom;
    }
    
    
    private void setPopTotale(int populationTotale)
    {
        this.populationTotale = populationTotale;
    }
    
}
