/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;
import java.awt.Polygon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Pays implements Serializable {
    
    private String nom;
    private Polygon polygone;
    private int popInitiale; 
    public ArrayList<Mesure> mesures = new ArrayList<>();
    public ArrayList<Region> listeRegions = new ArrayList<>();

    public Pays(Polygon polygone) {
        this.polygone = polygone;
    }

    public Pays(Pays pays) {
        this.nom = pays.nom;
        this.polygone = pays.polygone;
        this.popInitiale = pays.popInitiale;
        
        this.mesures.clear();
        for (Mesure mesure : pays.mesures) {
            this.mesures.add(new Mesure(mesure));
        }
        
        this.listeRegions.clear();
        for (Region region : pays.listeRegions) {
            this.listeRegions.add(new Region(region));
        }
    }
    
    
    public void avancerJournee(double tauxInf, double tauxMortalite, double tauxGuerison, int cptJours)
    {
        tauxInf /= 100d;
        tauxMortalite /= 100d;
        tauxGuerison /= 100d;
        
        double tauxInfAjuste = tauxInf;
        for (Region region : listeRegions)
        {
            for (Mesure m : mesures)
            {
                if(m.getActive()) {
                    tauxInfAjuste = tauxInfAjuste * (m.getTauxAdhesion() * m.getTauxReductionProp());
                }
            }
            region.contaminer(tauxInfAjuste, cptJours);
            region.guerirPop(tauxGuerison, cptJours);
            region.eliminerPopulation(tauxMortalite, cptJours);
        }
        
        contaminerInterRegions();
    }
    
    public void contaminerInterRegions()
    {
        for (Region r : listeRegions)
        {
            if (r.getPopInfectee() > 25)
            {
                for( Region f : listeRegions)
                {
                    double prob = Math.random();
                    if(!r.equals(f) && prob > 0.5){
                        int voyageursInfectes = (int)((float)r.getPopInfectee() * 0.01);
                        f.setPopInfectee(f.getPopInfectee()+ voyageursInfectes);
                        f.setPopSaine(f.getPopSaine() - voyageursInfectes);
                    }
                }
            }
        }
    }
    
    public void ajouterRegion(Region nouvelleRegion) {
        nouvelleRegion.setNom("Region " + listeRegions.size());
        listeRegions.add(nouvelleRegion);
    }
    
    public void retirerRegion(Region ancienneRegion) {
        if (listeRegions.contains(ancienneRegion)) {
            listeRegions.remove(ancienneRegion);
        }
    }
    
    public void retirerRegion(int index) {
        try {
            listeRegions.remove(index);
        } catch (Exception e) {
        }
    }
    
    public ArrayList<Region> getRegions() {
        return listeRegions;
    }
    
    public Region getRegion(Polygon p) {
        return listeRegions.stream().filter(x -> x.getPolygone().equals(p)).findFirst().get();
    }
        
    public String getNom(){ return nom; }
    
    public int getPopInfectee() {
        return listeRegions.stream().collect(Collectors.summingInt(x -> x.getPopInfectee()));
    }
    
    public int getPopSaine() {
        return listeRegions.stream().collect(Collectors.summingInt(x -> x.getPopSaine()));
    }
    
    public int getPopDecedee() {
        return listeRegions.stream().collect(Collectors.summingInt(x -> x.getPopDecedee()));
    }
    
    public int getPopTotale() {
        return listeRegions.stream().collect(Collectors.summingInt(x -> x.getPopTotale()));
    }
    
    public int getPopInitiale() {
        return popInitiale;
    }
    
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
    
    public Polygon getPolygone() { return polygone; };
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setPopInitiale(int popInitiale) {
        this.popInitiale = popInitiale;
    }
}