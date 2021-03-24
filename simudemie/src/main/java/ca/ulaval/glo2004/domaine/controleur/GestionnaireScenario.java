/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine.controleur;

import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Jour;
import ca.ulaval.glo2004.domaine.Pays;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;


public class GestionnaireScenario implements ActionListener {
    private ArrayList<Carte> cartes = new ArrayList<>();
    private List<Jour> jours = new ArrayList<>();
    private Jour jourCourant;
    private Timer timer;
    
    private static GestionnaireScenario instance;
    
    public static GestionnaireScenario GetInstance() {
        if (instance == null) {
            instance = new GestionnaireScenario();
        }
        return instance;
    }
    
    private GestionnaireScenario()
    {
        // todo change timer
        //timer = new Timer(3 * 1000, this);
        //demarrer();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        avancerJour();
    }
    
    private void chargerJour(Jour jour)
    {
        // Charger tous les elements selon la journee
        jourCourant = jour;
    }
    
    private void avancerJour()
    {
        jourCourant.carte.avancerJour();
        jours.add(jourCourant);
        chargerJour(new Jour(jourCourant));
    }

    private void importer(String filePath)
    {
        // Importer selon le path et creer une nouvelle liste de journees...? (todo UI)
    }
            
    private void exporter(String nomCarte, String filePath)
    {
        // Exporter dans un fichier qui contient toutes les journees...
        // RetournerResultats
    }
    
    public void ajouterVoie(Pays origine, Pays destination)
    {
        // new Voie...
        // jourCourant.carte.trouverPays(origine).AjouterVoie(nouvelleVoie);
        // jourCourant.carte.trouverPays(destination).AjouterVoie(nouvelleVoie);
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
                    
    private void editerMaladie(String nom, float tauxInfection, float tauxMortalite, float tauxGuerison)
    {
        //jourCourant.maladie
    }
                        
    public void pause()
    {
        timer.stop();
    }
    
    public void demarrer()
    {
        timer.restart();
    }
    
    public List<Jour> retournerResultats()
    {
        //TODO Retourner les résultats finaux
        return null;
    }
}
