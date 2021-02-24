/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.util.List;

public class Pays {
    
    private String nom;
    private int populationTotale;
    private List<VoieLiaison> frontieres;
    private List<Region> listeRegions;
    
    public Pays()
    {
        //TODO Constructeur Pays
    }
    
    private void AjouterRegion(Region nouvelleRegion)
    {
        //TODO Ajout d'une region à la liste
    }
    
    private void RetirerRegion(Region ancienneRegion)
    {
        //TODO Retrait d'une region à la liste
    }
    
    public void AjouterVoie(VoieLiaison nouvelleVoie)
    {
        //TODO Ajout d'une region à la liste
    }
    
    public void RetirerVoie(VoieLiaison ancienneVoie)
    {
        //TODO Retrait d'une region à la liste
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
