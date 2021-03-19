/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur;

import ca.ulaval.glo2004.afficheur.boutons.CreationCarteToggle;
import ca.ulaval.glo2004.afficheur.carteActions.ActionCarte;
import ca.ulaval.glo2004.afficheur.carteActions.AjouterPointAction;
import ca.ulaval.glo2004.afficheur.carteActions.DessinerPolygoneAction;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Jonathan
 */
public class CreationCarte extends javax.swing.JPanel {
    
    public enum Mode {
        Selection,
        Creation
    }
    private Mode mode;
    
    private CreationCarteToggle toggleCourant;
    private Polygon polygoneSousSouris;
    private int indexSousSouris;
    private Point pointDragInitial;
    
    private Stack<ActionCarte> actionsFaites = new Stack<>();
    private Stack<ActionCarte> actionsUndo = new Stack<>();
    private ArrayList<Polygon> polygones = new ArrayList<>();
    private ArrayList<Line2D.Double> lignesInvalides = new ArrayList<>();
    
    private final int taillePoint = 20;
    private final Color couleurPoint = new Color(136, 192, 208);
    private final Color couleurFill = new Color(85, 91, 100, 100);
    
    public CreationCarte() {
        initComponents();
        BoutonSelection.init(this, Mode.Selection, "icons8_cursor_25px");
        BoutonCrayon.init(this, Mode.Creation, "icons8_pen_25px");
        onToggleClick(BoutonSelection);
        
       polygones.add(new Polygon());
    }
    
    private void placerPoint(int x, int y) {
        AjouterPointAction action = new AjouterPointAction(polygones.get(polygones.size() - 1), x, y);
        ajouterAction(action);
        
        // Place le point seulement s'il est valide
        updateLignesInvalides(polygones.get(polygones.size() - 1));
        if (!lignesInvalides.isEmpty()) {
            undoAction();
        }
    }
    
    private void dessinerPolygone() {
        // Dessine le polygone seulement s'il est valide
        updateLignesInvalides(polygones.get(polygones.size() - 1));
        if (lignesInvalides.isEmpty()) {
            DessinerPolygoneAction action = new DessinerPolygoneAction(polygones);
            ajouterAction(action);
        }
        else {
            repaint();
        }
    }
    
