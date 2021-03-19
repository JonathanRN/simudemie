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
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import javax.swing.JTextField;

/**
 *
 * @author Jonathan
 */
public class Selection extends Mode {
    
    private Polygon selectionne, precedent;
    private Point anciennePos;
    
    public Selection(CreationCartePanel panel) {
        super(panel);
    }

    @Override
    public void onDesactive() {
        super.onDesactive();
        panel.getInformationsPaysPanel().setVisible(false);
    }
    
    @Override
    public void onActive() {
        super.onActive();
        selectionne = null;
        panel.getInformationsPaysPanel().setVisible(false);
    }
    
    @Override
    public void onMouseReleased(MouseEvent evt) {
        if (selectionne != null) {
            saveInfos(selectionne);
        }
        
        selectionne = null;
        for (Polygon p : panel.getPolygones()) {
            if (p.contains(evt.getX(), evt.getY())) {
                selectionne = p;
            }
        }
        
        panel.getInformationsPaysPanel().setVisible(selectionne != null);
        
        if (selectionne != null) {
            Point2D.Double centre = getCentrePolygone(selectionne);
            
            JTextField field = panel.getNomField();
            
            field.setText(panel.getNomPays(selectionne));
            panel.getPopField().setValue(panel.getPopTotale(selectionne));
            
            anciennePos = new Point((int)centre.x - field.getPreferredSize().width / 2, getHighestPointY(selectionne) - panel.getInformationsPaysPanel().getPreferredSize().height - 10);    
        }
    }
    
    @Override
    public void paint(Graphics2D g) {
        super.paint(g);
        if (selectionne != null) {
            paintLignes(g, Color.green, selectionne);
        }
        
        if (anciennePos != null) {
            panel.getInformationsPaysPanel().setLocation(anciennePos);
        }
    }
    
    private void saveInfos(Polygon g) {
        panel.setNomPays(panel.getNomField().getText(), g);
        panel.setPopulationTotale((int)panel.getPopField().getValue(), g);
    }
    
    private Point2D.Double getCentrePolygone(Polygon p) {
        double x = 0.;
        double y = 0.;
        for (int i = 0; i < p.npoints; i++){
            x += p.xpoints[i];
            y += p.ypoints[i];
        }

        x = x / p.npoints;
        y = y / p.npoints;

        return new Point2D.Double(x, y);
    }
    
    private int getHighestPointY(Polygon p) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < p.npoints; i++){
            if (p.ypoints[i] < min) {
                min = p.ypoints[i];
            }
        }
        return min;
    }
}
