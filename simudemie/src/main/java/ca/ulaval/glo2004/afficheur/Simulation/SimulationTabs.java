/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.Simulation;

import ca.ulaval.glo2004.afficheur.BoutonToggle;
import ca.ulaval.glo2004.afficheur.PanelArrondi;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.domaine.Mesure;
import ca.ulaval.glo2004.domaine.Pays;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JLabel;

/**
 *
 * @author Jonathan
 */
public class SimulationTabs extends PanelArrondi implements AdjustmentListener {
    
    private BoutonToggle toggleCourant;
    private Simulation simulation;
    private int indexPays;
    
    public SimulationTabs() {
        initComponents();
        
        try {
            updateBoutonAjouter(false, AjouterMesure);
            setBackground(new Color(76, 86, 106, 100));
            
            MesuresActives.getViewport().setOpaque(false);
            MesuresActives.getVerticalScrollBar().setUnitIncrement(15);
            MesuresActives.getVerticalScrollBar().addAdjustmentListener(this);
            
            BoutonMesures.init(this, "icons8_wash_your_hands_30px");
            
            onToggleClick(BoutonMesures);
        }
        catch(Exception e) {
        }
    }
    
    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
    
    public int getIndexPays() {
        return indexPays;
    }
    
    public void setIndexPays(int indexPays) {
        this.indexPays = indexPays;
        
        loadMesures();
    }
    
    public void loadMesures() {
        ContenuMesures.removeAll();
        ContenuMesures.getParent().validate();
        ContenuMesures.getRootPane().repaint();

        for (Mesure m : simulation.getScenario().getCarteJourCourant().getPays(indexPays).getMesures()) {
            addMesure(m);
        }
    }
    
    public void onToggleClick(BoutonToggle toggle) {        
        if (toggleCourant != null) {
            toggleCourant.setToggle(false);
        }
        
        toggleCourant = toggle;
        
        if (toggleCourant != null) {
            toggleCourant.setToggle(true);
        }
    }
    
    private void updateBoutonAjouter(boolean actif, JLabel bouton) {
        if (actif) {
            bouton.setFont(FontRegister.RobotoRegular.deriveFont(25f));
        }
        else {
            bouton.setFont(FontRegister.RobotoLight.deriveFont(25f));
        }
    }

    private MesurePanel addMesure(Mesure mesure) {
        MesurePanel panel = new MesurePanel(ContenuMesures, this, ContenuMesures.getComponentCount());
        if(mesure != null) {
            panel.chargerMesure(new Mesure(mesure));
        }
        ContenuMesures.add(panel);
        ContenuMesures.getParent().validate();
        ContenuMesures.getRootPane().repaint();
        return panel;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SidePanelParent = new javax.swing.JPanel();
        SidePanel = new ca.ulaval.glo2004.afficheur.PanelArrondi();
        BoutonMesures = new ca.ulaval.glo2004.afficheur.BoutonToggle();
        MesuresPanel = new javax.swing.JPanel();
        Titre = new javax.swing.JPanel();
        MesuresTitre = new javax.swing.JLabel();
        AjouterMesure = new javax.swing.JLabel();
        MesuresActives = new javax.swing.JScrollPane();
        ContenuMesures = new javax.swing.JPanel();

        setLayout(new javax.swing.OverlayLayout(this));

        SidePanelParent.setOpaque(false);
        SidePanelParent.setLayout(new java.awt.BorderLayout());

        SidePanel.setMaximumSize(new java.awt.Dimension(100, 200));
        SidePanel.setMinimumSize(new java.awt.Dimension(100, 200));
        SidePanel.setPreferredSize(new java.awt.Dimension(50, 200));
        SidePanel.setLayout(new java.awt.GridLayout(6, 1, 0, 10));
        SidePanel.add(BoutonMesures);

        SidePanelParent.add(SidePanel, java.awt.BorderLayout.WEST);

        add(SidePanelParent);

        MesuresPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 0));
        MesuresPanel.setOpaque(false);
        MesuresPanel.setLayout(new java.awt.BorderLayout());

        Titre.setOpaque(false);
        Titre.setLayout(new java.awt.BorderLayout());

        MesuresTitre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        MesuresTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MesuresTitre.setText("Mesures");
        MesuresTitre.setPreferredSize(new java.awt.Dimension(62, 30));
        Titre.add(MesuresTitre, java.awt.BorderLayout.CENTER);

        AjouterMesure.setFont(new java.awt.Font("Dialog", 0, 25)); // NOI18N
        AjouterMesure.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AjouterMesure.setText("+");
        AjouterMesure.setPreferredSize(new java.awt.Dimension(41, 30));
        AjouterMesure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AjouterMesureMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AjouterMesureMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AjouterMesureMouseReleased(evt);
            }
        });
        Titre.add(AjouterMesure, java.awt.BorderLayout.EAST);

        MesuresPanel.add(Titre, java.awt.BorderLayout.PAGE_START);

        MesuresActives.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        MesuresActives.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        MesuresActives.setOpaque(false);

        ContenuMesures.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ContenuMesures.setOpaque(false);
        ContenuMesures.setLayout(new javax.swing.BoxLayout(ContenuMesures, javax.swing.BoxLayout.Y_AXIS));
        MesuresActives.setViewportView(ContenuMesures);

        MesuresPanel.add(MesuresActives, java.awt.BorderLayout.CENTER);

        add(MesuresPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void AjouterMesureMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjouterMesureMouseEntered
        updateBoutonAjouter(true, (JLabel)evt.getSource());
        this.getRootPane().repaint();
    }//GEN-LAST:event_AjouterMesureMouseEntered

    private void AjouterMesureMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjouterMesureMouseExited
        updateBoutonAjouter(false, (JLabel)evt.getSource());
        this.getRootPane().repaint();
    }//GEN-LAST:event_AjouterMesureMouseExited

    private void AjouterMesureMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjouterMesureMouseReleased
        MesurePanel panel = addMesure(null);
        
        // On veut créer la mesure immédiatement, mais seulement quand on appuie +
        panel.sauvegarderMesure();
    }//GEN-LAST:event_AjouterMesureMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AjouterMesure;
    private ca.ulaval.glo2004.afficheur.BoutonToggle BoutonMesures;
    private javax.swing.JPanel ContenuMesures;
    private javax.swing.JScrollPane MesuresActives;
    private javax.swing.JPanel MesuresPanel;
    private javax.swing.JLabel MesuresTitre;
    private ca.ulaval.glo2004.afficheur.PanelArrondi SidePanel;
    private javax.swing.JPanel SidePanelParent;
    private javax.swing.JPanel Titre;
    // End of variables declaration//GEN-END:variables

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        this.getRootPane().repaint();
    }
}