    private ArrayList<Line2D.Double> getPolygonLines(Polygon po) {
        ArrayList<double[]> areaPoints = new ArrayList<>();
        ArrayList<Line2D.Double> areaSegments = new ArrayList<>();
        double[] coords = new double[6];

        for (PathIterator pi = po.getPathIterator(null); !pi.isDone(); pi.next()) {
            // The type will be SEG_LINETO, SEG_MOVETO, or SEG_CLOSE
            // Because the Area is composed of straight lines
            int type = pi.currentSegment(coords);
            // We record a double array of {segment type, x coord, y coord}
            double[] pathIteratorCoords = {type, coords[0], coords[1]};
            areaPoints.add(pathIteratorCoords);
        }

        double[] start = new double[3]; // To record where each polygon starts

        for (int i = 0; i < areaPoints.size(); i++) {
            // If we're not on the last point, return a line from this point to the next
            double[] currentElement = areaPoints.get(i);

            // We need a default value in case we've reached the end of the ArrayList
            double[] nextElement = {-1, -1, -1};
            if (i < areaPoints.size() - 1) {
                nextElement = areaPoints.get(i + 1);
            }

            // Make the lines
            if (currentElement[0] == PathIterator.SEG_MOVETO) {
                start = currentElement; // Record where the polygon started to close it later
            } 

            if (nextElement[0] == PathIterator.SEG_LINETO) {
                areaSegments.add(new Line2D.Double(currentElement[1], currentElement[2], nextElement[1], nextElement[2]));
            } else if (nextElement[0] == PathIterator.SEG_CLOSE && areaPoints.size() > 3) {
                areaSegments.add(new Line2D.Double(currentElement[1], currentElement[2], start[1], start[2]));
            }
        }
        return areaSegments;
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
    
    private void getPointSousSouris(int x, int y) {
        polygoneSousSouris = null;
        indexSousSouris = -1;
        int offset = taillePoint/2 + 5;
        
        for (Polygon p : polygones) {
            for (int i = 0; i < p.npoints; i++) {
                if (Math.abs(x - p.xpoints[i]) <= offset &&
                    Math.abs(y - p.ypoints[i]) <= offset) {
                    polygoneSousSouris = p;
                    indexSousSouris = i;
                }
            }
        }
    }
    
    private ArrayList<Line2D.Double> getLignesInvalides(Polygon p) {
        ArrayList<Line2D.Double> invalides = new ArrayList<>();
        
        ArrayList<Line2D.Double> lines = getPolygonLines(p);
        for (Line2D.Double line : lines) {
            for (int x = 0; x < polygones.size(); x++) {
                ArrayList<Line2D.Double> linesP2 = getPolygonLines(polygones.get(x));
                for (Line2D.Double lineP2 : linesP2) {
                    if (line.intersectsLine(lineP2) &&
                        line.x1 != lineP2.x1 &&
                        line.x2 != lineP2.x2 &&
                        line.y1 != lineP2.y1 &&
                        line.y2 != lineP2.y2 &&
                        line.x1 != lineP2.x2 &&
                        line.y1 != lineP2.y2 &&
                        lineP2.x1 != line.x2 &&
                        lineP2.y1 != line.y2) {
                        
                        invalides.add(line);
                        invalides.add(lineP2);
                    }
                }
            }
        }
        return invalides;
    }
    
    private void updateLignesInvalides(Polygon g) {
        lignesInvalides.clear();
        lignesInvalides = getLignesInvalides(g);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Polygon courant = polygones.get(polygones.size() - 1);
        
        Graphics2D graphics = (Graphics2D) g;
        
        graphics.setColor(couleurFill);
        for (int i = 0; i < polygones.size() - 1; i++) {
            graphics.fillPolygon(polygones.get(i));
        }
        
        graphics.setColor(couleurPoint);
        if (courant.npoints >= 2) {
            ArrayList<Line2D.Double> lines = getPolygonLines(courant);
            for (int i = 0; i < lines.size(); i++) {
                graphics.drawLine((int)lines.get(i).x1, (int)lines.get(i).y1, (int)lines.get(i).x2, (int)lines.get(i).y2);
            }
        }
        
        graphics.setColor(Color.white);
        for (int i = 0; i < polygones.size(); i++) {
            paintPointPolygone(graphics, polygones.get(i));
        }
        
        if (polygoneSousSouris != null) {
            graphics.setColor(Color.green);
            graphics.fillOval(polygoneSousSouris.xpoints[indexSousSouris] - taillePoint/2, polygoneSousSouris.ypoints[indexSousSouris] - taillePoint/2, taillePoint, taillePoint);
        }
        
        graphics.setColor(Color.red);
        for (Line2D.Double line : lignesInvalides) {
            graphics.drawLine((int)line.x1, (int)line.y1, (int)line.x2, (int)line.y2);
        }
    }
    
    private void paintPointPolygone(Graphics2D g, Polygon p) {
        if (p != null) {
            for (int i = 0; i < p.npoints; i++) {
                g.fillOval(p.xpoints[i] - taillePoint/2, p.ypoints[i] - taillePoint/2, taillePoint, taillePoint);
                g.drawString(Integer.toString(i), p.xpoints[i] - taillePoint/2, p.ypoints[i] - taillePoint/2 - 10);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        if (polygoneSousSouris == null) {
            if (mode == Mode.Creation) {
                placerPoint(evt.getX(), evt.getY());
            }
        }
        else {
            updateLignesInvalides(polygoneSousSouris);
            
            if (!lignesInvalides.isEmpty()) {
                polygoneSousSouris.ypoints[indexSousSouris] = pointDragInitial.y;
                polygoneSousSouris.xpoints[indexSousSouris] = pointDragInitial.x;
                lignesInvalides.clear();
                repaint();
            }
        }

        // Dessine le nouveau point comme etant selectionne
        getPointSousSouris(evt.getX(), evt.getY());
        requestFocusInWindow();
    }//GEN-LAST:event_formMouseReleased
    
    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (polygones.get(polygones.size() - 1).npoints >= 3 && evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dessinerPolygone();
        }
    }//GEN-LAST:event_formKeyReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (polygoneSousSouris != null) {
            polygoneSousSouris.xpoints[indexSousSouris] = evt.getX();
            polygoneSousSouris.ypoints[indexSousSouris] = evt.getY();
            
            updateLignesInvalides(polygoneSousSouris);
            
            repaint();
        }
    }//GEN-LAST:event_formMouseDragged

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        getPointSousSouris(evt.getX(), evt.getY());
        repaint();
    }//GEN-LAST:event_formMouseMoved

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        pointDragInitial = evt.getPoint();
    }//GEN-LAST:event_formMousePressed

    public void onToggleClick(CreationCarteToggle bouton) {
        if (toggleCourant != null) {
            toggleCourant.setToggle(false);
        }
        
        toggleCourant = bouton;
        toggleCourant.setToggle(true);
        mode = toggleCourant.getMode();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ca.ulaval.glo2004.afficheur.boutons.CreationCarteToggle BoutonCrayon;
    private ca.ulaval.glo2004.afficheur.boutons.CreationCarteToggle BoutonSelection;
    private javax.swing.JPanel ToolBar;
    // End of variables declaration//GEN-END:variables
}
