/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.Simulation;

import ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanelGauche;
import ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanel;
import ca.ulaval.glo2004.afficheur.FramePrincipal;
import ca.ulaval.glo2004.afficheur.utilsUI.ZoomInfoPanel;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.onglets.OngletScenario;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.domaine.Scenario;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireScenario;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jonathan
 */
public class Simulation extends javax.swing.JPanel implements ScenarioCallback {
    
    private boolean estEnDirect;
    private boolean mouseOverDirect, mouseOverFF;
    private int vitesse = 1;
    private final GestionnaireScenario gestionnaire;
    private final int index;
    private boolean estCommence;
    private OngletScenario onglet;
    
    public Simulation(int index, OngletScenario onglet) {
        this.onglet = onglet;
        this.index = index;
        this.gestionnaire = GestionnaireScenario.getInstance();
        gestionnaire.setIndexCourant(index);
        gestionnaire.setCallback(this);
        
        initComponents();
        SimulationMenuGauche.setSimulation(this);
        toggleSimulationTabs(false);
        
        initBoutonsGeneraux();
        
        // Cacher par default, tant que la simulation n'est pas commencee
        SliderJour.setVisible(false);
        
        //NomPays.setFont(FontRegister.RobotoLight.deriveFont(18f));
        FFLabel.setFont(FontRegister.RobotoLight.deriveFont(14f));
        CommenceLabel.setFont(FontRegister.RobotoLight.deriveFont(15f));
        CommenceLabel2.setFont(FontRegister.RobotoLight.deriveFont(15f));
        
        SimulationPanel.setSimulation(this);
        
        boolean commence = getScenario().estCommence();
        SliderJour.setVisible(commence);
        CommencePanel.setVisible(!commence);
        
        if (commence) {
            SliderJour.setMaximum(getScenario().getTotalJours());
            SliderJour.setValue(getScenario().getIndexJourCourant());
        }
        
        setVitesse(1);
    }
    
    private void initBoutonsGeneraux() {
        //TogglePaysRegion.setIcon("/icons/simulation/icons8_home_25px_1.png");
        ToggleLiens.setIcon("/icons/icons8_chain_25px.png");
        ToggleCouleurs.setIcon("/icons/simulation/icons8_radar_plot_25px_inf.png");
        BoutonPhoto.setIcon("/icons/simulation/icons8_unsplash_25px.png");
        MaladieBouton.setIcon("/icons/simulation/icons8_help_25px.png");
        HomeBouton.setIcon("/icons/icons8_home_25px_1.png");
    }
    
    public void toggleSimulationTabs(boolean actif) {
        SimulationMenuGauche.setVisible(actif);
        //NomPays.setVisible(actif);
    }
    
    public void setNomPays(String nom) {
        //NomPays.setText("Pays : " + nom);
    }
    
    public SimulationPanelGauche getSimulationTabs() {
        return SimulationMenuGauche;
    }
    
    public ZoomInfoPanel getZoomPopup() {
        return ZoomInfo;
    }
    
    public SimulationPanel getPanel() {
        return SimulationPanel;
    }
    
    public Scenario getScenario() {
        return gestionnaire.getElement(index);
    }
    
    public void setDirect(boolean direct) {
        estEnDirect = direct;
        
        if (getScenario().estCommence()) {
            if (direct) {
                gestionnaire.resumer();
            }
            else {
                gestionnaire.pause();
            }
        }
        
        updateDirectIcon();
    }
    
    public boolean getDirect() { return estEnDirect; }
    
