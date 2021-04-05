/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte;

import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.VoieLiaison;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.text.ParseException;
import java.util.stream.Collectors;

/**
 *
 * @author Jonathan
 */
public class Selection extends Mode {
    
    private Polygon selectionne;
    private InformationsPaysPanel panel;
    
    public Selection(CreationCarte panel) {
        this.setCreationCarte(panel);
    }

    @Override
    public void onDesactive() {
        super.onDesactive();
        
        creationCarte.getInformationsPanel().setVisible(false);
        creationCarte.getInformationsPanel().remove(panel);
    }
    
    @Override
    public void onActive() {
        super.onActive();
        selectionne = null;
        
        panel = new InformationsPaysPanel(this);
        creationCarte.getInformationsPanel().add(panel, BorderLayout.NORTH);
        creationCarte.getInformationsPanel().setVisible(false);
    }
    
    @Override
    public void onMouseReleased(Point point) {
        if (selectionne != null) {
            try {
                saveInfos(selectionne);
            } catch (ParseException ex) {
            }
        }
        
        selectionne = null;
        for (Polygon p : creationCarte.getPolygones()) {
            if (p.contains(point.x, point.y)) {
                selectionne = p;
            }
        }
        
        creationCarte.getInformationsPanel().setVisible(selectionne != null);
        
        if (selectionne != null) {
            Pays pays = creationCarte.getPays(selectionne);
            ca.ulaval.glo2004.domaine.Region region = creationCarte.getPanel().getRegion(pays, selectionne);
            
            panel.setNomPays(pays.getNom());
            panel.setNomRegion(region.getNom());
            panel.setPopTotale(pays.getPopTotale());
            panel.setPopRegion(region.getPopTotale());
        }
        
        super.onMouseReleased(point);
    }
    
    @Override
    public void paint(Graphics2D g) {        
        for (Polygon p : creationCarte.getPolygones()) {
            g.setColor(couleurFill);
            g.fillPolygon(p);
            
            paintLignes(g, Color.black, p);
        }
        
        super.paint(g);
        
        if (selectionne != null) {
            paintLignes(g, Color.green, selectionne);
        }
    }
    
    private void saveInfos(Polygon p) throws ParseException {
        Pays pays = creationCarte.getPays(p);
        ca.ulaval.glo2004.domaine.Region region = creationCarte.getPanel().getRegion(pays, p);
        pays.setNom(panel.getNomPays());
        region.setNom(panel.getNomRegion());
        region.setPopTotale(panel.getPopRegion());
    }

    public void onPaysSupprime() {
        // todo: faire en carte action
        Pays pays = carte.getPays(selectionne);
        
        // supprime les liens également
        for (VoieLiaison voie : carte.getVoies()
                .stream()
                .filter(x -> x.getPaysOrigine().equals(pays) || x.getPaysDestination().equals(pays))
                .collect(Collectors.toList())) {
            carte.retirerVoie(voie);
        }
        
        carte.retirerPays(pays);
        
        creationCarte.getInformationsPanel().setVisible(false);
        
        selectionne = null;
        creationCarte.repaint();
    }
}
