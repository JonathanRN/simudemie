/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte;

import ca.ulaval.glo2004.afficheur.FramePrincipal;
import ca.ulaval.glo2004.afficheur.ZoomInfoPanel;
import ca.ulaval.glo2004.afficheur.onglets.OngletCarte;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireCarte;
import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jonathan
 */
public class CreationCarte extends javax.swing.JPanel {
    
    private Mode mode;
    private final int carteIndex;
    private CreationCarteToggle toggleCourant;
    private OngletCarte onglet;
    
    public CreationCarte(int index, OngletCarte onglet) {
        carteIndex = index;
        this.onglet = onglet;
        initComponents();
        
        InformationsPanel.setBackground(new Color(59, 66, 82));
        InformationsPanel.setVisible(false);
        StartParent.setVisible(false);
        
        BoutonSelection.init(this, new Selection(this), "icons8_cursor_25px");
        BoutonCrayon.init(this, new Creation(this), "icons8_pen_25px");
        BoutonRegion.init(this, new ca.ulaval.glo2004.afficheur.CreationCarte.Region(this), "icons8_scissors_25px");
        BoutonLien.init(this, new LienPays(this), "icons8_chain_25px");
        
        BoutonQuitter.init(this, null, "icons8_home_25px_1");
        BoutonQuitter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                quitter();
            }
        });
        
        boolean estNouvelleCarte = getCarte().getListePays().isEmpty();
        onToggleClick(estNouvelleCarte ? BoutonCrayon : BoutonSelection);
        
        CreationCartePanel.setCreationCarte(this);
    }
    
    public JPanel getPopup() {
        return StartParent;
    }
    
    public ZoomInfoPanel getZoomPopup() {
        return ZoomInfo;
    }
    
    public CreationCartePanel getPanel() {
        return CreationCartePanel;
    }
    
    public ArrayList<Polygon> getPolygones() {
        return getCarte().getPolygonesRegions();
    }
    
    public Carte getCarte() {
        return GestionnaireCarte.getInstance().getElement(carteIndex);
    }
    
    public Pays getPays(Polygon p) {
        return getCarte().getPays(p);
    }
    
    public Mode getMode() {
        return mode;
    }
    
    public JPanel getInformationsPanel() {
        return InformationsPanel;
    }
    
    public void onToggleClick(CreationCarteToggle bouton) {
        if (toggleCourant != null) {
            toggleCourant.setToggle(false);
        }
        
        if (mode != null) {
            mode.onDesactive();
        }
        
        if (bouton.getMode() != null) {
            mode = bouton.getMode();
            mode.onActive();

            toggleCourant = bouton;
            toggleCourant.setToggle(true);
        }
    }
    
    public void onToggleClick(int index) {
        switch (index) {
            case 1:
                onToggleClick(BoutonSelection);
                break;
            case 2:
                onToggleClick(BoutonCrayon);
                break;
            case 3:
                onToggleClick(BoutonRegion);
                break;
            case 4:
                onToggleClick(BoutonLien);
                break;
        }
    }
    
    private void quitter() {
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("Retourner au menu principal.\nVoulez-vous sauvegarder vos modifications?");
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
        
        
        switch(result) {
            case JOptionPane.YES_OPTION:
                GestionnaireCarte.getInstance().sauvegarder();
                onglet.onRevenirSurOnglet();
            case JOptionPane.NO_OPTION:
                GestionnaireCarte.getInstance().charger();
                FramePrincipal frame = (FramePrincipal)SwingUtilities.windowForComponent(this);
                frame.returnToHome();
                onglet.onRevenirSurOnglet();
                break;
            default:
                onToggleClick(toggleCourant);
                BoutonQuitter.setToggle(false);
                break;
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

        StartParent = new javax.swing.JPanel();
        StartPanel = new ca.ulaval.glo2004.afficheur.PanelArrondi();
        StartLabel1 = new javax.swing.JLabel();
        StartLabel2 = new javax.swing.JLabel();
        ToolBarParent = new javax.swing.JPanel();
        ToolBar = new javax.swing.JPanel();
        BoutonSelection = new ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle();
        BoutonCrayon = new ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle();
        BoutonRegion = new ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle();
        BoutonLien = new ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle();
        BoutonQuitterParent = new javax.swing.JPanel();
        BoutonQuitter = new ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle();
        ZoomPanelParent = new javax.swing.JPanel();
        ZoomInfo = new ca.ulaval.glo2004.afficheur.ZoomInfoPanel();
        InformationsPanelParent = new javax.swing.JPanel();
        InformationsPanel = new javax.swing.JPanel();
        CreationCartePanel = new ca.ulaval.glo2004.afficheur.CreationCarte.CreationCartePanel();

        setBackground(new java.awt.Color(46, 52, 64));
        setLayout(new javax.swing.OverlayLayout(this));

        StartParent.setOpaque(false);

        StartPanel.setMaximumSize(new java.awt.Dimension(715, 50));
        StartPanel.setMinimumSize(new java.awt.Dimension(715, 50));
        StartPanel.setPreferredSize(new java.awt.Dimension(715, 50));
        StartPanel.setLayout(new java.awt.GridLayout(2, 0));

        StartLabel1.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        StartLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StartLabel1.setText("Cliquez pour placer un point.");
        StartLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        StartPanel.add(StartLabel1);

        StartLabel2.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        StartLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StartLabel2.setText("Puis appuyez sur ESPACE pour créer le polygone.");
        StartLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        StartPanel.add(StartLabel2);

        javax.swing.GroupLayout StartParentLayout = new javax.swing.GroupLayout(StartParent);
        StartParent.setLayout(StartParentLayout);
        StartParentLayout.setHorizontalGroup(
            StartParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StartParentLayout.createSequentialGroup()
                .addContainerGap(397, Short.MAX_VALUE)
                .addComponent(StartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(397, Short.MAX_VALUE))
        );
        StartParentLayout.setVerticalGroup(
            StartParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StartParentLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(StartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(661, Short.MAX_VALUE))
        );

        add(StartParent);

        ToolBarParent.setOpaque(false);
        ToolBarParent.setLayout(new java.awt.BorderLayout());

        ToolBar.setBackground(new java.awt.Color(67, 76, 94));
        ToolBar.setPreferredSize(new java.awt.Dimension(968, 50));
        ToolBar.setLayout(new javax.swing.BoxLayout(ToolBar, javax.swing.BoxLayout.X_AXIS));

        BoutonSelection.setToolTipText("Outil de sélection/édition");
        ToolBar.add(BoutonSelection);

        BoutonCrayon.setToolTipText("Outil de création pays");
        ToolBar.add(BoutonCrayon);

        BoutonRegion.setToolTipText("Outil découpage de région");
        ToolBar.add(BoutonRegion);

        BoutonLien.setToolTipText("Outils création liens ");
        ToolBar.add(BoutonLien);

        BoutonQuitterParent.setOpaque(false);
        BoutonQuitterParent.setLayout(new java.awt.BorderLayout());

        BoutonQuitter.setToolTipText("Retour à l'accueil");
        BoutonQuitterParent.add(BoutonQuitter, java.awt.BorderLayout.EAST);

        ToolBar.add(BoutonQuitterParent);

        ToolBarParent.add(ToolBar, java.awt.BorderLayout.SOUTH);

        ZoomPanelParent.setOpaque(false);

        javax.swing.GroupLayout ZoomPanelParentLayout = new javax.swing.GroupLayout(ZoomPanelParent);
        ZoomPanelParent.setLayout(ZoomPanelParentLayout);
        ZoomPanelParentLayout.setHorizontalGroup(
            ZoomPanelParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ZoomPanelParentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ZoomInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1086, Short.MAX_VALUE))
        );
        ZoomPanelParentLayout.setVerticalGroup(
            ZoomPanelParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ZoomPanelParentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ZoomInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(655, Short.MAX_VALUE))
        );

        ToolBarParent.add(ZoomPanelParent, java.awt.BorderLayout.CENTER);

        add(ToolBarParent);

        InformationsPanelParent.setOpaque(false);
        InformationsPanelParent.setLayout(new java.awt.BorderLayout());

        InformationsPanel.setFocusable(false);
        InformationsPanel.setPreferredSize(new java.awt.Dimension(225, 0));
        InformationsPanel.setLayout(new java.awt.BorderLayout());
        InformationsPanelParent.add(InformationsPanel, java.awt.BorderLayout.EAST);

        add(InformationsPanelParent);

        javax.swing.GroupLayout CreationCartePanelLayout = new javax.swing.GroupLayout(CreationCartePanel);
        CreationCartePanel.setLayout(CreationCartePanelLayout);
        CreationCartePanelLayout.setHorizontalGroup(
            CreationCartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1165, Short.MAX_VALUE)
        );
        CreationCartePanelLayout.setVerticalGroup(
            CreationCartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add(CreationCartePanel);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle BoutonCrayon;
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle BoutonLien;
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle BoutonQuitter;
    private javax.swing.JPanel BoutonQuitterParent;
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle BoutonRegion;
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle BoutonSelection;
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCartePanel CreationCartePanel;
    private javax.swing.JPanel InformationsPanel;
    private javax.swing.JPanel InformationsPanelParent;
    private javax.swing.JLabel StartLabel1;
    private javax.swing.JLabel StartLabel2;
    private ca.ulaval.glo2004.afficheur.PanelArrondi StartPanel;
    private javax.swing.JPanel StartParent;
    private javax.swing.JPanel ToolBar;
    private javax.swing.JPanel ToolBarParent;
    private ca.ulaval.glo2004.afficheur.ZoomInfoPanel ZoomInfo;
    private javax.swing.JPanel ZoomPanelParent;
    // End of variables declaration//GEN-END:variables
}
