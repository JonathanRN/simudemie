/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte.mode;

import ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarte;
import ca.ulaval.glo2004.afficheur.CreationCarte.panels.InformationsLienPanel;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.VoieLiaison;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Jonathan
 */
public class LienPays extends Mode {
    
    private InformationsLienPanel panel;
    
    private ArrayList<Pays> listePays = new ArrayList<>();
    private Point centrePaysOrigine, drag;
    
    private ArrayList<VoieLiaison> voies = new ArrayList<>();
    private ArrayList<Point> points = new ArrayList<>();
    
    private Point pointSelectionne, pointHighlight;
    private Pays origine;
    private Path2D.Double path = new Path2D.Double();
    private final ArrayList<Polygon> highlight = new ArrayList<>();
    
    public LienPays(CreationCarte panel) {
        this.setCreationCarte(panel);
        taillePoint *= 1.5;
    }

    @Override
    public void paint(Graphics2D g) {        
        paintPolygones(g);
        
        if (!highlight.isEmpty()) {
            g.setStroke(new BasicStroke(2));
            for (Polygon h : highlight) {
                paintLignes(g, couleurLigne, h);
            }
        }
        
        if (path != null) {
            g.setColor(couleurLigne);
            g.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f));
            g.draw(path);          
        }
        
        super.paint(g);
        
        for (int i = 0; i < voies.size(); i++) {
            g.setColor(Couleurs.blanc);
            g.fillOval((int)points.get(i).x - taillePoint/2, (int)points.get(i).y - taillePoint/2, taillePoint, taillePoint);
        }
        
        if (pointHighlight != null) {
            g.setColor(couleurLigne);
            g.fillOval(pointHighlight.x - taillePoint/2, pointHighlight.y - taillePoint/2, taillePoint, taillePoint);
        }
        
        if (pointSelectionne != null) {
            g.setColor(Couleurs.selectionneBorder);
            g.fillOval(pointSelectionne.x - taillePoint/2, pointSelectionne.y - taillePoint/2, taillePoint, taillePoint);
        }
    }

    @Override
    public void onMouseMoved(Point point) {
        // Ne pas call super pour redéfinir le highlight
        pointHighlight = null;
        for (Point p : points) {
            if (p.distance(point) <= taillePoint / 2) {
                pointHighlight = p;
                break;
            }
        }
        
        highlight.clear();
        for (Pays pays : carte.getListePays()) {
            if (pays.contient(point.x, point.y)) {
                highlight.addAll(pays.getListeRegions().stream().map(x -> x.getPolygone()).collect(Collectors.toList()));
            }
        }
    }

    @Override
    public void onMousePressed(Point point) {
        super.onMousePressed(point);
        path = null;
        centrePaysOrigine = null;
        for (Polygon p : carte.getPolygonesRegions()) {
            if (p.contains(point)) {
                Pays pays = carte.getPays(p);
                Point centre = getCentrePays(pays);
                centrePaysOrigine = centre;
                origine = pays;
            }
        }
        
        drag = null;
        for (Point p : points) {
            if (p.distance(point) <= taillePoint / 2) {
                drag = p;
                return;
            }
        }
    }

    @Override
    public void onMouseDragged(Point point) {
        super.onMouseDragged(point);
        if (centrePaysOrigine != null) {
            path = new Path2D.Double();
            path.moveTo(centrePaysOrigine.x, centrePaysOrigine.y);
            path.lineTo(point.x, point.y);
        }
        
        if (drag != null) {
            creationCarte.getInformationsPanel().setVisible(false);
            
            VoieLiaison voie = voies.get(points.indexOf(drag));
            Path2D.Double ligne = new Path2D.Double();
            Point centre = getCentrePays(voie.getPaysOrigine());
            ligne.moveTo(centre.x, centre.y);
            ligne.curveTo(point.x, point.y, point.x, point.y, voie.getLigne().getCurrentPoint().getX(), voie.getLigne().getCurrentPoint().getY());
            
            voie.setLigne(ligne);
            voie.setCentre(point);
            drag.setLocation(point);            
        }
    }

    @Override
    public void onMouseReleased(Point point) {
        super.onMouseReleased(point);
        
        pointSelectionne = null;
        for (Point p : points) {
            if (p.distance(point) <= taillePoint / 2) {
                pointSelectionne = p;
                break;
            }
        }
        
        creationCarte.getInformationsPanel().setVisible(pointSelectionne != null);
        if (pointSelectionne != null) {
            VoieLiaison voie = voies.get(points.indexOf(pointSelectionne));
            panel.setLien(voie);
        }
        
        if (centrePaysOrigine == null) {
            return;
        }
        
        path = null;
        for (Pays destination : listePays) {
            ArrayList<VoieLiaison.TypeVoie> nonUtilisees = carte.getVoiesDisponibles(origine, destination);
            
            if (!origine.equals(destination) && destination.contient(point.x, point.y) && !nonUtilisees.isEmpty()) {
                Point centre = getCentrePays(destination);
                path = new Path2D.Double();
                path.moveTo(centrePaysOrigine.x, centrePaysOrigine.y);
                path.lineTo(centre.x, centre.y);
                
                Point centreLigne = this.getCentreLigne(new Line2D.Double(centrePaysOrigine.x, centrePaysOrigine.y, centre.x, centre.y));
                
                VoieLiaison voie = new VoieLiaison(nonUtilisees.get(0), origine, destination, path, centreLigne);
                creationCarte.getPanel().ajouterLien(voie);
                
                path = null;
                
                updateVoies();
                updatePoints();
                break;
            }
        }
    }
    
    @Override
    public void onActive() {
        listePays = carte.getListePays();
        
        creationCarte.getInformationsPanel().setVisible(false);
        
        panel = new InformationsLienPanel(carte, this, creationCarte.getPanel());
        creationCarte.getInformationsPanel().add(panel, BorderLayout.NORTH);
        
        // Update toutes les voies dans le cas ou les points on bouges
        updateVoies();
        updatePoints();
    }

    @Override
    public void onDesactive() {
        pointSelectionne = null;
        creationCarte.getInformationsPanel().setVisible(false);
        creationCarte.getInformationsPanel().remove(panel);
    }

    @Override
    public void onRedo() {
        super.onRedo();
        updateVoies();
        updatePoints();
    }

    @Override
    public void onUndo() {
        super.onUndo();
        
        if (creationCarte.getInformationsPanel().isVisible()) {
            creationCarte.getInformationsPanel().setVisible(false);
            pointSelectionne = null;
        }
        updateVoies();
        updatePoints();
    }
    
    public void onLienSupprime(VoieLiaison lien) {
        carte.retirerVoie(lien);
        creationCarte.getInformationsPanel().setVisible(false);
        
        updateVoies();
        updatePoints();
        
        pointSelectionne = null;
        creationCarte.getPanel().sauvegarderEtat("Suppression lien " + lien.getPaysOrigine().getNom() + " -> " + lien.getPaysDestination().getNom());
    }
    
    private void updateVoies() {
        voies = carte.getVoies();
    }
    
    private void updatePoints() {
        points.clear();
        for (VoieLiaison voie : voies) {
            points.add(voie.getCentre());
        }
    }
}
