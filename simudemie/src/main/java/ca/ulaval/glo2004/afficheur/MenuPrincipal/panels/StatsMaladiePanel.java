/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.MenuPrincipal.panels;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.objetsUI.ObjetMaladie;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.onglets.OngletMaladie;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Jonathan
 */
public class StatsMaladiePanel extends javax.swing.JPanel implements ChangeListener, KeyListener {
    private OngletMaladie onglet;
    
    public StatsMaladiePanel() {
        initComponents();
        showFields(false);
        
        try {
            init();
        }
        catch(Exception e) {
        }
    }
    
    private void init() {        
        InfectionInput.addChangeListener(this);
        CuredInput.addChangeListener(this);
        DeadInput.addChangeListener(this);
        MaladieInput.addKeyListener(this);
        IncubationInput.addChangeListener(this);
        ImmunizationCheckbox.addChangeListener(this);
    }
    
    public void setNomMaladie(String nom) {
       MaladieInput.setText(nom);
    }
    
    public void setInfectionInput(double value) {
        InfectionInput.setValue(value);
    }
    
    public void setCuredInput(double value) {
        CuredInput.setValue(value);
    }
    
    public void setDeadInput(double value) {
        DeadInput.setValue(value);
    }
    
    public void setIncubationInput(int value) {
        IncubationInput.setValue(value);
    }
    
    public void setImmunizationCheckbox(boolean value) {
        ImmunizationCheckbox.setSelected(value);
    }
    
    public double getInfectionInput() {
        commitEdit();
        return (double) InfectionInput.getValue();
    }
    
    public double getCuredInput() {
        commitEdit();
        return (double) CuredInput.getValue();
    }
    
    public double getDeadInput() {
        commitEdit();
        return (double) DeadInput.getValue();
    }
    
    public int getIncubationInput() {
        commitEdit();
        return (int) IncubationInput.getValue();
    }
    
    public boolean isImmunizationChecked() {
        return ImmunizationCheckbox.isSelected();
    }
    
    private void commitEdit() {
        try {
            InfectionInput.commitEdit();
            CuredInput.commitEdit();
            DeadInput.commitEdit();
            IncubationInput.commitEdit();
        } catch(ParseException pe) {
        }
    }
    
    public void setOnglet(OngletMaladie onglet) {
        this.onglet = onglet;
    }
    
    private void sauvegarderMaladie() {
        Object[] args = { 
            onglet.getIndexCourant(),
            MaladieInput.getText(),
            getInfectionInput(),
            getDeadInput(),
            getCuredInput(),
            getIncubationInput(),
            isImmunizationChecked()
        };

        onglet.getController().creer(args);
        
        ObjetMaladie objetMaladie = (ObjetMaladie)onglet.getCourant();
        if (objetMaladie != null) {
            objetMaladie.setDeadProgressBar(getDeadInput());
            objetMaladie.setInfectedProgressBar(getInfectionInput());
            objetMaladie.setCuredProgressBar(getCuredInput());
            objetMaladie.setNom(MaladieInput.getText());
        }
        
        updateUI();
        repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Main = new ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi();
        TitreStatsPanel = new javax.swing.JPanel();
        MaladieInput = new javax.swing.JTextField();
        Parent = new javax.swing.JPanel();
        InfectionLabel = new javax.swing.JLabel();
        InfectionInput = new javax.swing.JSpinner();
        CuredLabel = new javax.swing.JLabel();
        CuredInput = new javax.swing.JSpinner();
        DeadLabel = new javax.swing.JLabel();
        DeadInput = new javax.swing.JSpinner();
        IncubationLabel = new javax.swing.JLabel();
        IncubationInput = new javax.swing.JSpinner();
        ImmunizationLabel = new javax.swing.JLabel();
        ImmunizationCheckbox = new javax.swing.JCheckBox();

        setOpaque(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        Main.setBackground(Couleurs.pannelArrondi);
        Main.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 25, 15));
        Main.setLayout(new java.awt.BorderLayout());

        TitreStatsPanel.setOpaque(false);
        TitreStatsPanel.setLayout(new java.awt.BorderLayout());

