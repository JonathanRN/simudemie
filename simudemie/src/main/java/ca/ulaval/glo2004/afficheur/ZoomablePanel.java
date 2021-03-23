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
import javax.swing.JPanel;

/**
 *
 * @author Jonathan
 */
public class ZoomablePanel extends JPanel {
    private double zoomFactor = 1;
    private double prevZoomFactor = 1;
    private boolean zoomer;
    private boolean dragger;
    private boolean released;
    private double xOffset = 0;
    private double yOffset = 0;
    private int xDiff = 0;
    private int yDiff = 0;
    private Point startPoint;
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        if (zoomer) {
//            AffineTransform at = new AffineTransform();
//
//            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
//            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();
//
//            double zoomDiv = zoomFactor / prevZoomFactor;
//
//            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
//            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;
//
//            at.translate(xOffset, yOffset);
//            at.scale(zoomFactor, zoomFactor);
//            prevZoomFactor = zoomFactor;
//            g2.transform(at);
//            zoomer = false;
//        }

        AffineTransform at = new AffineTransform();
        at.translate(xOffset + xDiff, yOffset + yDiff);
        at.scale(zoomFactor, zoomFactor);
        ((Graphics2D) g).transform(at);
    }
    
    public Point getOffset(Point point) {
        return new Point(point.x - (int)xOffset, point.y - (int)yOffset);
    }
    
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoomer = true;

        //Zoom in
        if (e.getWheelRotation() < 0) {
            zoomFactor *= 1.1;
        }
        //Zoom out
        if (e.getWheelRotation() > 0) {
            zoomFactor /= 1.1;
        }
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
