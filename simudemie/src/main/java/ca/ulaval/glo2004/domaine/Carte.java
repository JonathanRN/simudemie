/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.awt.Polygon;
import java.util.ArrayList;

public class Carte {
    
    private String nom;
    private ArrayList<Pays> listePays = new ArrayList<>();
    
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
        nouveauPays.setNom("Pays " +  listePays.size());
        listePays.add(nouveauPays);
    }
    
    public void retirerPays(Pays ancienPays)
    {
        if (listePays.contains(ancienPays)){
            listePays.remove(ancienPays);
        }
    }
    
    public Pays getPays(int index) {
        return listePays.get(index);
    }
    
    public Pays getPays(Polygon p) {
        for (Pays pays : listePays) {
            if (pays.getPolygone().equals(p)) {
                return pays;
            }
            else {
                for (Region r : pays.getRegions()) {
                    if (r.getPolygone().equals(p)) {
                        return pays;
                    }
                }
            }
        }
        return null;
    }

    public String getNom(){return nom;}
    
    public ArrayList<Pays> getListePays(){return listePays;}
    
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