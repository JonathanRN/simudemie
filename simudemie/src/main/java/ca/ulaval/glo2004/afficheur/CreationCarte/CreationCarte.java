/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte;

import ca.ulaval.glo2004.afficheur.FramePrincipal;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireCarte;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jonathan
 */
public class CreationCarte extends javax.swing.JPanel {
    
    private Mode mode;
    private final int carteIndex;
    private CreationCarteToggle toggleCourant;
    
    public CreationCarte(int index) {
        initComponents();
        
        InformationsPanel.setBackground(new Color(59, 66, 82));
        
        BoutonSelection.init(this, new Selection(this), "icons8_cursor_25px");
        BoutonCrayon.init(this, new Creation(this), "icons8_pen_25px");
        BoutonRegion.init(this, new ca.ulaval.glo2004.afficheur.CreationCarte.Region(this), "icons8_scissors_25px");
        BoutonLien.init(this, new LienPays(this), "icons8_chain_25px");
        
        BoutonQuitter.init(this, null, "icons8_exit_25px");
        BoutonQuitter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                quitter();
            }
        });
        
        onToggleClick(BoutonSelection);
        
        carteIndex = index;
        CreationCartePanel.setCreationCarte(this);
    }
    
    public CreationCartePanel getPanel() {
        return CreationCartePanel;
    }
    
    public ArrayList<Polygon> getPolygones() {
        return getCarte().getPolygonesRegions();
    }
    
    public Carte getCarte() {
        return GestionnaireCarte.getInstance().getElement(carteIndex);
    }
    
    public Pays getPays(Polygon p) {
        return getCarte().getPays(p);
    }
    
    public Mode getMode() {
        return mode;
    }
    
    public JPanel getInformationsPanel() {
        return InformationsPanel;
    }
    
    public void onToggleClick(CreationCarteToggle bouton) {
        if (toggleCourant != null) {
            toggleCourant.setToggle(false);
        }
        
        if (mode != null) {
            mode.onDesactive();
        }
        
        if (bouton.getMode() != null) {
            mode = bouton.getMode();
            mode.onActive();

            toggleCourant = bouton;
            toggleCourant.setToggle(true);
        }
    }
    
    private void quitter() {
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("Retourner au menu principal?\nVos modifications sont sauvegardées.");
        optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        optionPane.setOptionType(JOptionPane.YES_NO_CANCEL_OPTION);
        
        int result = JOptionPane.showOptionDialog(
            SwingUtilities.windowForComponent(this),
            optionPane.getMessage(),
            "Retour au menu?",
            optionPane.getOptionType(),
            optionPane.getMessageType(),
            optionPane.getIcon(),
            optionPane.getOptions(),
            optionPane.getInitialValue());
        
        if (result == JOptionPane.YES_OPTION) {
            FramePrincipal frame = (FramePrincipal)SwingUtilities.windowForComponent(this);
            frame.returnToHome();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CreationCartePanel = new ca.ulaval.glo2004.afficheur.CreationCarte.CreationCartePanel();
        InformationsPanel = new javax.swing.JPanel();
        ToolBar = new javax.swing.JPanel();
        BoutonSelection = new ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle();
        BoutonCrayon = new ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle();
        BoutonRegion = new ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle();
        BoutonLien = new ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle();
        BoutonQuitterParent = new javax.swing.JPanel();
        BoutonQuitter = new ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle();

        setBackground(new java.awt.Color(46, 52, 64));
        setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout CreationCartePanelLayout = new javax.swing.GroupLayout(CreationCartePanel);
        CreationCartePanel.setLayout(CreationCartePanelLayout);
        CreationCartePanelLayout.setHorizontalGroup(
            CreationCartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 916, Short.MAX_VALUE)
        );
        CreationCartePanelLayout.setVerticalGroup(
            CreationCartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 699, Short.MAX_VALUE)
        );

        add(CreationCartePanel, java.awt.BorderLayout.CENTER);

        InformationsPanel.setFocusable(false);
        InformationsPanel.setPreferredSize(new java.awt.Dimension(225, 0));
        InformationsPanel.setLayout(new java.awt.BorderLayout());
        add(InformationsPanel, java.awt.BorderLayout.EAST);

        ToolBar.setBackground(new java.awt.Color(67, 76, 94));
        ToolBar.setPreferredSize(new java.awt.Dimension(968, 50));
        ToolBar.setLayout(new javax.swing.BoxLayout(ToolBar, javax.swing.BoxLayout.X_AXIS));
        ToolBar.add(BoutonSelection);
        ToolBar.add(BoutonCrayon);
        ToolBar.add(BoutonRegion);
        ToolBar.add(BoutonLien);

        BoutonQuitterParent.setOpaque(false);
        BoutonQuitterParent.setLayout(new java.awt.BorderLayout());
        BoutonQuitterParent.add(BoutonQuitter, java.awt.BorderLayout.EAST);

        ToolBar.add(BoutonQuitterParent);

        add(ToolBar, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle BoutonCrayon;
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle BoutonLien;
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle BoutonQuitter;
    private javax.swing.JPanel BoutonQuitterParent;
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle BoutonRegion;
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle BoutonSelection;
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCartePanel CreationCartePanel;
    private javax.swing.JPanel InformationsPanel;
    private javax.swing.JPanel ToolBar;
    // End of variables declaration//GEN-END:variables
}
