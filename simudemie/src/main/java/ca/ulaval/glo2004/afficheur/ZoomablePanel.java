/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import javax.swing.JPanel;

/**
 *
 * @author Jonathan
 */
public class ZoomablePanel extends JPanel {
    
    protected double zoomFactor = 1;
    private double prevZoomFactor = 1;
    private double xOffset = 0;
    private double yOffset = 0;
    private int xDiff = 0;
    private int yDiff = 0;
    private double xRel = 0;
    private double yRel = 0;
    private double zoomDiv = 1;
    private Point startPoint;
    
    private Graphics2D g2;
    
    private final double zoomMax = 3;
    private final double zoomMin = 0.2;
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        AffineTransform at = new AffineTransform();
        
        at.translate(xOffset + xDiff, yOffset + yDiff);
        at.scale(zoomFactor, zoomFactor);
        
        g2 = (Graphics2D) g;
        g2.transform(at);
    }
    
    public Point getOffset(Point point) {
        try {
            Point2D p = g2.getTransform().createInverse().transform(point, point);
            return new Point((int)p.getX(), (int)p.getY());
        } catch (java.awt.geom.NoninvertibleTransformException e) {
            System.err.println(e);
        }
        return null;
    }
    
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            if (zoomFactor * 1.1 < zoomMax) {
                zoomFactor *= 1.1;
            }
        }
        if (e.getWheelRotation() > 0) {
            if (zoomFactor / 1.1 > zoomMin) {
                zoomFactor /= 1.1;
            }
        }
        
        xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
        yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();
        
        zoomDiv = zoomFactor / prevZoomFactor;
        
        xOffset = zoomDiv * xOffset + (1 - zoomDiv) * xRel;
        yOffset = zoomDiv * yOffset + (1 - zoomDiv) * yRel;
        
        prevZoomFactor = zoomFactor;
    }

    public void mouseDragged(MouseEvent e) {
        Point curPoint = e.getPoint();
        xDiff = curPoint.x - startPoint.x;
        yDiff = curPoint.y - startPoint.y;
    }

    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
    }

    public void mouseReleased(MouseEvent e) {
        xOffset += xDiff;
        yOffset += yDiff;
        
        xDiff = 0;
        yDiff = 0;
    }
}
