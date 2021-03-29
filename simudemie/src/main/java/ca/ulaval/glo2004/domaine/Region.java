/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.util.ArrayList;
import java.util.Random;
import org.apache.commons.math3.distribution.BinomialDistribution;
import java.awt.Polygon;
import java.io.Serializable;


public class Region implements Serializable {
    
    private String nom;
    private int populationSaine;
//    private int populationImmune;
    private int populationInfectee;
    private int populationDecedee;
    private Polygon polygone;
    
    public Region(Polygon polygone)
    {
        this.polygone = polygone;
    }
    
//    public void deplacementRegions()
//    {
//        //TODO Déplacement des populations entre les régions
//    }
    
    public void contaminer(double taux)
    {
        int nouveauxInfectes  = contaminationBinomiale(taux);
        setPopSaine(this.getPopSaine() - nouveauxInfectes);
        setPopInfectee(this.getPopInfectee() + nouveauxInfectes);
    }
    
    public void eliminerPopulation(double taux)
    {
        int deces  = (int)(this.getPopInfectee() * taux);
        setPopInfectee(this.getPopInfectee() - deces);
        setPopDecedee(this.getPopDecedee() + deces);
    }
    
    public void guerirPop(double taux)
    {
        int gueris = (int)(this.getPopInfectee() * taux);
        setPopInfectee(this.getPopInfectee() - gueris);
        setPopSaine(this.getPopSaine() + gueris);
    }
    
    public String getNom(){return nom;}
    
    public int getPopTotale() { return populationSaine + populationInfectee; }
    
    public int getPopSaine(){return populationSaine;}
    
//    public int getPopImmunisee(){return populationImmune;}
    
    public int getPopInfectee(){return populationInfectee;}
    
    public int getPopDecedee(){return populationDecedee;}
    
    public float getPourcentageInfectee() {
        try {
            return (getPopInfectee() / getPopTotale()) * 100;
        }
        catch (java.lang.ArithmeticException e) {
            return 0;
        }
    }
    
    public float getPourcentageSaine() {
        try {
            return (getPopSaine() / getPopTotale()) * 100;
        }
        catch (java.lang.ArithmeticException e) {
            return 0;
        }
    }
    
    public float getPourcentageDecedee() {
        try {
            return (getPopDecedee() / getPopTotale()) * 100;
        }
        catch (java.lang.ArithmeticException e) {
            return 0;
        }
    }
    
    public Polygon getPolygone() {
        return polygone;
    }
    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    public void setPopSaine(int populationSaine)
    {
        this.populationSaine = populationSaine;
    }
    
//    private void setPopImmune(int populationImmune)
//    {
//        this.populationImmune = populationImmune;
//    }
        
    public void setPopInfectee(int populationInfectee)
    {
        this.populationInfectee = populationInfectee;
    }
            
    public void setPopDecedee(int populationDecedee)
    {
        this.populationDecedee = populationDecedee;
    }
    
    private int contaminationBinomiale(double tauxPropag)
    {
        Random rnd = new Random (System.currentTimeMillis());
        double seuilTol = 0.0001;

        int nbInfectees = this.getPopInfectee();

        BinomialDistribution binomial = new BinomialDistribution(nbInfectees, tauxPropag);

        ArrayList<Double> probabilites = new ArrayList<>();
        ArrayList<Integer> nombreSucces = new ArrayList<>();

        double x = nbInfectees * tauxPropag;
        int cpt = 0;
        double prob = binomial.probability((int)x);

        while(prob > seuilTol && x >= 0){
            probabilites.add(prob);
            nombreSucces.add(cpt);
            cpt += 1;
            x -= 1;
            prob = binomial.probability((int)x);
        }

        double randomNumber = 0.0; 
        double current = 0.0; //threshold
        int success = 0;

        for (int k = 0; k < 10; k++){
            randomNumber = rnd.nextDouble();
            current = 0.0; //threshold
            success = 0;
            for(int i = 0; i < probabilites.size(); i++){
                current += probabilites.get(0);
                if(randomNumber < current){
                    success = nombreSucces.get(i);
                    break;
                }
            }
        }

        return (int)(nbInfectees*tauxPropag + success);        
    }
}