        MaladieInput.setBackground(new java.awt.Color(71, 76, 88));
        MaladieInput.setFont(FontRegister.RobotoThin.deriveFont(21f));
        MaladieInput.setText("Nom de la maladie");
        MaladieInput.setToolTipText("");
        MaladieInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MaladieInputKeyReleased(evt);
            }
        });
        TitreStatsPanel.add(MaladieInput, java.awt.BorderLayout.CENTER);
        MaladieInput.getAccessibleContext().setAccessibleName("");

        Main.add(TitreStatsPanel, java.awt.BorderLayout.NORTH);

        Parent.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
        Parent.setOpaque(false);
        Parent.setLayout(new java.awt.GridLayout(5, 2, 0, 10));

        InfectionLabel.setFont(FontRegister.RobotoThin.deriveFont(15f));
        InfectionLabel.setText("Taux d'infection");
        Parent.add(InfectionLabel);

        InfectionInput.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 99.99d, 0.5d));
        InfectionInput.setOpaque(true);
        Parent.add(InfectionInput);

        CuredLabel.setFont(FontRegister.RobotoThin.deriveFont(15f));
        CuredLabel.setText("Taux de guérison");
        Parent.add(CuredLabel);

        CuredInput.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 100.0d, 0.5d));
        CuredInput.setOpaque(true);
        Parent.add(CuredInput);

        DeadLabel.setFont(FontRegister.RobotoThin.deriveFont(15f));
        DeadLabel.setText("Taux de mortalité");
        Parent.add(DeadLabel);

        DeadInput.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 100.0d, 0.5d));
        DeadInput.setMaximumSize(new java.awt.Dimension(64, 22));
        DeadInput.setOpaque(true);
        Parent.add(DeadInput);

        IncubationLabel.setFont(FontRegister.RobotoThin.deriveFont(15f));
        IncubationLabel.setText("Temps d'incubation");
        Parent.add(IncubationLabel);

        IncubationInput.setModel(new javax.swing.SpinnerNumberModel(14, 0, 31, 1));
        IncubationInput.setOpaque(true);
        Parent.add(IncubationInput);

        ImmunizationLabel.setFont(FontRegister.RobotoThin.deriveFont(15f));
        ImmunizationLabel.setText("Possibilité d'immunisation");
        Parent.add(ImmunizationLabel);

        ImmunizationCheckbox.setBackground(Couleurs.pannelArrondiNoTransp);
        ImmunizationCheckbox.setToolTipText("");
        ImmunizationCheckbox.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Parent.add(ImmunizationCheckbox);

        Main.add(Parent, java.awt.BorderLayout.CENTER);

        add(Main);
    }// </editor-fold>//GEN-END:initComponents
    
    public void showFields(boolean enabled) {
        InfectionLabel.setVisible(enabled);
        CuredLabel.setVisible(enabled);
        DeadLabel.setVisible(enabled);
        IncubationLabel.setVisible(enabled);
        ImmunizationLabel.setVisible(enabled);
        
        MaladieInput.setVisible(enabled);
        InfectionInput.setVisible(enabled);
        CuredInput.setVisible(enabled);
        DeadInput.setVisible(enabled);
        IncubationInput.setVisible(enabled);
        ImmunizationCheckbox.setVisible(enabled);
        
        updateUI();
    }
    
    private void MaladieInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MaladieInputKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.requestFocusInWindow();
            updateUI();
        }
    }//GEN-LAST:event_MaladieInputKeyReleased

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        this.requestFocusInWindow();
        updateUI();
    }//GEN-LAST:event_formMouseReleased
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner CuredInput;
    private javax.swing.JLabel CuredLabel;
    private javax.swing.JSpinner DeadInput;
    private javax.swing.JLabel DeadLabel;
    private javax.swing.JCheckBox ImmunizationCheckbox;
    private javax.swing.JLabel ImmunizationLabel;
    private javax.swing.JSpinner IncubationInput;
    private javax.swing.JLabel IncubationLabel;
    private javax.swing.JSpinner InfectionInput;
    private javax.swing.JLabel InfectionLabel;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi Main;
    private javax.swing.JTextField MaladieInput;
    private javax.swing.JPanel Parent;
    private javax.swing.JPanel TitreStatsPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void stateChanged(ChangeEvent e) {
        sauvegarderMaladie();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        sauvegarderMaladie();
    }
}
