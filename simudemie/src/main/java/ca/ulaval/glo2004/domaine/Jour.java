/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

/**
 *
 * @author Jonathan
 */
public class Jour {
    public final Carte carte;
    public final Maladie maladie;
    
    
    public Jour(Carte carte, Maladie maladie) {
        this.carte = carte;
        this.maladie = maladie;
        
    }
    
    public Jour(Jour jour) {
        carte = jour.carte;
        maladie = jour.maladie;
        
    }
}