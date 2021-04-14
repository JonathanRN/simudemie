/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.Simulation;

import ca.ulaval.glo2004.afficheur.BoutonToggle;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Mesure;
import ca.ulaval.glo2004.domaine.VoieLiaison;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Jonathan
 */
public class SimulationTabs extends PanelArrondi implements AdjustmentListener {
    
    private BoutonToggle toggleCourant;
    private Simulation simulation;
    private int indexPays;
    private ArrayList<VoieLiaison.TypeVoie> voies = new ArrayList<>();
    
    public SimulationTabs() {
        initComponents();
        
        try {
            updateBoutonAjouter(false, AjouterMesure);
            setBackground(Couleurs.sideMenuTransp);
            
            BoutonMesures.init(this, "icons8_wash_your_hands_30px");
            MesuresActives.getViewport().setOpaque(false);
            MesuresActives.getVerticalScrollBar().setUnitIncrement(15);
            MesuresActives.getVerticalScrollBar().addAdjustmentListener(this);
            MesuresTitre.setFont(FontRegister.RobotoLight.deriveFont(14f));
            
            BoutonLiens.init(this, "icons8_chain_25px");
            LiensTitre.setFont(FontRegister.RobotoLight.deriveFont(14f));
            SPLiens.getViewport().setOpaque(false);
            
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
        
        loadElements();
    }
    
    public void loadElements() {
        if (toggleCourant.equals(BoutonMesures)) {
            loadMesures();
        }
        else if (toggleCourant.equals(BoutonLiens)) {
            loadLiens();
        }
    }
    
    public void loadMesures() {
        ContenuMesures.removeAll();
        ContenuMesures.getParent().validate();
        ContenuMesures.getRootPane().repaint();

        for (Mesure m : simulation.getScenario().getCarteJourCourant().getPays(indexPays).getMesures()) {
            addMesure(m);
        }
    }
    
    public void loadLiens() {
        voies.clear();
        ContenuLiens.removeAll();
        ContenuLiens.getParent().validate();
        ContenuLiens.getRootPane().repaint();
        
        Carte carte = simulation.getScenario().getCarteJourCourant();
        for (VoieLiaison voie : carte.getVoies()) {
            if (voie.getPaysOrigine().getPolygone().equals(carte.getPays(indexPays).getPolygone()) ||
                voie.getPaysDestination().getPolygone().equals(carte.getPays(indexPays).getPolygone())) {
                
                if (!voies.contains(voie.getType())) {
                    SimulationVoieLiaison sml = new SimulationVoieLiaison();
                    sml.setTypeVoie(voie.getType());
                    ContenuLiens.add(sml);
                    voies.add(voie.getType());
                }
            }
        }
        
        ContenuLiens.getParent().validate();
        ContenuLiens.getRootPane().repaint();
    }
    
    public void onToggleClick(BoutonToggle toggle) {        
        if (toggleCourant != null) {
            toggleCourant.setToggle(false);
        }
        
        toggleCourant = toggle;
        
        if (toggleCourant != null) {
            toggleCourant.setToggle(true);
        }
        
        MesuresPanel.setVisible(toggle.equals(BoutonMesures));
        LiensPanel.setVisible(toggle.equals(BoutonLiens));
        
        loadElements();
        
        repaint();
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
        SidePanel = new ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi();
        BoutonMesures = new ca.ulaval.glo2004.afficheur.BoutonToggle();
        BoutonLiens = new ca.ulaval.glo2004.afficheur.BoutonToggle();
        MesuresPanel = new javax.swing.JPanel();
        Titre = new javax.swing.JPanel();
        MesuresTitre = new javax.swing.JLabel();
        AjouterMesure = new javax.swing.JLabel();
        MesuresActives = new javax.swing.JScrollPane();
        ContenuMesures = new javax.swing.JPanel();
        LiensPanel = new javax.swing.JPanel();
        Titre1 = new javax.swing.JPanel();
        LiensTitre = new javax.swing.JLabel();
        SPLiens = new javax.swing.JScrollPane();
        ContenuLiens = new javax.swing.JPanel();

        setLayout(new javax.swing.OverlayLayout(this));

        SidePanelParent.setOpaque(false);
        SidePanelParent.setLayout(new java.awt.BorderLayout());

        SidePanel.setMaximumSize(new java.awt.Dimension(100, 200));
        SidePanel.setMinimumSize(new java.awt.Dimension(100, 200));
        SidePanel.setPreferredSize(new java.awt.Dimension(50, 200));
        SidePanel.setLayout(new java.awt.GridLayout(6, 1, 0, 10));
        SidePanel.add(BoutonMesures);
        SidePanel.add(BoutonLiens);

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
        AjouterMesure.setPreferredSize(new java.awt.Dimension(40, 30));
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

        LiensPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 0));
        LiensPanel.setOpaque(false);
        LiensPanel.setLayout(new java.awt.BorderLayout());

        Titre1.setOpaque(false);
        Titre1.setLayout(new java.awt.BorderLayout());

        LiensTitre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LiensTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LiensTitre.setText("Fermeture des voies");
        LiensTitre.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 40));
        LiensTitre.setPreferredSize(new java.awt.Dimension(62, 30));
        Titre1.add(LiensTitre, java.awt.BorderLayout.CENTER);

        LiensPanel.add(Titre1, java.awt.BorderLayout.PAGE_START);

        SPLiens.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SPLiens.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        ContenuLiens.setOpaque(false);
        ContenuLiens.setLayout(new java.awt.GridLayout(3, 0));
        SPLiens.setViewportView(ContenuLiens);

        LiensPanel.add(SPLiens, java.awt.BorderLayout.CENTER);

        add(LiensPanel);
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
    private ca.ulaval.glo2004.afficheur.BoutonToggle BoutonLiens;
    private ca.ulaval.glo2004.afficheur.BoutonToggle BoutonMesures;
    private javax.swing.JPanel ContenuLiens;
    private javax.swing.JPanel ContenuMesures;
    private javax.swing.JPanel LiensPanel;
    private javax.swing.JLabel LiensTitre;
    private javax.swing.JScrollPane MesuresActives;
    private javax.swing.JPanel MesuresPanel;
    private javax.swing.JLabel MesuresTitre;
    private javax.swing.JScrollPane SPLiens;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi SidePanel;
    private javax.swing.JPanel SidePanelParent;
    private javax.swing.JPanel Titre;
    private javax.swing.JPanel Titre1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        this.getRootPane().repaint();
    }
}
