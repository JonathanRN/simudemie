/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte.mode;

import ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarte;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class Creation extends Mode {
    
    private Polygon polygoneSousSouris;
    private int indexSousSouris;
    private Point pointDragInitial;

    public Creation(CreationCarte panel) {
        this.setCreationCarte(panel);
    }

    @Override
    public void onDesactive() {
        super.onDesactive();
        creationCarte.getPopup().setVisible(false);
    }

    @Override
    public void onActive() {
        super.onActive();
        creationCarte.getPopup().setVisible(creationCarte.getCarte().getListePays().isEmpty());
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
        
        g.setColor(Couleurs.blanc);
        for (Polygon p : creationCarte.getPolygones()) {
            if (!creationCarte.getPanel().estRegionUnique(p)) {
                paintPointPolygone(g, p);
            }
        }
        
        paintPointPolygone(g, creationCarte.getPanel().getCourant());
        
        g.setColor(Color.green);
        if (polygoneSousSouris != null && indexSousSouris != -1 && !creationCarte.getPanel().estRegionUnique(polygoneSousSouris)) {
            g.fillOval(polygoneSousSouris.xpoints[indexSousSouris] - taillePoint/2, polygoneSousSouris.ypoints[indexSousSouris] - taillePoint/2, taillePoint, taillePoint);
        }
        
        super.paint(g);
    }

    @Override
    public void onMouseMoved(Point point) {
        super.onMouseMoved(point);
        if (point != null) {
            getPolygoneSousSouris(point.x, point.y);
        }
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
            else if (indexSousSouris != -1) {
                creationCarte.getPanel().sauvegarderEtat(String.format("Déplacement point %s, %s", indexSousSouris, carte.getPays(polygoneSousSouris).getNom()));
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
        
        ArrayList<Polygon> polygones = creationCarte.getPolygones();
        polygones.add(creationCarte.getPanel().getCourant());
        
        for (Polygon p : polygones) {
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
