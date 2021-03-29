/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.awt.Polygon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Carte implements Serializable {
    
    private String nom;
    private final ArrayList<Pays> listePays = new ArrayList<>();
    private final ArrayList<VoieLiaison> frontieres = new ArrayList<>();
    private Maladie maladie;
    
    public Carte(String nom)
    {
        this.nom = nom;
    }    

    public Carte(Carte carteJourCourant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void avancerJour()
    {
        for (Pays pays : listePays){
            pays.avancerJournee(maladie.getTauxInfection(), maladie.getTauxMortalite(), maladie.getTauxGuerison());
        }
    }
    
    private void contaminerInterPays() {
        //En fonction de la pop infectee du pays, va infecter le pays lié par sa voie de liaison
        // à un taux de 0.001 (à determiner)
        double voyageursInfectees;
        Pays paysAInfecter;
        for (VoieLiaison voie : frontieres)
        {
            if (!voie.getAccessible())
            {
                continue;
            }
            for ( Pays pays : listePays)
            {
                if (pays.getPopInfecteePays() > 0)
                {
                    voyageursInfectees = (0.001 * pays.getPopInfecteePays()); //Déterminer quel sera le 0.001
                    if (this.getNom().equals(voie.getPaysOrigine().getNom()) )
                    {
                        paysAInfecter = voie.getPaysDestination(); //validation (selon origine/destination)
                    }else
                    {
                        paysAInfecter = voie.getPaysOrigine();
                    }
                    for (Region region : paysAInfecter.listeRegions )
                    {
                        double prob = Math.random();
                        if (prob > 0.5) //une chance sur deux
                        {//Arrondissement avec le int
                            region.setPopInfectee(pays.getPopInfecteePays() + (int)(voyageursInfectees / paysAInfecter.listeRegions.size()));
                        }
                    }
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
    
    public Maladie getMaladie() {return maladie;}
    
    public void setMaladie(Maladie maladie)
    {
        this.maladie = maladie;
    }
    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    /*
    public Pays trouverPays(Pays aTrouver)
    {
        return new Pays();
    }
    */
}