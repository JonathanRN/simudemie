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

/**
 *
 * @author Jonathan
 */
public class Creation extends Mode {
    
    private Polygon polygoneSousSouris;
    private int indexSousSouris;
    private Point pointDragInitial;

    public Creation(CreationCarte panel) {
        super(panel);
    }
    
    @Override
    public void paint(Graphics2D g) {
        if (creationCarte.getPanel().getCourant().npoints >= 2) {
            paintLignes(g, couleurLigne, creationCarte.getPanel().getCourant());
        }
        
        paintPolygones(g);
        
        if (polygoneSousSouris != null) {
            paintLignes(g, couleurLigne, polygoneSousSouris);
        }
        
        g.setColor(Color.white);
        for (Polygon p : creationCarte.getPanel().getPolygones()) {
            if (!creationCarte.getPanel().estRegionUnique(p)) {
                paintPointPolygone(g, p);
            }
        }
        
        g.setColor(Color.green);
        if (polygoneSousSouris != null && indexSousSouris != -1 && !creationCarte.getPanel().estRegionUnique(polygoneSousSouris)) {
            g.fillOval(polygoneSousSouris.xpoints[indexSousSouris] - taillePoint/2, polygoneSousSouris.ypoints[indexSousSouris] - taillePoint/2, taillePoint, taillePoint);
        }
        
        super.paint(g);
    }

    @Override
    public void onMouseMoved(Point point) {
        super.onMouseMoved(point);
        getPolygoneSousSouris(point.x, point.y);
    }

    @Override
    public void onMousePressed(Point point) {
        pointDragInitial = point;
        
        super.onMousePressed(point);
    }
    
    @Override
    public void onMouseReleased(Point point) {
        if (polygoneSousSouris == null) {
            creationCarte.getPanel().placerPoint(point.x, point.y);
            
            // Dessine le nouveau point comme etant selectionne
            getPolygoneSousSouris(point.x, point.y);
        }
        else {
            updateLignesInvalides(polygoneSousSouris);
            
            if (!lignesInvalides.isEmpty()) {
                polygoneSousSouris.ypoints[indexSousSouris] = pointDragInitial.y;
                polygoneSousSouris.xpoints[indexSousSouris] = pointDragInitial.x;
                lignesInvalides.clear();
            }
            
            polygoneSousSouris.invalidate();
        }
        super.onMouseReleased(point);
    }
    
    @Override
    public void onMouseDragged(Point point) {
        if (polygoneSousSouris != null && indexSousSouris != -1) {
            polygoneSousSouris.xpoints[indexSousSouris] = point.x;
            polygoneSousSouris.ypoints[indexSousSouris] = point.y;
            
            updateLignesInvalides(polygoneSousSouris);            
        }
        super.onMouseDragged(point);
    }
    
    private void getPolygoneSousSouris(int x, int y) {
        polygoneSousSouris = null;
        indexSousSouris = -1;
        int offset = taillePoint/2 + 5;
        
        for (Polygon p : creationCarte.getPanel().getPolygones()) {
            if (!p.equals(creationCarte.getPanel().getCourant()) && p.contains(x, y)) {
                polygoneSousSouris = p;
            }
            
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
