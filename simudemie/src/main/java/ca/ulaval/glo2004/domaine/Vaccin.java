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


public class Vaccin implements Externalizable {
    private static final long serialVersionUID = 1;
    
    private String nom;
    private double tauxImmunisation;
    private double tauxAdhesion = 1.0;
    private int vaccinationQuotidienne;
    private boolean active;    
    
    public Vaccin() {}
    
    public Vaccin(String nom, double tauxImmunisation, int vaccinationQuotidienne) {
        this.nom = nom;
        this.tauxImmunisation = tauxImmunisation;
        this.vaccinationQuotidienne = vaccinationQuotidienne;
    }
    
    public Vaccin(String nom, double tauxImmunisation, double tauxAdhesion, int vaccinationQuotidienne, boolean active) {
        this.nom = nom;
        this.tauxImmunisation = tauxImmunisation;
        this.tauxAdhesion = tauxAdhesion;
        this.vaccinationQuotidienne = vaccinationQuotidienne;
        this.active = active;
    }
    
    public Vaccin(Vaccin vaccin){
        this.nom = vaccin.nom;
        this.tauxImmunisation = vaccin.tauxImmunisation;
        this.tauxAdhesion = vaccin.tauxAdhesion;
        this.vaccinationQuotidienne = vaccin.vaccinationQuotidienne;
        this.active = vaccin.active;
    }

    public String getNom() {return nom;}
    
    public double getTauxImmunisation() {return tauxImmunisation;}

    public double getTauxAdhesion() {return tauxAdhesion;}

    public int getVaccinationQuotidienne() {return vaccinationQuotidienne;}
    
    public boolean getActive() {return active;}
    
    public void setNom(String nom) {this.nom = nom;}
    
    public void setTauxImmunisation(double tauxImmunisation) {this.tauxImmunisation = tauxImmunisation;}

    public void setTauxAdhesion(double tauxAdhesion) {this.tauxAdhesion = tauxAdhesion;}

    public void setVaccinationQuotidienne(int vaccinationQuotidienne) {this.vaccinationQuotidienne = vaccinationQuotidienne;}
    
    public void setActive(boolean active) {this.active = active;}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(nom);
        out.writeDouble(tauxImmunisation);
        out.writeDouble(tauxAdhesion);
        out.writeInt(vaccinationQuotidienne);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        nom = in.readUTF();
        tauxImmunisation = in.readDouble();
        tauxAdhesion = in.readDouble();
        vaccinationQuotidienne = in.readInt();
    }
    
    
}
