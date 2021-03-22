/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte;

import ca.ulaval.glo2004.afficheur.CreationCarte.Region.PolygoneDivise;
import ca.ulaval.glo2004.afficheur.boutons.CreationCarteToggle;
import ca.ulaval.glo2004.afficheur.carteActions.ActionCarte;
import ca.ulaval.glo2004.afficheur.carteActions.AjouterPointAction;
import ca.ulaval.glo2004.afficheur.carteActions.CreerPolygoneAction;
import ca.ulaval.glo2004.afficheur.carteActions.SplitPaysAction;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.Region;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireScenario;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Jonathan
 */
public class CreationCartePanel extends javax.swing.JPanel {
    
    private Mode mode;
    
    private final int carteIndex;
    private CreationCarteToggle toggleCourant;
    
    private final Stack<ActionCarte> actionsFaites = new Stack<>();
    private final Stack<ActionCarte> actionsUndo = new Stack<>();
    private final ArrayList<Polygon> polygones = new ArrayList<>();
    
    private final Color couleurFill = new Color(85, 91, 100, 100);
    private final JSpinner PopulationTotale;
    
    public CreationCartePanel(int index) {
        initComponents();
        
        PopulationTotale = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 30));
        PopPanel.add(PopulationTotale, java.awt.BorderLayout.CENTER);
        
        carteIndex = index;
        
        BoutonSelection.init(this, new Selection(this), "icons8_cursor_25px");
        BoutonCrayon.init(this, new Creation(this), "icons8_pen_25px");
        BoutonRegion.init(this, new ca.ulaval.glo2004.afficheur.CreationCarte.Region(this), "icons8_pen_25px");
        onToggleClick(BoutonSelection);
        
       polygones.add(new Polygon());
    }
    
    public int getIndexCarte() {
        return carteIndex;
    }
    
    public ArrayList<Polygon> getPolygones() {
        return polygones;
    }
    
    public Polygon getCourant() {
        return polygones.get(polygones.size() - 1);
    }
    
    public void placerPoint(int x, int y) {
        AjouterPointAction action = new AjouterPointAction(getCourant(), x, y);
        ajouterAction(action);
        
        // Place le point seulement s'il est valide
        if (!mode.estPolygoneValide(getCourant())) {
            undoAction();
        }
    }
    
    public void creerPolygone() {
        // Dessine le polygone seulement s'il est valide
        if (mode.estPolygoneValide(getCourant())) {
            CreerPolygoneAction action = new CreerPolygoneAction(carteIndex, new Pays(getCourant()), polygones);
            ajouterAction(action);
        }
        else {
            repaint();
        }
    }
    
    public void splitPays(Polygon p, PolygoneDivise divise) {
        SplitPaysAction action = new SplitPaysAction(p, divise.getGauche(), divise.getDroit(), this);
        ajouterAction(action);
    }
    
    public Pays getPays(Polygon p) {
        return GestionnaireScenario.GetInstance().getPays(carteIndex, p);
    }
    
    public ca.ulaval.glo2004.domaine.Region getRegion(Pays pays, Polygon p) {
        for (Region r : pays.getRegions()) {
            if (r.getPolygone().equals(p)) {
                return r;
            }
        }
        return null;
    }
    
    public JPanel getInformationsPaysPanel() {
        return InformationsPaysPanel;
    }
    
    public JTextField getRegionNomField() {
        return RegionNomTextField;
    }
    
    public JTextField getPaysNomField() {
        return PaysNomTextField;
    }
    
    public JSpinner getPopField() {
        return PopulationTotale;
    }
    
    public void setPopTotaleTexte(String string) {
        PopTotaleLabel.setText("Pop. totale : " + string);
    }
    
    private void ajouterAction(ActionCarte action) {
        actionsFaites.push(action);
        action.Executer();
        // On veut pas modifier le futur
        actionsUndo.clear();
        repaint();
    }
    
    private void undoAction() {
        if (!actionsFaites.isEmpty()) {
            ActionCarte action = actionsFaites.pop();
            action.Undo();
            actionsUndo.push(action);
            repaint();
        }
    }
    
    private void redoAction() {
        if (!actionsUndo.isEmpty()) {
            ActionCarte action = actionsUndo.pop();
            action.Executer();
            actionsFaites.push(action);
            repaint();
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D graphics = (Graphics2D) g;

        graphics.setColor(couleurFill);
        for (int i = 0; i < polygones.size() - 1; i++) {
            graphics.fillPolygon(polygones.get(i));
        }
        
        mode.paint(graphics);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InformationsParent = new javax.swing.JPanel();
        InformationsPaysPanel = new javax.swing.JPanel();
        PaysNomPanel = new javax.swing.JPanel();
        PaysNomLabel = new javax.swing.JLabel();
        PaysNomTextField = new javax.swing.JTextField();
        RegionNomPanel = new javax.swing.JPanel();
        RegionNomLabel = new javax.swing.JLabel();
        RegionNomTextField = new javax.swing.JTextField();
        PopPanel = new javax.swing.JPanel();
        PopLabel = new javax.swing.JLabel();
        PopTotalePanel = new javax.swing.JPanel();
        PopTotaleLabel = new javax.swing.JLabel();
        ToolBar = new javax.swing.JPanel();
        BoutonSelection = new ca.ulaval.glo2004.afficheur.boutons.CreationCarteToggle();
        BoutonCrayon = new ca.ulaval.glo2004.afficheur.boutons.CreationCarteToggle();
        BoutonRegion = new ca.ulaval.glo2004.afficheur.boutons.CreationCarteToggle();

        setBackground(new java.awt.Color(46, 52, 64));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        InformationsParent.setOpaque(false);
        InformationsParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InformationsPaysPanel.setOpaque(false);
        InformationsPaysPanel.setLayout(new java.awt.GridLayout(4, 1, 0, 2));

        PaysNomPanel.setOpaque(false);
        PaysNomPanel.setLayout(new java.awt.BorderLayout(5, 0));

        PaysNomLabel.setText("Pays :");
        PaysNomPanel.add(PaysNomLabel, java.awt.BorderLayout.WEST);

        PaysNomTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PaysNomTextField.setPreferredSize(new java.awt.Dimension(200, 25));
        PaysNomTextField.setSelectionColor(new java.awt.Color(67, 76, 94));
        PaysNomPanel.add(PaysNomTextField, java.awt.BorderLayout.CENTER);

        InformationsPaysPanel.add(PaysNomPanel);

        RegionNomPanel.setOpaque(false);
        RegionNomPanel.setLayout(new java.awt.BorderLayout(5, 0));

        RegionNomLabel.setText("Région :");
        RegionNomPanel.add(RegionNomLabel, java.awt.BorderLayout.WEST);

        RegionNomTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RegionNomTextField.setPreferredSize(new java.awt.Dimension(200, 25));
        RegionNomTextField.setSelectionColor(new java.awt.Color(67, 76, 94));
        RegionNomPanel.add(RegionNomTextField, java.awt.BorderLayout.CENTER);

        InformationsPaysPanel.add(RegionNomPanel);

        PopPanel.setOpaque(false);
        PopPanel.setLayout(new java.awt.BorderLayout(5, 0));

        PopLabel.setText("Pop. région  :");
        PopLabel.setToolTipText("");
        PopPanel.add(PopLabel, java.awt.BorderLayout.WEST);

        InformationsPaysPanel.add(PopPanel);

        PopTotalePanel.setOpaque(false);
        PopTotalePanel.setLayout(new java.awt.BorderLayout(5, 0));

        PopTotaleLabel.setText("Pop. totale  :");
        PopTotaleLabel.setToolTipText("");
        PopTotalePanel.add(PopTotaleLabel, java.awt.BorderLayout.WEST);

        InformationsPaysPanel.add(PopTotalePanel);

        InformationsParent.add(InformationsPaysPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, -1, -1));

        add(InformationsParent, java.awt.BorderLayout.CENTER);

        ToolBar.setBackground(new java.awt.Color(67, 76, 94));
        ToolBar.setPreferredSize(new java.awt.Dimension(968, 50));
        ToolBar.setLayout(new javax.swing.BoxLayout(ToolBar, javax.swing.BoxLayout.X_AXIS));
        ToolBar.add(BoutonSelection);
        ToolBar.add(BoutonCrayon);
        ToolBar.add(BoutonRegion);

        add(ToolBar, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        requestFocusInWindow();
        mode.onMouseReleased(evt);
        repaint();
    }//GEN-LAST:event_formMouseReleased
    
    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        mode.onKeyReleased(evt);
        repaint();
    }//GEN-LAST:event_formKeyReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        mode.onMouseDragged(evt);
        repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        mode.onMouseMoved(evt);
        repaint();
    }//GEN-LAST:event_formMouseMoved

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        mode.onMousePressed(evt);
        repaint();
    }//GEN-LAST:event_formMousePressed

    public void onToggleClick(CreationCarteToggle bouton) {
        if (toggleCourant != null) {
            toggleCourant.setToggle(false);
        }
        
        if (mode != null) {
            mode.onDesactive();
        }
        
        toggleCourant = bouton;
        toggleCourant.setToggle(true);
        mode = toggleCourant.getMode();
        
        mode.onActive();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ca.ulaval.glo2004.afficheur.boutons.CreationCarteToggle BoutonCrayon;
    private ca.ulaval.glo2004.afficheur.boutons.CreationCarteToggle BoutonRegion;
    private ca.ulaval.glo2004.afficheur.boutons.CreationCarteToggle BoutonSelection;
    private javax.swing.JPanel InformationsParent;
    private javax.swing.JPanel InformationsPaysPanel;
    private javax.swing.JLabel PaysNomLabel;
    private javax.swing.JPanel PaysNomPanel;
    private javax.swing.JTextField PaysNomTextField;
    private javax.swing.JLabel PopLabel;
    private javax.swing.JPanel PopPanel;
    private javax.swing.JLabel PopTotaleLabel;
    private javax.swing.JPanel PopTotalePanel;
    private javax.swing.JLabel RegionNomLabel;
    private javax.swing.JPanel RegionNomPanel;
    private javax.swing.JTextField RegionNomTextField;
    private javax.swing.JPanel ToolBar;
    // End of variables declaration//GEN-END:variables
}
