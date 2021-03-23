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
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class Mode {
    private Polygon highlight;
    
    protected final int taillePoint = 20;
    protected final Color couleurLigne = new Color(136, 192, 208);
    protected final CreationCarte creationCarte;
    protected ArrayList<Line2D.Double> lignesInvalides = new ArrayList<>(); 

    public Mode(CreationCarte panel) {
        this.creationCarte = panel;
    }
    
    public void paint(Graphics2D g) {
        if (highlight != null) {
            paintLignes(g, couleurLigne, highlight);
        }
        
        paintLignes(g, Color.red, lignesInvalides);
    }
    
    public void onActive() {
        
    }
    
    public void onDesactive() {
        
    }
    
    public void onMousePressed(Point point) {
        
    }
    
    public void onMouseReleased(Point point) {
    }
    
    public void onMouseDragged(Point point) {
    }
    
    public void onKeyReleased(KeyEvent evt) {
        if (creationCarte.getPanel().getCourant().npoints >= 3 && evt.getKeyCode() == KeyEvent.VK_SPACE) {
            creationCarte.getPanel().creerPolygone();
        }
    }
    
    public void onMouseMoved(Point point) {
        updateHighlight(point);
    }
    
    public boolean estPolygoneValide(Polygon g) {
        updateLignesInvalides(g);
        return lignesInvalides.isEmpty();
    }
    
    protected void paintPointPolygone(Graphics2D g, Polygon p) {
        if (p != null) {
            for (int i = 0; i < p.npoints; i++) {
                g.fillOval(p.xpoints[i] - taillePoint/2, p.ypoints[i] - taillePoint/2, taillePoint, taillePoint);
                g.drawString(Integer.toString(i), p.xpoints[i] - taillePoint/2, p.ypoints[i] - taillePoint/2 - 10);
            }
        }
    }
    
    protected void paintLignes(Graphics2D g, Color c, ArrayList<Line2D.Double> lignes) {
        g.setColor(c);
        for(Line2D.Double line : lignes) {
            g.drawLine((int)line.x1, (int)line.y1, (int)line.x2, (int)line.y2);
        }
    }
    
    protected void paintLignes(Graphics2D g, Color c, Polygon p) {
        g.setColor(c);
        for(Line2D.Double line : getPolygonLines(p)) {
            g.drawLine((int)line.x1, (int)line.y1, (int)line.x2, (int)line.y2);
        }
    }
    
    private ArrayList<Line2D.Double> getLignesInvalides(Polygon p) {
        ArrayList<Line2D.Double> invalides = new ArrayList<>();
        
        for (Line2D.Double line : getPolygonLines(p)) {
            for (int x = 0; x < creationCarte.getPanel().getPolygones().size(); x++) {
                ArrayList<Line2D.Double> linesP2 = getPolygonLines(creationCarte.getPanel().getPolygones().get(x));
                for (Line2D.Double lineP2 : linesP2) {
                    if (line.intersectsLine(lineP2) &&
                        line.x1 != lineP2.x1 &&
                        line.x2 != lineP2.x2 &&
                        line.y1 != lineP2.y1 &&
                        line.y2 != lineP2.y2 &&
                        line.x1 != lineP2.x2 &&
                        line.y1 != lineP2.y2 &&
                        lineP2.x1 != line.x2 &&
                        lineP2.y1 != line.y2) {
                        
                        invalides.add(line);
                        invalides.add(lineP2);
                    }
                }
            }
        }
        return invalides;
    }
    
    protected void updateLignesInvalides(Polygon g) {
        lignesInvalides.clear();
        lignesInvalides = getLignesInvalides(g);
    }
    
    protected ArrayList<Line2D.Double> getPolygonLines(Polygon po) {
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
                areaSegments.add(new Line2D.Double(currentElement[1], currentElement[2], nextElement[1], nextElement[2]));
            } else if (nextElement[0] == PathIterator.SEG_CLOSE && areaPoints.size() > 3) {
                areaSegments.add(new Line2D.Double(currentElement[1], currentElement[2], start[1], start[2]));
            }
        }
        return areaSegments;
    }

    private void updateHighlight(Point point) {
        highlight = null;
        for (Polygon p : creationCarte.getPanel().getPolygones()) {
            if (p.contains(point.x, point.y)) {
                highlight = p;
                return;
            }
        }
    }

    protected Point getCentrePolygone(Polygon p) {
        double x = 0.;
        double y = 0.;
        for (int i = 0; i < p.npoints; i++){
            x += p.xpoints[i];
            y += p.ypoints[i];
        }

        x = x / p.npoints;
        y = y / p.npoints;

        return creationCarte.getPanel().getOffset(new Point((int)x, (int)y));
    }
}