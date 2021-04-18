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
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.text.ParseException;
import java.util.stream.Collectors;

/**
 *
 * @author Jonathan
 */
public class Selection extends Mode {
    
    private Polygon selectionne, precedent;
    private Rectangle2D draggedBounds;
    private InformationsPaysPanel panel;
    
    private Point updateDrag, initialDrag;
    private final int boundsOffset = 100;
    private Rectangle2D intersection;
    private int snapPosition;
    private Pays snapOrigine;
    private Pays snapDestination;
        
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
        
        if (draggedBounds != null) {
            // Snap!
            if (intersection != null) {
                switch (snapPosition) {
                    // Gauche
                    case 0:
                        translateDraggedRegions((int)intersection.getMaxX() - (int)draggedBounds.getMaxX(), 0);
                        break;
                    // Haut
                    case 1:
                        translateDraggedRegions(0, (int)intersection.getMaxY() - (int)draggedBounds.getMaxY());
                       break;
                    // Droite
                    case 2:
                        translateDraggedRegions((int)intersection.getMinX() - (int)draggedBounds.getMinX(), 0);
                        break;
                    // Bas
                    case 3:
                        translateDraggedRegions(0, (int)intersection.getMinY() - (int)draggedBounds.getMinY());
                        break;
                }
                
                if (carte.peutSnapPays(snapOrigine, snapDestination)) {
                    // Ajouter un lien terrestre, seulement s'il en a pas
                    Path2D.Double path = new Path2D.Double();

                    Point centreOrg = this.getCentrePays(snapOrigine);
                    Point centreDest = this.getCentrePays(snapDestination);

                    path.moveTo(centreOrg.x, centreOrg.y);
                    path.lineTo(centreDest.x, centreDest.y);
                    Point centreLigne = getCentreLigne(new Line2D.Double(centreOrg.x, centreOrg.y, centreDest.x, centreDest.y));
                    VoieLiaison voie = new VoieLiaison(VoieLiaison.TypeVoie.Terrestre, snapOrigine, snapDestination, path, centreLigne);

                    // Important de call ajouter voie sur la carte directement, pour ne pas creer 2 sauvegardes pour rien
                    carte.ajouterVoie(voie);
                }
                
                creationCarte.getPanel().sauvegarderEtat("Snap " + snapOrigine.getNom());
            }
            // Drag normal
            else if (initialDrag != null && updateDrag != null) {
                int deltaX = initialDrag.x - updateDrag.x;
                int deltaY = initialDrag.y - updateDrag.y;
                if (deltaX != 0 && deltaY != 0) {
                    creationCarte.getPanel().sauvegarderEtat("Déplacement " + snapOrigine.getNom());
                }
            }
            draggedBounds = null;
            intersection = null;
        }
        
        super.onMouseReleased(point);
    }

    @Override
    public void onMouseDragged(Point point) {
        for (Pays p : carte.getListePays()) {
            if (p.contient(point.x, point.y)) {
                draggedBounds = p.getBounds();
                snapOrigine = p;
                break;
            }
        }
        
        if (draggedBounds != null && updateDrag != null) {
            translateDraggedRegions(point.x - updateDrag.x, point.y - updateDrag.y);
            updateDrag = point;
            
            intersection = null;
            for (Pays p : carte.getListePays()) {
                if (!snapOrigine.equals(p)) {
                    Rectangle2D bounds = p.getBounds();
                    Rectangle2D largeBounds = new Rectangle((int)bounds.getX() - boundsOffset/2, (int)bounds.getY() - boundsOffset/2, (int)bounds.getWidth() + boundsOffset, (int)bounds.getHeight() + boundsOffset);
                    if (draggedBounds.intersects(largeBounds)) {
                        setCoteIntersection(largeBounds);
                        snapDestination = p;
                        break;
                    }
                }
            }
        }
                
        super.onMouseDragged(point);
    }
    
    private void setCoteIntersection(Rectangle2D largeBounds) {
        intersection = draggedBounds.createIntersection(largeBounds);
                        
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
        for (Pays p : carte.getListePays()) {
            if (p.contient(point.x, point.y)) {
                updateDrag = point;
                initialDrag = point;
            }
        }
        
        super.onMousePressed(point);
    }
    
    @Override
    public void paint(Graphics2D g) {
        paintPolygones(g);
        super.paint(g);
        
        if (selectionne != null) {
            paintLignes(g, Color.green, selectionne);
        }
        
        // Draw les bounds pour le snap
        if (draggedBounds != null) {
            for (Pays pays : carte.getListePays()) {
                Rectangle2D bounds = pays.getBounds();
                if (snapOrigine.equals(pays)) {
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
            if (highlight != null) {
                highlight = null;
            }
        }
    }
    
    @Override
    public void onUndo() {
        super.onUndo();
        if (creationCarte.getInformationsPanel().isVisible()) {
            creationCarte.getInformationsPanel().setVisible(false);
            selectionne = null;
            if (highlight != null) {
                highlight = null;
            }
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
        for (ca.ulaval.glo2004.domaine.Region region : snapOrigine.getListeRegions()) {
            region.getPolygone().translate(x, y);
        }
    }
}
