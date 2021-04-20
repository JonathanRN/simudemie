/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine.controleur;

import ca.ulaval.glo2004.afficheur.Simulation.ScenarioCallback;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Maladie;
import ca.ulaval.glo2004.domaine.Mesure;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.Scenario;
import ca.ulaval.glo2004.domaine.Vaccin;
import ca.ulaval.glo2004.domaine.helper.FileHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;


public class GestionnaireScenario extends GestionnaireOnglet<Scenario> implements ActionListener {
    
    protected final String RELATIVE_PATH = "Scenarios\\scenarios.ser";
    private Timer timer;    
    private int scenarioCourant;
    
    private static GestionnaireScenario instance;
    
    public static GestionnaireScenario getInstance() {
        if (instance == null) {
            instance = new GestionnaireScenario();
        }
        return instance;
    }
    
    private GestionnaireScenario()
    {
        timer = new Timer(2000, this);
        timer.setInitialDelay(0);
        
        fileHelper = new FileHelper(RELATIVE_PATH);
        charger();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {getCourant().avancerJour();}
    
    public void setIndexCourant(int index) {scenarioCourant = index;}
    
    public Scenario getCourant() {return getElement(scenarioCourant);}
    
    // retourne true
    public boolean creerMesure(int indexMesure, int indexPays, String nom, double tauxAdhesion, double tauxReduction, int seuilActivation) {
        Pays pays = getCourant().getCarteJourCourant().getPays(indexPays);
        Mesure mesure = pays.getMesure(indexMesure);
        if (mesure == null) {
            mesure = new Mesure(nom, tauxAdhesion, tauxReduction, seuilActivation);
            pays.ajouterMesure(mesure);
        } else {
            mesure.setNom(nom);
            mesure.setTauxAdhesion(tauxAdhesion);
            mesure.setTauxReductionProp(tauxReduction);
            mesure.setSeuilActivation(seuilActivation);
        }
        
        return pays.setMesureActiveIfOverSeuil(mesure);
    }
    
    public void supprimerMesure(int indexMesure, int indexPays) {
        Pays pays = getCourant().getCarteJourCourant().getPays(indexPays);
        pays.supprimerMesure(indexMesure);
    }
    
    public void creerVaccin(int indexPays, String nom, double tauxImmunisation, double tauxAdhesion, double vaccinationQuotidienne, boolean active) {
        Vaccin vaccinGlobal = getCourant().getVaccin(nom);
        Vaccin vaccinPays = getCourant().getCarteJourCourant().getPays(indexPays).getVaccin(nom);
        Vaccin vaccin = new Vaccin(nom, tauxImmunisation, tauxAdhesion, vaccinationQuotidienne, active);
        
        if(vaccinGlobal == null) {
            getCourant().getCarteJourCourant().getPays(indexPays).ajouterVaccin(new Vaccin(vaccin));
            vaccin.setTauxAdhesion(0.01);
            vaccin.setVaccinationQuotidienne(0);
            vaccin.setActive(false);
            getCourant().ajouterVaccin(vaccin);
        } else if(vaccinPays == null) {
            getCourant().getCarteJourCourant().getPays(indexPays).ajouterVaccin(vaccin);
        } else if(vaccinPays.getTauxImmunisation() ==  tauxImmunisation) {
            vaccinPays.setTauxAdhesion(tauxAdhesion);
            vaccinPays.setVaccinationQuotidienne(vaccinationQuotidienne);
            // Si on veut un vaccin à la fois
            /*
            if(active) {
                for(Vaccin v : getCourant().getCarteJourCourant().getPays(indexPays).getVaccins()) {
                    v.setActive(false);
                }
            }
            */
            vaccinPays.setActive(active);
        } else {
            // Le taux d'immunisation a changé, on doit donc appliquer le nouveau taux à tous les pays
            for(Pays pays : getCourant().getCarteJourCourant().getListePays()) {
                vaccin = pays.getVaccin(nom);
                if(vaccin != null) {
                    vaccin.setTauxImmunisation(tauxImmunisation);
                }
            }
            vaccinGlobal.setTauxImmunisation(tauxImmunisation);
        }
    }
    
    /**
     *  Pour chaque vaccins existant globalement, si une version de ce vaccin existe dans un pays,
     *  on l'ajoute à la liste, sinon on ajoute la version globale.
     * @param indexPays
     * @return la liste des vaccins parcourus
     */
    public List<Vaccin> getVaccins(int indexPays) {
        List<Vaccin> vaccins = new ArrayList<>();
        for(Vaccin vaccin : getCourant().getVaccins()) {
            Vaccin vaccinPays = getCourant().getCarteJourCourant().getPays(indexPays).getVaccin(vaccin.getNom());
            if(vaccinPays != null) {
                vaccins.add(vaccinPays);
            } else {
                vaccins.add(vaccin);
            }
        }
        return vaccins;
    }
    
    public boolean supprimerVaccin(int indexPays, String nom) {
        boolean vaccinActiveElsewhere = false;
        getCourant().getCarteJourCourant().getPays(indexPays).supprimerVaccin(nom);

        for(Pays pays : getCourant().getCarteJourCourant().getListePays()) {
            Vaccin vaccin = pays.getVaccin(nom);
            if(vaccin != null && vaccin.getActive()) {
                vaccinActiveElsewhere = true;
                break;
            }
        }
        
        if(!vaccinActiveElsewhere) {
            getCourant().supprimerVaccin(nom);
        }
        
        return vaccinActiveElsewhere;
    }
    
    public void pause() {timer.stop();}
    
    public void resumer() {getCourant().retourEnDirect();timer.restart();}
    
    public void demarrer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        
        timer.start();
        
        getCourant().demarrer();
    }
    
    public void setCallback(ScenarioCallback scenarioCallback) {getCourant().setCallback(scenarioCallback);}
    
    public void setVitesse(int vitesse) {if (timer != null) {timer.setDelay((int)(1f / (float)vitesse * 1000));}}
    
    @Override
    public Scenario creer(Object... arguments) {
        String nom = (String) arguments[0];
        int indexCarte = (int) arguments[1];
        int indexMaladie = (int) arguments[2];
        
        Carte carte = GestionnaireCarte.getInstance().getElement(indexCarte);
        Maladie maladie = GestionnaireMaladie.getInstance().getElement(indexMaladie);
        
        Scenario scenario = new Scenario(nom, carte, maladie);
        scenario.initialisePopInit();
        ajouter(scenario);
        return scenario;
    }
}
