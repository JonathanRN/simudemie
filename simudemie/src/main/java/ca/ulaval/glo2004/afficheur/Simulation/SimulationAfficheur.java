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
    private final Simulation simulation;
    private Point souris;
    private boolean afficherInfosPays = false;
    private boolean afficherLiens = true;
    private int afficherCouleurs = 1;
    private final ArrayList<Polygon> highlight = new ArrayList<>();
    private final ArrayList<Polygon> selectionne = new ArrayList<>();
    
    private Pays paysHighlight;
    private Region regionHighlight;
    private Region regionInfectee;
    
    private final Point sourisOffset = new Point(20, 5);
    
    public SimulationAfficheur(Simulation simulation) {
        this.simulation = simulation;
        
        if (!simulation.getScenario().estCommence()) {
            // Il faut toujours avoir une region de selectionne
            regionInfectee = simulation.getScenario().getCarteJourCourant().getPays(0).getListeRegions().get(0);            
        }
    }
    
    @Override
    public void paint(Graphics2D g) {
        // Rafraichit toujours la carte courante
        // dans les cas ou l'on charge une ancienne carte
        carte = simulation.getScenario().getCarteJourCourant();
        updateHighlight(souris);
        
        for (Pays pays : carte.getListePays()) {
            Color couleur = Couleurs.infections;
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
            for (Region r : pays.getListeRegions()) {
                if (afficherInfosPays) {
                    g.setColor(this.getCouleurPolygone(pays, couleur));
                }
                else {
                    g.setColor(this.getCouleurPolygone(r, couleur));
                }
                g.fillPolygon(r.getPolygone());
                paintLignes(g, Color.black, r.getPolygone());
            }
        }
        
        // Highlight
        for (Polygon p : highlight) {
            this.paintLignes(g, couleurLigne, p);
        }
        
        // Selectionne
        for (Polygon p : selectionne) {
            this.paintLignes(g, Color.green, p);
        }
        
        // Region infectee initiale
        if (regionInfectee != null) {
            g.setColor(Couleurs.infections);
            this.paintLignes(g, Couleurs.infections, regionInfectee.getPolygone());
        }
        
        // Liens
        if (afficherLiens) {
            this.paintVoies(g);
        }
        
        // Infos
        if (!highlight.isEmpty()) {
            float zoomFactor = simulation.getPanel().getZoomFactor();
            if (afficherInfosPays && paysHighlight != null) {
                int y = drawNom(paysHighlight.getNom(), g, zoomFactor);
                y = drawPopulation(paysHighlight.getPopTotale(), g, zoomFactor, y);
                y = drawStats(paysHighlight.getPopInfectee(),
                        paysHighlight.getPopSaine(),
                        paysHighlight.getPopDecedee(),
                        paysHighlight.getPopImmune(),
                        paysHighlight.getPourcentageInfectee(),
                        paysHighlight.getPourcentageSaine(),
                        paysHighlight.getPourcentageDecedee(),
                        paysHighlight.getPourcentageImmunisee(),
                        g,
                        zoomFactor,
                        y);
                drawFooter("Appuyez sur Q pour voir les infos. sur la région", g, zoomFactor, y);
            }
            else if (regionHighlight != null) {
                int y = drawNom(regionHighlight.getNom(), g, zoomFactor);
                y = drawPopulation(regionHighlight.getPopTotale(), g, zoomFactor, y);
                y = drawStats(regionHighlight.getPopInfectee(),
                        regionHighlight.getPopSaine(),
                        regionHighlight.getPopDecedee(),
                        regionHighlight.getPopImmunisee(),
                        regionHighlight.getPourcentageInfectee(),
                        regionHighlight.getPourcentageSaine(),
                        regionHighlight.getPourcentageDecedee(),
                        regionHighlight.getPourcentageImmune(),
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
        selectionne.clear();
        int index = 0;
        for (Pays pays : carte.getListePays()) {
            if (pays.contient(point.x, point.y)) {
                if (simulation.getScenario().estCommence()) {
                    indexSelectionne = index;
                    selectionne.addAll(pays.getListeRegions().stream().map(x -> x.getPolygone()).collect(Collectors.toList()));
                }
                else {
                    for (Region r : pays.getListeRegions()) {
                        if (r.getPolygone().contains(point)) {
                            regionInfectee = r;
                        }
                    }
                }
                break;
            }
            index++;
        }
    }
    
    @Override
    protected void updateHighlight(Point point) {
        highlight.clear();
        if (point == null) {
            return;
        }
        
        for (Pays pays : carte.getListePays()) {
            if (pays.contient(point.x, point.y)) {
                if (afficherInfosPays) {
                    paysHighlight = pays;
                    highlight.addAll(pays.getListeRegions().stream().map(x -> x.getPolygone()).collect(Collectors.toList()));
                }
                else {
                    for (Region r : pays.getListeRegions()) {
                        if (r.getPolygone().contains(point)) {
                            highlight.add(r.getPolygone());
                            regionHighlight = r;
                        }
                    }
                }
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
        afficherCouleurs++;
        if (afficherCouleurs > 4) {
            afficherCouleurs = 1;
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
                
        float x = souris.x + sourisOffset.x / zoomFactor;
        float y = souris.y + sourisOffset.y / zoomFactor;
        int offsetY = (int)(y + 8 / zoomFactor);
        g.drawString(nom, x, y);
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
        g.drawString(texte, x, offsetY);
    }
    
    private Color getCouleurPolygone(Pays pays, Color color) {
        float pourcent = 1;
        switch (afficherCouleurs) {
            case 1:
                pourcent = pays.getPourcentageInfectee() / 100f;
                break;
            case 2:
                pourcent = pays.getPourcentageSaine() / 100f;
                break;
            case 3:
                pourcent = pays.getPourcentageDecedee() / 100f;
                break;
            case 4:
                pourcent = pays.getPourcentageImmunisee() / 100f;
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
    
    private Color getCouleurPolygone(Region region, Color color) {
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