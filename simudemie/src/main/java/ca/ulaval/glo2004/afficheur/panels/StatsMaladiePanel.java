/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.panels;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.objetsUI.ObjetMaladie;
import ca.ulaval.glo2004.afficheur.onglets.OngletMaladie;
import ca.ulaval.glo2004.domaine.Maladie;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Jonathan
 */
public class StatsMaladiePanel extends javax.swing.JPanel {
    
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
            init();
            Main.setBackground(new Color(216, 222, 233, 38));
        }
        catch(Exception e) {
        }
    }
    
    private void init() {        
        InfectionLabel = new javax.swing.JLabel();
        InfectionLabel.setFont(FontRegister.RobotoLight.deriveFont(24f));
        InfectionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        InfectionLabel.setText("Taux d'infection");
        InfectionLabel.setToolTipText("");
        Parent.add(InfectionLabel);
        
        InfectionInput = new JSpinner(new SpinnerNumberModel(0.0f, 0, 100, 0.10));
        InfectionInput.setEnabled(false);
        Parent.add(InfectionInput);

        CuredLabel = new javax.swing.JLabel();
        CuredLabel.setFont(FontRegister.RobotoLight.deriveFont(24f));
        CuredLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CuredLabel.setText("Taux de guérison");
        CuredLabel.setToolTipText("");
        Parent.add(CuredLabel);

        CuredInput = new JSpinner(new SpinnerNumberModel(0.0f, 0, 100, 0.10));
        CuredInput.setEnabled(false);
        Parent.add(CuredInput);

        DeadLabel = new javax.swing.JLabel();
        DeadLabel.setFont(FontRegister.RobotoLight.deriveFont(24f));
        DeadLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DeadLabel.setText("Taux de mortalité");
        DeadLabel.setToolTipText("");
        Parent.add(DeadLabel);

        DeadInput = new JSpinner(new SpinnerNumberModel(0.0f, 0, 100, 0.10));
        DeadInput.setEnabled(false);
        Parent.add(DeadInput);
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
        Object[] args = {MaladieInput.getText(), getInfectionInput(), getDeadInput(), getCuredInput()};
        Maladie maladie = onglet.getController().creer(args);
        
        ObjetMaladie objetMaladie = (ObjetMaladie)onglet.getCourant();
        
        objetMaladie.setNom(maladie.getNom());
        objetMaladie.setInfectedProgressBar(maladie.getTauxInfection());
        objetMaladie.setCuredProgressBar(maladie.getTauxGuerison());
        objetMaladie.setDeadProgressBar(maladie.getTauxMortalite());
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
        MaladieInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        MaladieInput.setText("Nom de la maladie");
        MaladieInput.setToolTipText("");
        MaladieInput.setEnabled(false);
        MaladieInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaladieInputActionPerformed(evt);
            }
        });
        MaladieInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MaladieInputKeyReleased(evt);
            }
        });
        StatsHeader.add(MaladieInput, java.awt.BorderLayout.CENTER);
        MaladieInput.getAccessibleContext().setAccessibleName("");

        Main.add(StatsHeader, java.awt.BorderLayout.NORTH);

        Parent.setOpaque(false);
        Parent.setLayout(new java.awt.GridLayout(6, 0));
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
        boolean isModifying = !onglet.getCardLocked();
        setModifying(isModifying);
        if(!isModifying) {
            sauvegarderMaladie();
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

    private void MaladieInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaladieInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaladieInputActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Buttons;
    private javax.swing.JButton DeleteButton;
    private ca.ulaval.glo2004.afficheur.PanelArrondi Main;
    private javax.swing.JTextField MaladieInput;
    private javax.swing.JButton ModifyButton;
    private javax.swing.JPanel Parent;
    private javax.swing.JPanel StatsHeader;
    // End of variables declaration//GEN-END:variables
}
