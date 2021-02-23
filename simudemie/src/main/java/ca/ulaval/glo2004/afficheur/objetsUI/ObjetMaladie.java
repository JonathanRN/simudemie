/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.objetsUI;

import ca.ulaval.glo2004.afficheur.FontRegister;
import ca.ulaval.glo2004.afficheur.onglets.OngletMaladie;


public class ObjetMaladie extends ObjetUI {    
    public ObjetMaladie(OngletMaladie tab) {
        super(tab);
        initComponents();
        
        MaladieNom.setFont(FontRegister.RobotoRegular.deriveFont(21.3062f));
    }
    
    public void setNom(String nom) {
        MaladieNom.setText(nom);
    }
    
    public String getNom() {
        return MaladieNom.getText();
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
        DiagramPanel = new javax.swing.JPanel();
        Bars = new javax.swing.JPanel();
        InfectedProgressBar = new javax.swing.JProgressBar();
        CuredProgressBar = new javax.swing.JProgressBar();
        DeadProgressBar = new javax.swing.JProgressBar();

        setMaximumSize(new java.awt.Dimension(351, 215));
        setMinimumSize(new java.awt.Dimension(351, 215));
        setPreferredSize(new java.awt.Dimension(351, 215));

        MaladieNom.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        MaladieNom.setText("Nom de la maladie");
        MaladieNom.setBorder(javax.swing.BorderFactory.createEmptyBorder(19, 42, 0, 0));

        DiagramPanel.setBackground(new java.awt.Color(59, 66, 82));
        DiagramPanel.setMaximumSize(new java.awt.Dimension(349, 85));
        DiagramPanel.setMinimumSize(new java.awt.Dimension(349, 85));
        DiagramPanel.setLayout(new java.awt.BorderLayout());

        Bars.setBackground(new java.awt.Color(59, 66, 82));
        Bars.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 42, 10, 42));
        Bars.setLayout(new java.awt.GridLayout(3, 0, 0, 10));

        InfectedProgressBar.setForeground(new java.awt.Color(191, 97, 106));
        InfectedProgressBar.setToolTipText("");
        InfectedProgressBar.setValue(25);
        InfectedProgressBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 3, 0));
        InfectedProgressBar.setBorderPainted(false);
        Bars.add(InfectedProgressBar);

        CuredProgressBar.setForeground(new java.awt.Color(163, 190, 140));
        CuredProgressBar.setValue(100);
        CuredProgressBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 3, 0));
        CuredProgressBar.setBorderPainted(false);
        Bars.add(CuredProgressBar);

        DeadProgressBar.setForeground(new java.awt.Color(180, 142, 173));
        DeadProgressBar.setValue(50);
        DeadProgressBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 3, 0));
        DeadProgressBar.setBorderPainted(false);
        Bars.add(DeadProgressBar);

        DiagramPanel.add(Bars, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MaladieNom, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(DiagramPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MaladieNom)
                .addGap(28, 28, 28)
                .addComponent(DiagramPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bars;
    private javax.swing.JProgressBar CuredProgressBar;
    private javax.swing.JProgressBar DeadProgressBar;
    private javax.swing.JPanel DiagramPanel;
    private javax.swing.JProgressBar InfectedProgressBar;
    private javax.swing.JLabel MaladieNom;
    // End of variables declaration//GEN-END:variables
}
