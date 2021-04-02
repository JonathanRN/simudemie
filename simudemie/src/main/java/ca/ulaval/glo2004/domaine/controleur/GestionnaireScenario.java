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
import ca.ulaval.glo2004.domaine.Scenario;
import ca.ulaval.glo2004.domaine.helper.FileHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;


public class GestionnaireScenario extends GestionnaireOnglet<Scenario> implements ActionListener {
    protected final String RELATIVE_PATH = "Scenarios\\scenarios.ser";
    private Timer timer;    
    private int scenarioCourant;
    
    private ScenarioCallback scenarioCallback;
    
    private static GestionnaireScenario instance;
    
    public static GestionnaireScenario getInstance() {
        if (instance == null) {
            instance = new GestionnaireScenario();
        }
        return instance;
    }
    
    private GestionnaireScenario()
    {
        fileHelper = new FileHelper(RELATIVE_PATH);
        charger();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int jour = getCourant().avancerJour();
        scenarioCallback.onAvancerJour(jour);
    }
    
    public Scenario getCourant() {
        return getElement(scenarioCourant);
    }
    
    public void creerMesure(String nom, double tauxAdhesion, double tauxReduction)
    {
        Mesure mesure = new Mesure(nom, tauxAdhesion, tauxReduction);
        // TODO: Quel pays ?!?
        //getCourant().getCarteJourCourant().
    }
                
    private void editerMesure(String nom, double tauxAdhesion, double tauxReduction)
    {
        //jourCourant.mesures[]...
    }
    
    public void pause() {
        timer.stop();
    }
    
    public void resumer() {
        getCourant().retourEnDirect();
        timer.restart();
    }
    
    public void demarrer(int index, int secondes, ScenarioCallback scenarioCallback) {
        scenarioCourant = index;
        
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        
        timer = new Timer(secondes * 1000, this);
        timer.setInitialDelay(0);
        timer.start();
        
        getCourant().demarrer();
        this.scenarioCallback = scenarioCallback;
    }
    
    public List<Carte> retournerResultats()
    {
        //TODO Retourner les résultats finaux
        return null;
    }

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
