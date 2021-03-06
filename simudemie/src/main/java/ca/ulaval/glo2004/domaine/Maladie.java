/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


public class Maladie implements Externalizable {
    private static final long serialVersionUID = 1;

    private String nom;
    private double tauxInfection;
    private double tauxMortalite;
    private double tauxGuerison;
    private int incubation = 14;
    private boolean immunitePossible = false;
    
    
    public Maladie() {}
    
    public Maladie(String nom, double tauxInfection, double tauxMortalite, double tauxGuerison, int incubation, boolean immunitePossible)
    {
        this.nom = nom;
        this.tauxInfection = tauxInfection;
        this.tauxMortalite = tauxMortalite;
        this.tauxGuerison = tauxGuerison;
        this.incubation = incubation;
        this.immunitePossible = immunitePossible;
    }
    
    public Maladie(Maladie maladie) {
        this.nom = maladie.nom;
        this.tauxInfection = maladie.tauxInfection;
        this.tauxGuerison = maladie.tauxGuerison;
        this.tauxMortalite = maladie.tauxMortalite;
        this.incubation = maladie.incubation;
        this.immunitePossible = maladie.immunitePossible;
    }
    
    public String getNom(){return nom;}
    
    public double getTauxInfection(){return tauxInfection;}
    
    public double getTauxMortalite(){return tauxMortalite;}
    
    public double getTauxGuerison(){return tauxGuerison;}
    
    public boolean getImmunitePossible(){return immunitePossible;}

    public int getIncubation(){return incubation;}

    public void setNom(String nom) {this.nom = nom;}
    
    public void setTauxInfection(double tauxInfection) {this.tauxInfection = tauxInfection;}
        
    public void setTauxMortalite(double tauxMortalite) {this.tauxMortalite = tauxMortalite;}
    
    public void setTauxGuerison(double tauxGuerison) {this.tauxGuerison = tauxGuerison;}
    
    public void setImmunitePossible(boolean immunitePossible) {this.immunitePossible = immunitePossible;}
    
    public void setIncubation(int incubation) {this.incubation = incubation;}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(nom);
        out.writeDouble(tauxInfection);
        out.writeDouble(tauxMortalite);
        out.writeDouble(tauxGuerison);
        out.writeInt(incubation);
        out.writeBoolean(immunitePossible);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        nom = in.readUTF();
        tauxInfection = in.readDouble();
        tauxMortalite = in.readDouble();
        tauxGuerison = in.readDouble();
        incubation = in.readInt();
        immunitePossible = in.readBoolean();
    }
   
}
