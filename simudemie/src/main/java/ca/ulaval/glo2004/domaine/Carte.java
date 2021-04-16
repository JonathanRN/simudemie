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

public class Carte implements Externalizable {
    private static final long serialVersionUID = 1;
    
    private String nom;
    private final ArrayList<Pays> listePays = new ArrayList<>();
    private final ArrayList<VoieLiaison> frontieres = new ArrayList<>();
    private Maladie maladie;

    public Carte() {}
    
    public Carte(String nom) {this.nom = nom;}    

    public void charger(Carte carte) {
        this.nom = carte.nom;
        if (carte.getMaladie() != null) {
            this.maladie = new Maladie(carte.getMaladie());
        }
        
        this.listePays.clear();
        for (Pays pays : carte.listePays) {
            this.listePays.add(new Pays(pays));
        }
        
        this.frontieres.clear();
        for (VoieLiaison voie : carte.frontieres) {
            this.frontieres.add(new VoieLiaison(voie));
        }
    }
    
    public Carte(Carte carteJourCourant) {
        charger(carteJourCourant);
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
        //Pour chaque pays, nous effectuons la méthode avancerJour qui contamine/guéri/éliminer la population
        for (Pays pays : listePays){
            pays.avancerJournee(maladie.getTauxInfection(), maladie.getTauxMortalite(), maladie.getTauxGuerison(), cptJours, maladie.getIncubation(), maladie.getImmunitePossible());
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
            if (!voie.getAccessible()) //frontière fermée
            {
                continue;
            }

            for (Pays p : listePays)
            {
                if (p.getNom().equals(voie.getPaysOrigine().getNom())){
                    paysOrigine = p;
                }
                if (p.getNom().equals(voie.getPaysDestination().getNom())){
                    paysDestination = p;
                }
            }
            
            //Pour les pays d'origine et destination, si la population d'une région 0 est supérieur à 0,
            //on 'tente' d'infecter le pays de destination (on le set à 1).
            if (paysOrigine.getPopInfectee() > 0 && paysDestination.getPopInfectee() == 0){
                contaminerParVoie(paysOrigine, paysDestination, voie.getTauxPropag());
            }else if (paysDestination.getPopInfectee() > 0 && paysOrigine.getPopInfectee() == 0){
                contaminerParVoie(paysDestination, paysOrigine, voie.getTauxPropag());
            }
        }
    }
    
    public void contaminerParVoie(Pays paysO, Pays paysD, double tauxPropag)
    {
        double prob = Math.random();
        if (prob < tauxPropag || (prob < tauxPropag + 0.2 && paysD.getPopInfectee() > 1000)){
            paysD.listeRegions.get(0).setPopInfectee(1); 
            paysD.listeRegions.get(0).setPopSaine(paysD.listeRegions.get(0).getPopSaine() - 1); 
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
    
    public ArrayList<VoieLiaison> getVoies() {return frontieres;}
    
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
    
    public boolean peutSnapPays(Pays origine, Pays destination) {
        return getVoiesDisponibles(origine, destination).contains(VoieLiaison.TypeVoie.Terrestre);
    }
    
    public Pays getPays(int index) {return listePays.get(index);}
    
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
    
    public int getPopulationInfectee() {
        return listePays.stream().collect(Collectors.summingInt(x -> x.getPopInfectee()));
    }
    
    public float getPourcentageInfectee() {
         return (float)getPopulationInfectee() / (float)getPopulationTotale();
    }
    
    public int getPopulationSaine() {
        return listePays.stream().collect(Collectors.summingInt(x -> x.getPopSaine()));
    }
    
    public float getPourcentageSaine() {
        return (float)getPopulationSaine() / (float)getPopulationTotale();
    }
    
    public int getPopulationDecedee() {
        return listePays.stream().collect(Collectors.summingInt(x -> x.getPopDecedee()));
    }
    
    public float getPourcentageDecedee() {
        return (float)getPopulationDecedee() / (float)getPopulationInitiale();
    }

    public int getPopulationImmune() {
        return listePays.stream().collect(Collectors.summingInt(x -> x.getPopImmune()));
    }
    
    public float getPourcentageImmune() {
        return (float)getPopulationImmune() / (float)getPopulationTotale();
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(nom);
        out.writeInt(listePays.size());
        for(int index = 0; index < listePays.size(); index++) {
            out.writeObject(listePays.get(index));
        }
        out.writeInt(frontieres.size());
        for(int index = 0; index < frontieres.size(); index++) {
            out.writeObject(frontieres.get(index));
        }
        out.writeObject(maladie);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        listePays.clear();
        frontieres.clear();
        nom = in.readUTF();
        int listePaysSize = in.readInt();
        for(int index = 0; index < listePaysSize; index++) {
            listePays.add((Pays) in.readObject());
        }
        int frontieresSize = in.readInt();
        for(int index = 0; index < frontieresSize; index++) {
            frontieres.add((VoieLiaison) in.readObject());
        }
        maladie = (Maladie) in.readObject();
    }
}