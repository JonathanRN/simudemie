/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur;

import ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarte;
import ca.ulaval.glo2004.afficheur.CreationCarte.Mode;
import ca.ulaval.glo2004.afficheur.Simulation.SimulationTabs;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author Jonathan
 */
public class BoutonToggle extends PanelArrondi {

    private Mode mode;
    private CreationCarte creation;
    private SimulationTabs simulationTabs;
    private final Color base, highlight;
    private boolean mouseOver;
    private boolean estToggle;
    
    public BoutonToggle() {
        initComponents();
        base = new Color(0, 0, 0, 0);
        highlight = new Color(128, 134, 143);
        setBackground(base);
    }
    
    public void init(CreationCarte creation, Mode mode, String path) {
        this.creation = creation;
        this.mode = mode;
        
        setIcon("/icons/" + path + ".png");
    }
    
    public void init(SimulationTabs tabs, String path) {
        this.simulationTabs = tabs;
        setIcon("/icons/simulation/" + path + ".png");
    }
    
    public void setIcon(String path) {
        jLabel1.setIcon(new ImageIcon(getClass().getResource(path)));
    }
    
    public void setToggle(boolean toggle) {
        estToggle = toggle;
        Color couleur = base;
        if (toggle || mouseOver) {
            couleur = highlight;
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

        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(90, 95, 104));
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

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_cursor_25px.png"))); // NOI18N
        add(jLabel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        mouseOver = true;
        if (!estToggle) {
            setBackground(highlight);
        }
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        mouseOver = false;
        if (!estToggle) {
            setBackground(base);
        }
    }//GEN-LAST:event_formMouseExited

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        setToggle(true);
        if (creation != null) {
            creation.onToggleClick(this);
        }
        
        if (simulationTabs != null) {
            simulationTabs.onToggleClick(this);
        }
    }//GEN-LAST:event_formMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}