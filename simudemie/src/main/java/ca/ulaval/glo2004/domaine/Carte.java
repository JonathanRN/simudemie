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

public class Carte implements Serializable {
    
    private String nom;
    private final ArrayList<Pays> listePays = new ArrayList<>();
    private final ArrayList<VoieLiaison> frontieres = new ArrayList<>();
    private Maladie maladie;

    
    public Carte(String nom) {
        this.nom = nom;
    }    

    public Carte(Carte carteJourCourant) {
        this.nom = carteJourCourant.nom;
        this.maladie = new Maladie(carteJourCourant.getMaladie());
        
        this.listePays.clear();
        for (Pays pays : carteJourCourant.listePays) {
            this.listePays.add(new Pays(pays));
        }
        
        this.frontieres.clear();
        for (VoieLiaison voie : carteJourCourant.frontieres) {
            this.frontieres.add(new VoieLiaison(voie));
        }
    }
    
    public void initiliserPopInit()
    {
        for(Pays pays : listePays)
        {
            pays.setPopInitiale(pays.getPopInfectee() + pays.getPopSaine());
            
            for (Region r : pays.listeRegions){
                r.setPopInitiale(r.getPopInfectee() + r.getPopSaine());
            }
        }
    }
    
    public void avancerJour(int cptJours) {

        for (Pays pays : listePays){
            pays.avancerJournee(maladie.getTauxInfection(), maladie.getTauxMortalite(), maladie.getTauxGuerison(), cptJours);
        }
        this.contaminerInterPays();
    }
    
    private void contaminerInterPays() {
        //En fonction de la pop infectee du pays, va infecter le pays lié par sa voie de liaison
        // à un taux de 0.001 (à determiner)
        Pays paysOrigine = null;
        Pays paysDestination = null;
        
        for (VoieLiaison voie : frontieres)
        {
            if (!voie.getAccessible())
            {
                continue;
            }

            for (Pays p : listePays)
            {
                if (p.getPolygone().equals(voie.getPaysOrigine().getPolygone())){
                    paysOrigine = p;
                }
                if (p.getPolygone().equals(voie.getPaysDestination().getPolygone())){
                    paysDestination = p;
                }
            }
            
            if (paysOrigine.listeRegions.get(0).getPopInfectee() > 0 && paysDestination.listeRegions.get(0).getPopInfectee() == 0 )
            {
                double prob = Math.random();
                if (prob < 0.1 || (prob < 0.3 && paysDestination.getPopInfectee() > 1000)){
                    //Selection de la region 0 (la region ou se situe la voie - "theoriquement")
                    paysDestination.listeRegions.get(0).setPopInfectee(1); 
                    paysDestination.listeRegions.get(0).setPopSaine(paysDestination.listeRegions.get(0).getPopSaine() - 1); 
                }
            }
            
            if (paysDestination.listeRegions.get(0).getPopInfectee() > 0 && paysOrigine.listeRegions.get(0).getPopInfectee() == 0)
            {
                double prob = Math.random();
                if (prob < 0.1 || (prob < 0.3 && paysDestination.getPopInfectee() > 1000)){
                    //Selection de la region 0 (la region ou se situe la voie - "theoriquement")
                    paysOrigine.listeRegions.get(0).setPopInfectee(paysOrigine.listeRegions.get(0).getPopInfectee()+1);
                    paysOrigine.listeRegions.get(0).setPopSaine(paysOrigine.listeRegions.get(0).getPopSaine() - 1);
                }
            }
        }
    }
    
    public void ajouterPays(Pays nouveauPays)
    {
        nouveauPays.setNom("Pays " +  listePays.size());
        listePays.add(nouveauPays);
    }
    
    public void retirerPays(Pays ancienPays)
    {
        if (listePays.contains(ancienPays)){
            listePays.remove(ancienPays);
        }
    }
    
    public void ajouterVoie(VoieLiaison nouvelleVoie) {
        if (!frontieres.contains(nouvelleVoie)) {
            frontieres.add(nouvelleVoie);
        }
    }
    
    public void retirerVoie(VoieLiaison ancienneVoie) {
        if (frontieres.contains(ancienneVoie)){
            frontieres.remove(ancienneVoie);
        }
    }
    
    public ArrayList<VoieLiaison> getVoies() {
        return frontieres;
    }
    
    public ArrayList<VoieLiaison.TypeVoie> getVoiesDisponibles(Pays origine, Pays destination) {
        ArrayList<VoieLiaison.TypeVoie> voies = new ArrayList<>();
        boolean terrestre = false, maritime = false, aerien = false;
        
        for (VoieLiaison voie : frontieres
                .stream()
                .filter(x -> (x.getPaysOrigine().equals(origine) && x.getPaysDestination().equals(destination)) ||
                              x.getPaysOrigine().equals(destination) && x.getPaysDestination().equals(origine))
                .collect(Collectors.toList())) {
            
            switch (voie.getType()) {
                case Terrestre:
                    terrestre = true;
                    break;
                case Maritime:
                    maritime = true;
                    break;
                case Aerien:
                    aerien = true;
                    break;
            }
        }
        
        if (!terrestre) {
            voies.add(VoieLiaison.TypeVoie.Terrestre);
        }
        
        if (!maritime) {
            voies.add(VoieLiaison.TypeVoie.Maritime);
        }
        
        if (!aerien) {
            voies.add(VoieLiaison.TypeVoie.Aerien);
        }
        
        return voies;
    }
    
    public Pays getPays(int index) {
        return listePays.get(index);
    }
    
    public Pays getPays(Polygon p) {
        for (Pays pays : listePays) {
            if (pays.getPolygone().equals(p)) {
                return pays;
            }
            else {
                for (Region r : pays.getRegions()) {
                    if (r.getPolygone().equals(p)) {
                        return pays;
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<Polygon> getPolygonesRegions() {
        ArrayList<Polygon> polygones = new ArrayList<>();
        for (Pays pays : getListePays()) {
            polygones.addAll(pays.getRegions().stream().map(x -> x.getPolygone()).collect(Collectors.toList()));
        }
        return polygones;
    }
    
    public String getNom(){return nom;}

    
    public ArrayList<Pays> getListePays(){return listePays;}
    
    public Maladie getMaladie() { return maladie; }
    
    public void setMaladie(Maladie maladie) {
        // Cree une copie pour garder la maladie en memoire
        this.maladie = new Maladie(maladie);
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public int getPopulationTotale() {
        int total = 0;
        for(Pays pays : getListePays()) {
            total += pays.getPopTotale();
        }
        return total;
    }
    
    public int getPopulationInitiale() {
        return listePays.stream().collect(Collectors.summingInt(x -> x.getPopInitiale()));
    }
    
    public float getPourcentageInfectee() {
        return (float)listePays.stream().collect(Collectors.summingInt(x -> x.getPopInfectee())) / (float)getPopulationTotale();
    }
    
    public float getPourcentageSaine() {
        return (float)listePays.stream().collect(Collectors.summingInt(x -> x.getPopSaine())) / (float)getPopulationTotale();
    }
    
    public float getPourcentageDecedee() {
        return (float)listePays.stream().collect(Collectors.summingInt(x -> x.getPopDecedee())) / (float)getPopulationInitiale();
    }
}