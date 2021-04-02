/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.objetsScenario;

import ca.ulaval.glo2004.afficheur.objetsUI.*;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.onglets.OngletScenarioMaladie;


public class ObjetScenarioMaladie extends ObjetUI {    
    private int index;
    
    public ObjetScenarioMaladie(OngletScenarioMaladie tab, int index, String nom, double tauxInfection, double tauxGuerison, double tauxMortalite) {
        super(tab);
        initComponents();
        
        MaladieNom.setFont(FontRegister.RobotoRegular.deriveFont(21.3062f));
        TauxInfectionLabel.setFont(FontRegister.RobotoLight.deriveFont(12f));
        TauxInfectionValue.setFont(FontRegister.RobotoLight.deriveFont(12f));
        TauxGuerisonLabel.setFont(FontRegister.RobotoLight.deriveFont(12f));
        TauxGuerisonValue.setFont(FontRegister.RobotoLight.deriveFont(12f));
        TauxMortaliteLabel.setFont(FontRegister.RobotoLight.deriveFont(12f));
        TauxMortaliteValue.setFont(FontRegister.RobotoLight.deriveFont(12f));
        
        setIndex(index);
        setNom(nom);
        setTauxInfection(tauxInfection);
        setTauxGuerison(tauxGuerison);
        setTauxMortalite(tauxMortalite);
        
    }
    
    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public void setNom(String nom) {
        MaladieNom.setText(nom);
    }
    
    public void setTauxInfection(double value) {
        TauxInfectionValue.setText(Double.toString(value));
    }
    
    public void setTauxGuerison(double value) {
        TauxGuerisonValue.setText(Double.toString(value));
    }
    
    public void setTauxMortalite(double value) {
        TauxMortaliteValue.setText(Double.toString(value));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MaladieNom = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        TauxInfectionLabel = new javax.swing.JLabel();
        TauxInfectionValue = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        TauxGuerisonLabel = new javax.swing.JLabel();
        TauxGuerisonValue = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        TauxMortaliteLabel = new javax.swing.JLabel();
        TauxMortaliteValue = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(19, 0, 19, 0));
        setMaximumSize(new java.awt.Dimension(224, 150));
        setMinimumSize(new java.awt.Dimension(224, 150));
        setPreferredSize(new java.awt.Dimension(224, 150));
        setLayout(new java.awt.BorderLayout(0, 25));

        MaladieNom.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        MaladieNom.setText("Nom de la maladie");
        MaladieNom.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 25, 0, 0));
        MaladieNom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(MaladieNom, java.awt.BorderLayout.PAGE_START);

        jPanel1.setEnabled(false);
        jPanel1.setFocusable(false);
        jPanel1.setLayout(new java.awt.GridLayout(3, 0));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        TauxInfectionLabel.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        TauxInfectionLabel.setText("Taux d'infection");
        TauxInfectionLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 25, 0, 0));
        TauxInfectionLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        TauxInfectionLabel.setMaximumSize(new java.awt.Dimension(150, 20));
        TauxInfectionLabel.setMinimumSize(new java.awt.Dimension(150, 20));
        TauxInfectionLabel.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel2.add(TauxInfectionLabel);

        TauxInfectionValue.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        TauxInfectionValue.setText("0");
        TauxInfectionValue.setToolTipText("");
        TauxInfectionValue.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TauxInfectionValue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        TauxInfectionValue.setMaximumSize(new java.awt.Dimension(200, 20));
        TauxInfectionValue.setMinimumSize(new java.awt.Dimension(200, 20));
        TauxInfectionValue.setPreferredSize(new java.awt.Dimension(200, 20));
        jPanel2.add(TauxInfectionValue);

        jPanel1.add(jPanel2);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        TauxGuerisonLabel.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        TauxGuerisonLabel.setText("Taux de guérison");
        TauxGuerisonLabel.setToolTipText("");
        TauxGuerisonLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 25, 0, 0));
        TauxGuerisonLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TauxGuerisonLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        TauxGuerisonLabel.setMaximumSize(new java.awt.Dimension(150, 20));
        TauxGuerisonLabel.setMinimumSize(new java.awt.Dimension(150, 20));
        TauxGuerisonLabel.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel3.add(TauxGuerisonLabel);

        TauxGuerisonValue.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        TauxGuerisonValue.setText("0");
        TauxGuerisonValue.setToolTipText("");
        TauxGuerisonValue.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TauxGuerisonValue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        TauxGuerisonValue.setMaximumSize(new java.awt.Dimension(200, 20));
        TauxGuerisonValue.setMinimumSize(new java.awt.Dimension(200, 20));
        TauxGuerisonValue.setPreferredSize(new java.awt.Dimension(200, 20));
        jPanel3.add(TauxGuerisonValue);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        TauxMortaliteLabel.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        TauxMortaliteLabel.setText("Taux de mortalité");
        TauxMortaliteLabel.setToolTipText("");
        TauxMortaliteLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 25, 0, 0));
        TauxMortaliteLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TauxMortaliteLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        TauxMortaliteLabel.setMaximumSize(new java.awt.Dimension(150, 20));
        TauxMortaliteLabel.setMinimumSize(new java.awt.Dimension(150, 20));
        TauxMortaliteLabel.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel4.add(TauxMortaliteLabel);

        TauxMortaliteValue.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        TauxMortaliteValue.setText("0");
        TauxMortaliteValue.setToolTipText("");
        TauxMortaliteValue.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TauxMortaliteValue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        TauxMortaliteValue.setMaximumSize(new java.awt.Dimension(200, 20));
        TauxMortaliteValue.setMinimumSize(new java.awt.Dimension(200, 20));
        TauxMortaliteValue.setPreferredSize(new java.awt.Dimension(200, 20));
        jPanel4.add(TauxMortaliteValue);

        jPanel1.add(jPanel4);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MaladieNom;
    private javax.swing.JLabel TauxGuerisonLabel;
    private javax.swing.JLabel TauxGuerisonValue;
    private javax.swing.JLabel TauxInfectionLabel;
    private javax.swing.JLabel TauxInfectionValue;
    private javax.swing.JLabel TauxMortaliteLabel;
    private javax.swing.JLabel TauxMortaliteValue;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
