/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte.panels;

import ca.ulaval.glo2004.afficheur.CreationCarte.mode.Selection;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.Region;
import java.text.ParseException;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Jonathan
 */
public class InformationsPaysPanel extends javax.swing.JPanel {
    
    private final JSpinner PopulationTotale;
    private final Selection selection;
    
    private String ancienNomPays, ancienNomRegion;
    private int anciennePop;
    
    public InformationsPaysPanel(Selection selection) {
        this.selection = selection;
        initComponents();
        
        PopulationTotale = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 100));
        PopulationTotale.getEditor().getComponent(0).setBackground(Couleurs.sideMenuNoTransp);
        PopRegionPanel.add(PopulationTotale, java.awt.BorderLayout.SOUTH);
        
        InformationsPaysLabel.setFont(FontRegister.RobotoLight.deriveFont(15f));
        PaysNomLabel.setFont(FontRegister.RobotoLight.deriveFont(13f));
        RegionNomLabel.setFont(FontRegister.RobotoLight.deriveFont(13f));
        PopRegionLabel.setFont(FontRegister.RobotoLight.deriveFont(13f));
        PopTotaleLabel.setFont(FontRegister.RobotoLight.deriveFont(13f));
        
        PaysNomTextField.setFont(FontRegister.RobotoLight.deriveFont(12f));
        PaysNomTextField.setBackground(Couleurs.sideMenuNoTransp);
        RegionNomTextField.setFont(FontRegister.RobotoLight.deriveFont(12f));
        RegionNomTextField.setBackground(Couleurs.sideMenuNoTransp);
        
    }
    
    public void Active(Pays pays, Region region) {
        setNomPays(pays.getNom());
        setNomRegion(region.getNom());
        setPopTotale(pays.getPopTotale());
        setPopRegion(region.getPopTotale());
        
        ancienNomPays = getNomPays();
        ancienNomRegion = getNomRegion();
        try {
            anciennePop = getPopRegion();
        } catch (ParseException ex) {
        }
    }
    
    public void Desactive() {
        try {
            // On detecte s'il y a eu au moins une modification
            if (!ancienNomPays.equals(getNomPays()) ||
                !ancienNomRegion.equals(getNomRegion()) ||
                anciennePop != getPopRegion()) {
                selection.saveInfos();
            }
        }
        catch (ParseException ex) {
        }
    }
    
    public String getNomPays() {
        return PaysNomTextField.getText();
    }
    
    public String getNomRegion() {
        return RegionNomTextField.getText();
    }
    
    public void setNomPays(String nom) {
        PaysNomTextField.setText(nom);
    }
    
    public void setNomRegion(String region) {
        RegionNomTextField.setText(region);
    }
    
    public void setPopTotale(int pop) {
        PopTotaleLabel.setText("Pop. Totale : " + Integer.toString(pop));
    }
    
    public void setPopRegion(int pop) {
        PopulationTotale.setValue(pop);
    }
    
    public int getPopRegion() throws ParseException {
        PopulationTotale.commitEdit();
        return (int)PopulationTotale.getValue();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InformationsPaysLabel = new javax.swing.JLabel();
        NomPaysPanel = new javax.swing.JPanel();
        PaysNomLabel = new javax.swing.JLabel();
        PaysNomTextField = new javax.swing.JTextField();
        PopTotalePaysPanel = new javax.swing.JPanel();
        PopTotaleLabel = new javax.swing.JLabel();
        NomRegionPanel = new javax.swing.JPanel();
        RegionNomLabel = new javax.swing.JLabel();
        RegionNomTextField = new javax.swing.JTextField();
        PopRegionPanel = new javax.swing.JPanel();
        PopRegionLabel = new javax.swing.JLabel();
        SupprimeBouton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 7, 7, 7));
        setOpaque(false);
        setLayout(new java.awt.GridLayout(6, 1, 0, 10));

        InformationsPaysLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        InformationsPaysLabel.setText("Informations Pays");
        add(InformationsPaysLabel);

        NomPaysPanel.setOpaque(false);
        NomPaysPanel.setPreferredSize(new java.awt.Dimension(200, 30));
        NomPaysPanel.setLayout(new java.awt.BorderLayout(5, 0));

        PaysNomLabel.setText("Pays");
        NomPaysPanel.add(PaysNomLabel, java.awt.BorderLayout.NORTH);

        PaysNomTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PaysNomTextField.setOpaque(false);
        PaysNomTextField.setPreferredSize(new java.awt.Dimension(200, 25));
        PaysNomTextField.setSelectionColor(new java.awt.Color(67, 76, 94));
        NomPaysPanel.add(PaysNomTextField, java.awt.BorderLayout.SOUTH);

        add(NomPaysPanel);

        PopTotalePaysPanel.setOpaque(false);
        PopTotalePaysPanel.setPreferredSize(new java.awt.Dimension(74, 30));
        PopTotalePaysPanel.setLayout(new java.awt.BorderLayout(5, 0));

        PopTotaleLabel.setText("Pop. totale  :");
        PopTotaleLabel.setToolTipText("");
        PopTotalePaysPanel.add(PopTotaleLabel, java.awt.BorderLayout.NORTH);

        add(PopTotalePaysPanel);

        NomRegionPanel.setOpaque(false);
        NomRegionPanel.setLayout(new java.awt.BorderLayout(5, 0));

        RegionNomLabel.setText("Région");
        NomRegionPanel.add(RegionNomLabel, java.awt.BorderLayout.NORTH);

        RegionNomTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RegionNomTextField.setOpaque(false);
        RegionNomTextField.setPreferredSize(new java.awt.Dimension(200, 25));
        RegionNomTextField.setSelectionColor(new java.awt.Color(67, 76, 94));
        NomRegionPanel.add(RegionNomTextField, java.awt.BorderLayout.SOUTH);

        add(NomRegionPanel);

        PopRegionPanel.setOpaque(false);
        PopRegionPanel.setLayout(new java.awt.BorderLayout(5, 0));

        PopRegionLabel.setText("Pop. région");
        PopRegionLabel.setToolTipText("");
        PopRegionPanel.add(PopRegionLabel, java.awt.BorderLayout.NORTH);

        add(PopRegionPanel);

        SupprimeBouton.setText("Retirer Pays");
        SupprimeBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SupprimeBoutonMouseReleased(evt);
            }
        });
        add(SupprimeBouton);
    }// </editor-fold>//GEN-END:initComponents

    private void SupprimeBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupprimeBoutonMouseReleased
        selection.onPaysSupprime();
    }//GEN-LAST:event_SupprimeBoutonMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel InformationsPaysLabel;
    private javax.swing.JPanel NomPaysPanel;
    private javax.swing.JPanel NomRegionPanel;
    private javax.swing.JLabel PaysNomLabel;
    private javax.swing.JTextField PaysNomTextField;
    private javax.swing.JLabel PopRegionLabel;
    private javax.swing.JPanel PopRegionPanel;
    private javax.swing.JLabel PopTotaleLabel;
    private javax.swing.JPanel PopTotalePaysPanel;
    private javax.swing.JLabel RegionNomLabel;
    private javax.swing.JTextField RegionNomTextField;
    private javax.swing.JButton SupprimeBouton;
    // End of variables declaration//GEN-END:variables
}
