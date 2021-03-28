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
import java.text.ParseException;
import javax.swing.JTextField;

/**
 *
 * @author Jonathan
 */
public class Selection extends Mode {
    
    private Polygon selectionne;
    
    public Selection(CreationCarte panel) {
        super(panel);
    }

    @Override
    public void onDesactive() {
        super.onDesactive();
        //creationCarte.getInformationsPaysPanel().setVisible(false);
        creationCarte.getInformationsPanel().setVisible(false);

    }
    
    @Override
    public void onActive() {
        super.onActive();
        selectionne = null;
        //creationCarte.getInformationsPaysPanel().setVisible(true);
        creationCarte.getInformationsPanel().setVisible(false);
    }
    
    @Override
    public void onMouseReleased(Point point) {
        if (selectionne != null) {
            saveInfos(selectionne);
        }
        
        selectionne = null;
        for (Polygon p : creationCarte.getPanel().getPolygones()) {
            if (p.contains(point.x, point.y)) {
                selectionne = p;
            }
        }
        
        creationCarte.getInformationsPanel().setVisible(selectionne != null);
        
        if (selectionne != null) {
            Pays pays = creationCarte.getPanel().getPays(selectionne);
            ca.ulaval.glo2004.domaine.Region region = creationCarte.getPanel().getRegion(pays, selectionne);
            
            //JTextField paysField = creationCarte.getPaysNomField();
            //paysField.setText(pays.getNom());
            
            //creationCarte.getRegionNomField().setText(region.getNom());
            //creationCarte.getPopField().setValue(region.getPopTotale());
            //creationCarte.setPopTotaleTexte(Integer.toString(pays.getPopTotale()));
        }
        
        super.onMouseReleased(point);
    }
    
    @Override
    public void paint(Graphics2D g) {
        paintPolygones(g);
        
        for (Polygon p : creationCarte.getPanel().getPolygones()) {
            paintLignes(g, Color.black, p);
        }
        
        super.paint(g);
        
        if (selectionne != null) {
            paintLignes(g, Color.green, selectionne);
        }
    }
    
    private void saveInfos(Polygon p) {
        Pays pays = creationCarte.getPanel().getPays(p);
        ca.ulaval.glo2004.domaine.Region region = creationCarte.getPanel().getRegion(pays, p);
        
//        pays.setNom(creationCarte.getPaysNomField().getText());
//        region.setNom(creationCarte.getRegionNomField().getText());
//        try {
//            creationCarte.getPopField().commitEdit();
//            region.setPopSaine((int)creationCarte.getPopField().getValue());
//        } catch (ParseException e) {
//        }
    }
}
