/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;


public class VoieLiaison {
    
    private Pays paysOrigine;
    
    private Pays paysDestination;
    
    private boolean accessible;
    
    public VoieLiaison()
    {
        //TODO  Constructeur
    }
        
    public Pays getPaysOrigine(){return paysOrigine;}
    
    public Pays getPaysDestination(){return paysDestination;}
    
    public boolean getAccessible(){return accessible;}
    
    private void setPaysOrigine(Pays paysOrigine)
    {
        this.paysOrigine = paysOrigine;
    }
    
    private void setPaysDestination(Pays paysDestination)
    {
        this.paysDestination = paysDestination;
    }
    
    private void setAccessible(boolean accessible)
    {
        this.accessible = accessible;
    }
    
}
