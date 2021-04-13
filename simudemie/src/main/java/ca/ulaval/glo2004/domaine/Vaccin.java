/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

/**
 *
 * @author Fred
 */
public class Vaccin {
    private String nom;
    private double tauxImmunisation;
    private double tauxAdhesion = 1.0;
    
    public Vaccin() {}
    
    public Vaccin(String nom, double tauxImmunisation) {
        this.nom = nom;
        this.tauxImmunisation = tauxImmunisation;
    }
    
    public Vaccin(String nom, double tauxImmunisation, double tauxAdhesion) {
        this.nom = nom;
        this.tauxImmunisation = tauxImmunisation;
        this.tauxAdhesion = tauxAdhesion;
    }

    public String getNom() {
        return nom;
    }

    public double getTauxAdhesion() {
        return tauxAdhesion;
    }

    public double getTauxImmunisation() {
        return tauxImmunisation;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTauxAdhesion(double tauxAdhesion) {
        this.tauxAdhesion = tauxAdhesion;
    }

    public void setTauxImmunisation(double tauxImmunisation) {
        this.tauxImmunisation = tauxImmunisation;
    }
    
    
}
