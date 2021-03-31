/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.panels;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.objetsUI.ObjetMaladie;
import ca.ulaval.glo2004.afficheur.onglets.OngletMaladie;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Jonathan
 */
public class StatsMaladiePanel extends javax.swing.JPanel implements ChangeListener {
    
    private JLabel InfectionLabel;
    private JSpinner InfectionInput;
    private JLabel CuredLabel;
    private JSpinner CuredInput;
    private JLabel DeadLabel;
    private JSpinner DeadInput;
        
    private OngletMaladie onglet;
    
    public StatsMaladiePanel() {
        initComponents();
        
        try {
            MaladieInput.setFont(FontRegister.RobotoThin.deriveFont(21f));
            DeleteButton.setBackground(new Color(216, 222, 233, 38));
            DeleteButton.setFont(FontRegister.RobotoLight.deriveFont(15f));
            ModifyButton.setBackground(new Color(216, 222, 233, 38));
            ModifyButton.setFont(FontRegister.RobotoLight.deriveFont(15f));
            init();
            showFields(false);
            Main.setBackground(new Color(216, 222, 233, 38));
        }
        catch(Exception e) {
        }
    }
    
    private void init() {        
        InfectionLabel = new javax.swing.JLabel();
        InfectionLabel.setFont(FontRegister.RobotoThin.deriveFont(15f));
        InfectionLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        InfectionLabel.setText("Taux d'infection");
        InfectionLabel.setToolTipText("");
        Parent.add(InfectionLabel);
        
        InfectionInput = new JSpinner(new SpinnerNumberModel(0.0f, 0, 100, 0.5));
        InfectionInput.setEnabled(false);
        Parent.add(InfectionInput);

        CuredLabel = new javax.swing.JLabel();
        CuredLabel.setFont(FontRegister.RobotoThin.deriveFont(15f));
        CuredLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        CuredLabel.setText("Taux de guérison");
        CuredLabel.setToolTipText("");
        Parent.add(CuredLabel);

        CuredInput = new JSpinner(new SpinnerNumberModel(0.0f, 0, 100, 0.5));
        CuredInput.setEnabled(false);
        Parent.add(CuredInput);

        DeadLabel = new javax.swing.JLabel();
        DeadLabel.setFont(FontRegister.RobotoThin.deriveFont(15f));
        DeadLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        DeadLabel.setText("Taux de mortalité");
        DeadLabel.setToolTipText("");
        Parent.add(DeadLabel);

        DeadInput = new JSpinner(new SpinnerNumberModel(0.0f, 0, 100, 0.5));
        DeadInput.setEnabled(false);
        Parent.add(DeadInput);
        
        InfectionInput.addChangeListener(this);
        CuredInput.addChangeListener(this);
        DeadInput.addChangeListener(this);
    }
    
    public void setNomMaladie(String nom) {
       MaladieInput.setText(nom);
    }
    
    public void setInfectionInput(double value) {
        InfectionInput.setValue(value);
        try {
            InfectionInput.commitEdit();
        } catch(ParseException pe) {
            
        }
    }
    
    public void setCuredInput(double value) {
        CuredInput.setValue(value);
        try {
            CuredInput.commitEdit();
        } catch(ParseException pe) {
            
        }
    }
    
    public void setDeadInput(double value) {
        DeadInput.setValue(value);
        try {
            DeadInput.commitEdit();
        } catch(ParseException pe) {
            
        }
    }
    
    public double getInfectionInput() {
        return (double) InfectionInput.getValue();
    }
    
    public double getCuredInput() {
        return (double) CuredInput.getValue();
    }
    
    public double getDeadInput() {
        return (double) DeadInput.getValue();
    }
    
    public void setOnglet(OngletMaladie onglet) {
        this.onglet = onglet;
    }
    
