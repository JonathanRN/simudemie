/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte.panels;

import ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarte;
import ca.ulaval.glo2004.afficheur.CreationCarte.mode.Region.PolygoneDivise;
import ca.ulaval.glo2004.afficheur.utilsUI.ZoomablePanel;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.Region;
import ca.ulaval.glo2004.domaine.VoieLiaison;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.util.Stack;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jonathan
 */
public class CreationCartePanel extends ZoomablePanel {
    
    private class Etat {
        private final Polygon courant;
        private final Carte carte;

        public Etat(Polygon courant, Carte carte) {
            this.courant = new Polygon(courant.xpoints, courant.ypoints, courant.npoints);
            this.carte = new Carte(carte);
        }
        
        public Polygon getCourant() {
            return courant;
        }
        
        public Carte getCarte() {
            return carte;
        }
    }
    
    private final Stack<Etat> etats = new Stack<>();
    private int pointeur = -1;
    
    private Polygon courant = new Polygon();
        
    private CreationCarte creationCarte;
    
    public CreationCartePanel() {
        initComponents();
    }
    
    public Polygon getCourant() {
        return courant;
    }
    
    public void setCreationCarte(CreationCarte creationCarte) {
        this.creationCarte = creationCarte;
        
        // Il faut avoir l'etat initial
        sauvegarderEtat();
    }
    
    public void sauvegarderEtat() {
        // On supprime tout ce qui etait apres cet etat
        for (int i = etats.size() - 1; i > pointeur; i--) {
            etats.remove(i);
        }
        
        etats.push(new Etat(courant, creationCarte.getCarte()));
        pointeur++;
        
        creationCarte.setRedoActif(false);
        creationCarte.setUndoActif(true);
        creationCarte.repaint();
        
        System.out.println("Sauvegarde " + pointeur);
    }
    
    public void placerPoint(int x, int y) {
        Polygon testeur = new Polygon(courant.xpoints, courant.ypoints, courant.npoints);
        testeur.addPoint(x, y);
        
        // Place le point seulement s'il est valide
        if (creationCarte.getMode().estPolygoneValide(testeur)) {
            courant.addPoint(x, y);
            sauvegarderEtat();
        }
    }
    
    public void creerPolygone() {
        // Dessine le polygone seulement s'il est valide
        if (creationCarte.getMode().estPolygoneValide(getCourant())) {
            Pays pays = new Pays(getCourant());
            pays.ajouterRegion(new ca.ulaval.glo2004.domaine.Region(pays.getPolygone()));
            creationCarte.getCarte().ajouterPays(pays);
            
            courant.reset();
            sauvegarderEtat();
                        
            creationCarte.getPopup().setVisible(false);
        }
        else {
            // Pour ne pas call repaint() deux fois, si l'action est ajoutee
            creationCarte.repaint();
        }
    }
    
    public void ajouterLien(VoieLiaison voie) {
        creationCarte.getCarte().ajouterVoie(voie);
        
        sauvegarderEtat();
    }
    
    public void splitPays(Polygon p, PolygoneDivise divise) {
        sauvegarderEtat();
    }
    
    public boolean estRegionUnique(Polygon p) {
        Pays pays = creationCarte.getPays(p);
        return pays != null && pays.getRegions().size() > 1;
    }
    
    public ca.ulaval.glo2004.domaine.Region getRegion(Pays pays, Polygon p) {
        for (Region r : pays.getRegions()) {
            if (r.getPolygone().equals(p)) {
                return r;
            }
        }
        return null;
    }
    
    public void undo() {
        if (pointeur > 0) {
            Etat undo = etats.get(--pointeur);
            creationCarte.chargerCarte(undo.getCarte());
            courant = new Polygon(undo.getCourant().xpoints, undo.getCourant().ypoints, undo.getCourant().npoints);

            if (pointeur <= 0) {
                creationCarte.setUndoActif(false);
            }
            
            creationCarte.setRedoActif(true);
            
            creationCarte.repaint();
            System.out.println("Undo " + pointeur);
        }
    }
    
    public void redo() {
        if (pointeur < etats.size()) {
            Etat redo = etats.get(++pointeur);
            creationCarte.chargerCarte(redo.getCarte());
            courant = new Polygon(redo.getCourant().xpoints, redo.getCourant().ypoints, redo.getCourant().npoints);
            
            if (pointeur >= etats.size() - 1) {
                creationCarte.setRedoActif(false);
            }
            
            creationCarte.setUndoActif(true);
            
            creationCarte.repaint();
            System.out.println("Redo " + pointeur);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        
        if (creationCarte != null) {
            creationCarte.getMode().paint(graphics);
        }        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(46, 52, 64));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                formMouseWheelMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 834, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        requestFocusInWindow();
        if (SwingUtilities.isLeftMouseButton(evt)) {
            creationCarte.getMode().onMouseReleased(getOffset(evt.getPoint()));
        }
        
        if (SwingUtilities.isRightMouseButton(evt)) {
            mouseReleased(evt);
        }
        creationCarte.repaint();
    }//GEN-LAST:event_formMouseReleased
    
    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_1) {
            creationCarte.onToggleClick(1);
        }
        
        if (evt.getKeyCode() == KeyEvent.VK_2) {
            creationCarte.onToggleClick(2);
        }
        
        if (evt.getKeyCode() == KeyEvent.VK_3) {
            creationCarte.onToggleClick(3);
        }
        
        if (evt.getKeyCode() == KeyEvent.VK_4) {
            creationCarte.onToggleClick(4);
        }
        
        creationCarte.getMode().onKeyReleased(evt);
        creationCarte.repaint();
    }//GEN-LAST:event_formKeyReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (SwingUtilities.isLeftMouseButton(evt)) {
            creationCarte.getMode().onMouseDragged(getOffset(evt.getPoint()));
        }
        
        if (SwingUtilities.isRightMouseButton(evt)) {
            mouseDragged(evt);
        }
        creationCarte.repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        creationCarte.getMode().onMouseMoved(getOffset(evt.getPoint()));
        creationCarte.repaint();
    }//GEN-LAST:event_formMouseMoved

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if (SwingUtilities.isLeftMouseButton(evt)) {
            creationCarte.getMode().onMousePressed(getOffset(evt.getPoint()));        
        }
        
        if (SwingUtilities.isRightMouseButton(evt)) {
            mousePressed(evt);
        }
        creationCarte.repaint();
    }//GEN-LAST:event_formMousePressed

    private void formMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_formMouseWheelMoved
        mouseWheelMoved(evt);
        
        creationCarte.getZoomPopup().onMouseWheel(zoomFactor);
        
        creationCarte.repaint();
    }//GEN-LAST:event_formMouseWheelMoved
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
