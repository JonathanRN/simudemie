/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import java.text.ParseException;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Jonathan
 */
public class InformationsPaysPanel extends javax.swing.JPanel {
    
    private final JSpinner PopulationTotale;
    private final Selection selection;
    
    public InformationsPaysPanel(Selection selection) {
        this.selection = selection;
        initComponents();
        
        PopulationTotale = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 100));
        PopPanel.add(PopulationTotale, java.awt.BorderLayout.SOUTH);
        
        InformationsPaysLabel.setFont(FontRegister.RobotoLight.deriveFont(15f));
        PaysNomLabel.setFont(FontRegister.RobotoLight.deriveFont(13f));
        RegionNomLabel.setFont(FontRegister.RobotoLight.deriveFont(13f));
        PopLabel.setFont(FontRegister.RobotoLight.deriveFont(13f));
        PopTotaleLabel.setFont(FontRegister.RobotoLight.deriveFont(13f));
        
        PaysNomTextField.setFont(FontRegister.RobotoLight.deriveFont(12f));
        RegionNomTextField.setFont(FontRegister.RobotoLight.deriveFont(12f));
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
        SupprimeBouton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 7, 7, 7));
        setOpaque(false);
        setLayout(new java.awt.GridLayout(6, 1, 0, 10));

        InformationsPaysLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        InformationsPaysLabel.setText("Informations Pays");
        add(InformationsPaysLabel);

        PaysNomPanel.setOpaque(false);
        PaysNomPanel.setPreferredSize(new java.awt.Dimension(200, 30));
        PaysNomPanel.setLayout(new java.awt.BorderLayout(5, 0));

        PaysNomLabel.setText("Pays");
        PaysNomPanel.add(PaysNomLabel, java.awt.BorderLayout.NORTH);

        PaysNomTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PaysNomTextField.setPreferredSize(new java.awt.Dimension(200, 25));
        PaysNomTextField.setSelectionColor(new java.awt.Color(67, 76, 94));
        PaysNomPanel.add(PaysNomTextField, java.awt.BorderLayout.SOUTH);

        add(PaysNomPanel);

        PopTotalePanel.setOpaque(false);
        PopTotalePanel.setPreferredSize(new java.awt.Dimension(74, 30));
        PopTotalePanel.setLayout(new java.awt.BorderLayout(5, 0));

        PopTotaleLabel.setText("Pop. totale  :");
        PopTotaleLabel.setToolTipText("");
        PopTotalePanel.add(PopTotaleLabel, java.awt.BorderLayout.NORTH);

        add(PopTotalePanel);

        RegionNomPanel.setOpaque(false);
        RegionNomPanel.setLayout(new java.awt.BorderLayout(5, 0));

        RegionNomLabel.setText("Région");
        RegionNomPanel.add(RegionNomLabel, java.awt.BorderLayout.NORTH);

        RegionNomTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RegionNomTextField.setPreferredSize(new java.awt.Dimension(200, 25));
        RegionNomTextField.setSelectionColor(new java.awt.Color(67, 76, 94));
        RegionNomPanel.add(RegionNomTextField, java.awt.BorderLayout.SOUTH);

        add(RegionNomPanel);

        PopPanel.setOpaque(false);
        PopPanel.setLayout(new java.awt.BorderLayout(5, 0));

        PopLabel.setText("Pop. région");
        PopLabel.setToolTipText("");
        PopPanel.add(PopLabel, java.awt.BorderLayout.NORTH);

        add(PopPanel);

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
    private javax.swing.JButton SupprimeBouton;
    // End of variables declaration//GEN-END:variables
}
