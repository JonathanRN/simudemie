/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte;

import ca.ulaval.glo2004.afficheur.CreationCarte.panels.CreationCartePanel;
import ca.ulaval.glo2004.afficheur.CreationCarte.mode.Creation;
import ca.ulaval.glo2004.afficheur.CreationCarte.mode.LienPays;
import ca.ulaval.glo2004.afficheur.CreationCarte.mode.Mode;
import ca.ulaval.glo2004.afficheur.CreationCarte.mode.Selection;
import ca.ulaval.glo2004.afficheur.boutons.ToggleBouton;
import ca.ulaval.glo2004.afficheur.FramePrincipal;
import ca.ulaval.glo2004.afficheur.utilsUI.ZoomInfoPanel;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.onglets.OngletCarte;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireCarte;
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
    private ToggleBouton toggleCourant;
    private OngletCarte onglet;
    
    public CreationCarte(int index, OngletCarte onglet) {
        carteIndex = index;
        this.onglet = onglet;
        initComponents();
        
        nomCarte.setText("Carte :   " + getCarte().getNom());
        nomCarte.setFont(FontRegister.RobotoLight.deriveFont(17f));
        
        InformationsPanel.setBackground(Couleurs.titleBar);
        InformationsPanel.setVisible(false);
        IndiceCommenceParent.setVisible(false);
        
        BoutonSelection.init(this, new Selection(this), "icons8_cursor_25px");
        BoutonCrayon.init(this, new Creation(this), "icons8_pen_25px");
        BoutonRegion.init(this, new ca.ulaval.glo2004.afficheur.CreationCarte.mode.Region(this), "icons8_scissors_25px");
        BoutonLien.init(this, new LienPays(this), "icons8_chain_25px");
        
        BoutonUndo.init(this, null, "icons8_undo_25px");
        BoutonRedo.init(this, null, "icons8_redo_25px");
        setUndoActif(false);
        setRedoActif(false);
        
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
    
    public void setUndoActif(boolean actif) {
        BoutonUndo.setActif(actif);
    }
    
    public void setRedoActif(boolean actif) {
        BoutonRedo.setActif(actif);
    }
    
    public JPanel getPopup() {
        return IndiceCommenceParent;
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
    
    public void onToggleClick(ToggleBouton bouton) {
        if (bouton.getMode() != null) {
            if (toggleCourant != null) {
                toggleCourant.setToggle(false);
            }

            if (mode != null) {
                mode.onDesactive();
            }
            
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

        IndiceCommenceParent = new javax.swing.JPanel();
        IndiceCommencePanel = new ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi();
        StartLabel1 = new javax.swing.JLabel();
        StartLabel2 = new javax.swing.JLabel();
        ToolBarParent = new javax.swing.JPanel();
        ToolBar = new javax.swing.JPanel();
        BoutonsPrincipauxParent = new javax.swing.JPanel();
        BoutonSelection = new ca.ulaval.glo2004.afficheur.boutons.ToggleBouton();
        BoutonCrayon = new ca.ulaval.glo2004.afficheur.boutons.ToggleBouton();
        BoutonRegion = new ca.ulaval.glo2004.afficheur.boutons.ToggleBouton();
        BoutonLien = new ca.ulaval.glo2004.afficheur.boutons.ToggleBouton();
        BoutonUndoParent = new javax.swing.JPanel();
        BoutonUndo = new ca.ulaval.glo2004.afficheur.boutons.ToggleBouton();
        BoutonRedoParent = new javax.swing.JPanel();
        BoutonRedo = new ca.ulaval.glo2004.afficheur.boutons.ToggleBouton();
        BoutonQuitterParent = new javax.swing.JPanel();
        BoutonQuitter = new ca.ulaval.glo2004.afficheur.boutons.ToggleBouton();
        nomCarte = new javax.swing.JLabel();
        ZoomPanelParent = new javax.swing.JPanel();
        ZoomInfo = new ca.ulaval.glo2004.afficheur.utilsUI.ZoomInfoPanel();
        InformationsPanelParent = new javax.swing.JPanel();
        InformationsPanel = new javax.swing.JPanel();
        CreationCartePanel = new ca.ulaval.glo2004.afficheur.CreationCarte.panels.CreationCartePanel();

        setBackground(new java.awt.Color(46, 52, 64));
        setLayout(new javax.swing.OverlayLayout(this));

        IndiceCommenceParent.setOpaque(false);

        IndiceCommencePanel.setMaximumSize(new java.awt.Dimension(715, 50));
        IndiceCommencePanel.setMinimumSize(new java.awt.Dimension(715, 50));
        IndiceCommencePanel.setPreferredSize(new java.awt.Dimension(715, 50));
        IndiceCommencePanel.setLayout(new java.awt.GridLayout(2, 0));

        StartLabel1.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        StartLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StartLabel1.setText("Cliquez pour placer un point.");
        StartLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        IndiceCommencePanel.add(StartLabel1);

        StartLabel2.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        StartLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StartLabel2.setText("Puis appuyez sur ESPACE pour créer le polygone.");
        StartLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        IndiceCommencePanel.add(StartLabel2);

        javax.swing.GroupLayout IndiceCommenceParentLayout = new javax.swing.GroupLayout(IndiceCommenceParent);
        IndiceCommenceParent.setLayout(IndiceCommenceParentLayout);
        IndiceCommenceParentLayout.setHorizontalGroup(
            IndiceCommenceParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, IndiceCommenceParentLayout.createSequentialGroup()
                .addContainerGap(292, Short.MAX_VALUE)
                .addComponent(IndiceCommencePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(293, Short.MAX_VALUE))
        );
        IndiceCommenceParentLayout.setVerticalGroup(
            IndiceCommenceParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IndiceCommenceParentLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(IndiceCommencePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(661, Short.MAX_VALUE))
        );

        add(IndiceCommenceParent);

        ToolBarParent.setOpaque(false);
        ToolBarParent.setLayout(new java.awt.BorderLayout());

        ToolBar.setBackground(new java.awt.Color(67, 76, 94));
        ToolBar.setPreferredSize(new java.awt.Dimension(968, 50));
        ToolBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ToolBarMousePressed(evt);
            }
        });
        ToolBar.setLayout(new java.awt.GridLayout(1, 0));

        BoutonsPrincipauxParent.setOpaque(false);
        BoutonsPrincipauxParent.setLayout(new javax.swing.BoxLayout(BoutonsPrincipauxParent, javax.swing.BoxLayout.LINE_AXIS));

        BoutonSelection.setToolTipText("Outil de sélection/édition (1)");
        BoutonsPrincipauxParent.add(BoutonSelection);

        BoutonCrayon.setToolTipText("Outil de création pays (2)");
        BoutonsPrincipauxParent.add(BoutonCrayon);

        BoutonRegion.setToolTipText("Outil découpage de région (3)");
        BoutonsPrincipauxParent.add(BoutonRegion);

        BoutonLien.setToolTipText("Outils création liens (4)");
        BoutonsPrincipauxParent.add(BoutonLien);

        ToolBar.add(BoutonsPrincipauxParent);

        BoutonUndoParent.setOpaque(false);
        BoutonUndoParent.setLayout(new java.awt.BorderLayout());

        BoutonUndo.setToolTipText("Undo (CTRL-Z)");
        BoutonUndo.setActif(true);
        BoutonUndo.setEnabled(false);
        BoutonUndo.setFocusable(false);
        BoutonUndo.setToggle(false);
        BoutonUndo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BoutonUndoMouseReleased(evt);
            }
        });
        BoutonUndoParent.add(BoutonUndo, java.awt.BorderLayout.EAST);

        ToolBar.add(BoutonUndoParent);

        BoutonRedoParent.setOpaque(false);
        BoutonRedoParent.setLayout(new java.awt.BorderLayout());

        BoutonRedo.setToolTipText("Redo (CTRL-Y)");
        BoutonRedo.setActif(true);
        BoutonRedo.setFocusable(false);
        BoutonRedo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BoutonRedoMouseReleased(evt);
            }
        });
        BoutonRedoParent.add(BoutonRedo, java.awt.BorderLayout.WEST);

        ToolBar.add(BoutonRedoParent);

        BoutonQuitterParent.setOpaque(false);
        BoutonQuitterParent.setLayout(new java.awt.BorderLayout());

        BoutonQuitter.setToolTipText("Retour à l'accueil");
        BoutonQuitterParent.add(BoutonQuitter, java.awt.BorderLayout.EAST);
        BoutonQuitterParent.add(nomCarte, java.awt.BorderLayout.CENTER);

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
                .addContainerGap(956, Short.MAX_VALUE))
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
            .addGap(0, 956, Short.MAX_VALUE)
        );
        CreationCartePanelLayout.setVerticalGroup(
            CreationCartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add(CreationCartePanel);
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonUndoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoutonUndoMouseReleased
        getPanel().undoAction();
    }//GEN-LAST:event_BoutonUndoMouseReleased

    private void BoutonRedoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoutonRedoMouseReleased
        getPanel().redoAction();
    }//GEN-LAST:event_BoutonRedoMouseReleased

    private void ToolBarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ToolBarMousePressed
        // Garder l'event ici pour empecher de click au travers du UI
    }//GEN-LAST:event_ToolBarMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ca.ulaval.glo2004.afficheur.boutons.ToggleBouton BoutonCrayon;
    private ca.ulaval.glo2004.afficheur.boutons.ToggleBouton BoutonLien;
    private ca.ulaval.glo2004.afficheur.boutons.ToggleBouton BoutonQuitter;
    private javax.swing.JPanel BoutonQuitterParent;
    private ca.ulaval.glo2004.afficheur.boutons.ToggleBouton BoutonRedo;
    private javax.swing.JPanel BoutonRedoParent;
    private ca.ulaval.glo2004.afficheur.boutons.ToggleBouton BoutonRegion;
    private ca.ulaval.glo2004.afficheur.boutons.ToggleBouton BoutonSelection;
    private ca.ulaval.glo2004.afficheur.boutons.ToggleBouton BoutonUndo;
    private javax.swing.JPanel BoutonUndoParent;
    private javax.swing.JPanel BoutonsPrincipauxParent;
    private ca.ulaval.glo2004.afficheur.CreationCarte.panels.CreationCartePanel CreationCartePanel;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi IndiceCommencePanel;
    private javax.swing.JPanel IndiceCommenceParent;
    private javax.swing.JPanel InformationsPanel;
    private javax.swing.JPanel InformationsPanelParent;
    private javax.swing.JLabel StartLabel1;
    private javax.swing.JLabel StartLabel2;
    private javax.swing.JPanel ToolBar;
    private javax.swing.JPanel ToolBarParent;
    private ca.ulaval.glo2004.afficheur.utilsUI.ZoomInfoPanel ZoomInfo;
    private javax.swing.JPanel ZoomPanelParent;
    private javax.swing.JLabel nomCarte;
    // End of variables declaration//GEN-END:variables
}
