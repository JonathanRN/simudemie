/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.boutons;

import ca.ulaval.glo2004.afficheur.CreationCarte;
import ca.ulaval.glo2004.afficheur.PanelArrondi;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author Jonathan
 */
public class CreationCarteToggle extends PanelArrondi {

    private CreationCarte.Mode mode;
    private CreationCarte creation;
    private Color base, highlight;
    private boolean mouseOver;
    private boolean estToggle;
    
    public CreationCarteToggle() {
        initComponents();
        base = new Color(0, 0, 0, 0);
        highlight = new Color(128, 134, 143);
        setBackground(base);
    }
    
    public void init(CreationCarte creation, CreationCarte.Mode mode, String path) {
        this.creation = creation;
        this.mode = mode;
        
        setIcon("/icons/" + path + ".png");
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
    
    public CreationCarte.Mode getMode() { return mode; }

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
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

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        setToggle(true);
        if (creation != null) {
            creation.onToggleClick(this);
        }
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