    public void HomeButtonReleased(MouseEvent evt) {
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("Retourner au menu principal?");
        optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        optionPane.setOptionType(JOptionPane.YES_NO_OPTION);
        
        int result = JOptionPane.showOptionDialog(
            SwingUtilities.windowForComponent(this),
            optionPane.getMessage(),
            "Retour au menu?",
            optionPane.getOptionType(),
            optionPane.getMessageType(),
            optionPane.getIcon(),
            optionPane.getOptions(),
            optionPane.getInitialValue());
        
        if (result == JOptionPane.YES_OPTION) {
            setDirect(false);
            
            GestionnaireScenario.getInstance().sauvegarder();
            
            FramePrincipal frame = (FramePrincipal)SwingUtilities.windowForComponent(this);
            frame.returnToHome();
            onglet.onRevenirSurOnglet();
        }
    }
    
    private void updateDirectIcon() {
        String path = "/icons/";
        path += estEnDirect ? "pause_button_35px" : "direct_button_35px" ;
        path += mouseOverDirect ? "_highlight.png" : ".png";
        PlayPauseIcon.setIcon(new ImageIcon(getClass().getResource(path)));
    }
    
    private void updateFastForwardIcon() {
        String path = "/icons/FastForward/";
        path += "speed_" + vitesse;
        path += mouseOverFF ? "_highlight.png" : ".png";
        
        FastForward.setIcon(new ImageIcon(getClass().getResource(path)));
    }
    
    private void setVitesse(int vit) {
        vitesse = vit > 4 ? 1 : vit;
        
        FFLabel.setText("x" + vitesse);
        
        updateFastForwardIcon();
        
        GestionnaireScenario.getInstance().setVitesse(vitesse);
    }
    
    @Override
    public void onAvancerJour(int jour) {
        SliderJour.setMaximum(jour);
        SliderJour.setValue(SliderJour.getMaximum());
        
        repaint();
    }
    
