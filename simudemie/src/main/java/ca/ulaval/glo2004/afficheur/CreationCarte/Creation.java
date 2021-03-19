/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jonathan
 */
public class Creation extends Mode {
    
    private Polygon polygoneSousSouris;
    private int indexSousSouris;
    private Point pointDragInitial;

    public Creation(CreationCartePanel panel) {
        super(panel);
    }
    
    @Override
    public void paint(Graphics2D g) {
        if (panel.getCourant().npoints >= 2) {
            paintLignes(g, couleurLigne, panel.getCourant());
        }
        
        if (polygoneSousSouris != null) {
            paintLignes(g, couleurLigne, polygoneSousSouris);
        }
        
        g.setColor(Color.white);
        for (Polygon p : panel.getPolygones()) {
            paintPointPolygone(g, p);
        }
        
        g.setColor(Color.green);
        if (polygoneSousSouris != null && indexSousSouris != -1) {
            g.fillOval(polygoneSousSouris.xpoints[indexSousSouris] - taillePoint/2, polygoneSousSouris.ypoints[indexSousSouris] - taillePoint/2, taillePoint, taillePoint);
        }
        
        super.paint(g);
    }
    
    @Override
    public void onDesactive() {
        super.onDesactive();
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onMouseMoved(MouseEvent evt) {
        super.onMouseMoved(evt);
        getPolygoneSousSouris(evt.getX(), evt.getY());
    }

    @Override
    public void onMousePressed(MouseEvent evt) {
        pointDragInitial = evt.getPoint();
        
        super.onMousePressed(evt);
    }
    
    @Override
    public void onMouseReleased(MouseEvent evt) {
        if (polygoneSousSouris == null) {
            panel.placerPoint(evt.getX(), evt.getY());
            
            // Dessine le nouveau point comme etant selectionne
            getPolygoneSousSouris(evt.getX(), evt.getY());
        }
        else {
            updateLignesInvalides(polygoneSousSouris);
            
            if (!lignesInvalides.isEmpty()) {
                polygoneSousSouris.ypoints[indexSousSouris] = pointDragInitial.y;
                polygoneSousSouris.xpoints[indexSousSouris] = pointDragInitial.x;
                lignesInvalides.clear();
            }
        }
        super.onMouseReleased(evt);
    }
    
    @Override
    public void onMouseDragged(MouseEvent evt) {
        if (polygoneSousSouris != null) {
            polygoneSousSouris.xpoints[indexSousSouris] = evt.getX();
            polygoneSousSouris.ypoints[indexSousSouris] = evt.getY();
            
            updateLignesInvalides(polygoneSousSouris);            
        }
        super.onMouseDragged(evt);
    }
    
    private void getPolygoneSousSouris(int x, int y) {
        polygoneSousSouris = null;
        indexSousSouris = -1;
        int offset = taillePoint/2 + 5;
        
        for (Polygon p : panel.getPolygones()) {
            for (int i = 0; i < p.npoints; i++) {
                if (Math.abs(x - p.xpoints[i]) <= offset &&
                    Math.abs(y - p.ypoints[i]) <= offset) {
                    indexSousSouris = i;
                    polygoneSousSouris = p;
                }
            }
        }
    }
}
