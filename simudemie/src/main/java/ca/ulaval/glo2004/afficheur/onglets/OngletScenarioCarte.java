/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.onglets;

import ca.ulaval.glo2004.afficheur.objetsScenario.ObjetScenarioCarte;
import ca.ulaval.glo2004.afficheur.objetsUI.ObjetUI;

/**
 *
 * @author Jonathan
 */
public class OngletScenarioCarte extends OngletUI {
    
    /**
     * Creates new form ScenarioTab
     */
    public OngletScenarioCarte() {
        //initComponents();
    }

    @Override
    public void onClickObjetUI(ObjetUI objet) {
        super.onClickObjetUI(objet);
    }

    public int getIndexCourant() {
        int index = -1;
        if(getCourant() != null) {
            ObjetScenarioCarte osc = (ObjetScenarioCarte) getCourant();
            index = osc.getIndex();
        }
        return index;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(46, 52, 64));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 35, 35));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
