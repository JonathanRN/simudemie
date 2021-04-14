/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.Simulation.objetsUI;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.domaine.VoieLiaison;
import javax.swing.ImageIcon;

/**
 *
 * @author Jonathan
 */
public class ObjetSimulationVoieLiaison extends javax.swing.JPanel {
    
    private boolean mouseOver, checked = true;
        
    public ObjetSimulationVoieLiaison() {
        initComponents();
        
        VoieLabel.setFont(FontRegister.RobotoLight.deriveFont(14f));
        
        updateIcon();
    }
    
    public void setTypeVoie(VoieLiaison.TypeVoie type) {
        String texte = "Voie " + type;        
        if (type == VoieLiaison.TypeVoie.Aerien) {
            texte = "Voie Aérienne";
        }
        
        VoieLabel.setText(texte);
        VoieIcon.setIcon(new ImageIcon(getClass().getResource("/icons/simulation/lien/" + type + ".png")));        
    }
    
    private void updateIcon() {
        String path = "/icons/simulation/mesure/";
        path += checked ? "checked" : "unchecked";
        path += mouseOver ? "_highlight.png" : ".png";
        Activer.setIcon(new ImageIcon(getClass().getResource(path)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Activer = new javax.swing.JLabel();
        VoieLabel = new javax.swing.JLabel();
        VoieIcon = new javax.swing.JLabel();

        setOpaque(false);
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
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        Activer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Activer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/simulation/mesure/unchecked.png"))); // NOI18N
        Activer.setMaximumSize(new java.awt.Dimension(50, 50));
        Activer.setMinimumSize(new java.awt.Dimension(50, 50));
        Activer.setPreferredSize(new java.awt.Dimension(50, 50));
        add(Activer);

        VoieLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VoieLabel.setText("Voie Terrestre");
        VoieLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 20));
        VoieLabel.setPreferredSize(new java.awt.Dimension(50, 16));
        add(VoieLabel);
        add(VoieIcon);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        mouseOver = true;
        updateIcon();
        this.getRootPane().repaint();
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        mouseOver = false;
        updateIcon();
        this.getRootPane().repaint();
    }//GEN-LAST:event_formMouseExited

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        checked = !checked;
        updateIcon();
        this.getRootPane().repaint();
    }//GEN-LAST:event_formMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Activer;
    private javax.swing.JLabel VoieIcon;
    private javax.swing.JLabel VoieLabel;
    // End of variables declaration//GEN-END:variables
}
