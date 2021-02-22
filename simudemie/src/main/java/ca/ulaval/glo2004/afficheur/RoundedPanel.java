/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jonathan
 */
public class RoundedPanel extends javax.swing.JPanel {
    
    private Color bgColor, borderColor;
    protected final Color defaultColor = new Color(216, 222, 233, 38);
    /**
     * Creates new form RoundedPanel
     */
    public RoundedPanel() {
        initComponents();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw background
        graphics.setColor(bgColor);
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
        
        // Draw border
        graphics.setColor(borderColor);
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
    }
    
    @Override
    public void setBackground(Color color) {
        bgColor = color;
        
        // Force un repaint sur le JFrame pour redessiner
        try {
            JFrame topFrame = (JFrame)SwingUtilities.getWindowAncestor(this);
            topFrame.repaint();
        } catch (Exception e) {   
        }
    }
    
    protected void setBorderColor(Color color) {
        borderColor = color;
        
        // Force un repaint sur le JFrame pour redessiner
        try {
            JFrame topFrame = (JFrame)SwingUtilities.getWindowAncestor(this);
            topFrame.repaint();
        } catch (Exception e) {   
        }
    }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
