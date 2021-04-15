/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import ca.ulaval.glo2004.afficheur.Simulation.ScenarioCallback;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mick
 */
public class Scenario implements Externalizable {
    private static final long serialVersionUID = 1;   
    
    private String nom;
    private final List<Carte> cartes = new ArrayList<>();
    private int indexCourant;
    private boolean estCommence;
    private List<Vaccin> vaccins = new ArrayList<>();
    
    transient private ScenarioCallback scenarioCallback;
    
    public Scenario() {}
    
    public Scenario(String nom, Carte carte, Maladie maladie) {
        this.nom = nom;
        
        // Ajoute la carte en parametre comme jour 0
        carte.setMaladie(new Maladie(maladie));
        cartes.add(new Carte(carte));
    }
    
    public void initialisePopInit() {cartes.get(0).initiliserPopInit();}
    
    public void setCallback(ScenarioCallback cb) {this.scenarioCallback = cb;}
    
    public boolean estCommence() {return estCommence;}
    
    public Carte getCarteJourCourant() {return cartes.get(indexCourant);}
    
    public int getIndexJourCourant() {return indexCourant;}
    
    public int getTotalJours() {return cartes.size() - 1;}
    
    public Carte getCarteDernierJour() {return cartes.get(getTotalJours());}
    
    public List<Carte> getListeCartes() {return cartes;}
    
    public String getNom() {return nom;}
    
    public Vaccin getVaccin(String nom) {
        return vaccins.stream()
                .filter(v -> v.getNom().equals(nom))
                .findFirst()
                .orElse(null);
    }
    
    public List<Vaccin> getVaccins() { return vaccins; }
    
    public void ajouterVaccin(Vaccin vaccin) { vaccins.add(vaccin); }
    
    public void supprimerVaccin(String nom) { vaccins.removeIf(v -> v.getNom().equals(nom)); }
    
    public void setNom(String nom) {this.nom = nom;}
    
    public void chargerJour(int index) {
        indexCourant = index;
        scenarioCallback.onChargerJour(index);
    }
    
    public void stepJour(int step) {
        if (step == -1 && indexCourant > 0) {
            chargerJour(indexCourant - 1);
        }
        else if (step == 1 && indexCourant < cartes.size() - 1) {
            chargerJour(indexCourant + 1);
        }
    }
    
    public void demarrer() {estCommence = true; retourEnDirect();}
    
    public void retourEnDirect() {indexCourant = getTotalJours();}
    
    public int avancerJour() {
        // Ajoute une nouvelle journee selon les nouvelles donnees modifies de la carte courante
        cartes.add(new Carte(getCarteJourCourant()));
        
        indexCourant++;
        getCarteJourCourant().avancerJour(indexCourant);
        
        scenarioCallback.onAvancerJour(indexCourant);
        
        return indexCourant;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(nom);
        out.writeInt(cartes.size());
        for(Carte carte : cartes) {
            out.writeObject(carte);
        }
        out.writeInt(indexCourant);
        out.writeBoolean(estCommence);
        out.writeInt(vaccins.size());
        for(Vaccin v : vaccins) {
            out.writeObject(v);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        cartes.clear();
        vaccins.clear();
        
        nom = in.readUTF();
        int cartesSize = in.readInt();
        for(int index = 0; index < cartesSize; index++) {
            cartes.add((Carte) in.readObject());
        }
        indexCourant = in.readInt();
        estCommence = in.readBoolean();
        int vaccinsSize = in.readInt();
        for(int index = 0; index < vaccinsSize; index++) {
            vaccins.add((Vaccin) in.readObject());
        }
    }
}
