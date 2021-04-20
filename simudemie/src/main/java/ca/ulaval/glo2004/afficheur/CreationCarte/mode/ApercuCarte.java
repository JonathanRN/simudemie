/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte.mode;

import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.VoieLiaison;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 *
 * @author Jonathan
 */
public class ApercuCarte extends Mode {
    
    public int afficherCouleurs = 1;
    public boolean modeOnglet = false;
    
    public ApercuCarte(Carte carte) {
        this.carte = carte;
    }
    
    public void setMode(boolean mode) { 
        modeOnglet = mode;
    }
    
    @Override
    public void paint(Graphics2D g) {
        
        if (modeOnglet){
            Color couleur = Couleurs.infections;
            for (Pays pays : carte.getListePays()) {
                switch (afficherCouleurs) {
                    case 2:
                       couleur = Couleurs.sains;
                        break;
                    case 3:
                       couleur = Couleurs.morts;
                        break;
                    case 4:
                       couleur = Couleurs.immunisations;
                        break;
                    }

                for (ca.ulaval.glo2004.domaine.Region r : pays.getListeRegions()) {
                    g.setColor(this.getCouleurPolygone(r, couleur));
                    g.fillPolygon(r.getPolygone());
                    paintLignes(g, Color.black, r.getPolygone());
                }
            }  
        }
        else {
            paintPolygones(g);
        }
        for (Polygon p : carte.getPolygonesRegions()) {
            paintLignes(g, Color.black, p);
        }
        
        for (VoieLiaison voie : carte.getVoies()) {
            g.setColor(voie.getCouleur());
            g.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f));
            g.draw(voie.getLigne());
        }
        
        super.paint(g);
    }
    
    private Color getCouleurPolygone(ca.ulaval.glo2004.domaine.Region region, Color color) {
        float pourcent = 1;
        switch (afficherCouleurs) {
            case 1:
                pourcent = region.getPourcentageInfectee() / 100f;
                break;
            case 2:
                pourcent = region.getPourcentageSaine() / 100f;
                break;
            case 3:
                pourcent = region.getPourcentageDecedee() / 100f;
                break;
            case 4:
                pourcent = region.getPourcentageImmune() / 100f;
                break;
        }

        Color c1 = Couleurs.remplissageNoTransp;
        Color c2 = color;
        return new Color(
            interpoler(c1.getRed(), c2.getRed(), pourcent) / 255,
            interpoler(c1.getGreen(), c2.getGreen(), pourcent) / 255,
            interpoler(c1.getBlue(), c2.getBlue(), pourcent) / 255
        );
    }
   
    private float interpoler(float v1, float v2, float p) {
        return (v2 - v1) * p + v1;
    }
}
