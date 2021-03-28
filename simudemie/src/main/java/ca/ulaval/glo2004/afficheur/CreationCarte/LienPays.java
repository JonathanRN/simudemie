/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte;

import ca.ulaval.glo2004.afficheur.carteActions.AjouterLienAction;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.VoieLiaison;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
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
    private Point initial, initialDrag;
    
    private ArrayList<VoieLiaison> voies = new ArrayList<>();
    private ArrayList<Point> points = new ArrayList<>();
    
    private Point pointSelectionne, pointHighlight;
    private Pays origine;
    private Path2D.Double path = new Path2D.Double();
    private Polygon highlight;
    
    public LienPays(CreationCarte panel) {
        super(panel);
        taillePoint *= 1.5;
    }

    @Override
    public void paint(Graphics2D g) {        
        for (Pays pays : listePays) {
            Polygon p = pays.getPolygone();
            g.setColor(couleurFill);
            g.fillPolygon(p);
            g.setStroke(new BasicStroke(1));
            paintLignes(g, Color.black, p);
        }
        
        if (highlight != null) {
            g.setStroke(new BasicStroke(1));
            paintLignes(g, couleurLigne, highlight);
        }
        
        if (path != null) {
            // todo couleur du type selectionne
            g.setColor(couleurLigne);
            g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f));
            g.draw(path);          
        }
        
        for (int i = 0; i < voies.size(); i++) {
            g.setColor(voies.get(i).getCouleur());
            g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f));
            g.draw(voies.get(i).getLigne());
            
            g.setColor(Color.white);
            g.fillOval((int)points.get(i).x - taillePoint/2, (int)points.get(i).y - taillePoint/2, taillePoint, taillePoint);
        }
        
        if (pointHighlight != null) {
            g.setColor(couleurLigne);
            g.fillOval(pointHighlight.x - taillePoint/2, pointHighlight.y - taillePoint/2, taillePoint, taillePoint);
        }
        
        if (pointSelectionne != null) {
            g.setColor(Color.green);
            g.fillOval(pointSelectionne.x - taillePoint/2, pointSelectionne.y - taillePoint/2, taillePoint, taillePoint);
        }
        
        super.paint(g);
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
        
        highlight = null;
        for (Polygon p : listePays.stream().map(x -> x.getPolygone()).collect(Collectors.toList())) {
            if (p.contains(point)) {
                highlight = p;
                break;
            }
        }
    }

    @Override
    public void onMousePressed(Point point) {
        super.onMousePressed(point);
        path = null;
        initial = null;
        for (Pays pays : listePays) {
            Polygon p = pays.getPolygone();
            if (p.contains(point)) {
                Point centre = getCentrePolygone(p);
                initial = centre;
                origine = pays;
            }
        }
        
        initialDrag = null;
        for (Point p : points) {
            if (p.distance(point) <= taillePoint / 2) {
                initialDrag = p;
                return;
            }
        }
    }

    @Override
    public void onMouseDragged(Point point) {
        super.onMouseDragged(point);
        if (initial != null) {
            path = new Path2D.Double();
            path.moveTo(initial.x, initial.y);
            path.lineTo(point.x, point.y);
        }
        
        if (initialDrag != null) {
            creationCarte.getInformationsPanel().setVisible(false);
            
            VoieLiaison voie = voies.get(points.indexOf(initialDrag));
            Path2D.Double ligne = new Path2D.Double();
            Point centre = getCentrePolygone(voie.getPaysOrigine().getPolygone());
            ligne.moveTo(centre.x, centre.y);
            ligne.curveTo(point.x, point.y, point.x, point.y, voie.getLigne().getCurrentPoint().getX(), voie.getLigne().getCurrentPoint().getY());
            
            voie.setLigne(ligne);
            voie.setCentre(point);
            initialDrag.setLocation(point);
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
        
        if (initial == null) {
            return;
        }
        
        path = null;
        for (Pays destination : listePays) {
            Polygon p = destination.getPolygone();
            ArrayList<VoieLiaison.TypeVoie> nonUtilisees = carte.getVoiesDisponibles(origine, destination);
            
            if (!origine.equals(destination) && p.contains(point) && !nonUtilisees.isEmpty()) {
                Point centre = getCentrePolygone(p);
                path = new Path2D.Double();
                path.moveTo(initial.x, initial.y);
                path.lineTo(centre.x, centre.y);
                
                Point centreLigne = this.getCentreLigne(new Line2D.Double(initial.x, initial.y, centre.x, centre.y));
                
                VoieLiaison voie = new VoieLiaison(nonUtilisees.get(0), origine, destination, path, centreLigne);
                AjouterLienAction action = new AjouterLienAction(voie, carte);
                creationCarte.getPanel().ajouterAction(action);
                
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
        
        panel = new InformationsLienPanel(carte, this);
        creationCarte.getInformationsPanel().add(panel, BorderLayout.NORTH);
        
        // Update toutes les voies dans le cas ou les points on bouges
        for (VoieLiaison voie : carte.getVoies()) {
            Path2D.Double ligne = new Path2D.Double();
            Point centreOrigine = getCentrePolygone(voie.getPaysOrigine().getPolygone());
            Point centreDestination = getCentrePolygone(voie.getPaysDestination().getPolygone());
            
            ligne.moveTo(centreOrigine.x, centreOrigine.y);
            ligne.curveTo(voie.getCentre().x, voie.getCentre().y, voie.getCentre().x, voie.getCentre().y, centreDestination.x, centreDestination.y);

            voie.setLigne(ligne);
        }
    }

    @Override
    public void onDesactive() {
        pointSelectionne = null;
        creationCarte.getInformationsPanel().setVisible(false);
        creationCarte.getInformationsPanel().remove(panel);
    }
    
    public void onLienSupprime(VoieLiaison lien) {
        // todo: faire en carte action
        carte.retirerVoie(lien);
        creationCarte.getInformationsPanel().setVisible(false);
        
        updateVoies();
        updatePoints();
        
        pointSelectionne = null;
        
        creationCarte.repaint();
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
    
    private Point getCentrePolygone(Polygon p) {
        double x = 0.;
        double y = 0.;
        for (int i = 0; i < p.npoints; i++){
            x += p.xpoints[i];
            y += p.ypoints[i];
        }

        x = x / p.npoints;
        y = y / p.npoints;

        return new Point((int)x, (int)y);
    }
    
    private Point getCentreLigne(Line2D.Double line) {
        return new Point(((int)line.x1 + (int)line.x2) / 2, ((int)line.y1 + (int)line.y2) / 2);
    }
}
