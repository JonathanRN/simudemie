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


public class Mesure implements Externalizable{
    
    private String nom;
    private double tauxAdhesion;
    private double tauxReductionProp;
    private boolean active;
    
    public Mesure() {}
    
    public Mesure(String nom, double tauxAdhesion, double tauxReductionProp, boolean active)
    {
        this.nom = nom; 
        this.tauxAdhesion = tauxAdhesion;
        this.tauxReductionProp = tauxReductionProp;
        this.active = active;
    }
    
    public Mesure(Mesure mesure)
    {
        this.nom = mesure.nom; 
        this.tauxAdhesion = mesure.tauxAdhesion;
        this.tauxReductionProp = mesure.tauxReductionProp;
        this.active = mesure.active;
    }
    
    public String getNom(){return nom;}
    
    public double getTauxAdhesion(){return tauxAdhesion;}
    
    public double getTauxReductionProp(){return tauxReductionProp;}
    
    public boolean getActive() {
        return active;
    }
    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    public void setTauxAdhesion(double tauxAdhesion)
    {
        this.tauxAdhesion = tauxAdhesion;
    }
        
    public void setTauxReductionProp(double tauxReductionProp)
    {
        this.tauxReductionProp = tauxReductionProp;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(nom);
        out.writeDouble(tauxAdhesion);
        out.writeDouble(tauxReductionProp);
        out.writeBoolean(active);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        nom = in.readUTF();
        tauxAdhesion = in.readDouble();
        tauxReductionProp = in.readDouble();
        active = in.readBoolean();
    }
}
