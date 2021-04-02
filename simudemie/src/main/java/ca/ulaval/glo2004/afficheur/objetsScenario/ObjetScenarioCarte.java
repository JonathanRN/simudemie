/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.objetsScenario;

import ca.ulaval.glo2004.afficheur.objetsUI.ObjetUI;
import ca.ulaval.glo2004.afficheur.onglets.OngletScenarioCarte;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;

public class ObjetScenarioCarte extends ObjetUI {    
    private int index;
    
    public ObjetScenarioCarte(OngletScenarioCarte tab, int index, String nom, int valeurPays, int valeurPopulation) {
        super(tab);
        initComponents();
        
        setIndex(index);
        setNom(nom);
        setPaysValue(valeurPays);
        setPopulationValue(valeurPopulation);
        
        MapName.setFont(FontRegister.RobotoRegular.deriveFont(21.3062f));
        PaysTotalLabel1.setFont(FontRegister.RobotoLight.deriveFont(12f));
        PaysTotalValue1.setFont(FontRegister.RobotoLight.deriveFont(12f));
        PopulationTotalLabel.setFont(FontRegister.RobotoLight.deriveFont(12f));
        PopulationTotalValue.setFont(FontRegister.RobotoLight.deriveFont(12f));
    }

    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public void setNom(String text) {
        MapName.setText(text);
    }
    
    public void setPaysValue(int value) {
        PaysTotalValue1.setText(String.valueOf(value));
    }
    
    public void setPopulationValue(int value) {
        PopulationTotalValue.setText(String.valueOf(value));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MapName = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        PaysTotalLabel1 = new javax.swing.JLabel();
        PaysTotalValue1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        PopulationTotalLabel = new javax.swing.JLabel();
        PopulationTotalValue = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(19, 0, 19, 0));
        setMaximumSize(new java.awt.Dimension(224, 150));
        setMinimumSize(new java.awt.Dimension(224, 150));
        setPreferredSize(new java.awt.Dimension(224, 150));
        setLayout(new java.awt.BorderLayout(0, 25));

        MapName.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        MapName.setText("Nom de la carte");
        MapName.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 25, 0, 0));
        MapName.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        add(MapName, java.awt.BorderLayout.PAGE_START);

        jPanel1.setEnabled(false);
        jPanel1.setFocusable(false);
        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        PaysTotalLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        PaysTotalLabel1.setText("Nombre de pays");
        PaysTotalLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 25, 0, 0));
        PaysTotalLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        PaysTotalLabel1.setMaximumSize(new java.awt.Dimension(150, 20));
        PaysTotalLabel1.setMinimumSize(new java.awt.Dimension(150, 20));
        PaysTotalLabel1.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel2.add(PaysTotalLabel1);

        PaysTotalValue1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        PaysTotalValue1.setText("0");
        PaysTotalValue1.setToolTipText("");
        PaysTotalValue1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PaysTotalValue1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PaysTotalValue1.setMaximumSize(new java.awt.Dimension(200, 20));
        PaysTotalValue1.setMinimumSize(new java.awt.Dimension(200, 20));
        PaysTotalValue1.setPreferredSize(new java.awt.Dimension(200, 20));
        jPanel2.add(PaysTotalValue1);

        jPanel1.add(jPanel2);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        PopulationTotalLabel.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        PopulationTotalLabel.setText("Population totale");
        PopulationTotalLabel.setToolTipText("");
        PopulationTotalLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 25, 0, 0));
        PopulationTotalLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PopulationTotalLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        PopulationTotalLabel.setMaximumSize(new java.awt.Dimension(150, 20));
        PopulationTotalLabel.setMinimumSize(new java.awt.Dimension(150, 20));
        PopulationTotalLabel.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel3.add(PopulationTotalLabel);

        PopulationTotalValue.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        PopulationTotalValue.setText("0");
        PopulationTotalValue.setToolTipText("");
        PopulationTotalValue.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PopulationTotalValue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PopulationTotalValue.setMaximumSize(new java.awt.Dimension(200, 20));
        PopulationTotalValue.setMinimumSize(new java.awt.Dimension(200, 20));
        PopulationTotalValue.setPreferredSize(new java.awt.Dimension(200, 20));
        jPanel3.add(PopulationTotalValue);

        jPanel1.add(jPanel3);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MapName;
    private javax.swing.JLabel PaysTotalLabel1;
    private javax.swing.JLabel PaysTotalValue1;
    private javax.swing.JLabel PopulationTotalLabel;
    private javax.swing.JLabel PopulationTotalValue;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}