    @Override
    public void onChargerJour(int jour) {
        // On recharge le UI dans le cas que ca change
        if (SimulationMenuGauche.isVisible()) {
            SimulationMenuGauche.loadMesures();
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

        ZoomInfoParent = new javax.swing.JPanel();
        ZoomInfo = new ca.ulaval.glo2004.afficheur.utilsUI.ZoomInfoPanel();
        CommenceParent = new javax.swing.JPanel();
        CommencePanel = new ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi();
        CommenceLabel = new javax.swing.JLabel();
        CommenceIcon = new javax.swing.JLabel();
        CommenceLabel2 = new javax.swing.JLabel();
        SliderParent = new javax.swing.JPanel();
        Slider = new javax.swing.JPanel();
        SliderJour = new javax.swing.JSlider();
        BoutonsPanel = new javax.swing.JPanel();
        PlayPauseIcon = new javax.swing.JLabel();
        FF = new javax.swing.JPanel();
        FastForward = new javax.swing.JLabel();
        FFLabel = new javax.swing.JLabel();
        MenuGaucheParent = new javax.swing.JPanel();
        SimulationMenuGauche = new ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanelGauche();
        MenuDroitParent = new javax.swing.JPanel();
        SimulationMenuDroit = new ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi();
        MaladieBouton = new ca.ulaval.glo2004.afficheur.boutons.SimulationBouton();
        TogglePaysRegion = new ca.ulaval.glo2004.afficheur.boutons.SimulationBouton();
        ToggleLiens = new ca.ulaval.glo2004.afficheur.boutons.SimulationBouton();
        ToggleCouleurs = new ca.ulaval.glo2004.afficheur.boutons.SimulationBouton();
        BoutonPhoto = new ca.ulaval.glo2004.afficheur.boutons.SimulationBouton();
        HomeBouton = new ca.ulaval.glo2004.afficheur.boutons.SimulationBouton();
        SimulationPanel = new ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanel();

        setBackground(new java.awt.Color(46, 52, 64));
        setLayout(new javax.swing.OverlayLayout(this));

        ZoomInfoParent.setOpaque(false);

        javax.swing.GroupLayout ZoomInfoParentLayout = new javax.swing.GroupLayout(ZoomInfoParent);
        ZoomInfoParent.setLayout(ZoomInfoParentLayout);
        ZoomInfoParentLayout.setHorizontalGroup(
            ZoomInfoParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ZoomInfoParentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ZoomInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1080, Short.MAX_VALUE))
        );
        ZoomInfoParentLayout.setVerticalGroup(
            ZoomInfoParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ZoomInfoParentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ZoomInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(744, Short.MAX_VALUE))
        );

        add(ZoomInfoParent);

        CommenceParent.setOpaque(false);

        CommencePanel.setMaximumSize(new java.awt.Dimension(715, 50));
        CommencePanel.setMinimumSize(new java.awt.Dimension(715, 50));
        CommencePanel.setPreferredSize(new java.awt.Dimension(715, 50));
        CommencePanel.setLayout(new javax.swing.BoxLayout(CommencePanel, javax.swing.BoxLayout.X_AXIS));

        CommenceLabel.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        CommenceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CommenceLabel.setText("Appuyez sur une région pour choisir la région initialement infectée, puis appuyez sur");
        CommenceLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        CommencePanel.add(CommenceLabel);

        CommenceIcon.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        CommenceIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/direct_button_35px.png"))); // NOI18N
        CommencePanel.add(CommenceIcon);

        CommenceLabel2.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        CommenceLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CommenceLabel2.setText("pour démarrer la simulation.");
        CommenceLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));
        CommencePanel.add(CommenceLabel2);
        CommenceLabel2.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout CommenceParentLayout = new javax.swing.GroupLayout(CommenceParent);
        CommenceParent.setLayout(CommenceParentLayout);
        CommenceParentLayout.setHorizontalGroup(
            CommenceParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CommenceParentLayout.createSequentialGroup()
                .addContainerGap(138, Short.MAX_VALUE)
                .addComponent(CommencePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 803, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );
        CommenceParentLayout.setVerticalGroup(
            CommenceParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CommenceParentLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(CommencePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(664, Short.MAX_VALUE))
        );

        add(CommenceParent);

        SliderParent.setOpaque(false);
        SliderParent.setLayout(new java.awt.BorderLayout());

        Slider.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 0, 20));
        Slider.setOpaque(false);
        Slider.setLayout(new java.awt.BorderLayout());

        SliderJour.setMaximum(0);
        SliderJour.setSnapToTicks(true);
        SliderJour.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 10));
        SliderJour.setFocusable(false);
        SliderJour.setPreferredSize(new java.awt.Dimension(200, 70));
        SliderJour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SliderJourMouseReleased(evt);
            }
        });
        Slider.add(SliderJour, java.awt.BorderLayout.CENTER);

        BoutonsPanel.setToolTipText("");
        BoutonsPanel.setMinimumSize(new java.awt.Dimension(35, 70));
        BoutonsPanel.setOpaque(false);
        BoutonsPanel.setPreferredSize(new java.awt.Dimension(80, 70));
        BoutonsPanel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        PlayPauseIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlayPauseIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/direct_button_35px.png"))); // NOI18N
        PlayPauseIcon.setToolTipText("Retour en direct");
        PlayPauseIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PlayPauseIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PlayPauseIconMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                PlayPauseIconMouseReleased(evt);
            }
        });
        BoutonsPanel.add(PlayPauseIcon);

        FF.setOpaque(false);
        FF.setPreferredSize(new java.awt.Dimension(50, 70));
        FF.setLayout(new java.awt.BorderLayout());

        FastForward.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FastForward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/FastForward/speed_1.png"))); // NOI18N
        FastForward.setToolTipText("Changer la vitesse");
        FastForward.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        FastForward.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                FastForwardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                FastForwardMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                FastForwardMouseReleased(evt);
            }
        });
        FF.add(FastForward, java.awt.BorderLayout.CENTER);

        FFLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        FFLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FFLabel.setText("x1");
        FFLabel.setPreferredSize(new java.awt.Dimension(14, 20));
        FF.add(FFLabel, java.awt.BorderLayout.PAGE_START);

        BoutonsPanel.add(FF);

        Slider.add(BoutonsPanel, java.awt.BorderLayout.EAST);

        SliderParent.add(Slider, java.awt.BorderLayout.SOUTH);

        add(SliderParent);

        MenuGaucheParent.setBackground(new java.awt.Color(46, 52, 64));
        MenuGaucheParent.setOpaque(false);

        javax.swing.GroupLayout MenuGaucheParentLayout = new javax.swing.GroupLayout(MenuGaucheParent);
        MenuGaucheParent.setLayout(MenuGaucheParentLayout);
        MenuGaucheParentLayout.setHorizontalGroup(
            MenuGaucheParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuGaucheParentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SimulationMenuGauche, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(730, Short.MAX_VALUE))
        );
        MenuGaucheParentLayout.setVerticalGroup(
            MenuGaucheParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuGaucheParentLayout.createSequentialGroup()
                .addContainerGap(196, Short.MAX_VALUE)
                .addComponent(SimulationMenuGauche, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
        );

        add(MenuGaucheParent);

        MenuDroitParent.setOpaque(false);

        SimulationMenuDroit.setMaximumSize(new java.awt.Dimension(100, 200));
        SimulationMenuDroit.setMinimumSize(new java.awt.Dimension(100, 200));
        SimulationMenuDroit.setPreferredSize(new java.awt.Dimension(50, 200));
        SimulationMenuDroit.setLayout(new java.awt.GridLayout(6, 1));

        MaladieBouton.setToolTipText("Modifier la maladie");
        SimulationMenuDroit.add(MaladieBouton);

        TogglePaysRegion.setToolTipText("Afficher infomations pays/régions (Q)");
        SimulationMenuDroit.add(TogglePaysRegion);

        ToggleLiens.setToolTipText("Afficher les liens (W)");
        SimulationMenuDroit.add(ToggleLiens);

        ToggleCouleurs.setToolTipText("Changer les couleurs visuelles (E)");
        SimulationMenuDroit.add(ToggleCouleurs);

        BoutonPhoto.setToolTipText("Prendre une photo de la carte courante (S)");
        SimulationMenuDroit.add(BoutonPhoto);

        HomeBouton.setToolTipText("Quitter la simulation (ESC)");
        HomeBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                HomeBoutonMouseReleased(evt);
            }
        });
        SimulationMenuDroit.add(HomeBouton);

        javax.swing.GroupLayout MenuDroitParentLayout = new javax.swing.GroupLayout(MenuDroitParent);
        MenuDroitParent.setLayout(MenuDroitParentLayout);
        MenuDroitParentLayout.setHorizontalGroup(
            MenuDroitParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuDroitParentLayout.createSequentialGroup()
                .addContainerGap(1024, Short.MAX_VALUE)
                .addComponent(SimulationMenuDroit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        MenuDroitParentLayout.setVerticalGroup(
            MenuDroitParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuDroitParentLayout.createSequentialGroup()
                .addContainerGap(196, Short.MAX_VALUE)
                .addComponent(SimulationMenuDroit, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
        );

        add(MenuDroitParent);

        SimulationPanel.setOpaque(false);
        SimulationPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SimulationPanelKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout SimulationPanelLayout = new javax.swing.GroupLayout(SimulationPanel);
        SimulationPanel.setLayout(SimulationPanelLayout);
        SimulationPanelLayout.setHorizontalGroup(
            SimulationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, Short.MAX_VALUE, Short.MAX_VALUE)
        );
        SimulationPanelLayout.setVerticalGroup(
            SimulationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 744, Short.MAX_VALUE)
        );

        add(SimulationPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void SliderJourMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SliderJourMouseReleased
        if (estEnDirect) {
            setDirect(false);
        }
        
        getScenario().chargerJour(SliderJour.getValue());
        getPanel().repaint();
    }//GEN-LAST:event_SliderJourMouseReleased

    private void SimulationPanelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SimulationPanelKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            PlayPauseIconMouseReleased(null);
        }
        
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT && !estEnDirect) {
            getScenario().stepJour(1);
            SliderJour.setValue(getScenario().getIndexJourCourant());
            getPanel().repaint();
        }
        
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            if (estEnDirect) {
                setDirect(false);
            }
            getScenario().stepJour(-1);
            SliderJour.setValue(getScenario().getIndexJourCourant());
            getPanel().repaint();
        }
    }//GEN-LAST:event_SimulationPanelKeyReleased

    private void PlayPauseIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayPauseIconMouseEntered
        mouseOverDirect = true;
        updateDirectIcon();
    }//GEN-LAST:event_PlayPauseIconMouseEntered

    private void PlayPauseIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayPauseIconMouseExited
        mouseOverDirect = false;
        updateDirectIcon();
    }//GEN-LAST:event_PlayPauseIconMouseExited

    private void PlayPauseIconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayPauseIconMouseReleased
        setDirect(!estEnDirect);
        
        if (!getScenario().estCommence()) {
            SliderJour.setVisible(true);
            CommencePanel.setVisible(false);
            
            getPanel().getAfficheur().onSimulationDemaree();
        }
        
        if (!estCommence) {
            estCommence = true;
            
            gestionnaire.demarrer();
        }
        
        if (estEnDirect) {
            SliderJour.setValue(SliderJour.getMaximum());
        }
    }//GEN-LAST:event_PlayPauseIconMouseReleased

    private void FastForwardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FastForwardMouseEntered
        mouseOverFF = true;
        updateFastForwardIcon();
    }//GEN-LAST:event_FastForwardMouseEntered

    private void FastForwardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FastForwardMouseExited
        mouseOverFF = false;
        updateFastForwardIcon();
    }//GEN-LAST:event_FastForwardMouseExited

    private void FastForwardMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FastForwardMouseReleased
        setVitesse(vitesse + 1);
    }//GEN-LAST:event_FastForwardMouseReleased

    private void HomeBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeBoutonMouseReleased
        this.HomeButtonReleased(evt);
    }//GEN-LAST:event_HomeBoutonMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ca.ulaval.glo2004.afficheur.boutons.SimulationBouton BoutonPhoto;
    private javax.swing.JPanel BoutonsPanel;
    private javax.swing.JLabel CommenceIcon;
    private javax.swing.JLabel CommenceLabel;
    private javax.swing.JLabel CommenceLabel2;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi CommencePanel;
    private javax.swing.JPanel CommenceParent;
    private javax.swing.JPanel FF;
    private javax.swing.JLabel FFLabel;
    private javax.swing.JLabel FastForward;
    private ca.ulaval.glo2004.afficheur.boutons.SimulationBouton HomeBouton;
    private ca.ulaval.glo2004.afficheur.boutons.SimulationBouton MaladieBouton;
    private javax.swing.JPanel MenuDroitParent;
    private javax.swing.JPanel MenuGaucheParent;
    private javax.swing.JLabel PlayPauseIcon;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi SimulationMenuDroit;
    private ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanelGauche SimulationMenuGauche;
    private ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanel SimulationPanel;
    private javax.swing.JPanel Slider;
    private javax.swing.JSlider SliderJour;
    private javax.swing.JPanel SliderParent;
    private ca.ulaval.glo2004.afficheur.boutons.SimulationBouton ToggleCouleurs;
    private ca.ulaval.glo2004.afficheur.boutons.SimulationBouton ToggleLiens;
    private ca.ulaval.glo2004.afficheur.boutons.SimulationBouton TogglePaysRegion;
    private ca.ulaval.glo2004.afficheur.utilsUI.ZoomInfoPanel ZoomInfo;
    private javax.swing.JPanel ZoomInfoParent;
    // End of variables declaration//GEN-END:variables
}
