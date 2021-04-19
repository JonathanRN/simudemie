/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.Simulation.panels;

import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi;

/**
 *
 * @author henri
 */
public class SimulationAidePanel extends PanelArrondi {

    /**
     * Creates new form SimulationAidePanel
     */
    public SimulationAidePanel() {
        initComponents();
        
        super.setBackground(Couleurs.sideMenuLessTransp);
        
        AideTitleLabel.setFont(FontRegister.RobotoRegular.deriveFont(21f));
        Commandes.setFont(FontRegister.RobotoLight.deriveFont(17f));
        
        PaysRegion.setFont(FontRegister.RobotoThin.deriveFont(15f));
        Liens.setFont(FontRegister.RobotoThin.deriveFont(15f));
        Couleurs1.setFont(FontRegister.RobotoThin.deriveFont(15f));
        Photo.setFont(FontRegister.RobotoThin.deriveFont(15f));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AideTitlePanel = new javax.swing.JPanel();
        AideTitleLabel = new javax.swing.JLabel();
        InformationsPanel = new javax.swing.JPanel();
        Commandes = new javax.swing.JLabel();
        PaysRegion = new javax.swing.JLabel();
        Liens = new javax.swing.JLabel();
        Couleurs1 = new javax.swing.JLabel();
        Photo = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        AideTitlePanel.setOpaque(false);
        AideTitlePanel.setLayout(new java.awt.BorderLayout());

        AideTitleLabel.setFont(new java.awt.Font("Dialog", 0, 21)); // NOI18N
        AideTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AideTitleLabel.setText("Aide");
        AideTitleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AideTitlePanel.add(AideTitleLabel, java.awt.BorderLayout.CENTER);

        add(AideTitlePanel, java.awt.BorderLayout.NORTH);

        InformationsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        InformationsPanel.setOpaque(false);
        InformationsPanel.setLayout(new java.awt.GridLayout(5, 0));

        Commandes.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        Commandes.setText("Raccourcis clavier ");
        InformationsPanel.add(Commandes);

        PaysRegion.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        PaysRegion.setText("Appuyez sur Q pour changer l'information affichée (Pays/Rgion)");
        PaysRegion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        InformationsPanel.add(PaysRegion);

        Liens.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        Liens.setText("Appuyez sur W pour afficher les liens entre les pays");
        Liens.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        InformationsPanel.add(Liens);

        Couleurs1.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        Couleurs1.setText("Appuyez sur E pour changer la représentation des couleurs");
        Couleurs1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        InformationsPanel.add(Couleurs1);

        Photo.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        Photo.setText("Appuyez sur S pour effectuer une capture d'écran ");
        Photo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        InformationsPanel.add(Photo);

        add(InformationsPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AideTitleLabel;
    private javax.swing.JPanel AideTitlePanel;
    private javax.swing.JLabel Commandes;
    private javax.swing.JLabel Couleurs1;
    private javax.swing.JPanel InformationsPanel;
    private javax.swing.JLabel Liens;
    private javax.swing.JLabel PaysRegion;
    private javax.swing.JLabel Photo;
    // End of variables declaration//GEN-END:variables
}
