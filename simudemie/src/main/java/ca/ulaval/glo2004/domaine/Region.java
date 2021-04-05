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
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Vector;


public class Region implements Externalizable {
    
    private String nom;
    private int populationSaine;
    private int populationInfectee;
    private int populationDecedee;
    public int popInitiale;
    private Polygon polygone;
    private Vector<Integer> listeInfections = new Vector<>();
    private int populationImmune;

    public Region() {}
    
    public Region(Polygon polygone) {
        this.polygone = polygone;
        setPopTotale(100000);
    }

    public Region(Region region) {
        this.nom = region.nom;
        this.populationSaine = region.populationSaine;
        this.populationInfectee = region.populationInfectee;
        this.populationDecedee = region.populationDecedee;
        this.populationImmune = region.populationImmune;
        this.popInitiale = region.popInitiale;
        this.listeInfections.clear();
        for (int infections : region.listeInfections) {
            this.listeInfections.add(infections);
        }

        // Voir si modifier une carte modifie le polygone ici
        this.polygone = region.polygone;
    }
    
    
    public void contaminer(double taux, int cptJours)
    {
        int nouveauxInfectes = contaminationBinomiale(taux);
        this.listeInfections.add(nouveauxInfectes);
        setPopInfectee(this.getPopInfectee() + nouveauxInfectes);
        setPopSaine(this.getPopSaine() - nouveauxInfectes);
    }
    
    public void eliminerPopulation(double taux, int cptJours)
    {
        if (cptJours > 14 && getPopInfectee() > 0f){
            int deces = (int)(this.listeInfections.get(cptJours - 14) * taux);
            setPopDecedee(this.getPopDecedee() + deces);
            setPopInfectee(this.getPopInfectee() - deces);
        }
    }
    
    public void guerirPop(double taux, int cptJours)
    {
        if (cptJours > 14){
            int gueris = (int)(this.listeInfections.get(cptJours - 14) * taux);
            setPopSaine(this.getPopSaine() + gueris);
            setPopInfectee(this.getPopInfectee() - gueris);   
        }
    }
    
    public void vaccinationPop(double taux, int vaccinationParJour)
    {
        int nouveauxImmune = (int)((float)vaccinationParJour * taux);
        
    }
    
    public String getNom(){return nom;}
    
    public void setPopTotale(int pop) {
        this.populationSaine = pop;
    }
    
    public int getPopTotale() { return populationSaine + populationInfectee + populationImmune; }
    
    public int getPopSaine(){ return populationSaine; }
    
    public int getPopImmunisee(){return populationImmune;}
    
    public int getPopInfectee(){ return populationInfectee; }
    
    public int getPopDecedee(){ return populationDecedee; }
    
    public float getPourcentageInfectee() {
        if (this.getPopTotale() <= 0) {
            return 0;
        }
        return ((float)this.getPopInfectee() / (float)this.getPopTotale()) * 100f;
    }
    
    public float getPourcentageSaine() {
        if (this.getPopTotale() <= 0) {
            return 0;
        }
        return ((float)this.getPopSaine() / (float)this.getPopTotale()) * 100f;
    }
    
    public float getPourcentageDecedee() {
        if (this.getPopTotale() <= 0) {
            return 0;
        }
        return ((float)this.getPopDecedee() / (float)this.getPopInitiale()) * 100f;
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
        this.populationSaine = clamp(populationSaine, 0, this.getPopTotale());
    }
    
    private void setPopImmune(int populationImmune)
    {
        this.populationImmune = populationImmune;
    }
        
    public void setPopInfectee(int populationInfectee)
    {
        this.populationInfectee = clamp(populationInfectee, 0, this.getPopTotale());
    }
    
    public void setPopDecedee(int populationDecedee)
    {
        this.populationDecedee = clamp(populationDecedee, 0, this.getPopInitiale());
    }
    
    private int contaminationBinomiale(double tauxPropag) {
        int nbInfectes = this.getPopInfectee();

        if (nbInfectes <= 0) {
            return 0;
        }
        
        Random rnd = new Random (System.currentTimeMillis());
        double seuilTol = 0.0001;

        BinomialDistribution binomial = new BinomialDistribution(nbInfectes, tauxPropag);

        ArrayList<Double> probabilites = new ArrayList<>();
        ArrayList<Integer> nombreSucces = new ArrayList<>();

        double x = nbInfectes * tauxPropag;
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
        double current = 0.0; 
        int success = 0;

        for (int k = 0; k < 10; k++){
            randomNumber = rnd.nextDouble();
            current = 0.0;
            success = 0;
            for(int i = 0; i < probabilites.size(); i++){
                current += probabilites.get(0);
                if(randomNumber < current){
                    success = nombreSucces.get(i);
                    break;
                }
            }
        }


        nbInfectes = (int)(this.getPopInfectee()/(cpt*0.2));
        if (nbInfectes > this.getPopSaine()){
            nbInfectes = this.getPopSaine();
        }
        return (int)Math.ceil(nbInfectes * tauxPropag + success);
    }

    // https://stackoverflow.com/questions/16656651/does-java-have-a-clamp-function
    private int clamp(int value, int min, int max) {
        return value > max ? max : value < min ? min : value;
    }
    
    public void setPopInitiale(int popInitiale) {
        this.popInitiale = popInitiale;
    }

    public int getPopInitiale() {
        return popInitiale;
    }    

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(nom);
        out.writeInt(populationSaine);
        out.writeInt(populationInfectee);
        out.writeInt(populationDecedee);
        out.writeInt(popInitiale);
        out.writeObject(polygone);
        out.writeInt(listeInfections.size());
        for(Integer i : listeInfections) {
            out.writeInt(i);
        }
        out.writeInt(popInitiale);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        listeInfections.clear();
        nom = in.readUTF();
        populationSaine = in.readInt();
        populationInfectee = in.readInt();
        populationDecedee = in.readInt();
        popInitiale = in.readInt();
        polygone = (Polygon) in.readObject();
        int listeInfectionsSize = in.readInt();
        for(int index = 0; index < listeInfectionsSize; index++) {
            listeInfections.add(in.readInt());
        }
        popInitiale = in.readInt();
    }
}