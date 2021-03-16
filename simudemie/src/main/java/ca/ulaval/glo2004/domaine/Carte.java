/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.util.List;
import java.awt.Polygon;

public class Carte {
    
    private String nom;
    private List<Pays> listePays;
    
    public Carte(String nom)
    {
        this.nom = nom;
    }    
    
    public void avancerJour()
    {
        //TODO Fonction qui applique tous les effets sur les populations des régions
    }
    
    public void ajouterPays(Pays nouveauPays)
    {
         listePays.add(nouveauPays);
    }
    
    public void retirerPays(Pays ancienPays)
    {
        if (listePays.contains(ancienPays)){
            listePays.remove(ancienPays);
        }
    }

    public String getNom(){return nom;}
    
    public List<Pays> getListePays(){return listePays;}
    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    /*
    public Pays trouverPays(Pays aTrouver)
    {
        return new Pays();
    }
    */
}