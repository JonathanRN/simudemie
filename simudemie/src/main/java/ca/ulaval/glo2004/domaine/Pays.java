/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;
import java.awt.Polygon;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Pays implements Externalizable {
    
    private String nom;
    private Polygon polygone;
    private int popInitiale; 
    //private ArrayList<Vaccin> vaccins = new ArrayList<>(); //Au cas ou il y aurait plusieurs vaccins - a voir
    private ArrayList<Mesure> mesures = new ArrayList<>();
    public ArrayList<Region> listeRegions = new ArrayList<>();

    public Pays() {}
    
    public Pays(Polygon polygone) {this.polygone = new Polygon(polygone.xpoints, polygone.ypoints, polygone.npoints);}

    public Pays(Pays pays) {
        this.nom = pays.nom;
        this.polygone = new Polygon(pays.polygone.xpoints, pays.polygone.ypoints, pays.polygone.npoints);
        this.popInitiale = pays.popInitiale;
        
        this.mesures.clear();
        for (Mesure mesure : pays.mesures) {
            this.mesures.add(new Mesure(mesure));
        }
        
        this.listeRegions.clear();
        for (Region region : pays.listeRegions) {
            this.listeRegions.add(new Region(region));
        }
        
//      this.vaccins.clear();
//      for (Vaccin vaccin : pays.vaccins) {
//          this.vaccins.add(new Vaccin(vaccin));
//      }
    }
    
    
    public void avancerJournee(double tauxInf, double tauxMortalite, double tauxGuerison, int cptJours, int incubation)
    {
        tauxInf /= 100d;
        tauxMortalite /= 100d;
        tauxGuerison /= 100d;
        
        double tauxInfAjuste = tauxInf;
        for (Region region : listeRegions)
        {
            //Chaque mesure vient appliquer une réduction sur le taux de propagation
            for (Mesure m : mesures)
            {
                if (m.getActive()) {
                    tauxInfAjuste = tauxInfAjuste * (m.getTauxAdhesion() / 100.0 * m.getTauxReductionProp() / 100.0);
                }
            }
            region.contaminerPopulation(tauxInfAjuste, cptJours, incubation);
            region.guerirPopulation(tauxGuerison, cptJours, incubation);
            region.eliminerPopulation(tauxMortalite, cptJours, incubation);
        }
        
        contaminerInterRegions();
        seuilAtteint();
        //vaccination();
    }
    
    public void seuilAtteint(){
        for (Mesure m : mesures){
            if (this.getPourcentageInfectee() >= m.getSeuilActivation()){
                m.setActive(true);
            }else{
                m.setActive(false);
            }
        }
    }
    
//    public void vaccination(){
//        if(!this.vaccins.isEmpty()){
//            for (Region r : listeRegions){
//                for (Vaccin v : vaccins){
//                    r.vaccinationPop(v.getTauxImmunisation(), v.getVaccinationQuotidienne());
//                }
//            }
//        }
//    }
    
    public void contaminerInterRegions()
    //Pour chaque région infectée, si le nombre d'infection est superieure à 25, il y a une chance sur deux de contaminer 
    //une autre région du pays. 
    {
        for (Region r : listeRegions)
        {
            if (r.getPopInfectee() > 25)
            {
                for( Region f : listeRegions)
                {
                    double prob = Math.random();
                    if(!r.equals(f) && prob < r.getTauxContaInterRegion()){
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
    
    public ArrayList<Region> getRegions() {return listeRegions;}
    
    public Region getRegion(Polygon p) {return listeRegions.stream().filter(x -> x.getPolygone().equals(p)).findFirst().get();}
        
    public String getNom(){return nom;}
    
    public int getPopInfectee() {return listeRegions.stream().collect(Collectors.summingInt(x -> x.getPopInfectee()));}
    
    public int getPopSaine() {return listeRegions.stream().collect(Collectors.summingInt(x -> x.getPopSaine()));}
    
    public int getPopDecedee() {return listeRegions.stream().collect(Collectors.summingInt(x -> x.getPopDecedee()));}
    
    public int getPopTotale() {return listeRegions.stream().collect(Collectors.summingInt(x -> x.getPopTotale()));}
    
    public int getPopImmune() {return listeRegions.stream().collect(Collectors.summingInt(x -> x.getPopImmunisee()));}
    
    public int getPopInitiale() {return popInitiale;}
    
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
    
    public Mesure getMesure(int index) {
        if(index >= mesures.size()) { return null;}
        return mesures.get(index);
    }
    
    public ArrayList<Mesure> getMesures() {return mesures;}
    
    public Polygon getPolygone() {return polygone;};
    
    public ArrayList<Region> getListeRegions(){return listeRegions;}
    
    public void setNom(String nom) {this.nom = nom;}
    
    public void setPopInitiale(int popInitiale) {this.popInitiale = popInitiale;}
    
    public void setMesure(int index, Mesure mesure) {mesures.set(index, mesure);}
    
    public void ajouterMesure(Mesure mesure) {mesures.add(mesure);}
    
    public void supprimerMesure(int index) {mesures.remove(index);}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(nom);
        out.writeObject(polygone);
        out.writeInt(popInitiale);
        out.writeObject(mesures);
        out.writeObject(listeRegions);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        nom = in.readUTF();
        polygone = (Polygon) in.readObject();
        popInitiale = in.readInt();
        mesures = (ArrayList<Mesure>) in.readObject();
        listeRegions = (ArrayList<Region>) in.readObject();
    }
}