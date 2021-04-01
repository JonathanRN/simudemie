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
    
    public void avancerJour(int cptJours) {

        for (Pays pays : listePays){
            pays.avancerJournee(maladie.getTauxInfection(), maladie.getTauxMortalite(), maladie.getTauxGuerison(), cptJours);
        }
        contaminerInterPays();
    }
    
    private void contaminerInterPays() {
        //En fonction de la pop infectee du pays, va infecter le pays lié par sa voie de liaison
        // à un taux de 0.001 (à determiner)
        
        for (VoieLiaison voie : frontieres)
        {
            if (!voie.getAccessible())
            {
                continue;
            }
            
            Pays paysOrigine = voie.getPaysOrigine();
            Pays paysDestination = voie.getPaysDestination();
            
            if (paysOrigine.getPourcentageInfectee() > 0)
            {
                double prob = Math.random();
                if (prob * 10 < paysOrigine.getPourcentageInfectee()/10){
                    //Selection de la region 0 (la region ou se situe la voie - "theoriquement")
                    int infectees = voie.getPaysDestination().listeRegions.get(0).getPopInfectee();
                    voie.getPaysDestination().listeRegions.get(0).setPopInfectee(infectees + 1);
                    voie.getPaysDestination().listeRegions.get(0).setPopSaine(infectees + 1);
                }
            }
            
            if (paysDestination.getPourcentageInfectee() > 0)
            {
                double prob = Math.random();
                if (prob * 10 < paysDestination.getPourcentageInfectee()/10){
                    //Selection de la region 0 (la region ou se situe la voie - "theoriquement")
                    int infectees = voie.getPaysOrigine().listeRegions.get(0).getPopInfectee();
                    voie.getPaysOrigine().listeRegions.get(0).setPopInfectee(infectees + 1);
                    voie.getPaysOrigine().listeRegions.get(0).setPopSaine(infectees + 1);
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
}