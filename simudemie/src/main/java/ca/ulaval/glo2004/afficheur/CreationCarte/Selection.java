/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jonathan
 */
public class Selection extends Mode {
    
    private Polygon selectionne;
    
    public Selection(CreationCartePanel panel) {
        super(panel);
    }

    @Override
    public void onMouseReleased(MouseEvent evt) {
        selectionne = null;
        for (Polygon p : panel.getPolygones()) {
            if (p.contains(evt.getX(), evt.getY())) {
                selectionne = p;
            }
        }
        
        super.onMousePressed(evt);
    }
    
    @Override
    public void paint(Graphics2D g) {
        super.paint(g);
        if (selectionne != null) {
            paintLignes(g, Color.green, selectionne);
        }
    }
}
