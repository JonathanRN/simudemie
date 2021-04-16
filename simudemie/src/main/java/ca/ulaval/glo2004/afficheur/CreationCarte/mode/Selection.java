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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
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
            // Check si tous les points du polygone qu'on drag ne se trouvent pas dans un autre polygone
            // Sauf les regions qui sont dans lui-meme
            boolean polygoneDansPolygone = false;
//            for (int i = 0; i < dragged.npoints; i++) {
//                for (Polygon p : creationCarte.getPolygones()) {
//                    if (!creationCarte.getCarte().getPays(p).getPolygone().equals(dragged) && p.contains(dragged.xpoints[i], dragged.ypoints[i])) {
//                        polygoneDansPolygone = true;
//                        break;
//                    }
//                }
//            }
            
            int deltaX = initialDrag.x - updateDrag.x;
            int deltaY = initialDrag.y - updateDrag.y;
            if (polygoneDansPolygone) {
                translateDraggedRegions(deltaX, deltaY);
            }
            else {
                // Tout est beau, on peut sauvegarder
                if (!polygoneDansPolygone && deltaX != 0 && deltaY != 0) {
                    creationCarte.getPanel().sauvegarderEtat("Déplacement " + creationCarte.getCarte().getPays(dragged).getNom());
                }
            }
        }
        
        super.onMouseReleased(point);
    }

    @Override
    public void onMouseDragged(Point point) {
        if (dragged != null) {
            translateDraggedRegions(point.x - updateDrag.x, point.y - updateDrag.y);
            updateDrag = point;            
        }
        
        super.onMouseDragged(point);
    }

    @Override
    public void onMousePressed(Point point) {
        initialDrag = null;
        updateDrag = null;
        dragged = null;
        for (Polygon p : creationCarte.getPolygones()) {
            if (p.contains(point.x, point.y)) {
                dragged = p;
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
        for (Polygon region : creationCarte.getCarte().getPays(dragged).getRegions().stream().map(a -> a.getPolygone()).collect(Collectors.toList())) {
            region.translate(x, y);
        }
    }
}
