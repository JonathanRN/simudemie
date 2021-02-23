/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.controleur;


import ca.ulaval.glo2004.domaine.Carte;


public class GestionnaireScenario {

    private Carte carte;
    private boolean scenarioEnCours = false;
    
    public GestionnaireScenario()
    {
        //TODO Constructeur
    }
    
    private void Sauvegarder(String nomCarte)
    {
        //TODO
    }
    
    private void Charger(String nomCarte) //À valider
    {
        //TODO
    }
        
    private void Importer(String filePath)
    {
        //TODO
    }
            
    private void Exporter(String nomCarte, String filePath)
    {
        //TODO
    }
    
    private void CreerMesure(String nom, float tauxAdhesion, float tauxReduction)
    {
        //TODO appel au constructeur de Mesure
    }
                
    private void EditerMesure(String nom, float tauxAdhesion, float tauxReduction)
    {
        //TODO Set les nouveaux parametre des mesures
    }
                    
    private void EditerMaladie(String nom, float tauxInfection, float tauxMortalite, float tauxGuerison)
    {
        //TODO Set les nouveaux parametres de la maladie
    }
                        
    private void Pauser()
    {
        if (scenarioEnCours == true){
            this.scenarioEnCours = false;
        }else{
            this.scenarioEnCours = true;
        }
        
    }
    
    private void RetournerResultats()
    {
        //TODO Retourner les résultats finaux
    }
    
    private void Demarrer()
    {
        this.scenarioEnCours = true;
        //TODO démarrer le compteur de jour
    }
    
    
}
