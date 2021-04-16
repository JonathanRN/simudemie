/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte.mode;

import ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarte;
import ca.ulaval.glo2004.afficheur.CreationCarte.panels.InformationsPaysPanel;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.VoieLiaison;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.text.ParseException;
import java.util.stream.Collectors;

/**
 *
 * @author Jonathan
 */
public class Selection extends Mode {
    
    private Polygon selectionne, precedent, dragged;
    private InformationsPaysPanel panel;
    
    private Point updateDrag, initialDrag;
    private final int boundsOffset = 100;
    private Rectangle2D intersection;
    private int snapPosition;
        
    public Selection(CreationCarte panel) {
        this.setCreationCarte(panel);
    }

    @Override
    public void onDesactive() {
        super.onDesactive();
        
        precedent = selectionne;
        if (precedent != null) {
            panel.Desactive();
        }
        creationCarte.getInformationsPanel().setVisible(false);
        creationCarte.getInformationsPanel().remove(panel);
    }
    
    @Override
    public void onActive() {
        super.onActive();
        selectionne = null;
        
        panel = new InformationsPaysPanel(this);
        creationCarte.getInformationsPanel().add(panel, BorderLayout.NORTH);
        creationCarte.getInformationsPanel().setVisible(false);
    }
    
    @Override
    public void onMouseReleased(Point point) {
        precedent = selectionne;
        
        selectionne = null;
        for (Polygon p : creationCarte.getPolygones()) {
            if (p.contains(point.x, point.y)) {
                selectionne = p;
            }
        }
        
        creationCarte.getInformationsPanel().setVisible(selectionne != null);
        
        if (selectionne != null) {
            if (precedent != null && !precedent.equals(selectionne)) {
                // Force un check de sauvegarde dans le cas ou l'on click pays -> pays
                panel.Desactive();
            }
            
            Pays pays = creationCarte.getPays(selectionne);
            ca.ulaval.glo2004.domaine.Region region = creationCarte.getPanel().getRegion(pays, selectionne);
            panel.Active(pays, region);
        }
        else if (precedent != null) {
            panel.Desactive();
        }
        
        if (dragged != null) {
            // Snap!
            if (intersection != null) {
                switch (snapPosition) {
                    // Gauche
                    case 0:
                        translateDraggedRegions((int)intersection.getMaxX() - (int)dragged.getBounds2D().getMaxX(), 0);
                        break;
                    // Haut
                    case 1:
                        translateDraggedRegions(0, (int)intersection.getMaxY() - (int)dragged.getBounds2D().getMaxY());
                       break;
                    // Droite
                    case 2:
                        translateDraggedRegions((int)intersection.getMinX() - (int)dragged.getBounds2D().getMinX(), 0);
                        break;
                    // Bas
                    case 3:
                        translateDraggedRegions(0, (int)intersection.getMinY() - (int)dragged.getBounds2D().getMinY());
                        break;
                }
                creationCarte.getPanel().sauvegarderEtat("Snap " + creationCarte.getCarte().getPays(dragged).getNom());
            }
            // Drag normal
            else {
                int deltaX = initialDrag.x - updateDrag.x;
                int deltaY = initialDrag.y - updateDrag.y;
                if (deltaX != 0 && deltaY != 0) {
                    creationCarte.getPanel().sauvegarderEtat("Déplacement " + creationCarte.getCarte().getPays(dragged).getNom());
                }
            }
            dragged = null;
            intersection = null;
        }
        
        super.onMouseReleased(point);
    }

    @Override
    public void onMouseDragged(Point point) {
        if (dragged != null) {
            translateDraggedRegions(point.x - updateDrag.x, point.y - updateDrag.y);
            updateDrag = point;
            
            intersection = null;
            for (Pays p : carte.getListePays()) {
                if (!carte.getPays(dragged).estEgal(p)) {
                    Rectangle2D bounds = p.getPolygone().getBounds();
                    Rectangle2D largeBounds = new Rectangle((int)bounds.getX() - boundsOffset/2, (int)bounds.getY() - boundsOffset/2, (int)bounds.getWidth() + boundsOffset, (int)bounds.getHeight() + boundsOffset);
                    if (dragged.getBounds2D().intersects(largeBounds)) {
                        setCoteIntersection(largeBounds);
                    }
                }
            }
        }
        
        super.onMouseDragged(point);
    }
    
    private void setCoteIntersection(Rectangle2D largeBounds) {
        intersection = dragged.getBounds2D().createIntersection(largeBounds);
                        
        // Trouver le cote le plus pres de lintersection
        // 0 = left, 1 = top, 2 = right, 3 = down
        snapPosition = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            int val = Math.abs((int)intersection.getCenterX() - (int)largeBounds.getMinX());
            switch (i) {
                case 1:
                    val = Math.abs((int)intersection.getCenterY() - (int)largeBounds.getMinY());
                    break;
                case 2:
                    val = Math.abs((int)intersection.getCenterX() - (int)largeBounds.getMaxX());
                    break;
                case 3:
                    val = Math.abs((int)intersection.getCenterY() - (int)largeBounds.getMaxY());
                    break;
            }
            if (val < min) {
                min = val;
                snapPosition = i;
            }
        }

