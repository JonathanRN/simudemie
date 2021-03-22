/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.awt.Polygon;
import java.util.List;

public class Pays {
    
    private String nom;
    private int populationTotale;
    private List<VoieLiaison> frontieres;
    private List<Region> listeRegions;
    private Polygon polygone;
    public List<Mesure> mesures;

    public Pays(String nom, int populationTotale, Polygon polygone) {
        this.nom = nom;
        this.populationTotale = populationTotale;
        this.polygone = polygone;
    }
    
    public void avancerJournee(float tauxInf, float tauxMortalite, float tauxGuerison)
    {
        float tauxInfAjuste = tauxInf;
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
    
    public void ajouterRegion(Region nouvelleRegion)
    {
        listeRegions.add(nouvelleRegion);
    }
    
    public void retirerRegion(Region ancienneRegion)
    {
        if (listeRegions.contains(ancienneRegion)){
            listeRegions.remove(ancienneRegion);
        }
    }
    
    public void ajouterVoie(VoieLiaison nouvelleVoie)
    {
        frontieres.add(nouvelleVoie);
    }
    
    public void retirerVoie(VoieLiaison ancienneVoie)
    {
        if (frontieres.contains(ancienneVoie)){
            frontieres.remove(ancienneVoie);
        }
    }
        
    public String getNom(){ return nom; }
    
    public int getPopTotale(){ return populationTotale; }
    
    public Polygon getPolygone() { return polygone; };
    
    public void setNom(String nom)
    {
        System.out.println("Pays: " + this.nom + " -> " + nom);
        this.nom = nom;
    }
     
    public void setPopTotale(int populationTotale)
    {
        System.out.println("Pays: " + this.populationTotale + " -> " + populationTotale);
        this.populationTotale = populationTotale;
    }
}