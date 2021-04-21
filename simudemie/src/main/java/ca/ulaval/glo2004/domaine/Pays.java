/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Pays implements Externalizable {
    private static final long serialVersionUID = 1;   
    
    private String nom;
    private int popInitiale;
    private ArrayList<Vaccin> vaccins = new ArrayList<>();
    private ArrayList<Mesure> mesures = new ArrayList<>();
    public ArrayList<Region> listeRegions = new ArrayList<>();

    public Pays(Pays pays) {
        this.nom = pays.nom;
        this.popInitiale = pays.popInitiale;
        
        this.mesures.clear();
        for (Mesure mesure : pays.mesures) {
            this.mesures.add(new Mesure(mesure));
        }
        
        this.listeRegions.clear();
        for (Region region : pays.listeRegions) {
            this.listeRegions.add(new Region(region));
        }
        
        this.vaccins.clear();
        for (Vaccin vaccin : pays.vaccins) {
            this.vaccins.add(new Vaccin(vaccin));
        }
    }

    public Pays() {
    }
    
    public void avancerJournee(double tauxInf, double tauxMortalite, double tauxGuerison, int cptJours, int incubation, boolean immunitePossible) {
        tauxInf /= 100d;
        tauxMortalite /= 100d;
        tauxGuerison /= 100d;
        
        seuilAtteint();
        double tauxInfAjuste = tauxInf;
        for (Region region : listeRegions)
        {
            //Chaque mesure vient appliquer une réduction sur le taux de propagation
            for (Mesure m : mesures)
            {
                if (m.getActive()) {
                    tauxInfAjuste = tauxInfAjuste - (m.getTauxAdhesion() / 100.0 * m.getTauxReductionProp() / 100.0);
                    if (tauxInfAjuste < 0){
                        tauxInfAjuste = 0.01;
                    }
                }
            }
            region.contaminerPopulation(tauxInfAjuste, cptJours, incubation);
            region.guerirPopulation(tauxGuerison, cptJours, incubation, immunitePossible);
            region.eliminerPopulation(tauxMortalite, cptJours, incubation);
        }
        
        contaminerInterRegions();
        vaccination();
    }
    
    public void seuilAtteint(){
        for (Mesure m : mesures){
            setMesureActiveIfOverSeuil(m);
        }
    }
    
    public boolean setMesureActiveIfOverSeuil(Mesure mesure) {
        boolean active;
        if (this.getPourcentageInfectee() >= mesure.getSeuilActivation()){
            active = true;
        } else  {
            active = false;
        }
        mesure.setActive(active);
        return active;
    }
    
    public void vaccination(){
        for (Vaccin v : vaccins) {
            if(v.getActive()) {
                for (Region r : listeRegions) {
                    r.vaccinerPopulation(v.getTauxImmunisation(), v.getVaccinationQuotidienne());
                }
            }
        }
    }
    
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
    
    public float getPourcentageImmunisee() {
        if (this.getPopTotale() <= 0) {
            return 0;
        }
        return ((float)this.getPopImmune() / (float)this.getPopTotale()) * 100f;
    }
    
    public Mesure getMesure(int index) {
        if(index >= mesures.size()) { return null;}
        return mesures.get(index);
    }
    
    public ArrayList<Mesure> getMesures() {return mesures;}
    
    public Vaccin getVaccin(String nom) {
        Vaccin vaccin = null;
        for(Vaccin v : vaccins) {
            if(v.getNom().equals(nom)) {
                vaccin = v;
            }
        }
        return vaccin;
    }
    
    public ArrayList<Vaccin> getVaccins() { return vaccins; }
    
    public ArrayList<Region> getListeRegions(){return listeRegions;}
    
    public void setNom(String nom) {this.nom = nom;}
    
    public void setPopInitiale(int popInitiale) {this.popInitiale = popInitiale;}
    
    public void setMesure(int index, Mesure mesure) {mesures.set(index, mesure);}
    
    public void ajouterMesure(Mesure mesure) {mesures.add(mesure);}
    
    public void supprimerMesure(int index) {mesures.remove(index);}
    
    public void ajouterVaccin(Vaccin vaccin) { vaccins.add(vaccin); }
    
    public void supprimerVaccin(String nom) { vaccins.removeIf(v -> v.getNom().equals(nom));}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(nom);
        out.writeInt(popInitiale);
        out.writeInt(mesures.size());
        for(Mesure m : mesures) {
            out.writeObject(m);
        }
        out.writeInt(listeRegions.size());
        for(Region r : listeRegions) {
            out.writeObject(r);
        }
        out.writeInt(vaccins.size());
        for(Vaccin v : vaccins) {
            out.writeObject(v);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        mesures.clear();
        listeRegions.clear();
        vaccins.clear();
        
        nom = in.readUTF();
        popInitiale = in.readInt();
        int mesuresSize = in.readInt();
        for(int index = 0; index < mesuresSize; index++) {
            mesures.add((Mesure) in.readObject());
        }
        
        int listeRegionsSize = in.readInt();
        for(int index = 0; index < listeRegionsSize; index++) {
            listeRegions.add((Region) in.readObject());
        }
        
        int vaccinsSize = in.readInt();
        for(int index = 0; index < vaccinsSize; index++) {
            vaccins.add((Vaccin) in.readObject());
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + nom.hashCode();
        hash = 11 * hash + listeRegions.hashCode();
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        Pays autre = (Pays)obj;
        // Devrait relativement est correct...
        return nom.equals(autre.nom) && listeRegions.equals(autre.listeRegions);
    }
    
    public boolean contient(int x, int y) {
        for (Region r : listeRegions) {
            if (r.getPolygone().contains(x, y)) {
                return true;
            }
        }
        return false;
    }
    
    public Rectangle2D getBounds() {
        Rectangle2D bounds = null;
        for (Region r : listeRegions) {
            if (bounds == null) {
                bounds = r.getPolygone().getBounds2D();
            }
            else {
                bounds.add(r.getPolygone().getBounds2D());
            }
        }
        return bounds;
    }
}