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
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.domaine.Scenario;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireScenario;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    private JFileChooser fileChooser;
    public OngletScenario onglet;
    
    
    public Simulation(int index, OngletScenario onglet) {
        this.onglet = onglet;
        this.index = index;
        this.gestionnaire = GestionnaireScenario.getInstance();
        gestionnaire.setIndexCourant(index);
        gestionnaire.setCallback(this);
        
        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("png", "png");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        initComponents();
        SimulationMenuGauche.setSimulation(this);
        SimulationMenuDroit.setSimulation(this);
        toggleSimulationTabs(false);  
        
        //Aide Panel Init
        AidePanel.setVisible(false);
        AidePanel.setBackground(Couleurs.fondNoir);
        AidePanelEnfant.setBackground(Couleurs.sideMenuLessTransp);
        
        AideTitleLabel.setFont(FontRegister.RobotoRegular.deriveFont(21f));
        Commandes.setFont(FontRegister.RobotoRegular.deriveFont(17f));
        CommandesSouris.setFont(FontRegister.RobotoRegular.deriveFont(17f));
        ClickGauche.setFont(FontRegister.RobotoLight.deriveFont(15f));
        ClickDroit.setFont(FontRegister.RobotoLight.deriveFont(15f));
        MouseWheel.setFont(FontRegister.RobotoLight.deriveFont(15f));
        Space.setFont(FontRegister.RobotoLight.deriveFont(15f));
        PaysRegion.setFont(FontRegister.RobotoLight.deriveFont(15f));
        Liens.setFont(FontRegister.RobotoLight.deriveFont(15f));
        Couleurs1.setFont(FontRegister.RobotoLight.deriveFont(15f));
        Photo.setFont(FontRegister.RobotoLight.deriveFont(15f));
       
        
        // Cacher par default, tant que la simulation n'est pas commencee
        SliderJour.setVisible(false);
        
        //NomPays.setFont(FontRegister.RobotoLight.deriveFont(18f));
        FFLabel.setFont(FontRegister.RobotoLight.deriveFont(14f));
        CommenceLabel.setFont(FontRegister.RobotoLight.deriveFont(15f));
        CommenceLabel2.setFont(FontRegister.RobotoLight.deriveFont(15f));
        CommencePanel.setBackground(Couleurs.sideMenuTransp);
        
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
    
    public void toggleSimulationTabs(boolean actif) {
        SimulationMenuGauche.setVisible(actif);
    }
    
    public void toggleMenuDroitMaladie (boolean actif) {
        SimulationMenuDroit.setOpaque(actif);
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
    
    public void AideBoutonReleased(MouseEvent evt) {
        if (!AidePanel.isVisible()) {
            setDirect(false);
            AidePanel.setVisible(true);
            updateUI();
        }
        else {
            AidePanel.setVisible(false);
            updateUI();
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
    
    public void prendrePhoto() {
        int result = fileChooser.showDialog(null, "Capture d'écran");
        if(fileChooser.getSelectedFile() != null  && result == JFileChooser.OPEN_DIALOG) {
            BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            paintAll(img.getGraphics());
            
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            if(!path.endsWith(".png")) {
                path += ".png";
            }
            
            try {
                ImageIO.write(img, "png", new File(path));
            }
            catch (Exception e) {
            }
        }
    }
    
    @Override
    public void onAvancerJour(int jour) {
        SliderJour.setMaximum(jour);
        SliderJour.setValue(SliderJour.getMaximum());
        
        if (SimulationMenuGauche.isVisible()) {
            SimulationMenuGauche.onAvancerJour(jour);
        }

        repaint();
    }
    
    @Override
    public void onChargerJour(int jour) {
        if(SimulationMenuDroit.isVisible()) {
            SimulationMenuDroit.loadMaladie();
            repaint();
        }
        
        // On recharge le UI dans le cas que ca change
        if (SimulationMenuGauche.isVisible()) {
            SimulationMenuGauche.onChargerJour(jour);
            repaint();
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

        AidePanel = new javax.swing.JPanel();
        AidePanelEnfant = new ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi();
        AideTitlePanel = new javax.swing.JPanel();
        AideTitleLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        InformationsPanel = new javax.swing.JPanel();
        CommandesSouris = new javax.swing.JLabel();
        ClickGauche = new javax.swing.JLabel();
        ClickDroit = new javax.swing.JLabel();
        MouseWheel = new javax.swing.JLabel();
        Commandes = new javax.swing.JLabel();
        Space = new javax.swing.JLabel();
        PaysRegion = new javax.swing.JLabel();
        Liens = new javax.swing.JLabel();
        Couleurs1 = new javax.swing.JLabel();
        Photo = new javax.swing.JLabel();
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
        SimulationMenuDroit = new ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanelDroit();
        SimulationPanel = new ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanel();

        setBackground(new java.awt.Color(46, 52, 64));
        setLayout(new javax.swing.OverlayLayout(this));

        AidePanel.setOpaque(false);
        AidePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AidePanelMouseReleased(evt);
            }
        });

        AidePanelEnfant.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        AidePanelEnfant.setLayout(new java.awt.BorderLayout());

        AideTitlePanel.setOpaque(false);
        AideTitlePanel.setLayout(new java.awt.BorderLayout());

        AideTitleLabel.setFont(new java.awt.Font("Dialog", 0, 21)); // NOI18N
        AideTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AideTitleLabel.setText("Aide");
        AideTitleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        AideTitleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AideTitlePanel.add(AideTitleLabel, java.awt.BorderLayout.CENTER);
        AideTitlePanel.add(jSeparator1, java.awt.BorderLayout.PAGE_END);

        AidePanelEnfant.add(AideTitlePanel, java.awt.BorderLayout.NORTH);

        InformationsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        InformationsPanel.setOpaque(false);
        InformationsPanel.setLayout(new java.awt.GridLayout(10, 0));

        CommandesSouris.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        CommandesSouris.setText("Commandes Souris");
        CommandesSouris.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        InformationsPanel.add(CommandesSouris);

        ClickGauche.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        ClickGauche.setText("Cliquez sur un pays pour afficher le menu d'intervention");
        ClickGauche.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        InformationsPanel.add(ClickGauche);

        ClickDroit.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        ClickDroit.setText("Maintenez le clic droit et glisser pour vous déplacer sur la carte");
        ClickDroit.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        InformationsPanel.add(ClickDroit);

        MouseWheel.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        MouseWheel.setText("Scroll up/down pour zoom");
        MouseWheel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        InformationsPanel.add(MouseWheel);

        Commandes.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        Commandes.setText("Raccourcis clavier ");
        InformationsPanel.add(Commandes);

        Space.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        Space.setText("Appuyez sur [ ESPACE ] pour mettre la simulation en direct/pause");
        Space.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        InformationsPanel.add(Space);

        PaysRegion.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        PaysRegion.setText("Appuyez sur [ Q ] pour changer l'information affichée (Pays/Région)");
        PaysRegion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        InformationsPanel.add(PaysRegion);

        Liens.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        Liens.setText("Appuyez sur [ W ] pour afficher les liens entre les pays");
        Liens.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        InformationsPanel.add(Liens);

        Couleurs1.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        Couleurs1.setText("Appuyez sur [ E ] pour changer la représentation des couleurs");
        Couleurs1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        InformationsPanel.add(Couleurs1);

        Photo.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        Photo.setText("Appuyez sur [ S ] pour effectuer une capture d'écran ");
        Photo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        InformationsPanel.add(Photo);

        AidePanelEnfant.add(InformationsPanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout AidePanelLayout = new javax.swing.GroupLayout(AidePanel);
        AidePanel.setLayout(AidePanelLayout);
        AidePanelLayout.setHorizontalGroup(
            AidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AidePanelLayout.createSequentialGroup()
                .addContainerGap(271, Short.MAX_VALUE)
                .addComponent(AidePanelEnfant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(316, Short.MAX_VALUE))
        );
        AidePanelLayout.setVerticalGroup(
            AidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AidePanelLayout.createSequentialGroup()
                .addContainerGap(250, Short.MAX_VALUE)
                .addComponent(AidePanelEnfant, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );

        add(AidePanel);

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
        MenuGaucheParent.setPreferredSize(new java.awt.Dimension(1080, 780));

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

        MenuDroitParent.setBackground(new java.awt.Color(46, 52, 64));
        MenuDroitParent.setOpaque(false);

        SimulationMenuDroit.setMinimumSize(new java.awt.Dimension(127, 200));
        SimulationMenuDroit.setOpaque(false);

        javax.swing.GroupLayout MenuDroitParentLayout = new javax.swing.GroupLayout(MenuDroitParent);
        MenuDroitParent.setLayout(MenuDroitParentLayout);
        MenuDroitParentLayout.setHorizontalGroup(
            MenuDroitParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuDroitParentLayout.createSequentialGroup()
                .addContainerGap(730, Short.MAX_VALUE)
                .addComponent(SimulationMenuDroit, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        MenuDroitParentLayout.setVerticalGroup(
            MenuDroitParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuDroitParentLayout.createSequentialGroup()
                .addGap(0, 196, Short.MAX_VALUE)
                .addComponent(SimulationMenuDroit, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 196, Short.MAX_VALUE))
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

    private void AidePanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AidePanelMouseReleased
        if (AidePanel.isVisible()) {
            AidePanel.setVisible(false);
            updateUI();
        }
    }//GEN-LAST:event_AidePanelMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AidePanel;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi AidePanelEnfant;
    private javax.swing.JLabel AideTitleLabel;
    private javax.swing.JPanel AideTitlePanel;
    private javax.swing.JPanel BoutonsPanel;
    private javax.swing.JLabel ClickDroit;
    private javax.swing.JLabel ClickGauche;
    private javax.swing.JLabel Commandes;
    private javax.swing.JLabel CommandesSouris;
    private javax.swing.JLabel CommenceIcon;
    private javax.swing.JLabel CommenceLabel;
    private javax.swing.JLabel CommenceLabel2;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi CommencePanel;
    private javax.swing.JPanel CommenceParent;
    private javax.swing.JLabel Couleurs1;
    private javax.swing.JPanel FF;
    private javax.swing.JLabel FFLabel;
    private javax.swing.JLabel FastForward;
    private javax.swing.JPanel InformationsPanel;
    private javax.swing.JLabel Liens;
    private javax.swing.JPanel MenuDroitParent;
    private javax.swing.JPanel MenuGaucheParent;
    private javax.swing.JLabel MouseWheel;
    private javax.swing.JLabel PaysRegion;
    private javax.swing.JLabel Photo;
    private javax.swing.JLabel PlayPauseIcon;
    private ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanelDroit SimulationMenuDroit;
    private ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanelGauche SimulationMenuGauche;
    private ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanel SimulationPanel;
    private javax.swing.JPanel Slider;
    private javax.swing.JSlider SliderJour;
    private javax.swing.JPanel SliderParent;
    private javax.swing.JLabel Space;
    private ca.ulaval.glo2004.afficheur.utilsUI.ZoomInfoPanel ZoomInfo;
    private javax.swing.JPanel ZoomInfoParent;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
