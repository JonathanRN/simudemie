/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.Simulation;

import ca.ulaval.glo2004.afficheur.CreationCarte.Mode;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.Region;
import ca.ulaval.glo2004.domaine.VoieLiaison;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class SimulationAfficheur extends Mode {
    
    private final Simulation simulation;
    private ArrayList<Polygon> polygones;
    private Point souris;
    private boolean afficherInfosPays = false;
    
    private final Point sourisOffset = new Point(20, 5);
    
    public SimulationAfficheur(Simulation simulation) {
        this.simulation = simulation;
        onActive();
    }

    @Override
    public void onActive() {
        super.onActive();
        polygones = simulation.getCarte().getPolygonesRegions();
    }
    
    @Override
    public void paint(Graphics2D g) {        
        for (Polygon p : polygones) {
            g.setColor(couleurFill);
            g.fillPolygon(p);
            this.paintLignes(g, Color.black, p);
        }
        
        for (VoieLiaison voie : simulation.getCarte().getVoies()) {
            g.setColor(voie.getCouleur());
            g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f));
            g.draw(voie.getLigne());
        }
        
        g.setStroke(new BasicStroke(1));
        super.paint(g);
        
        if (highlight != null) {
            float zoomFactor = simulation.getPanel().getZoomFactor();
            Pays pays = simulation.getCarte().getPays(highlight);
            Region region = pays.getRegion(highlight);
            
            if (afficherInfosPays) {
                int y = drawNom(pays.getNom() + "/" + simulation.getCarte().getNom(), g, zoomFactor);
                y = drawPopulation(pays.getPopTotale(), g, zoomFactor, y);
                y = drawStats(pays.getPopInfectee(), pays.getPopSaine(), pays.getPopDecedee(), g, zoomFactor, y);
                drawFooter("Appuyez sur Q pour voir les infos. sur la région", g, zoomFactor, y);
            }
            else {
                int y = drawNom(region.getNom() + "/" + pays.getNom(), g, zoomFactor);
                y = drawPopulation(region.getPopTotale(), g, zoomFactor, y);
                y = drawStats(region.getPopInfectee(), region.getPopSaine(), region.getPopDecedee(), g, zoomFactor, y);
                drawFooter("Appuyez sur Q pour voir les infos. sur le pays", g, zoomFactor, y);
            }
        }
    }

    @Override
    public void onMouseMoved(Point point) {
        super.onMouseMoved(point);
        
        souris = point;
    }
    
    @Override
    protected void updateHighlight(Point point) {
        highlight = null;
        if (point == null) {
            return;
        }
        
        for (Polygon p : polygones) {
            if (p.contains(point.x, point.y)) {
                highlight = p;
                break;
            }
        }
    }
    
    public void onSwapInformations() {
        afficherInfosPays = !afficherInfosPays;
    }
    
    private int drawNom(String nom, Graphics2D g, float zoomFactor) {
        g.setColor(Color.white);
        g.setFont(FontRegister.RobotoLight.deriveFont(18f / zoomFactor));
        
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int width = metrics.stringWidth(nom);
        
        float x = souris.x + sourisOffset.x / zoomFactor;
        float y = souris.y + sourisOffset.y / zoomFactor;
        int offsetY = (int)(y + 8 / zoomFactor);
        g.drawString(nom, x, y);
        g.drawLine((int)x, offsetY, width + (int)x, offsetY);
        return offsetY;
    }
    
    private int drawPopulation(int population, Graphics2D g, float zoomFactor, int offset) {
        g.setFont(FontRegister.RobotoLight.deriveFont(14f / zoomFactor));
        
        float x = souris.x + sourisOffset.x / zoomFactor;
        int offsetY = (int)(offset + 15 / zoomFactor);
        g.drawString("Population: " + population, x, offsetY);
        return (int)offsetY;
    }
    
    private int drawStats(int infectes, int retablis, int morts, Graphics2D g, float zoomFactor, int offset) {
        float x = souris.x + sourisOffset.x / zoomFactor;
        int offsetY = (int)(offset + 20 / zoomFactor);
        
        g.drawString(String.format("%s infectés (%s%%)", infectes, 0), x, offsetY);
        g.drawString(String.format("%s sains (%s%%)", retablis, 0), x, offsetY + 20 / zoomFactor);
        g.drawString(String.format("%s morts (%s%%)", morts, 0), x, offsetY + 40 / zoomFactor);
        
        return (int)(offsetY + 40 / zoomFactor);
    }
    
    private void drawFooter(String texte, Graphics2D g, float zoomFactor, int offset) {
        float x = souris.x + sourisOffset.x / zoomFactor;
        int offsetY = (int)(offset + 25 / zoomFactor);
        g.setFont(FontRegister.RobotoLight.deriveFont(13f / zoomFactor));
        
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int width = metrics.stringWidth(texte);
        
        g.drawString(texte, x, offsetY);
        g.drawLine((int)x, (int)(offsetY + 5 / zoomFactor), width + (int)x, (int)(offsetY + 5 / zoomFactor));
    }
}