    private void sauvegarderMaladie() {
        Object[] args = { onglet.getIndexCourant(), MaladieInput.getText(), getInfectionInput(), getDeadInput(), getCuredInput() };
        onglet.getController().creer(args);
        
        setInfectionInput(getInfectionInput());
        setCuredInput(getCuredInput());
        setDeadInput(getDeadInput());
        
        ObjetMaladie objetMaladie = (ObjetMaladie)onglet.getCourant();
        objetMaladie.setNom(MaladieInput.getText());
        
        updateUI();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        ObjetMaladie objetMaladie = (ObjetMaladie)onglet.getCourant();
        if (objetMaladie != null) {
            objetMaladie.setInfectedProgressBar(getInfectionInput());
            objetMaladie.setCuredProgressBar(getCuredInput());
            objetMaladie.setDeadProgressBar(getDeadInput());
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

        Main = new ca.ulaval.glo2004.afficheur.PanelArrondi();
        StatsHeader = new javax.swing.JPanel();
        MaladieInput = new javax.swing.JTextField();
        Parent = new javax.swing.JPanel();
        Buttons = new javax.swing.JPanel();
        DeleteButton = new javax.swing.JButton();
        ModifyButton = new javax.swing.JButton();

        setOpaque(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        Main.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 25, 15));
        Main.setLayout(new java.awt.BorderLayout());

        StatsHeader.setOpaque(false);
        StatsHeader.setLayout(new java.awt.BorderLayout());

        MaladieInput.setBackground(new java.awt.Color(71, 76, 88));
        MaladieInput.setFont(new java.awt.Font("Dialog", 0, 21)); // NOI18N
        MaladieInput.setText("Nom de la maladie");
        MaladieInput.setToolTipText("");
        MaladieInput.setEnabled(false);
        MaladieInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MaladieInputKeyReleased(evt);
            }
        });
        StatsHeader.add(MaladieInput, java.awt.BorderLayout.CENTER);
        MaladieInput.getAccessibleContext().setAccessibleName("");

        Main.add(StatsHeader, java.awt.BorderLayout.NORTH);

        Parent.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 0, 0));
        Parent.setOpaque(false);
        Parent.setLayout(new java.awt.GridLayout(6, 0, 0, 5));
        Main.add(Parent, java.awt.BorderLayout.CENTER);

        add(Main);

        Buttons.setOpaque(false);
        Buttons.setPreferredSize(new java.awt.Dimension(691, 431));
        Buttons.setLayout(new java.awt.GridLayout(2, 1, 0, 25));

        DeleteButton.setText("Supprimer");
        DeleteButton.setToolTipText("Supprimer la maladie");
        DeleteButton.setFocusable(false);
        DeleteButton.setMaximumSize(new java.awt.Dimension(75, 22));
        DeleteButton.setMinimumSize(new java.awt.Dimension(75, 22));
        DeleteButton.setPreferredSize(new java.awt.Dimension(75, 22));
        DeleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                DeleteButtonMouseReleased(evt);
            }
        });
        Buttons.add(DeleteButton);

        ModifyButton.setText("Modifier");
        ModifyButton.setToolTipText("Modifier la maladie");
        ModifyButton.setFocusable(false);
        ModifyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ModifyButtonMouseReleased(evt);
            }
        });
        Buttons.add(ModifyButton);

        add(Buttons);
    }// </editor-fold>//GEN-END:initComponents

    private void DeleteButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteButtonMouseReleased
        onglet.retirerCourant();
    }//GEN-LAST:event_DeleteButtonMouseReleased

    private void ModifyButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModifyButtonMouseReleased
        if(onglet.getCourant() != null) {
            boolean isModifying = !onglet.getCardLocked();
            setModifying(isModifying);
            if(!isModifying) {
                sauvegarderMaladie();
            }
        }
    }//GEN-LAST:event_ModifyButtonMouseReleased

    public void setModifying(boolean isModifying) {
        if(isModifying) {
            ModifyButton.setText("Enregistrer");
            ModifyButton.setToolTipText("Enregistrer les informations");
        } else {
            ModifyButton.setText("Modifier");
            ModifyButton.setToolTipText("Modifier les informations");
        }
        
        enableFields(isModifying);
    }
    
    private void enableFields(boolean enabled) {
        onglet.setCardLocked(enabled);
        MaladieInput.setEnabled(enabled);
        InfectionInput.setEnabled(enabled);
        CuredInput.setEnabled(enabled);
        DeadInput.setEnabled(enabled);
    }
    
    public void showFields(boolean enabled) {
        InfectionLabel.setVisible(enabled);
        CuredLabel.setVisible(enabled);
        DeadLabel.setVisible(enabled);
        
        MaladieInput.setVisible(enabled);
        InfectionInput.setVisible(enabled);
        CuredInput.setVisible(enabled);
        DeadInput.setVisible(enabled);
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
    private javax.swing.JPanel Buttons;
    private javax.swing.JButton DeleteButton;
    private ca.ulaval.glo2004.afficheur.PanelArrondi Main;
    private javax.swing.JTextField MaladieInput;
    private javax.swing.JButton ModifyButton;
    private javax.swing.JPanel Parent;
    private javax.swing.JPanel StatsHeader;
    // End of variables declaration//GEN-END:variables

    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}
