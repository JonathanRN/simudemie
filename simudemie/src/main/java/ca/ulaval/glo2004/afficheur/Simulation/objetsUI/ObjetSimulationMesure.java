/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.Simulation.objetsUI;

import ca.ulaval.glo2004.afficheur.Simulation.panels.SimulationPanelGauche;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.domaine.Mesure;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireScenario;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jonathan
 */
public class ObjetSimulationMesure extends javax.swing.JPanel {
    
    private JPanel conteneur;
    private SimulationPanelGauche simulationTabs;
    private boolean edition, mouseOverEdition, mouseOverActif, estActif;
    private int index;
    
    public ObjetSimulationMesure() {
        initComponents();
    }
    
    public ObjetSimulationMesure(JPanel conteneur, SimulationPanelGauche simulationTabs, int index) {
        this.conteneur = conteneur;
        this.simulationTabs = simulationTabs;
        this.index = index;
        initComponents();
        
        // Met tout de suite en mode edition lors de la creation
        setEdition(true, false);
        setActif(true, false);
    }
    
    private void setActif(boolean actif, boolean mouseOver) {
        estActif = actif;
        
        updateActifIcon(mouseOver);
    }
    
    private void setEdition(boolean edition, boolean mouseOver) {
        this.edition = edition;
        
        NomMesureTextField.setEnabled(edition);
        TauxReduction.setEnabled(edition);
        TauxAdhesion.setEnabled(edition);
        SeuilActivation.setEnabled(edition);
        
        updateEditerIcon(mouseOver);
    }
    
    public void chargerMesure(Mesure mesure) {
        NomMesureTextField.setText(mesure.getNom());
        TauxAdhesion.setValue(mesure.getTauxAdhesion());
        TauxReduction.setValue(mesure.getTauxReductionProp());
        SeuilActivation.setValue(mesure.getSeuilActivation());
        setActif(mesure.getActive(), false);
        setEdition(false, false);
    }
    
    public void sauvegarderMesure() {
        try {
            TauxAdhesion.commitEdit();
            TauxReduction.commitEdit();
            SeuilActivation.commitEdit();
        } catch(ParseException pe) {
        }
        
        GestionnaireScenario.getInstance().creerMesure(index, simulationTabs.getIndexPays(), NomMesureTextField.getText(), (double) TauxAdhesion.getValue(), (double) TauxReduction.getValue(), (int) SeuilActivation.getValue(), estActif);
    }
    
    private void updateEditerIcon(boolean actif) {
        mouseOverEdition = actif;
        String path = "/icons/simulation/mesure/";
        path += edition ? "save" : "editer";
        path += actif ? "_highlight.png" : ".png";
        Modifie.setIcon(new ImageIcon(getClass().getResource(path)));
        conteneur.getRootPane().repaint();
    }
    
    private void updateSupprimerIcon(boolean actif) {
        String path = "/icons/simulation/mesure/";
        path += "supprimer";
        path += actif ? "_highlight.png" : ".png";
        Supprimer.setIcon(new ImageIcon(getClass().getResource(path)));
        conteneur.getRootPane().repaint();
    }
    
