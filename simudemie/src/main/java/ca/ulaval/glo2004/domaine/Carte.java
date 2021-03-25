/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.awt.Polygon;
import java.io.Serializable;
import java.util.ArrayList;

public class Carte implements Serializable {
    
    private String nom;
    private ArrayList<Pays> listePays = new ArrayList<>();
    private Maladie maladie;
    
    public Carte(String nom)
    {
        this.nom = nom;
    }    

    public Carte(Carte carteJourCourant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void avancerJour()
    {
        for (Pays pays : listePays){
            pays.avancerJournee(maladie.getTauxInfection(), maladie.getTauxMortalite(), maladie.getTauxGuerison());
        }
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
    
    public Maladie getMaladie() {return maladie;}
    
    public void setMaladie(Maladie maladie)
    {
        this.maladie = maladie;
    }
    
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