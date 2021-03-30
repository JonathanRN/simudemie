/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine.controleur;

import ca.ulaval.glo2004.afficheur.Simulation.ScenarioCallback;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Scenario;
import ca.ulaval.glo2004.domaine.helper.FileHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;


public class GestionnaireScenario extends GestionnaireOnglet<Scenario> implements ActionListener {
    protected final String RELATIVE_PATH = "Scenarios\\scenarios.ser";
    private Timer timer;    
    private Scenario courant;
    
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
        scenarioCallback.onAvancerJour();
        courant.avancerJour();
    }
    
    public Scenario getCourant() {
        return courant;
    }
    
    private void creerMesure(String nom, float tauxAdhesion, float tauxReduction)
    {
        
        //new Mesure()...
        //jourCourant.mesures.add(e);
    }
                
    private void editerMesure(String nom, float tauxAdhesion, float tauxReduction)
    {
        //jourCourant.mesures[]...
    }
    
    public void pause() {
        timer.stop();
    }
    
    public void resumer() {
        timer.restart();
    }
    
    public void demarrer(int index, int secondes, ScenarioCallback scenarioCallback) {
        timer = new Timer(secondes * 1000, this);
        timer.start();
        
        this.scenarioCallback = scenarioCallback;
        courant = getElement(index);
        
        // On commence immediatement
        scenarioCallback.onAvancerJour();
        courant.avancerJour();
    }
    
    public List<Carte> retournerResultats()
    {
        //TODO Retourner les résultats finaux
        return null;
    }

    @Override
    public Scenario creer(Object... arguments) {
        String nom = (String) arguments[0];
        
        // TODO
        Scenario scenario = new Scenario(nom, GestionnaireCarte.getInstance().getElement(0), GestionnaireMaladie.getInstance().getElement(0));
        ajouter(scenario);
        return scenario;
    }
}
