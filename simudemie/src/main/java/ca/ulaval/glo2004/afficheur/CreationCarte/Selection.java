/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte;

import ca.ulaval.glo2004.domaine.Pays;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.text.ParseException;
import javax.swing.JTextField;

/**
 *
 * @author Jonathan
 */
public class Selection extends Mode {
    
    private Polygon selectionne;
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
            Pays pays = panel.getPays(selectionne);
            Point2D.Double centre = getCentrePolygone(pays.getPolygone());
            ca.ulaval.glo2004.domaine.Region region = panel.getRegion(pays, selectionne);
            
            JTextField paysField = panel.getPaysNomField();
            paysField.setText(pays.getNom());
            
            panel.getRegionNomField().setText(region.getNom());
            panel.getPopField().setValue(region.getPopTotale());
            panel.setPopTotaleTexte(Integer.toString(pays.getPopTotale()));
            
            anciennePos = new Point((int)centre.x - paysField.getPreferredSize().width / 2, getHighestPointY(pays.getPolygone()) - panel.getInformationsPaysPanel().getPreferredSize().height - 10);    
        }
        
        super.onMouseReleased(evt);
    }
    
    @Override
    public void paint(Graphics2D g) {
        for (Polygon p : panel.getPolygones()) {
            paintLignes(g, Color.black, p);
        }
        
        super.paint(g);
        
        if (selectionne != null) {
            paintLignes(g, Color.green, selectionne);
        }
        
        if (anciennePos != null) {
            panel.getInformationsPaysPanel().setLocation(anciennePos);
        }
    }
    
    private void saveInfos(Polygon p) {
        Pays pays = panel.getPays(p);
        ca.ulaval.glo2004.domaine.Region region = panel.getRegion(pays, p);
        
        pays.setNom(panel.getPaysNomField().getText());
        region.setNom(panel.getRegionNomField().getText());
        try {
            panel.getPopField().commitEdit();
            region.setPopSaine((int)panel.getPopField().getValue());
        } catch (ParseException e) {
        }
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
