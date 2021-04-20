/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte.mode;

import ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarte;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class Region extends Mode {
    
    private Point initial;
    private Line2D.Double line;
    private Polygon polygoneSplit;
    
    public Region(CreationCarte panel) {
        this.setCreationCarte(panel);
    }

    @Override
    public void paint(Graphics2D g) {
        paintPolygones(g);
        
        for (Polygon p : creationCarte.getPolygones()) {
            paintLignes(g, Color.black, p);
        }
        
        super.paint(g);
        
        if (line != null) {
            g.setColor(Couleurs.mesures);
            g.drawLine((int)line.x1, (int)line.y1, (int)line.x2, (int)line.y2);
            
            for (Polygon p : creationCarte.getPolygones()) {
                for (Point.Double point : getIntersections(p, line)) {
                    g.fillOval((int)point.x - 5, (int)point.y - 5, 10, 10);
                }
            }
        }
    }

    @Override
    public void onMouseDragged(Point point) {
        super.onMouseDragged(point);
        line = new Line2D.Double(initial.x, initial.y, point.x, point.y);
    }

    @Override
    public void onMousePressed(Point point) {
        super.onMousePressed(point);
        initial = point;
        line = null;
    }

    @Override
    public void onMouseReleased(Point point) {
        super.onMouseReleased(point);
        
        polygoneSplit = null;
        
        if (line == null) {
            return;
        }
        
        for (Polygon p : creationCarte.getPolygones()) {
            if (polygoneSplitLigne(p, line)) {
                polygoneSplit = p;
            }
        }
        
        if (polygoneSplit != null) {
            PolygoneDivise divise = splitPolygone(polygoneSplit, line);
            if (divise != null) {
                creationCarte.getPanel().splitPays(polygoneSplit, divise);
            }
        }
        
        line = null;
    }
    
    private boolean polygoneSplitLigne(Polygon p, Line2D.Double line) {
        int inter = 0;
        
        ArrayList<Point.Double> intersections = getIntersections(p, line);
        if (intersections.size() < 2) {
            return false;
        }
        Point.Double centre = new Point.Double((intersections.get(0).x + intersections.get(1).x) / 2, (intersections.get(0).y + intersections.get(1).y) / 2);
        
        for (Line2D.Double pLine : getPolygonLines(p)) {
            if (line.intersectsLine(pLine) && p.contains(centre)) {
                inter++;
            }
        }
        return inter == 2;
    }
    
    private ArrayList<Point.Double> getIntersections(Polygon p, Line2D.Double line) {
        ArrayList<Point.Double> intersects = new ArrayList<>();
        ArrayList<Line2D.Double> lines = getPolygonLines(p);
        for (Line2D.Double l : lines) {
            if (l.intersectsLine(line)) {
                // Line AB represented as a1x + b1y = c1 
                double a1 = line.y2 - line.y1; 
                double b1 = line.x1 - line.x2; 
                double c1 = a1*(line.x1) + b1*(line.y1); 

                // Line CD represented as a2x + b2y = c2 
                double a2 = l.y2 - l.y1; 
                double b2 = l.x1 - l.x2; 
                double c2 = a2*(l.x1)+ b2*(l.y1); 

                double determinant = a1*b2 - a2*b1;
                double x = (b2*c1 - b1*c2)/determinant; 
                double y = (a1*c2 - a2*c1)/determinant;
                intersects.add(new Point.Double(x, y));
            }
        }
        
        return intersects;
    }

    private PolygoneDivise splitPolygone(Polygon p, Line2D.Double line) {
        Polygon gauche = new Polygon();
        Polygon droit = new Polygon();
        int count = 0;
        
        ArrayList<Point.Double> intersections = getIntersections(p, line);
        if (intersections.size() != 2) {
            return null;
        }
        
        for (int i = 0; i < p.npoints; i++) {
            int next = i + 1;
            if (i == p.npoints - 1) {
                next = 0;
            }
            Line2D.Double edge = new Line2D.Double(p.xpoints[i], p.ypoints[i], p.xpoints[next], p.ypoints[next]);
            
            if (count % 2 != 0) {
                droit.addPoint(p.xpoints[i], p.ypoints[i]);
            }
            else {
                gauche.addPoint(p.xpoints[i], p.ypoints[i]);
            }
            
            if (edge.intersectsLine(line)) {
                gauche.addPoint((int)intersections.get(count).x, (int)intersections.get(count).y);
                droit.addPoint((int)intersections.get(count).x, (int)intersections.get(count).y);

                count++;
            }
        }
        
        return new PolygoneDivise(gauche, droit);
    }
    
    public class PolygoneDivise {
        private final Polygon gauche;
        private final Polygon droit;

        public PolygoneDivise(Polygon gauche, Polygon droit) {
            this.gauche = gauche;
            this.droit = droit;
        }

        public Polygon getGauche() {
            return gauche;
        }

        public Polygon getDroit() {
            return droit;
        }
    }
}
