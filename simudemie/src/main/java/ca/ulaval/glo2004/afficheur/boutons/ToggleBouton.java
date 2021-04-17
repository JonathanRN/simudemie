/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.boutons;

import ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi;
import ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarte;
import ca.ulaval.glo2004.afficheur.CreationCarte.mode.Mode;
import ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanelGauche;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author Jonathan
 */
public class ToggleBouton extends PanelArrondi {

    private Mode mode;
    private CreationCarte creation;
    private SimulationPanelGauche simulationTabs;
    private boolean mouseOver;
    private boolean estToggle;
    private boolean actif;
    
    public ToggleBouton() {
        initComponents();
        setActif(true);
    }
    
    public void init(CreationCarte creation, Mode mode, String path) {
        this.creation = creation;
        this.mode = mode;        
        setIcon("/icons/" + path + ".png");
    }
    
    public void init(SimulationPanelGauche tabs, String path) {
        this.simulationTabs = tabs;
        setIcon("/icons/simulation/" + path + ".png");
    }
    
    public void setActif(boolean actif) {
        this.actif = actif;
        this.setVisible(actif);
        setToggle(false);
    }
    
    public void setIcon(String path) {
        BoutonLabel.setIcon(new ImageIcon(getClass().getResource(path)));
    }
    
    public void setToggle(boolean toggle) {
        estToggle = toggle;
        Color couleur = Couleurs.invisible;
        if (toggle || mouseOver) {
            couleur = Couleurs.pannelArrondi;
        }
        
        setBackground(couleur);
    }
    
    public Mode getMode() { return mode; }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BoutonLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(50, 50));
        setMinimumSize(new java.awt.Dimension(50, 50));
        setPreferredSize(new java.awt.Dimension(50, 50));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        BoutonLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BoutonLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_cursor_25px.png"))); // NOI18N
        add(BoutonLabel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        mouseOver = true;
        if (!estToggle && actif) {
            setBackground(Couleurs.pannelArrondi);
        }
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        mouseOver = false;
        if (!estToggle && actif) {
            setBackground(Couleurs.invisible);
        }
    }//GEN-LAST:event_formMouseExited

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if (!actif) {
            return;
        }
        
        if (creation != null) {
            creation.onToggleClick(this);
        }
        
        if (simulationTabs != null) {
            simulationTabs.onToggleClick(this);
        }
    }//GEN-LAST:event_formMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BoutonLabel;
    // End of variables declaration//GEN-END:variables
}
