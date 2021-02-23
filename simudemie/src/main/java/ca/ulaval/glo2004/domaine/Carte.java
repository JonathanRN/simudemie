/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.util.List;


public class Carte {
    
    private String nom;
    private List<Region> listePays; 
    private int jour;
    
    
    public Carte()
    {
        //TODO Constructeur de la classe pays
    }    
    
    void AvancerJour()
    {
        //TODO Fonction qui applique tous les effets sur les populations des régions
    }
    
    private void AjouterPays(Pays nouveauPays)
    {
        //TODO Ajout d'une region à la liste
    }
    
    private void RetirerPays(Pays ancienPays)
    {
        //TODO Retrait d'une region à la liste
    }
    
    public String getNom(){return nom;}
    
    public List<Region> getListePays(){return listePays;} //Pas certain?
      
    public int getJour(){return jour;}
    
    private void setNom(String nom)
    {
        this.nom = nom;
    }
    
    private void setJour(int jour)
    {
        this.jour = jour;
    }
    
}
