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
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.Map;
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
    
    private Stack<ActionCarte> actionsFaites = new Stack<>();
    private Stack<ActionCarte> actionsUndo = new Stack<>();
    private Polygon courant = new Polygon();
    private ArrayList<Polygon> polygones = new ArrayList<>();
    
    private final int rayonPoint = 20;
    private final Color couleurPoint = new Color(136, 192, 208);
    private final Color couleurFill = new Color(85, 91, 100, 100);
    
    public CreationCarte() {
        initComponents();
        BoutonSelection.init(this, Mode.Selection, "icons8_cursor_25px");
        BoutonCrayon.init(this, Mode.Creation, "icons8_pen_25px");
        onToggleClick(BoutonSelection);        
    }
    
    private void placerPoint(int x, int y) {
        AjouterPointAction action = new AjouterPointAction(courant, x, y);
        ajouterAction(action);
    }
    
    private void dessinerPolygone() {
        DessinerPolygoneAction action = new DessinerPolygoneAction(courant, polygones);
        ajouterAction(action);
        
        courant = new Polygon();
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
                areaSegments.add(
                    new Line2D.Double(
                        currentElement[1], currentElement[2],
                        nextElement[1], nextElement[2]
                    )
                );
            } else if (nextElement[0] == PathIterator.SEG_CLOSE) {
                areaSegments.add(
                    new Line2D.Double(
                        currentElement[1], currentElement[2],
                        start[1], start[2]
                    )
                );
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
        int offset = rayonPoint/2 + 5;
        
        //TODO meilleure facon de gerer courant + polygones
        // Check le courant
        for (int i = 0; i < courant.npoints; i++) {
                if (Math.abs(x - courant.xpoints[i]) <= offset &&
                    Math.abs(y - courant.ypoints[i]) <= offset) {
                    polygoneSousSouris = courant;
                    indexSousSouris = i;
                }
            }
        
        // Check tous les polygones
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
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D graphics = (Graphics2D) g;
        
        graphics.setColor(couleurFill);
        for (Polygon p : polygones) {
            graphics.fillPolygon(p);
        }
        
        graphics.setColor(couleurPoint);
        if (courant.npoints >= 3) {
            ArrayList<Line2D.Double> lines = getPolygonLines(courant);
            for (int i = 0; i < lines.size(); i++) {
                graphics.drawLine((int)lines.get(i).x1, (int)lines.get(i).y1, (int)lines.get(i).x2, (int)lines.get(i).y2);
            }
        }
        
        graphics.setColor(Color.white);
        for (Polygon p : polygones) {
            paintPointPolygone(graphics, p);
        }
        paintPointPolygone(graphics, courant);
        
        if (polygoneSousSouris != null) {
            graphics.setColor(Color.green);
            graphics.fillOval(polygoneSousSouris.xpoints[indexSousSouris] - rayonPoint/2, polygoneSousSouris.ypoints[indexSousSouris] - rayonPoint/2, rayonPoint, rayonPoint);
        }
    }
    
    private void paintPointPolygone(Graphics2D g, Polygon p) {
        if (p != null) {
            for (int i = 0; i < p.npoints; i++) {
                g.fillOval(p.xpoints[i] - rayonPoint/2, p.ypoints[i] - rayonPoint/2, rayonPoint, rayonPoint);
                g.drawString(Integer.toString(i), p.xpoints[i] - rayonPoint/2, p.ypoints[i] - rayonPoint/2 - 10);
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
        if (mode == Mode.Creation && polygoneSousSouris == null) {
            placerPoint(evt.getX(), evt.getY());
            
            // Dessine le nouveau point comme etant selectionne
            getPointSousSouris(evt.getX(), evt.getY());
            repaint();
        }
        
        requestFocusInWindow();
    }//GEN-LAST:event_formMouseReleased
    
    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (courant.npoints >= 3 && evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dessinerPolygone();
        }
    }//GEN-LAST:event_formKeyReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (polygoneSousSouris != null) {
            polygoneSousSouris.xpoints[indexSousSouris] = evt.getX();
            polygoneSousSouris.ypoints[indexSousSouris] = evt.getY();
            repaint();
        }
    }//GEN-LAST:event_formMouseDragged

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        getPointSousSouris(evt.getX(), evt.getY());
        repaint();
    }//GEN-LAST:event_formMouseMoved

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
