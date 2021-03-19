/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte;

import ca.ulaval.glo2004.afficheur.boutons.CreationCarteToggle;
import ca.ulaval.glo2004.afficheur.carteActions.ActionCarte;
import ca.ulaval.glo2004.afficheur.carteActions.AjouterPointAction;
import ca.ulaval.glo2004.afficheur.carteActions.CreerPolygoneAction;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Stack;

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
    
    public CreationCartePanel(int index) {
        initComponents();
        carteIndex = index;
        
        BoutonSelection.init(this, new Selection(this), "icons8_cursor_25px");
        BoutonCrayon.init(this, new Creation(this), "icons8_pen_25px");
        onToggleClick(BoutonSelection);
        
       polygones.add(new Polygon());
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
            CreerPolygoneAction action = new CreerPolygoneAction(carteIndex, polygones);
            ajouterAction(action);
        }
        else {
            repaint();
        }
    }
    
    private boolean estRegion(Polygon p) {
        int count = 0;
        for (int i = 0; i < p.npoints; i++) {
            for (Polygon p2 : polygones) {
                if (!p.equals(p2) && p2.contains(p.xpoints[i], p.ypoints[i])) {
                    count++;
                }
            }
        }
        return count == p.npoints && count != 0;
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

        InformationsPays = new ca.ulaval.glo2004.afficheur.PanelArrondi();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        ToolBar = new javax.swing.JPanel();
        BoutonSelection = new ca.ulaval.glo2004.afficheur.boutons.CreationCarteToggle();
        BoutonCrayon = new ca.ulaval.glo2004.afficheur.boutons.CreationCarteToggle();

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

        InformationsPays.setPreferredSize(new java.awt.Dimension(200, 706));

        jTextField1.setText("jTextField1");

        jLabel1.setText("Nom");

        javax.swing.GroupLayout InformationsPaysLayout = new javax.swing.GroupLayout(InformationsPays);
        InformationsPays.setLayout(InformationsPaysLayout);
        InformationsPaysLayout.setHorizontalGroup(
            InformationsPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InformationsPaysLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );
        InformationsPaysLayout.setVerticalGroup(
            InformationsPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InformationsPaysLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(InformationsPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(647, Short.MAX_VALUE))
        );

        add(InformationsPays, java.awt.BorderLayout.EAST);

        ToolBar.setBackground(new java.awt.Color(67, 76, 94));
        ToolBar.setPreferredSize(new java.awt.Dimension(968, 50));

        javax.swing.GroupLayout ToolBarLayout = new javax.swing.GroupLayout(ToolBar);
        ToolBar.setLayout(ToolBarLayout);
        ToolBarLayout.setHorizontalGroup(
            ToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ToolBarLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(BoutonSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(BoutonCrayon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        ToolBarLayout.setVerticalGroup(
            ToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ToolBarLayout.createSequentialGroup()
                .addGroup(ToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BoutonSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BoutonCrayon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        add(ToolBar, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
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
        requestFocusInWindow();
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
    private ca.ulaval.glo2004.afficheur.boutons.CreationCarteToggle BoutonSelection;
    private ca.ulaval.glo2004.afficheur.PanelArrondi InformationsPays;
    private javax.swing.JPanel ToolBar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