    private void updateActifIcon(boolean actif) {
        mouseOverActif = actif;
        String path = "/icons/simulation/mesure/";
        path += estActif ? "checked" : "unchecked";
        path += actif ? "_highlight.png" : ".png";
        Activer.setIcon(new ImageIcon(getClass().getResource(path)));
        conteneur.getRootPane().repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitreMesurePanel = new javax.swing.JPanel();
        NomMesureTextField = new javax.swing.JTextField();
        BoutonsPanel = new javax.swing.JPanel();
        Modifie = new javax.swing.JLabel();
        Supprimer = new javax.swing.JLabel();
        Activer = new javax.swing.JLabel();
        TauxReductionPanel = new javax.swing.JPanel();
        TauxReductionLabel = new javax.swing.JLabel();
        TauxReduction = new javax.swing.JSpinner();
        TauxAdhesionPanel = new javax.swing.JPanel();
        TauxAdhesionLabel = new javax.swing.JLabel();
        TauxAdhesion = new javax.swing.JSpinner();
        SeuilActivationPanel = new javax.swing.JPanel();
        SeuilActivationLabel = new javax.swing.JLabel();
        SeuilActivation = new javax.swing.JSpinner();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 15, 0));
        setMaximumSize(new java.awt.Dimension(32767, 135));
        setMinimumSize(new java.awt.Dimension(208, 135));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(208, 135));
        setLayout(new java.awt.GridLayout(4, 1));

        TitreMesurePanel.setOpaque(false);
        TitreMesurePanel.setLayout(new java.awt.BorderLayout());

        NomMesureTextField.setFont(FontRegister.RobotoLight.deriveFont(14f));
        NomMesureTextField.setText("Nom de la mesure");
        NomMesureTextField.setSelectionColor(new java.awt.Color(136, 192, 208));
        TitreMesurePanel.add(NomMesureTextField, java.awt.BorderLayout.CENTER);

        BoutonsPanel.setOpaque(false);
        BoutonsPanel.setLayout(new java.awt.GridLayout(1, 3));

        Modifie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Modifie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/simulation/mesure/editer.png"))); // NOI18N
        Modifie.setMaximumSize(new java.awt.Dimension(30, 30));
        Modifie.setMinimumSize(new java.awt.Dimension(30, 30));
        Modifie.setPreferredSize(new java.awt.Dimension(30, 30));
        Modifie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ModifieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ModifieMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ModifieMouseReleased(evt);
            }
        });
        BoutonsPanel.add(Modifie);

        Supprimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Supprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/simulation/mesure/supprimer.png"))); // NOI18N
        Supprimer.setMaximumSize(new java.awt.Dimension(30, 30));
        Supprimer.setMinimumSize(new java.awt.Dimension(30, 30));
        Supprimer.setPreferredSize(new java.awt.Dimension(30, 30));
        Supprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SupprimerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SupprimerMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SupprimerMouseReleased(evt);
            }
        });
        BoutonsPanel.add(Supprimer);

        Activer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Activer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/simulation/mesure/unchecked.png"))); // NOI18N
        Activer.setMaximumSize(new java.awt.Dimension(30, 30));
        Activer.setMinimumSize(new java.awt.Dimension(30, 30));
        Activer.setPreferredSize(new java.awt.Dimension(30, 30));
        Activer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ActiverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ActiverMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ActiverMouseReleased(evt);
            }
        });
        BoutonsPanel.add(Activer);

        TitreMesurePanel.add(BoutonsPanel, java.awt.BorderLayout.LINE_END);

        add(TitreMesurePanel);

        TauxReductionPanel.setOpaque(false);
        TauxReductionPanel.setLayout(new java.awt.BorderLayout());

        TauxReductionLabel.setFont(FontRegister.RobotoLight.deriveFont(14f));
        TauxReductionLabel.setText("Taux réduction inf. : ");
        TauxReductionLabel.setToolTipText("");
        TauxReductionLabel.setMaximumSize(new java.awt.Dimension(200, 19));
        TauxReductionLabel.setMinimumSize(new java.awt.Dimension(200, 19));
        TauxReductionLabel.setPreferredSize(new java.awt.Dimension(200, 19));
        TauxReductionPanel.add(TauxReductionLabel, java.awt.BorderLayout.WEST);

        TauxReduction.setModel(new javax.swing.SpinnerNumberModel(0.01d, 0.01d, 99.9d, 0.5d));
        TauxReductionPanel.add(TauxReduction, java.awt.BorderLayout.CENTER);

        add(TauxReductionPanel);

        TauxAdhesionPanel.setOpaque(false);
        TauxAdhesionPanel.setLayout(new java.awt.BorderLayout());

        TauxAdhesionLabel.setFont(FontRegister.RobotoLight.deriveFont(14f));
        TauxAdhesionLabel.setText("Taux d'adhésion :");
        TauxAdhesionLabel.setMaximumSize(new java.awt.Dimension(200, 19));
        TauxAdhesionLabel.setMinimumSize(new java.awt.Dimension(200, 19));
        TauxAdhesionLabel.setPreferredSize(new java.awt.Dimension(200, 19));
        TauxAdhesionPanel.add(TauxAdhesionLabel, java.awt.BorderLayout.WEST);

        TauxAdhesion.setModel(new javax.swing.SpinnerNumberModel(0.01d, 0.01d, 99.9d, 0.5d));
        TauxAdhesionPanel.add(TauxAdhesion, java.awt.BorderLayout.CENTER);

        add(TauxAdhesionPanel);

        SeuilActivationPanel.setOpaque(false);
        SeuilActivationPanel.setLayout(new java.awt.BorderLayout());

        SeuilActivationLabel.setFont(FontRegister.RobotoLight.deriveFont(14f));
        SeuilActivationLabel.setText("Sueil d'activation (%) :");
        SeuilActivationLabel.setMaximumSize(new java.awt.Dimension(200, 19));
        SeuilActivationLabel.setMinimumSize(new java.awt.Dimension(200, 19));
        SeuilActivationLabel.setPreferredSize(new java.awt.Dimension(200, 19));
        SeuilActivationPanel.add(SeuilActivationLabel, java.awt.BorderLayout.WEST);

        SeuilActivation.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));
        SeuilActivationPanel.add(SeuilActivation, java.awt.BorderLayout.CENTER);

        add(SeuilActivationPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void ModifieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModifieMouseEntered
        updateEditerIcon(true);
    }//GEN-LAST:event_ModifieMouseEntered

    private void ModifieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModifieMouseExited
        updateEditerIcon(false);
    }//GEN-LAST:event_ModifieMouseExited

    private void ActiverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActiverMouseEntered
        updateActifIcon(true);
    }//GEN-LAST:event_ActiverMouseEntered

    private void ActiverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActiverMouseExited
        updateActifIcon(false);
    }//GEN-LAST:event_ActiverMouseExited

    private void SupprimerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupprimerMouseEntered
        updateSupprimerIcon(true);
    }//GEN-LAST:event_SupprimerMouseEntered

    private void SupprimerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupprimerMouseExited
        updateSupprimerIcon(false);
    }//GEN-LAST:event_SupprimerMouseExited

    private void SupprimerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupprimerMouseReleased
        int result = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer " + "\"" + NomMesureTextField.getText() + "\"?", "", JOptionPane.WARNING_MESSAGE);

        if(result == JOptionPane.YES_OPTION) {
            GestionnaireScenario.getInstance().supprimerMesure(index, simulationTabs.getIndexPays());
            conteneur.remove(this);
            
            simulationTabs.loadMesures();
            
            conteneur.getParent().validate();
            conteneur.getRootPane().repaint();
        }
    }//GEN-LAST:event_SupprimerMouseReleased

    private void ModifieMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModifieMouseReleased
        setEdition(!edition, mouseOverEdition);
        if(!edition) {
            sauvegarderMesure();
        }
    }//GEN-LAST:event_ModifieMouseReleased

    private void ActiverMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActiverMouseReleased
        setActif(!estActif, mouseOverActif);
        sauvegarderMesure();
    }//GEN-LAST:event_ActiverMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Activer;
    private javax.swing.JPanel BoutonsPanel;
    private javax.swing.JLabel Modifie;
    private javax.swing.JTextField NomMesureTextField;
    private javax.swing.JSpinner SeuilActivation;
    private javax.swing.JLabel SeuilActivationLabel;
    private javax.swing.JPanel SeuilActivationPanel;
    private javax.swing.JLabel Supprimer;
    private javax.swing.JSpinner TauxAdhesion;
    private javax.swing.JLabel TauxAdhesionLabel;
    private javax.swing.JPanel TauxAdhesionPanel;
    private javax.swing.JSpinner TauxReduction;
    private javax.swing.JLabel TauxReductionLabel;
    private javax.swing.JPanel TauxReductionPanel;
    private javax.swing.JPanel TitreMesurePanel;
    // End of variables declaration//GEN-END:variables
}
