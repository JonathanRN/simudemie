/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;
import java.awt.Polygon;
import java.io.Serializable;
import java.util.ArrayList;

public class Pays implements Serializable {
    
    private String nom;
    private Polygon polygone;
    public ArrayList<Mesure> mesures =new ArrayList<>();
    public ArrayList<Region> listeRegions = new ArrayList<>();

    public Pays(Polygon polygone) {
        this.polygone = polygone;
    }
    
    public void avancerJournee(double tauxInf, double tauxMortalite, double tauxGuerison)
    {
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
        //contaminerInterPays();
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
        
    public String getNom(){ return nom; }
    
    public int getPopInfecteePays()
    {
        int sum = 0;
        for (Region r : listeRegions) {
            sum += r.getPopInfectee();
        }
        return sum;
    }
    
    public int getPopTotale() {
        int sum = 0;
        for (Region r : listeRegions) {
            sum += r.getPopTotale();
        }
        return sum;
    }
    
    public Polygon getPolygone() { return polygone; };
    
    public void setNom(String nom) {
        this.nom = nom;
    }
}