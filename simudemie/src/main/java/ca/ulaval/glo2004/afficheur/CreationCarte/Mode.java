/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class Mode {
    private Polygon highlight;
    
    protected final int taillePoint = 20;
    protected final Color couleurLigne = new Color(136, 192, 208);
    protected final CreationCartePanel panel;
    protected ArrayList<Line2D.Double> lignesInvalides = new ArrayList<>(); 

    public Mode(CreationCartePanel panel) {
        this.panel = panel;
    }
    
    public void paint(Graphics2D g) {
        paintLignes(g, Color.red, lignesInvalides);
        
        if (highlight != null) {
            paintLignes(g, couleurLigne, highlight);
        }
    }
    
    public void onActive() {
        
    }
    
    public void onDesactive() {
        
    }
    
    public void onMousePressed(MouseEvent evt) {
        
    }
    
    public void onMouseReleased(MouseEvent evt) {
    }
    
    public void onMouseDragged(MouseEvent evt) {
    }
    
    public void onKeyReleased(KeyEvent evt) {
        if (panel.getCourant().npoints >= 3 && evt.getKeyCode() == KeyEvent.VK_SPACE) {
            panel.creerPolygone();
        }
    }
    
    public void onMouseMoved(MouseEvent evt) {
        updateHighlight(evt);
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
            for (int x = 0; x < panel.getPolygones().size(); x++) {
                ArrayList<Line2D.Double> linesP2 = getPolygonLines(panel.getPolygones().get(x));
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
    
    private ArrayList<Line2D.Double> getPolygonLines(Polygon po) {
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

    private void updateHighlight(MouseEvent evt) {
        highlight = null;
        for (Polygon p : panel.getPolygones()) {
            if (p.contains(evt.getX(), evt.getY())) {
                highlight = p;
                return;
            }
        }
    }
}