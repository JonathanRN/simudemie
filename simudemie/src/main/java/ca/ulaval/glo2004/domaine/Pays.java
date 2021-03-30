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
    public ArrayList<Mesure> mesures = new ArrayList<>();
    public ArrayList<Region> listeRegions = new ArrayList<>();

    public Pays(Polygon polygone) {
        this.polygone = polygone;
    }

    public Pays(Pays pays) {
        this.nom = pays.nom;
        this.polygone = pays.polygone;
        
        this.mesures.clear();
        for (Mesure mesure : pays.mesures) {
            this.mesures.add(new Mesure(mesure));
        }
        
        this.listeRegions.clear();
        for (Region region : pays.listeRegions) {
            this.listeRegions.add(new Region(region));
        }
    }
    
    public void avancerJournee(double tauxInf, double tauxMortalite, double tauxGuerison)
    {
        tauxInf /= 100d;
        tauxMortalite /= 100d;
        tauxGuerison /= 100d;
        
        double tauxInfAjuste = tauxInf;
        for (Region region : listeRegions)
        {
            for (Mesure m : mesures)
            {
                tauxInfAjuste = tauxInfAjuste * (m.getTauxAdhesion() + m.getTauxReductionProp());
            }
            region.contaminer(tauxInfAjuste);
            region.guerirPop(tauxGuerison);
            region.eliminerPopulation(tauxMortalite);
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
    
    public float getPourcentageInfectee() {
        try {
            return (getPopInfectee() / getPopTotale()) * 100f;
        }
        catch (java.lang.ArithmeticException e) {
            return 0;
        }
    }
    
    public float getPourcentageSaine() {
        try {
            return (getPopSaine() / getPopTotale()) * 100f;
        }
        catch (java.lang.ArithmeticException e) {
            return 0;
        }
    }
    
    public float getPourcentageDecedee() {
        try {
            return (getPopDecedee() / getPopTotale()) * 100f;
        }
        catch (java.lang.ArithmeticException e) {
            return 0;
        }
    }
    
    public Polygon getPolygone() { return polygone; };
    
    public void setNom(String nom) {
        this.nom = nom;
    }
}