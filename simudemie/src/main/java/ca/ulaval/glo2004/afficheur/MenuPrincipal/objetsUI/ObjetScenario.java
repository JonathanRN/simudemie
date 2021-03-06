/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.MenuPrincipal.objetsUI;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.onglets.OngletScenario;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;

/**
 * Adapte depuis https://stackoverflow.com/questions/15025092/border-with-rounded-corners-transparency
 * @author Jonathan
 */
public class ObjetScenario extends ObjetUI {
    public ObjetScenario(OngletScenario tab) {
        super(tab);
        initComponents();
        
        NomSimulation.setFont(FontRegister.RobotoRegular.deriveFont(21.3062f));
        Jour.setFont(FontRegister.RobotoLight.deriveFont(15.98f));
        NomCarte.setFont(FontRegister.RobotoLight.deriveFont(12f));
        NomVirus.setFont(FontRegister.RobotoLight.deriveFont(12f));
    }
    
    public String getSimulationName() {
        return NomSimulation.getText();
    }
    
    public void setSimulationName(String name) {
        NomSimulation.setText(name);
    }
    
    public void setDays(int jour) {
        Jour.setText("Jour " + jour);
    }
    
    public void setMapName(String name) {
        NomCarte.setText(name);
    }
    
    public String getMapName() {
        return NomCarte.getText();
    }
    
    public void setVirusName(String name) {
        NomVirus.setText(name);
    }
    
    public void setInfectedPercent(float percent) {
        InfectionsBar.setValue((int)percent);
    }
    
    public void setCuredPercent(float percent) {
        SainsBar.setValue((int)percent);
    }
    
    public void setImmunedPercent(float percent) {
        ImmuniseBar.setValue((int)percent);
    }
    
    public void setDeadPercent(float percent) {
        MortsBar.setValue((int)percent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NomSimulation = new javax.swing.JLabel();
        Jour = new javax.swing.JLabel();
        DiagramPanel = new javax.swing.JPanel();
        Bars = new javax.swing.JPanel();
        InfectionsBar = new javax.swing.JProgressBar();
        SainsBar = new javax.swing.JProgressBar();
        MortsBar = new javax.swing.JProgressBar();
        ImmuniseBar = new javax.swing.JProgressBar();
        NomCarte = new javax.swing.JLabel();
        NomVirus = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(351, 215));
        setMinimumSize(new java.awt.Dimension(351, 215));
        setPreferredSize(new java.awt.Dimension(351, 215));

        NomSimulation.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        NomSimulation.setText("Nom de la simulation");

        Jour.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        Jour.setText("Non commenc??e");
        Jour.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Jour.setBorder(javax.swing.BorderFactory.createEmptyBorder(-1, -1, -1, -1));
        Jour.setIconTextGap(0);

        DiagramPanel.setBackground(new java.awt.Color(59, 66, 82));
        DiagramPanel.setMaximumSize(new java.awt.Dimension(349, 85));
        DiagramPanel.setMinimumSize(new java.awt.Dimension(349, 85));
        DiagramPanel.setPreferredSize(new java.awt.Dimension(349, 85));
        DiagramPanel.setLayout(new java.awt.BorderLayout());

        Bars.setBackground(new java.awt.Color(59, 66, 82));
        Bars.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 42, 4, 42));
        Bars.setLayout(new java.awt.GridLayout(4, 1));

        InfectionsBar.setForeground(Couleurs.infections);
        InfectionsBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 3, 0));
        InfectionsBar.setBorderPainted(false);
        InfectionsBar.setString("Infect??s");
        Bars.add(InfectionsBar);

        SainsBar.setForeground(Couleurs.sains);
        SainsBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 3, 0));
        SainsBar.setBorderPainted(false);
        SainsBar.setString("Gu??ris");
        Bars.add(SainsBar);

        MortsBar.setForeground(Couleurs.morts);
        MortsBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 3, 0));
        MortsBar.setBorderPainted(false);
        MortsBar.setString("Morts");
        Bars.add(MortsBar);

        ImmuniseBar.setForeground(Couleurs.immunisations);
        ImmuniseBar.setToolTipText("");
        ImmuniseBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 3, 0));
        ImmuniseBar.setBorderPainted(false);
        ImmuniseBar.setString("Immunise");
        Bars.add(ImmuniseBar);

        DiagramPanel.add(Bars, java.awt.BorderLayout.CENTER);

        NomCarte.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        NomCarte.setText("Nom de la carte");

        NomVirus.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        NomVirus.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        NomVirus.setText("Nom du virus");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NomSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Jour, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(NomCarte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NomVirus)
                .addGap(41, 41, 41))
            .addComponent(DiagramPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(NomSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(Jour)
                .addGap(14, 14, 14)
                .addComponent(DiagramPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NomCarte)
                    .addComponent(NomVirus))
                .addGap(50, 50, 50))
        );
    }// </editor-fold>//GEN-END:initComponents
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bars;
    private javax.swing.JPanel DiagramPanel;
    private javax.swing.JProgressBar ImmuniseBar;
    private javax.swing.JProgressBar InfectionsBar;
    private javax.swing.JLabel Jour;
    private javax.swing.JProgressBar MortsBar;
    private javax.swing.JLabel NomCarte;
    private javax.swing.JLabel NomSimulation;
    private javax.swing.JLabel NomVirus;
    private javax.swing.JProgressBar SainsBar;
    // End of variables declaration//GEN-END:variables
  
}
