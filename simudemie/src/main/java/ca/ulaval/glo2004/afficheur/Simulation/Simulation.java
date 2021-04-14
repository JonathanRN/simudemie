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
        SimulationTabs.setSimulation(this);
        toggleSimulationTabs(false);
        
        initBoutonsGeneraux();
        
        // Cacher par default, tant que la simulation n'est pas commencee
        SliderJour.setVisible(false);
        
        //NomPays.setFont(FontRegister.RobotoLight.deriveFont(18f));
        FFLabel.setFont(FontRegister.RobotoLight.deriveFont(14f));
        StartLabel1.setFont(FontRegister.RobotoLight.deriveFont(15f));
        StartLabel2.setFont(FontRegister.RobotoLight.deriveFont(15f));
        
        SimulationPanel.setSimulation(this);
        
        boolean commence = getScenario().estCommence();
        SliderJour.setVisible(commence);
        StartPanel.setVisible(!commence);
        
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
        Help.setIcon("/icons/simulation/icons8_help_25px.png");
        HomeButton.setIcon("/icons/icons8_home_25px_1.png");
    }
    
    public void toggleSimulationTabs(boolean actif) {
        SimulationTabs.setVisible(actif);
        //NomPays.setVisible(actif);
    }
    
    public void setNomPays(String nom) {
        //NomPays.setText("Pays : " + nom);
    }
    
    public SimulationPanelGauche getSimulationTabs() {
        return SimulationTabs;
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
        optionPane.setOptionType(JOptionPane.YES_NO_CANCEL_OPTION);
        
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
        DirectIcon.setIcon(new ImageIcon(getClass().getResource(path)));
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
        if (SimulationTabs.isVisible()) {
            SimulationTabs.loadMesures();
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
        StartParent = new javax.swing.JPanel();
        StartPanel = new ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi();
        StartLabel1 = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        StartLabel2 = new javax.swing.JLabel();
        SliderParent = new javax.swing.JPanel();
        Slider = new javax.swing.JPanel();
        SliderJour = new javax.swing.JSlider();
        Boutons = new javax.swing.JPanel();
        DirectIcon = new javax.swing.JLabel();
        FF = new javax.swing.JPanel();
        FastForward = new javax.swing.JLabel();
        FFLabel = new javax.swing.JLabel();
        TabsParent = new javax.swing.JPanel();
        SimulationTabs = new ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanelGauche();
        MenuDroitParent = new javax.swing.JPanel();
        SidePanel = new ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi();
        TogglePaysRegion = new ca.ulaval.glo2004.afficheur.boutons.SimulationBouton();
        ToggleLiens = new ca.ulaval.glo2004.afficheur.boutons.SimulationBouton();
        ToggleCouleurs = new ca.ulaval.glo2004.afficheur.boutons.SimulationBouton();
        BoutonPhoto = new ca.ulaval.glo2004.afficheur.boutons.SimulationBouton();
        Help = new ca.ulaval.glo2004.afficheur.boutons.SimulationBouton();
        HomeButton = new ca.ulaval.glo2004.afficheur.boutons.SimulationBouton();
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

        StartParent.setOpaque(false);

        StartPanel.setMaximumSize(new java.awt.Dimension(715, 50));
        StartPanel.setMinimumSize(new java.awt.Dimension(715, 50));
        StartPanel.setPreferredSize(new java.awt.Dimension(715, 50));
        StartPanel.setLayout(new javax.swing.BoxLayout(StartPanel, javax.swing.BoxLayout.X_AXIS));

        StartLabel1.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        StartLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StartLabel1.setText("Appuyez sur une région pour choisir la région initialement infectée, puis appuyez sur");
        StartLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        StartPanel.add(StartLabel1);

        icon.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/direct_button_35px.png"))); // NOI18N
        StartPanel.add(icon);

        StartLabel2.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        StartLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StartLabel2.setText("pour démarrer la simulation.");
        StartLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));
        StartPanel.add(StartLabel2);
        StartLabel2.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout StartParentLayout = new javax.swing.GroupLayout(StartParent);
        StartParent.setLayout(StartParentLayout);
        StartParentLayout.setHorizontalGroup(
            StartParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StartParentLayout.createSequentialGroup()
                .addContainerGap(138, Short.MAX_VALUE)
                .addComponent(StartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 803, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );
        StartParentLayout.setVerticalGroup(
            StartParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StartParentLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(StartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(664, Short.MAX_VALUE))
        );

        add(StartParent);

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

        Boutons.setToolTipText("");
        Boutons.setMinimumSize(new java.awt.Dimension(35, 70));
        Boutons.setOpaque(false);
        Boutons.setPreferredSize(new java.awt.Dimension(80, 70));
        Boutons.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        DirectIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DirectIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/direct_button_35px.png"))); // NOI18N
        DirectIcon.setToolTipText("Retour en direct");
        DirectIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DirectIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DirectIconMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                DirectIconMouseReleased(evt);
            }
        });
        Boutons.add(DirectIcon);

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

        Boutons.add(FF);

        Slider.add(Boutons, java.awt.BorderLayout.EAST);

        SliderParent.add(Slider, java.awt.BorderLayout.SOUTH);

        add(SliderParent);

        TabsParent.setBackground(new java.awt.Color(46, 52, 64));
        TabsParent.setOpaque(false);

        javax.swing.GroupLayout TabsParentLayout = new javax.swing.GroupLayout(TabsParent);
        TabsParent.setLayout(TabsParentLayout);
        TabsParentLayout.setHorizontalGroup(
            TabsParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabsParentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SimulationTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(730, Short.MAX_VALUE))
        );
        TabsParentLayout.setVerticalGroup(
            TabsParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabsParentLayout.createSequentialGroup()
                .addContainerGap(196, Short.MAX_VALUE)
                .addComponent(SimulationTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
        );

        add(TabsParent);

        MenuDroitParent.setOpaque(false);

        SidePanel.setMaximumSize(new java.awt.Dimension(100, 200));
        SidePanel.setMinimumSize(new java.awt.Dimension(100, 200));
        SidePanel.setPreferredSize(new java.awt.Dimension(50, 200));
        SidePanel.setLayout(new java.awt.GridLayout(6, 1, 0, 10));

        TogglePaysRegion.setToolTipText("Afficher infomations pays/régions (Q)");
        SidePanel.add(TogglePaysRegion);

        ToggleLiens.setToolTipText("Afficher les liens (W)");
        SidePanel.add(ToggleLiens);

        ToggleCouleurs.setToolTipText("Changer les couleurs visuelles (E)");
        SidePanel.add(ToggleCouleurs);

        BoutonPhoto.setToolTipText("Prendre une photo de la carte courante (S)");
        SidePanel.add(BoutonPhoto);

        Help.setToolTipText("Afficher l'aide");
        SidePanel.add(Help);

        HomeButton.setToolTipText("Quitter la simulation (ESC)");
        HomeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                HomeButtonMouseReleased(evt);
            }
        });
        SidePanel.add(HomeButton);

        javax.swing.GroupLayout MenuDroitParentLayout = new javax.swing.GroupLayout(MenuDroitParent);
        MenuDroitParent.setLayout(MenuDroitParentLayout);
        MenuDroitParentLayout.setHorizontalGroup(
            MenuDroitParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuDroitParentLayout.createSequentialGroup()
                .addContainerGap(1024, Short.MAX_VALUE)
                .addComponent(SidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        MenuDroitParentLayout.setVerticalGroup(
            MenuDroitParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuDroitParentLayout.createSequentialGroup()
                .addContainerGap(196, Short.MAX_VALUE)
                .addComponent(SidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            DirectIconMouseReleased(null);
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

    private void DirectIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DirectIconMouseEntered
        mouseOverDirect = true;
        updateDirectIcon();
    }//GEN-LAST:event_DirectIconMouseEntered

    private void DirectIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DirectIconMouseExited
        mouseOverDirect = false;
        updateDirectIcon();
    }//GEN-LAST:event_DirectIconMouseExited

    private void DirectIconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DirectIconMouseReleased
        setDirect(!estEnDirect);
        
        if (!getScenario().estCommence()) {
            SliderJour.setVisible(true);
            StartPanel.setVisible(false);
            
            getPanel().getAfficheur().onSimulationDemaree();
        }
        
        if (!estCommence) {
            estCommence = true;
            
            gestionnaire.demarrer();
        }
        
        if (estEnDirect) {
            SliderJour.setValue(SliderJour.getMaximum());
        }
    }//GEN-LAST:event_DirectIconMouseReleased

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

    private void HomeButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeButtonMouseReleased
        this.HomeButtonReleased(evt);
    }//GEN-LAST:event_HomeButtonMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ca.ulaval.glo2004.afficheur.boutons.SimulationBouton BoutonPhoto;
    private javax.swing.JPanel Boutons;
    private javax.swing.JLabel DirectIcon;
    private javax.swing.JPanel FF;
    private javax.swing.JLabel FFLabel;
    private javax.swing.JLabel FastForward;
    private ca.ulaval.glo2004.afficheur.boutons.SimulationBouton Help;
    private ca.ulaval.glo2004.afficheur.boutons.SimulationBouton HomeButton;
    private javax.swing.JPanel MenuDroitParent;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi SidePanel;
    private ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanel SimulationPanel;
    private ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanelGauche SimulationTabs;
    private javax.swing.JPanel Slider;
    private javax.swing.JSlider SliderJour;
    private javax.swing.JPanel SliderParent;
    private javax.swing.JLabel StartLabel1;
    private javax.swing.JLabel StartLabel2;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi StartPanel;
    private javax.swing.JPanel StartParent;
    private javax.swing.JPanel TabsParent;
    private ca.ulaval.glo2004.afficheur.boutons.SimulationBouton ToggleCouleurs;
    private ca.ulaval.glo2004.afficheur.boutons.SimulationBouton ToggleLiens;
    private ca.ulaval.glo2004.afficheur.boutons.SimulationBouton TogglePaysRegion;
    private ca.ulaval.glo2004.afficheur.utilsUI.ZoomInfoPanel ZoomInfo;
    private javax.swing.JPanel ZoomInfoParent;
    private javax.swing.JLabel icon;
    // End of variables declaration//GEN-END:variables
}
