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
    private int vaccinationQuotidienne;
    private double tauxAdhesion = 1.0;
    
    public Vaccin() {}
    
    public Vaccin(String nom, double tauxImmunisation, int vaccinationQuotidienne) {
        this.nom = nom;
        this.tauxImmunisation = tauxImmunisation;
        this.vaccinationQuotidienne = vaccinationQuotidienne;
    }
    
    public Vaccin(String nom, double tauxImmunisation, double tauxAdhesion, int vaccinationQuotidienne) {
        this.nom = nom;
        this.tauxImmunisation = tauxImmunisation;
        this.vaccinationQuotidienne = vaccinationQuotidienne;
        this.tauxAdhesion = tauxAdhesion;
    }
    
    public Vaccin(Vaccin vaccin){
        this.nom = vaccin.nom;
        this.tauxImmunisation = vaccin.tauxImmunisation;
        this.vaccinationQuotidienne = vaccin.vaccinationQuotidienne;
        this.tauxAdhesion = vaccin.tauxAdhesion;
    }

    public String getNom() {return nom;}

    public double getTauxAdhesion() {return tauxAdhesion;}

    public double getTauxImmunisation() {return tauxImmunisation;}

    public int getVaccinationQuotidienne() {return vaccinationQuotidienne;}
    
    public void setNom(String nom) {this.nom = nom;}

    public void setTauxAdhesion(double tauxAdhesion) {this.tauxAdhesion = tauxAdhesion;}

    public void setTauxImmunisation(double tauxImmunisation) {this.tauxImmunisation = tauxImmunisation;}

    public void setVaccinationQuotidienne(int vaccinationQuotidienne) {this.vaccinationQuotidienne = vaccinationQuotidienne;}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(nom);
        out.writeDouble(tauxImmunisation);
        out.writeInt(vaccinationQuotidienne);
        out.writeDouble(tauxAdhesion);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        nom = in.readUTF();
        tauxImmunisation = in.readDouble();
        vaccinationQuotidienne = in.readInt();
        tauxAdhesion = in.readDouble();
    }
    
    
}
