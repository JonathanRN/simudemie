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
    
    public void creerMesure(int indexMesure, int indexPays, String nom, double tauxAdhesion, double tauxReduction, int seuilActivation, boolean active) {
        Pays pays = getCourant().getCarteJourCourant().getPays(indexPays);
        Mesure mesure = pays.getMesure(indexMesure);
        if (mesure == null) {
            pays.ajouterMesure(new Mesure(nom, tauxAdhesion, tauxReduction, seuilActivation, active));
        } else {
            mesure.setNom(nom);
            mesure.setTauxAdhesion(tauxAdhesion);
            mesure.setTauxReductionProp(tauxReduction);
            mesure.setSeuilActivation(seuilActivation);
            mesure.setActive(active);
        }
    }
    
    public void supprimerMesure(int indexMesure, int indexPays) {
        Pays pays = getCourant().getCarteJourCourant().getPays(indexPays);
        pays.supprimerMesure(indexMesure);
    }
    
    public void creerVaccin(int indexPays, String nom, double tauxImmunisation, double tauxAdhesion, int vaccinationQuotidienne, boolean active) {
        Vaccin vaccinGlobal = getCourant().getVaccin(nom);
        Vaccin vaccinPays = getCourant().getCarteJourCourant().getPays(indexPays).getVaccin(nom);
        Vaccin vaccin = new Vaccin(nom, tauxImmunisation, tauxAdhesion, vaccinationQuotidienne, active);
        
        if(vaccinGlobal == null) {
            getCourant().ajouterVaccin(vaccin);
        } else if(vaccinPays == null) {
            getCourant().getCarteJourCourant().getPays(indexPays).ajouterVaccin(vaccin);
        } else if(vaccinPays.getTauxImmunisation() ==  tauxImmunisation) {
            vaccinPays.setTauxAdhesion(tauxAdhesion);
            vaccinPays.setVaccinationQuotidienne(vaccinationQuotidienne);
            if(active) {
                for(Vaccin v : getCourant().getCarteJourCourant().getPays(indexPays).getVaccins()) {
                    v.setActive(false);
                }
            }
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
