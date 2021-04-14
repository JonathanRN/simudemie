/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.MenuPrincipal.objetsUI;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.onglets.OngletCreationScenarioMaladie;


public class ObjetScenarioMaladie extends ObjetUI {    
    private int index;
    
    public ObjetScenarioMaladie(OngletCreationScenarioMaladie tab, int index, String nom, double tauxInfection, double tauxGuerison, double tauxMortalite) {
        super(tab);
        initComponents();
        
        NomMaladie.setFont(FontRegister.RobotoRegular.deriveFont(21.3062f));
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
        NomMaladie.setText(nom);
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

        NomMaladie = new javax.swing.JLabel();
        InfoObjetPanel = new javax.swing.JPanel();
        InfectionsPanel = new javax.swing.JPanel();
        TauxInfectionLabel = new javax.swing.JLabel();
        TauxInfectionValue = new javax.swing.JLabel();
        GuerisonsPanel = new javax.swing.JPanel();
        TauxGuerisonLabel = new javax.swing.JLabel();
        TauxGuerisonValue = new javax.swing.JLabel();
        MortalitePanel = new javax.swing.JPanel();
        TauxMortaliteLabel = new javax.swing.JLabel();
        TauxMortaliteValue = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(19, 0, 19, 0));
        setMaximumSize(new java.awt.Dimension(224, 150));
        setMinimumSize(new java.awt.Dimension(224, 150));
        setPreferredSize(new java.awt.Dimension(224, 150));
        setLayout(new java.awt.BorderLayout(0, 25));

        NomMaladie.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        NomMaladie.setText("Nom de la maladie");
        NomMaladie.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 25, 0, 0));
        NomMaladie.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(NomMaladie, java.awt.BorderLayout.PAGE_START);

        InfoObjetPanel.setEnabled(false);
        InfoObjetPanel.setFocusable(false);
        InfoObjetPanel.setLayout(new java.awt.GridLayout(3, 0));

        InfectionsPanel.setLayout(new javax.swing.BoxLayout(InfectionsPanel, javax.swing.BoxLayout.LINE_AXIS));

        TauxInfectionLabel.setText("Taux d'infection");
        TauxInfectionLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 25, 0, 0));
        TauxInfectionLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        TauxInfectionLabel.setMaximumSize(new java.awt.Dimension(150, 20));
        TauxInfectionLabel.setMinimumSize(new java.awt.Dimension(150, 20));
        TauxInfectionLabel.setPreferredSize(new java.awt.Dimension(150, 20));
        InfectionsPanel.add(TauxInfectionLabel);

        TauxInfectionValue.setText("0");
        TauxInfectionValue.setToolTipText("");
        TauxInfectionValue.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TauxInfectionValue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        TauxInfectionValue.setMaximumSize(new java.awt.Dimension(200, 20));
        TauxInfectionValue.setMinimumSize(new java.awt.Dimension(200, 20));
        TauxInfectionValue.setPreferredSize(new java.awt.Dimension(200, 20));
        InfectionsPanel.add(TauxInfectionValue);

        InfoObjetPanel.add(InfectionsPanel);

        GuerisonsPanel.setLayout(new javax.swing.BoxLayout(GuerisonsPanel, javax.swing.BoxLayout.LINE_AXIS));

        TauxGuerisonLabel.setText("Taux de guérison");
        TauxGuerisonLabel.setToolTipText("");
        TauxGuerisonLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 25, 0, 0));
        TauxGuerisonLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TauxGuerisonLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        TauxGuerisonLabel.setMaximumSize(new java.awt.Dimension(150, 20));
        TauxGuerisonLabel.setMinimumSize(new java.awt.Dimension(150, 20));
        TauxGuerisonLabel.setPreferredSize(new java.awt.Dimension(150, 20));
        GuerisonsPanel.add(TauxGuerisonLabel);

        TauxGuerisonValue.setText("0");
        TauxGuerisonValue.setToolTipText("");
        TauxGuerisonValue.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TauxGuerisonValue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        TauxGuerisonValue.setMaximumSize(new java.awt.Dimension(200, 20));
        TauxGuerisonValue.setMinimumSize(new java.awt.Dimension(200, 20));
        TauxGuerisonValue.setPreferredSize(new java.awt.Dimension(200, 20));
        GuerisonsPanel.add(TauxGuerisonValue);

        InfoObjetPanel.add(GuerisonsPanel);

        MortalitePanel.setLayout(new javax.swing.BoxLayout(MortalitePanel, javax.swing.BoxLayout.LINE_AXIS));

        TauxMortaliteLabel.setText("Taux de mortalité");
        TauxMortaliteLabel.setToolTipText("");
        TauxMortaliteLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 25, 0, 0));
        TauxMortaliteLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TauxMortaliteLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        TauxMortaliteLabel.setMaximumSize(new java.awt.Dimension(150, 20));
        TauxMortaliteLabel.setMinimumSize(new java.awt.Dimension(150, 20));
        TauxMortaliteLabel.setPreferredSize(new java.awt.Dimension(150, 20));
        MortalitePanel.add(TauxMortaliteLabel);

        TauxMortaliteValue.setText("0");
        TauxMortaliteValue.setToolTipText("");
        TauxMortaliteValue.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TauxMortaliteValue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        TauxMortaliteValue.setMaximumSize(new java.awt.Dimension(200, 20));
        TauxMortaliteValue.setMinimumSize(new java.awt.Dimension(200, 20));
        TauxMortaliteValue.setPreferredSize(new java.awt.Dimension(200, 20));
        MortalitePanel.add(TauxMortaliteValue);

        InfoObjetPanel.add(MortalitePanel);

        add(InfoObjetPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GuerisonsPanel;
    private javax.swing.JPanel InfectionsPanel;
    private javax.swing.JPanel InfoObjetPanel;
    private javax.swing.JPanel MortalitePanel;
    private javax.swing.JLabel NomMaladie;
    private javax.swing.JLabel TauxGuerisonLabel;
    private javax.swing.JLabel TauxGuerisonValue;
    private javax.swing.JLabel TauxInfectionLabel;
    private javax.swing.JLabel TauxInfectionValue;
    private javax.swing.JLabel TauxMortaliteLabel;
    private javax.swing.JLabel TauxMortaliteValue;
    // End of variables declaration//GEN-END:variables
}
