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
    private static final long serialVersionUID = 1;   
    
    private String nom;
    private int populationSaine;
    private int populationInfectee;
    private int populationDecedee;
    public int popInitiale;
    private int populationImmune;
    private Polygon polygone;
    private Vector<Integer> listeInfections = new Vector<>();
    private double tauxContaInterRegion = 0.1;
    private double tauxContaIntraRegion = 0.1;

    public Region() {}
    
    public Region(Polygon polygone) {
        this.polygone = new Polygon(polygone.xpoints, polygone.ypoints, polygone.npoints);
        setPopTotale(1000000);
    }
    
    public Region(Polygon polygone, int popSaine) {
        this.polygone = new Polygon(polygone.xpoints, polygone.ypoints, polygone.npoints);
        this.populationSaine = popSaine;
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

        this.polygone = new Polygon(region.polygone.xpoints, region.polygone.ypoints, region.polygone.npoints);
    }
    
    
    public void contaminerPopulation(double taux, int cptJours, int incubation)
    {
        //Nous ajoutons les nouveaux infectés dans un vecteur pour ajouter une dose de réalisme.
        //Lorsque l'élimination et guérison de la pop ont lieu, ces méthodes s'appliquent sur la 
        //pop infectée d'il y a 2 semaines (inspiré du Covid - période d'incubation d'environ 2 semaines)
        int nouveauxInfectes = (int)Math.ceil(contaminationBinomiale(taux, incubation)/2f);
        this.listeInfections.add(nouveauxInfectes);
        setPopInfectee(this.getPopInfectee() + nouveauxInfectes);
        setPopSaine(this.getPopSaine() - nouveauxInfectes);
    }
    
    public void eliminerPopulation(double taux, int cptJours, int incubation)
    {
        //Les premières infections ont lieu 2 semaines après la 1ere infection
        if (cptJours > 14 && getPopInfectee() > 0f){
            int deces = (int)Math.ceil(this.listeInfections.get(cptJours - incubation) * taux);
            setPopDecedee(this.getPopDecedee() + deces);
            setPopInfectee(this.getPopInfectee() - deces);
        }
    }
    
    public void guerirPopulation(double taux, int cptJours, int incubation, boolean immunitePossible)
    {
        //Les premières guerisons ont lieu 2 semaines après la 1ere infection
        if (cptJours > 14){
            int gueris = (int)Math.ceil(this.listeInfections.get(cptJours - incubation) * taux);
            if (immunitePossible){
                setPopImmune(this.getPopImmunisee() + gueris);
                setPopInfectee(this.getPopInfectee() - gueris); 
            }else{
                setPopSaine(this.getPopSaine() + gueris);
                setPopInfectee(this.getPopInfectee() - gueris);                   
            }
        }
    }
    
    public void vaccinerPopulation(double taux, double vaccinationParJour)
    {
        int nouveauxImmune = (int)(this.getPopSaine() * taux/100 * vaccinationParJour/100);
        this.setPopImmune(this.getPopImmunisee() + nouveauxImmune);
        this.setPopSaine(this.getPopSaine()- nouveauxImmune);
    }
    
    public String getNom(){return nom;}
    
    public void setPopTotale(int pop) {this.populationSaine = pop;}
    
    public int getPopTotale() { return populationSaine + populationInfectee + populationImmune; }
    
    public int getPopSaine(){ return populationSaine; }
    
    public int getPopImmunisee(){return populationImmune;}
    
    public int getPopInfectee(){ return populationInfectee; }
    
    public int getPopDecedee(){ return populationDecedee; }

    public double getTauxContaInterRegion() {return tauxContaInterRegion;}

    public double getTauxContaIntraRegion() {return tauxContaIntraRegion;}
    
    
    public float getPourcentageInfectee() {
        if (this.getPopTotale() <= 0) {return 0;}
        
        return ((float)this.getPopInfectee() / (float)this.getPopTotale()) * 100f;
    }
    
    public float getPourcentageSaine() {
        if (this.getPopTotale() <= 0) {return 0;}
    
        return ((float)this.getPopSaine() / (float)this.getPopTotale()) * 100f;
    }
    
    public float getPourcentageDecedee() {
        if (this.getPopTotale() <= 0) {return 0;}
        
        return ((float)this.getPopDecedee() / (float)this.getPopInitiale()) * 100f;
    }
    
    public float getPourcentageImmune() {
        if (this.getPopTotale() <= 0) {return 0;}
        
        return ((float)this.getPopImmunisee() / (float)this.getPopTotale()) * 100f;
    }
    
    public Polygon getPolygone() {return polygone;}
    
    public void setNom(String nom) {this.nom = nom;}
    
    public void setPopInitiale(int popInitiale) {this.popInitiale = popInitiale;}
    
    public int getPopInitiale() {return popInitiale;}    
    
    public void setPopSaine(int populationSaine){this.populationSaine = clamp(populationSaine, 0, this.getPopTotale());}
    
    private void setPopImmune(int populationImmune) {this.populationImmune = populationImmune;}
        
    public void setPopInfectee(int populationInfectee) {this.populationInfectee = clamp(populationInfectee, 0, this.getPopTotale());}
    
    public void setPopDecedee(int populationDecedee) {this.populationDecedee = clamp(populationDecedee, 0, this.getPopInitiale());}

    public void setTauxContaInterRegion(double tauxContaInterRegion) {this.tauxContaInterRegion = tauxContaInterRegion;}

    public void setTauxContaIntraRegion(double tauxContaIntraRegion) {this.tauxContaIntraRegion = tauxContaIntraRegion;}
    
    private int contaminationBinomiale(double tauxPropag, int incubation) {
        
        /*
        Contanimation suivant une distribution binomiale inspiré du labo présenté par Anthony.
        Nous prenons le nombre d'infectés et, suivant les probabilités, ajoutons un nombre réalistes
        de personnes infectées.
        */
        
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

        if (cpt < incubation){
            nbInfectes = (int)(this.getPopInfectee()/(cpt*0.2));
            
        }else{
            nbInfectes = (int)(this.getPopInfectee()/(incubation*0.25));
            
        }
        
        if (nbInfectes > this.getPopSaine()){
            nbInfectes = this.getPopSaine();
        }
        
        success = (int)(success * (1.0 + this.getTauxContaIntraRegion()));
        return (int)Math.ceil(nbInfectes * tauxPropag + success);
    }

    // https://stackoverflow.com/questions/16656651/does-java-have-a-clamp-function
    private int clamp(int value, int min, int max) {return value > max ? max : value < min ? min : value;}
       
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
        out.writeInt(populationImmune);
        out.writeDouble(tauxContaInterRegion);
        out.writeDouble(tauxContaIntraRegion);
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
        populationImmune = in.readInt();
        tauxContaInterRegion = in.readDouble();
        tauxContaIntraRegion = in.readDouble();
    }

    @Override
    public boolean equals(Object obj) {
        final Region other = (Region) obj;
        
        if(polygone.npoints != other.polygone.npoints) {
            return false;
        }
        
        for(int index = 0; index < polygone.npoints; index++) {
            if(polygone.xpoints[index] != other.polygone.xpoints[index] ||
                    polygone.ypoints[index] != other.polygone.ypoints[index]) {
                return false;
            }
        }
        
        return true;
    }
    
    
}