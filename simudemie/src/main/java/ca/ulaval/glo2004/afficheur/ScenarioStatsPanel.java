/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur;

import java.awt.Color;

/**
 *
 * @author Jonathan
 */
public class ScenarioStatsPanel extends javax.swing.JPanel {

    private ScenarioTab tab; 
    
    public ScenarioStatsPanel() {
        initComponents();
        
        try {
            StatsLabel.setFont(FontRegister.RobotoThin.deriveFont(21f));
            Main.setBackground(new Color(216, 222, 233, 38));
            DeleteButton.setBackground(new Color(216, 222, 233, 38));
            DeleteButton.setFont(FontRegister.RobotoLight.deriveFont(15f));
            ResumeButton.setBackground(new Color(216, 222, 233, 38));
            ResumeButton.setFont(FontRegister.RobotoLight.deriveFont(15f));
        }
        catch(Exception e) {
        }
    }
    
    public void setScenarioTab(ScenarioTab tab) {
        this.tab = tab;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Main = new ca.ulaval.glo2004.afficheur.RoundedPanel();
        StatsHeader = new javax.swing.JPanel();
        StatsLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Buttons = new javax.swing.JPanel();
        DeleteButton = new javax.swing.JButton();
        ResumeButton = new javax.swing.JButton();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout(0, 25));

        Main.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 25, 15));
        Main.setLayout(new java.awt.BorderLayout());

        StatsHeader.setOpaque(false);
        StatsHeader.setLayout(new java.awt.BorderLayout());

        StatsLabel.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        StatsLabel.setText("Statistiques");
        StatsHeader.add(StatsLabel, java.awt.BorderLayout.CENTER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_upload_20px.png"))); // NOI18N
        StatsHeader.add(jLabel1, java.awt.BorderLayout.EAST);

        Main.add(StatsHeader, java.awt.BorderLayout.NORTH);

        add(Main, java.awt.BorderLayout.CENTER);

        Buttons.setOpaque(false);
        Buttons.setPreferredSize(new java.awt.Dimension(100, 50));
        Buttons.setLayout(new java.awt.GridLayout(1, 0, 25, 0));

        DeleteButton.setText("Supprimer");
        DeleteButton.setFocusable(false);
        DeleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                DeleteButtonMouseReleased(evt);
            }
        });
        Buttons.add(DeleteButton);

        ResumeButton.setText("Résumer");
        ResumeButton.setFocusable(false);
        ResumeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ResumeButtonMouseReleased(evt);
            }
        });
        Buttons.add(ResumeButton);

        add(Buttons, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void DeleteButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteButtonMouseReleased
        tab.removeSimulationCard();
    }//GEN-LAST:event_DeleteButtonMouseReleased

    private void ResumeButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResumeButtonMouseReleased
        tab.onStartSimulation();
    }//GEN-LAST:event_ResumeButtonMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Buttons;
    private javax.swing.JButton DeleteButton;
    private ca.ulaval.glo2004.afficheur.RoundedPanel Main;
    private javax.swing.JButton ResumeButton;
    private javax.swing.JPanel StatsHeader;
    private javax.swing.JLabel StatsLabel;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
