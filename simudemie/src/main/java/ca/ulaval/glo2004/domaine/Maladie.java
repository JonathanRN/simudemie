/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.io.Serializable;


public class Maladie implements Serializable {

    private String nom;
    private float tauxInfection;
    private float tauxMortalite;
    private float tauxGuerison;
    
    public Maladie(String nom, float tauxInfection, float tauxMortalite, float tauxGuerison)
    {
        this.nom = nom;
        this.tauxInfection = tauxInfection;
        this.tauxMortalite = tauxMortalite;
        this.tauxGuerison = tauxGuerison;
    }
    
    public String getNom(){return nom;}
    
    public float getTauxInfection(){return tauxInfection;}
    
    public float getTauxMortalite(){return tauxMortalite;}
    
    public float getTauxGuerison(){return tauxGuerison;}
    
    private void setNom(String nom)
    {
        this.nom = nom;
    }
    
    private void setTauxInfection(float tauxInfection)
    {
        this.tauxInfection = tauxInfection;
    }
        
    private void setTauxMortalite(float tauxMortalite)
    {
        this.tauxMortalite = tauxMortalite;
    }
    
    private void setTauxGuerison(float tauxGuerison)
    {
        this.tauxGuerison = tauxGuerison;
    }
    
            /*
        Random rnd = new Random (System.currentTimeMillis());
        double seuilTol = 0.0001;
        
        BinomialDistribution binomial = new BinomialDistribution(100, 0.05);

        
        ArrayList<Double> probabilites = new ArrayList<>();
        ArrayList<Integer> nombreSucces = new ArrayList<>();
        
        int x = 5;
        double prob = binomial.probability(x);
        while(prob > seuilTol && x >= 0){
            probabilites.add(prob);
            nombreSucces.add(x);
            x -= 1;
            prob = binomial.probability(x);
        }
        
        System.out.println(probabilites);
        System.out.println(nombreSucces);
        
        double randomNumber = 0.0;
        double current = 0.0; //treshold
        int success = 0;
        
        for (int k = 0; k < 10; k++){
            randomNumber = rnd.nextDouble();
            current = 0.0; //treshold
            success = 0;
            for(int i = 0; i < probabilites.size(); i++){
                current += probabilites.get(0);
                if(randomNumber < current){
                    success = nombreSucces.get(i);
                    break;
                }
            }
        }
        System.out.println(success);
        */
        
}
