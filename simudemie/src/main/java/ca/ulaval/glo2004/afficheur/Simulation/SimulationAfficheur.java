/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.Simulation;

import ca.ulaval.glo2004.afficheur.CreationCarte.mode.Mode;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
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
import java.util.stream.Collectors;

/**
 *
 * @author Jonathan
 */
public class SimulationAfficheur extends Mode {
    
    private int indexSelectionne;
    private Pays selectionne;
    private final Simulation simulation;
    private ArrayList<Polygon> polygones;
    private Point souris;
    private boolean afficherInfosPays = false;
    private boolean afficherLiens = true;
    private int afficherCouleurs = 1;
    
    private Region regionInfectee;
    
    private final Point sourisOffset = new Point(20, 5);
    
    public SimulationAfficheur(Simulation simulation) {
        this.simulation = simulation;
        
        if (!simulation.getScenario().estCommence()) {
            // Il faut toujours avoir une region de selectionne
            regionInfectee = simulation.getScenario().getCarteJourCourant().getPays(0).getRegions().get(0);            
        }
    }
    
    @Override
    public void paint(Graphics2D g) {
        // Rafraichit toujours la carte courante
        // dans les cas ou l'on charge une ancienne carte
        carte = simulation.getScenario().getCarteJourCourant();
        polygones = carte.getPolygonesRegions();
        
        for (Polygon p : afficherInfosPays ? carte.getListePays().stream().map(x -> x.getPolygone()).collect(Collectors.toList()) : polygones) {
            if (afficherCouleurs == 1)
                {
                    g.setColor(this.getCouleurPolygone(p, Couleurs.infections));
                }
            if (afficherCouleurs == 2)
                {
                    g.setColor(this.getCouleurPolygone(p, Couleurs.sains));
                }
            if (afficherCouleurs == 3)
                {
                    g.setColor(this.getCouleurPolygone(p, Couleurs.morts));
                }
            if (afficherCouleurs == 4)
                {
                    g.setColor(this.getCouleurPolygone(p, Couleurs.immunisations));  
                }   
            g.fillPolygon(p);
            this.paintLignes(g, Color.black, p);
        }

        super.paint(g);
        updateHighlight(souris);
        
        if (regionInfectee != null) {
            g.setColor(Couleurs.infections);
            this.paintLignes(g, Couleurs.infections, regionInfectee.getPolygone());
        }
        
        if (selectionne != null) {
            g.setStroke(new BasicStroke(2));
            this.paintLignes(g, Couleurs.immunisations, selectionne.getPolygone());
        }
        
        if(afficherLiens) {
            for (VoieLiaison voie : carte.getVoies()) {
                g.setColor(voie.getCouleur());
                g.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f));
                g.draw(voie.getLigne());
            }
        }
        
        g.setStroke(new BasicStroke(2));

        
        if (highlight != null) {
            float zoomFactor = simulation.getPanel().getZoomFactor();
            Pays pays = carte.getPays(highlight);
            if (afficherInfosPays) {
                int y = drawNom(pays.getNom(), g, zoomFactor);
                y = drawPopulation(pays.getPopTotale(), g, zoomFactor, y);
                y = drawStats(pays.getPopInfectee(),
                        pays.getPopSaine(),
                        pays.getPopDecedee(),
                        pays.getPopImmune(),
                        pays.getPourcentageInfectee(),
                        pays.getPourcentageSaine(),
                        pays.getPourcentageDecedee(),
                        pays.getPourcentageImmunisee(),
                        g,
                        zoomFactor,
                        y);
                drawFooter("Appuyez sur Q pour voir les infos. sur la région", g, zoomFactor, y);
            }
            else {
                Region region = pays.getRegion(highlight);
                int y = drawNom(region.getNom(), g, zoomFactor);
                y = drawPopulation(region.getPopTotale(), g, zoomFactor, y);
                y = drawStats(region.getPopInfectee(),
                        region.getPopSaine(),
                        region.getPopDecedee(),
                        region.getPopImmunisee(),
                        region.getPourcentageInfectee(),
                        region.getPourcentageSaine(),
                        region.getPourcentageDecedee(),
                        region.getPourcentageImmune(),
                        g,
                        zoomFactor,
                        y);
                drawFooter("Appuyez sur Q pour voir les infos. sur le pays", g, zoomFactor, y);
            }
        }
    }

    @Override
    public void onMouseMoved(Point point) {
        souris = point;
    }

    @Override
    public void onMouseReleased(Point point) {
        super.onMouseReleased(point);
        
        indexSelectionne = -1;
        selectionne = null;
        for (Polygon p : polygones) {
            if (p.contains(point.x, point.y)) {
                if (simulation.getScenario().estCommence()) {
                    selectionne = carte.getPays(p);
                    indexSelectionne = carte.getListePays().indexOf(selectionne);
                }
                else {
                    regionInfectee = carte.getPays(p).getRegion(p);
                }
            }
        }
    }
    
    @Override
    protected void updateHighlight(Point point) {
        highlight = null;
        if (point == null) {
            return;
        }
        
        for (Polygon p : afficherInfosPays ? carte.getListePays().stream().map(x -> x.getPolygone()).collect(Collectors.toList()) : polygones) {
            if (p.contains(point.x, point.y)) {
                highlight = p;
                break;
            }
        }
    }
    
    public void onSwapInformations() {
        afficherInfosPays = !afficherInfosPays;
        
        updateHighlight(souris);
    }
    
    public void onSwapLinks() {
        afficherLiens = !afficherLiens;
    }
    
    public void onSwapCouleurs() {
        if (afficherCouleurs == 1)
            {
                afficherCouleurs = 2;
                return;
            }
        if (afficherCouleurs == 2)
            {
                afficherCouleurs = 3;
                return;
            }
        if (afficherCouleurs == 3)
            {
                afficherCouleurs = 4; 
                return;
            }
        if (afficherCouleurs == 4)
            {
                afficherCouleurs = 1;
                return;
            }   
    }
    
    public int getPaysSelectionne() {
        return indexSelectionne;
    }
    
    public void onSimulationDemaree() {
        int infections = 1;
        
        regionInfectee.setPopSaine(regionInfectee.getPopSaine() - infections);
        regionInfectee.setPopInfectee(infections);
        regionInfectee = null;
    }
    
    private int drawNom(String nom, Graphics2D g, float zoomFactor) {
        g.setColor(Couleurs.blanc);
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
    
    private int drawStats(int infectes, int retablis, int morts, int immunises, float pourInf, float pourRet, float pourMorts, float pourImm, Graphics2D g, float zoomFactor, int offset) {
        float x = souris.x + sourisOffset.x / zoomFactor;
        int offsetY = (int)(offset + 20 / zoomFactor);
        
        g.drawString(String.format("%s infectés (%.2f%%)", infectes, pourInf), x, offsetY);
        g.drawString(String.format("%s sains (%.2f%%)", retablis, pourRet), x, offsetY + 20 / zoomFactor);
        g.drawString(String.format("%s morts (%.2f%%)", morts, pourMorts), x, offsetY + 40 / zoomFactor);
        g.drawString(String.format("%s immunisés (%.2f%%)", immunises, pourImm), x, offsetY + 60 / zoomFactor);
        
        return (int)(offsetY + 60 / zoomFactor);
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
    
    private Color getCouleurPolygone(Polygon p, Color color) {
        Pays pays = carte.getPays(p);
        float pourcent = 1;
   
        if (afficherInfosPays) {
            if (afficherCouleurs == 1)
                {
                    pourcent = pays.getPourcentageInfectee() / 100f;
                }
            if (afficherCouleurs == 2)
                {
                    pourcent = pays.getPourcentageSaine() / 100f;
                }
            if (afficherCouleurs == 3)
                {
                    pourcent = pays.getPourcentageDecedee() / 100f;
                }
            if (afficherCouleurs == 4)
                {
                    pourcent = pays.getPourcentageImmunisee() / 100f;   
                }   
        }
        else {
            Region region = pays.getRegion(p);
            if (afficherCouleurs == 1)
                {
                    pourcent = region.getPourcentageInfectee() / 100f;
                }
            if (afficherCouleurs == 2)
                {
                    pourcent = region.getPourcentageSaine() / 100f;
                }
            if (afficherCouleurs == 3)
                {
                    pourcent = region.getPourcentageDecedee() / 100f;
                }
            if (afficherCouleurs == 4)
                {
                    pourcent = region.getPourcentageImmune() / 100f;    
                }                 
        }
        
        Color c1 = Couleurs.remplissageNoTransp;
        Color c2 = color;
        return new Color(
            interpoler(c1.getRed(), c2.getRed(), pourcent) / 255,
            interpoler(c1.getGreen(), c2.getGreen(), pourcent) / 255,
            interpoler(c1.getBlue(), c2.getBlue(), pourcent) / 255
        );
    }
    
    private float interpoler(float v1, float v2, float p)
    {
        return (v2 - v1) * p + v1;
    }
}