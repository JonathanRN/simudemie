/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte;

import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.VoieLiaison;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 *
 * @author Jonathan
 */
public class PrisePhoto extends Mode {
    
    public PrisePhoto(Carte carte) {
        this.carte = carte;
    }

    @Override
    public void paint(Graphics2D g) {
        paintPolygones(g);
        
        for (Polygon p : carte.getPolygonesRegions()) {
            paintLignes(g, Color.black, p);
        }
        
        for (VoieLiaison voie : carte.getVoies()) {
            g.setColor(voie.getCouleur());
            g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f));
            g.draw(voie.getLigne());
        }
        
        super.paint(g);
    }
}
