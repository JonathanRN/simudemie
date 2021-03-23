/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Jonathan
 */
public class CreationCarte extends javax.swing.JPanel {
    
    private Mode mode;
    private int carteIndex;
    private CreationCarteToggle toggleCourant;
    private final JSpinner PopulationTotale;
    
    public CreationCarte(int index) {
        initComponents();
        
        BoutonSelection.init(this, new Selection(this), "icons8_cursor_25px");
        BoutonCrayon.init(this, new Creation(this), "icons8_pen_25px");
        BoutonRegion.init(this, new ca.ulaval.glo2004.afficheur.CreationCarte.Region(this), "icons8_pen_25px");
        onToggleClick(BoutonSelection);
        
        PopulationTotale = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 100));
        PopPanel.add(PopulationTotale, java.awt.BorderLayout.SOUTH);
        
        PaysNomLabel.setFont(FontRegister.RobotoLight.deriveFont(15f));
        RegionNomLabel.setFont(FontRegister.RobotoLight.deriveFont(13f));
        PopLabel.setFont(FontRegister.RobotoLight.deriveFont(13f));
        PopTotaleLabel.setFont(FontRegister.RobotoLight.deriveFont(13f));
        
        carteIndex = index;
        CreationCartePanel.setCreationCarte(this);
    }
    
    public CreationCartePanel getPanel() {
        return CreationCartePanel;
    }
    
    public int getIndexCarte() {
        return carteIndex;
    }
    
    public Mode getMode() {
        return mode;
    }
    
    public JPanel getInformationsPaysPanel() {
        return InformationsPaysPanel;
    }
    
    public JTextField getRegionNomField() {
        return RegionNomTextField;
    }
    
    public JTextField getPaysNomField() {
        return PaysNomTextField;
    }
    
    public JSpinner getPopField() {
        return PopulationTotale;
    }
    
    public void setPopTotaleTexte(String string) {
        PopTotaleLabel.setText("Pop. totale : " + string);
    }
    
    public void onToggleClick(CreationCarteToggle bouton) {
        if (toggleCourant != null) {
            toggleCourant.setToggle(false);
        }
        
        if (mode != null) {
            mode.onDesactive();
        }
        
        mode = bouton.getMode();
        mode.onActive();
        
        toggleCourant = bouton;
        toggleCourant.setToggle(true);
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
        InformationsPaysPanel = new javax.swing.JPanel();
        PaysNomPanel = new javax.swing.JPanel();
        PaysNomLabel = new javax.swing.JLabel();
        PaysNomTextField = new javax.swing.JTextField();
        PopTotalePanel = new javax.swing.JPanel();
        PopTotaleLabel = new javax.swing.JLabel();
        RegionNomPanel = new javax.swing.JPanel();
        RegionNomLabel = new javax.swing.JLabel();
        RegionNomTextField = new javax.swing.JTextField();
        PopPanel = new javax.swing.JPanel();
        PopLabel = new javax.swing.JLabel();
        ToolBar = new javax.swing.JPanel();
        BoutonSelection = new ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle();
        BoutonCrayon = new ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle();
        BoutonRegion = new ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle();

        setBackground(new java.awt.Color(46, 52, 64));
        setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout CreationCartePanelLayout = new javax.swing.GroupLayout(CreationCartePanel);
        CreationCartePanel.setLayout(CreationCartePanelLayout);
        CreationCartePanelLayout.setHorizontalGroup(
            CreationCartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 921, Short.MAX_VALUE)
        );
        CreationCartePanelLayout.setVerticalGroup(
            CreationCartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );

        add(CreationCartePanel, java.awt.BorderLayout.CENTER);

        InformationsPanel.setFocusable(false);
        InformationsPanel.setLayout(new java.awt.BorderLayout());

        InformationsPaysPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        InformationsPaysPanel.setLayout(new java.awt.GridLayout(4, 1, 0, 10));

        PaysNomPanel.setOpaque(false);
        PaysNomPanel.setLayout(new java.awt.BorderLayout(5, 0));

        PaysNomLabel.setText("Pays");
        PaysNomPanel.add(PaysNomLabel, java.awt.BorderLayout.NORTH);

        PaysNomTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PaysNomTextField.setPreferredSize(new java.awt.Dimension(200, 25));
        PaysNomTextField.setSelectionColor(new java.awt.Color(67, 76, 94));
        PaysNomPanel.add(PaysNomTextField, java.awt.BorderLayout.SOUTH);

        InformationsPaysPanel.add(PaysNomPanel);

        PopTotalePanel.setOpaque(false);
        PopTotalePanel.setPreferredSize(new java.awt.Dimension(74, 30));
        PopTotalePanel.setLayout(new java.awt.BorderLayout(5, 0));

        PopTotaleLabel.setText("Pop. totale  :");
        PopTotaleLabel.setToolTipText("");
        PopTotalePanel.add(PopTotaleLabel, java.awt.BorderLayout.NORTH);

        InformationsPaysPanel.add(PopTotalePanel);

        RegionNomPanel.setOpaque(false);
        RegionNomPanel.setLayout(new java.awt.BorderLayout(5, 0));

        RegionNomLabel.setText("Région");
        RegionNomPanel.add(RegionNomLabel, java.awt.BorderLayout.NORTH);

        RegionNomTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RegionNomTextField.setPreferredSize(new java.awt.Dimension(200, 25));
        RegionNomTextField.setSelectionColor(new java.awt.Color(67, 76, 94));
        RegionNomPanel.add(RegionNomTextField, java.awt.BorderLayout.SOUTH);

        InformationsPaysPanel.add(RegionNomPanel);

        PopPanel.setOpaque(false);
        PopPanel.setLayout(new java.awt.BorderLayout(5, 0));

        PopLabel.setText("Pop. région");
        PopLabel.setToolTipText("");
        PopPanel.add(PopLabel, java.awt.BorderLayout.NORTH);

        InformationsPaysPanel.add(PopPanel);

        InformationsPanel.add(InformationsPaysPanel, java.awt.BorderLayout.PAGE_START);

        add(InformationsPanel, java.awt.BorderLayout.LINE_END);

        ToolBar.setBackground(new java.awt.Color(67, 76, 94));
        ToolBar.setPreferredSize(new java.awt.Dimension(968, 50));
        ToolBar.setLayout(new javax.swing.BoxLayout(ToolBar, javax.swing.BoxLayout.X_AXIS));
        ToolBar.add(BoutonSelection);
        ToolBar.add(BoutonCrayon);
        ToolBar.add(BoutonRegion);

        add(ToolBar, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle BoutonCrayon;
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle BoutonRegion;
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarteToggle BoutonSelection;
    private ca.ulaval.glo2004.afficheur.CreationCarte.CreationCartePanel CreationCartePanel;
    private javax.swing.JPanel InformationsPanel;
    private javax.swing.JPanel InformationsPaysPanel;
    private javax.swing.JLabel PaysNomLabel;
    private javax.swing.JPanel PaysNomPanel;
    private javax.swing.JTextField PaysNomTextField;
    private javax.swing.JLabel PopLabel;
    private javax.swing.JPanel PopPanel;
    private javax.swing.JLabel PopTotaleLabel;
    private javax.swing.JPanel PopTotalePanel;
    private javax.swing.JLabel RegionNomLabel;
    private javax.swing.JPanel RegionNomPanel;
    private javax.swing.JTextField RegionNomTextField;
    private javax.swing.JPanel ToolBar;
    // End of variables declaration//GEN-END:variables
}
