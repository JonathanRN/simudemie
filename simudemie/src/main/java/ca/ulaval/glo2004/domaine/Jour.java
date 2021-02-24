/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class Jour {
    public final Carte carte;
    public final Maladie maladie;
    public final ArrayList<Mesure> mesures;
    
    public Jour(Carte carte, Maladie maladie, ArrayList<Mesure> mesures) {
        this.carte = carte;
        this.maladie = maladie;
        this.mesures = mesures;
    }
    
    public Jour(Jour jour) {
        carte = jour.carte;
        maladie = jour.maladie;
        mesures = jour.mesures;
    }
}