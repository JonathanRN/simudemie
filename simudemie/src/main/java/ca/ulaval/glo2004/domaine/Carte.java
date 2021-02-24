/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.util.List;


public class Carte {
    
    private String nom;
    private List<Pays> listePays;
    
    public Carte()
    {
        //TODO Constructeur de la classe pays
    }    
    
    public void AvancerJour()
    {
        //TODO Fonction qui applique tous les effets sur les populations des régions
    }
    
    public void AjouterPays(Pays nouveauPays)
    {
        //TODO Ajout d'une region à la liste
    }
    
    public void RetirerPays(Pays ancienPays)
    {
        //TODO Retrait d'une region à la liste
    }
    
    public Pays trouverPays(Pays aTrouver)
    {
        return new Pays();
    }
    
    public String getNom(){return nom;}
    
    public List<Pays> getListePays(){return listePays;}
    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
}