        switch (snapPosition) {
            // Gauche
            case 0:
                intersection = new Rectangle((int)largeBounds.getMinX(), (int)largeBounds.getMinY(), boundsOffset / 2, (int)largeBounds.getHeight());
                break;
            // Haut
            case 1:
                intersection = new Rectangle((int)largeBounds.getMinX(), (int)largeBounds.getMinY(),(int)largeBounds.getWidth(), boundsOffset / 2);
               break;
            // Droite
            case 2:
                intersection = new Rectangle((int)largeBounds.getMaxX() - boundsOffset / 2, (int)largeBounds.getMinY(), boundsOffset / 2, (int)largeBounds.getHeight());
                break;
            // Bas
            case 3:
                intersection = new Rectangle((int)largeBounds.getMinX(), (int)largeBounds.getMaxY() - boundsOffset / 2,(int)largeBounds.getWidth(), boundsOffset / 2);
                break;
        }
    }

    @Override
    public void onMousePressed(Point point) {
        initialDrag = null;
        updateDrag = null;
        dragged = null;
        for (Pays p : carte.getListePays()) {
            if (p.getPolygone().contains(point.x, point.y)) {
                dragged = p.getPolygone();
                updateDrag = point;
                initialDrag = point;
            }
        }
        
        super.onMousePressed(point);
    }
    
    @Override
    public void paint(Graphics2D g) {        
        for (Polygon p : creationCarte.getPolygones()) {
            g.setColor(couleurRempli);
            g.fillPolygon(p);
            
            paintLignes(g, Color.black, p);
        }
        
        super.paint(g);
        
        if (selectionne != null) {
            paintLignes(g, Color.green, selectionne);
        }
        
        // Draw les bounds pour le snap
        if (dragged != null) {
            for (Pays p : carte.getListePays()) {
                Rectangle2D bounds = p.getPolygone().getBounds2D();
                if (carte.getPays(dragged).estEgal(p)) {
                    g.setColor(Color.green);
                    g.setStroke(new BasicStroke(2f));
                    g.drawRect((int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight());
                }
                else {
                    g.setColor(Color.yellow);
                    g.setStroke(new BasicStroke(5f));
                    g.drawRect((int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight());
                    g.setStroke(new BasicStroke(2f));
                    g.drawRect((int)bounds.getX() - boundsOffset/2, (int)bounds.getY() - boundsOffset/2, (int)bounds.getWidth() + boundsOffset, (int)bounds.getHeight() + boundsOffset);
                }
            }
        }
        
        if (intersection != null) {
            g.setColor(new Color(255, 255, 255, 100));
            g.fillRect((int)intersection.getX(), (int)intersection.getY(), (int)intersection.getWidth(), (int)intersection.getHeight());
        }
    }

    @Override
    public void onRedo() {
        super.onRedo();
        if (creationCarte.getInformationsPanel().isVisible()) {
            creationCarte.getInformationsPanel().setVisible(false);
            selectionne = null;
        }
    }
    
    @Override
    public void onUndo() {
        super.onUndo();
        if (creationCarte.getInformationsPanel().isVisible()) {
            creationCarte.getInformationsPanel().setVisible(false);
            selectionne = null;
        }
    }
    
    public void saveInfos() throws ParseException {
        if (precedent != null) {
            Pays pays = creationCarte.getPays(precedent);
            ca.ulaval.glo2004.domaine.Region region = creationCarte.getPanel().getRegion(pays, precedent);

            pays.setNom(panel.getNomPays());
            region.setNom(panel.getNomRegion());
            try {
                region.setPopTotale(panel.getPopRegion());
            } catch (ParseException ex) {
            }
            creationCarte.getPanel().sauvegarderEtat("Changement infos " + region.getNom());
        }
    }

    public void onPaysSupprime() {
        Pays pays = carte.getPays(selectionne);
        
        // supprime les liens également
        for (VoieLiaison voie : carte.getVoies()
                .stream()
                .filter(x -> x.getPaysOrigine().equals(pays) || x.getPaysDestination().equals(pays))
                .collect(Collectors.toList())) {
            carte.retirerVoie(voie);
        }
        
        carte.retirerPays(pays);
        
        creationCarte.getPanel().sauvegarderEtat("Suppression " + pays.getNom());
        
        creationCarte.getInformationsPanel().setVisible(false);
        
        selectionne = null;
        creationCarte.repaint();
    }
    
    private void translateDraggedRegions(int x, int y) {
        // Il faut parcourir toutes les regions du pays pour le deplacer
        Pays pays = creationCarte.getCarte().getPays(dragged);
        pays.getPolygone().translate(x, y);
        for (Polygon region : pays.getRegions().stream().map(a -> a.getPolygone()).collect(Collectors.toList())) {
            region.translate(x, y);
        }
    }
}
