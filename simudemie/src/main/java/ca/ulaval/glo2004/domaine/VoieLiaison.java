/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;


public class VoieLiaison {
    
    private Pays paysOrigine;
    private Pays paysDestination;
    private final String nom;
    private boolean accessible;
    
    public VoieLiaison(String nom, Pays origine, Pays destination)
    {
        this.nom = nom;
        this.paysOrigine = origine;
        this.paysDestination = destination;
    }
        
    public Pays getPaysOrigine(){return paysOrigine;}
    
    public Pays getPaysDestination(){return paysDestination;}
    
    public String getNom() {return nom;}
    
    public boolean getAccessible(){return accessible;}
    
    public void setPaysOrigine(Pays paysOrigine)
    {
        this.paysOrigine = paysOrigine;
    }
    
    public void setPaysDestination(Pays paysDestination)
    {
        this.paysDestination = paysDestination;
    }
    
    public void setAccessible(boolean accessible)
    {
        this.accessible = accessible;
    }
}
