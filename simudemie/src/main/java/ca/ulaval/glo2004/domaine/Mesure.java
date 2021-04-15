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


public class Mesure implements Externalizable {
    private static final long serialVersionUID = 1;
      
    private String nom;
    private int seuilActivation;
    private double tauxAdhesion;
    private double tauxReductionProp;
    private boolean active;
    
    public Mesure() {}
    
    public Mesure(String nom, double tauxAdhesion, double tauxReductionProp, int seuilActivation, boolean active)
    {
        this.nom = nom; 
        this.tauxAdhesion = tauxAdhesion;
        this.tauxReductionProp = tauxReductionProp;
        this.seuilActivation = seuilActivation;
        this.active = active;
    }
    
    public Mesure(Mesure mesure)
    {
        this.nom = mesure.nom; 
        this.tauxAdhesion = mesure.tauxAdhesion;
        this.tauxReductionProp = mesure.tauxReductionProp;
        this.seuilActivation = mesure.seuilActivation;
        this.active = mesure.active;
    }
    
    public String getNom(){return nom;}
    
    public double getTauxAdhesion(){return tauxAdhesion;}
    
    public double getTauxReductionProp(){return tauxReductionProp;}
    
    public int getSeuilActivation(){return seuilActivation;}
    
    public boolean getActive() {return active;}
    
    public void setNom(String nom) {this.nom = nom;}
    
    public void setTauxAdhesion(double tauxAdhesion){this.tauxAdhesion = tauxAdhesion;}
        
    public void setTauxReductionProp(double tauxReductionProp){this.tauxReductionProp = tauxReductionProp;}
        
    public void setSeuilActivation(int seuil){this.seuilActivation = seuil;}
    
    public void setActive(boolean active) {this.active = active;}


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(nom);
        out.writeDouble(tauxAdhesion);
        out.writeDouble(tauxReductionProp);
        out.writeInt(seuilActivation);
        out.writeBoolean(active);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        nom = in.readUTF();
        tauxAdhesion = in.readDouble();
        tauxReductionProp = in.readDouble();
        seuilActivation = in.readInt();
        active = in.readBoolean();
    }
}
