/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte;

import ca.ulaval.glo2004.afficheur.CreationCarte.Region.PolygoneDivise;
import ca.ulaval.glo2004.afficheur.ZoomablePanel;
import ca.ulaval.glo2004.afficheur.carteActions.ActionCarte;
import ca.ulaval.glo2004.afficheur.carteActions.AjouterPointAction;
import ca.ulaval.glo2004.afficheur.carteActions.CreerPolygoneAction;
import ca.ulaval.glo2004.afficheur.carteActions.SplitPaysAction;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.Region;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireCreationCarte;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jonathan
 */
public class CreationCartePanel extends ZoomablePanel {
    
    private final Stack<ActionCarte> actionsFaites = new Stack<>();
    private final Stack<ActionCarte> actionsUndo = new Stack<>();
    private final ArrayList<Polygon> polygones = new ArrayList<>();
    
    private final Color couleurFill = new Color(85, 91, 100, 100);
    
    private CreationCarte creationCarte;
    
    public CreationCartePanel() {
        initComponents();
        
        polygones.add(new Polygon());
    }
    
    public void setCreationCarte(CreationCarte creationCarte) {
        this.creationCarte = creationCarte;
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
        if (!creationCarte.getMode().estPolygoneValide(getCourant())) {
            undoAction();
        }
    }
    
    public void creerPolygone() {
        // Dessine le polygone seulement s'il est valide
        if (creationCarte.getMode().estPolygoneValide(getCourant())) {
            CreerPolygoneAction action = new CreerPolygoneAction(creationCarte.getIndexCarte(), new Pays(getCourant()), polygones);
            ajouterAction(action);
        }
        else {
            repaint();
        }
    }
    
    public void splitPays(Polygon p, PolygoneDivise divise) {
        SplitPaysAction action = new SplitPaysAction(p, divise.getGauche(), divise.getDroit(), creationCarte);
        ajouterAction(action);
    }
    
    public Pays getPays(Polygon p) {
        return GestionnaireCreationCarte.getInstance().getPays(creationCarte.getIndexCarte(), p);
    }
    
    public boolean estRegionUnique(Polygon p) {
        Pays pays = getPays(p);
        return pays != null && pays.getRegions().size() > 1;
    }
    
    public ca.ulaval.glo2004.domaine.Region getRegion(Pays pays, Polygon p) {
        for (Region r : pays.getRegions()) {
            if (r.getPolygone().equals(p)) {
                return r;
            }
        }
        return null;
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
        
        if (creationCarte != null) {
            creationCarte.getMode().paint(graphics);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(46, 52, 64));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                formMouseWheelMoved(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1118, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        requestFocusInWindow();
        if (SwingUtilities.isLeftMouseButton(evt)) {
            creationCarte.getMode().onMouseReleased(getOffset(evt.getPoint()));
        }
        
        if (SwingUtilities.isRightMouseButton(evt)) {
            mouseReleased(evt);
        }
        repaint();
    }//GEN-LAST:event_formMouseReleased
    
    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        creationCarte.getMode().onKeyReleased(evt);
        repaint();
    }//GEN-LAST:event_formKeyReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (SwingUtilities.isLeftMouseButton(evt)) {
            creationCarte.getMode().onMouseDragged(getOffset(evt.getPoint()));
        }
        
        if (SwingUtilities.isRightMouseButton(evt)) {
            mouseDragged(evt);
        }
        repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        creationCarte.getMode().onMouseMoved(getOffset(evt.getPoint()));
        repaint();
    }//GEN-LAST:event_formMouseMoved

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if (SwingUtilities.isLeftMouseButton(evt)) {
            creationCarte.getMode().onMousePressed(getOffset(evt.getPoint()));        
        }
        
        if (SwingUtilities.isRightMouseButton(evt)) {
            mousePressed(evt);
        }
        repaint();
    }//GEN-LAST:event_formMousePressed

    private void formMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_formMouseWheelMoved
        mouseWheelMoved(evt);
        repaint();
    }//GEN-LAST:event_formMouseWheelMoved
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
