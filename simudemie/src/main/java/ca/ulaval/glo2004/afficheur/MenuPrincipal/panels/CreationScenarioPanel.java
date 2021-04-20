/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.MenuPrincipal.panels;

import ca.ulaval.glo2004.afficheur.MenuPrincipal.objetsUI.ObjetScenarioCarte;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.objetsUI.ObjetScenarioMaladie;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.onglets.OngletScenario;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.onglets.OngletCreationScenarioCarte;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.onglets.OngletCreationScenarioMaladie;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Maladie;
import ca.ulaval.glo2004.domaine.Scenario;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireCarte;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireMaladie;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireScenario;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.Box;
import javax.swing.JPanel;

/**
 *
 * @author Mick
 */
public class CreationScenarioPanel extends javax.swing.JPanel implements AdjustmentListener {
    
    private OngletCreationScenarioCarte ongletScenarioCarte;
    private OngletCreationScenarioMaladie ongletScenarioMaladie;
    
    private OngletScenario ongletScenario;
    
    public CreationScenarioPanel() {
        initComponents();
        
        try {
            ongletScenarioCarte = new OngletCreationScenarioCarte();
            ongletScenarioMaladie = new OngletCreationScenarioMaladie();

            SimulationInput.setFont(FontRegister.RobotoRegular.deriveFont(16f));
            SelectNomLabel.setFont(FontRegister.RobotoRegular.deriveFont(18f));
            SelectObjetLabel.setFont(FontRegister.RobotoRegular.deriveFont(18f));
            super.setBackground(Couleurs.fondNoir);
            BackgroundArrondi.setBackground(Couleurs.pannelArrondiNoTransp);
            ConteneurCartesPanel.setBackground(Couleurs.pannelArrondiNoTransp);
            ConteneurMaladiesPanel.setBackground(Couleurs.pannelArrondiNoTransp);

            AnnulerBouton.setFont(FontRegister.RobotoRegular.deriveFont(15f));
            AnnulerBouton.setBackground(Couleurs.pannelArrondiNoTransp);
            CreerBouton.setFont(FontRegister.RobotoRegular.deriveFont(15f));
            CreerBouton.setBackground(Couleurs.pannelArrondiNoTransp);

            CartesScrollPane.getVerticalScrollBar().setUnitIncrement(20);
            MaladiesScrollPane.getVerticalScrollBar().setUnitIncrement(20);

            CartesScrollPane.getVerticalScrollBar().addAdjustmentListener(this);
            MaladiesScrollPane.getVerticalScrollBar().addAdjustmentListener(this);
            MaladiesScrollPane.setBackground(Couleurs.pannelArrondiNoTransp);
            MaladiesScrollPane.getViewport().setOpaque(false);
            CartesScrollPane.setBackground(Couleurs.pannelArrondiNoTransp);
            CartesScrollPane.getViewport().setOpaque(false);
        }
        catch (Exception e) {
        }
        
    }
    
    public void loadElements() {
        loadCartes();
        loadMaladies();
    }

    public JPanel getCartesContainer() {
        return ConteneurCartesPanel;
    }

    public void setOngletScenario(OngletScenario ongletScenario) {
        this.ongletScenario = ongletScenario;
    }
    
    public void addCarte(ObjetScenarioCarte objetScenarioCarte) {
        if(ConteneurCartesPanel.getComponentCount() != 0) {
            ConteneurCartesPanel.add(getFiller(25));
        }
        ConteneurCartesPanel.add(objetScenarioCarte);
        CartesScrollPane.revalidate();
        CartesScrollPane.repaint();
    }
    
    public void addMaladie(ObjetScenarioMaladie objetScenarioMaladie) {
        if(ConteneurMaladiesPanel.getComponentCount() != 0) {
            ConteneurMaladiesPanel.add(getFiller(25));
        }
        ConteneurMaladiesPanel.add(objetScenarioMaladie);
        MaladiesScrollPane.revalidate();
        MaladiesScrollPane.repaint();
    }
    
    private Component getFiller(int y) {
        return Box.createRigidArea(new Dimension(0, y));
    }
    
    private void loadCartes() {
        ConteneurCartesPanel.removeAll();
        ongletScenarioCarte.clear();
        
        List<Carte> cartes = GestionnaireCarte.getInstance().getList();
        for(int index = 0; index < cartes.size(); index++) {
            Carte carte = cartes.get(index);
            ObjetScenarioCarte osc = new ObjetScenarioCarte(ongletScenarioCarte, index,
                                            carte.getNom(), carte.getListePays().size(), carte.getPopulationTotale());
            addCarte(osc);
            ongletScenarioCarte.ajouterObjetUI(osc);
        }
    }
    
    private void loadMaladies() {
        ConteneurMaladiesPanel.removeAll();
        ongletScenarioMaladie.clear();
        
        List<Maladie> maladies = GestionnaireMaladie.getInstance().getList();
        for(int index = 0; index < maladies.size(); index++) {
            Maladie maladie = maladies.get(index);
            ObjetScenarioMaladie osm = new ObjetScenarioMaladie(ongletScenarioMaladie, index, maladie.getNom(),
                                            maladie.getTauxInfection(), maladie.getTauxGuerison(), maladie.getTauxMortalite());
            addMaladie(osm);
            ongletScenarioMaladie.ajouterObjetUI(osm);
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

        CreationScenarioPanel = new javax.swing.JPanel();
        BackgroundArrondi = new ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi();
        NomPanel = new javax.swing.JPanel();
        SelectNomLabel = new javax.swing.JLabel();
        SimulationInput = new javax.swing.JTextField();
        SelectObjetLabel = new javax.swing.JLabel();
        CreationScenarioScrollPanes = new javax.swing.JPanel();
        CartesScrollPane = new javax.swing.JScrollPane();
        ConteneurCartesPanel = new javax.swing.JPanel();
        MaladiesScrollPane = new javax.swing.JScrollPane();
        ConteneurMaladiesPanel = new javax.swing.JPanel();
        BoutonsPanel = new javax.swing.JPanel();
        AnnulerBouton = new javax.swing.JButton();
        CreerBouton = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 0));
        setRequestFocusEnabled(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        setLayout(new javax.swing.OverlayLayout(this));

        CreationScenarioPanel.setOpaque(false);

        BackgroundArrondi.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        BackgroundArrondi.setLayout(new java.awt.BorderLayout(15, 0));

        NomPanel.setOpaque(false);
        NomPanel.setLayout(new java.awt.GridLayout(3, 0));

        SelectNomLabel.setText("Entrez le nom de ce nouveau scénario ");
        NomPanel.add(SelectNomLabel);

        SimulationInput.setBackground(new java.awt.Color(71, 76, 88));
        SimulationInput.setFont(new java.awt.Font("Dialog", 0, 21)); // NOI18N
        SimulationInput.setToolTipText("Nom de la simulation");
        SimulationInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimulationInputActionPerformed(evt);
            }
        });
        SimulationInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SimulationInputKeyReleased(evt);
            }
        });
        NomPanel.add(SimulationInput);

        SelectObjetLabel.setText("Choisir la carte et la maladie");
        SelectObjetLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 1, 1, 1));
        NomPanel.add(SelectObjetLabel);

        BackgroundArrondi.add(NomPanel, java.awt.BorderLayout.PAGE_START);

        CreationScenarioScrollPanes.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        CreationScenarioScrollPanes.setOpaque(false);
        CreationScenarioScrollPanes.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        CartesScrollPane.setBackground(new java.awt.Color(46, 52, 64));
        CartesScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        CartesScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        CartesScrollPane.setMaximumSize(new java.awt.Dimension(75, 250));
        CartesScrollPane.setMinimumSize(new java.awt.Dimension(75, 250));
        CartesScrollPane.setOpaque(false);
        CartesScrollPane.setPreferredSize(new java.awt.Dimension(75, 250));

        ConteneurCartesPanel.setBackground(Couleurs.pannelArrondi);
        ConteneurCartesPanel.setOpaque(false);
        ConteneurCartesPanel.setLayout(new javax.swing.BoxLayout(ConteneurCartesPanel, javax.swing.BoxLayout.Y_AXIS));
        CartesScrollPane.setViewportView(ConteneurCartesPanel);

        CreationScenarioScrollPanes.add(CartesScrollPane);

        MaladiesScrollPane.setBackground(new java.awt.Color(46, 52, 64));
        MaladiesScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        MaladiesScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        MaladiesScrollPane.setMaximumSize(new java.awt.Dimension(75, 250));
        MaladiesScrollPane.setMinimumSize(new java.awt.Dimension(75, 250));
        MaladiesScrollPane.setOpaque(false);
        MaladiesScrollPane.setPreferredSize(new java.awt.Dimension(75, 250));

        ConteneurMaladiesPanel.setBackground(Couleurs.pannelArrondi);
        ConteneurMaladiesPanel.setOpaque(false);
        ConteneurMaladiesPanel.setLayout(new javax.swing.BoxLayout(ConteneurMaladiesPanel, javax.swing.BoxLayout.Y_AXIS));
        MaladiesScrollPane.setViewportView(ConteneurMaladiesPanel);

        CreationScenarioScrollPanes.add(MaladiesScrollPane);

        BackgroundArrondi.add(CreationScenarioScrollPanes, java.awt.BorderLayout.CENTER);

        BoutonsPanel.setOpaque(false);
        BoutonsPanel.setLayout(new java.awt.GridLayout(1, 2, 15, 0));

        AnnulerBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_multiply_20px.png"))); // NOI18N
        AnnulerBouton.setText("Annuler");
        AnnulerBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AnnulerBoutonMouseReleased(evt);
            }
        });
        BoutonsPanel.add(AnnulerBouton);

        CreerBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_checkmark_20px.png"))); // NOI18N
        CreerBouton.setText("Créer");
        CreerBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                CreerBoutonMouseReleased(evt);
            }
        });
        BoutonsPanel.add(CreerBouton);

        javax.swing.GroupLayout CreationScenarioPanelLayout = new javax.swing.GroupLayout(CreationScenarioPanel);
        CreationScenarioPanel.setLayout(CreationScenarioPanelLayout);
        CreationScenarioPanelLayout.setHorizontalGroup(
            CreationScenarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreationScenarioPanelLayout.createSequentialGroup()
                .addContainerGap(280, Short.MAX_VALUE)
                .addGroup(CreationScenarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BackgroundArrondi, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                    .addComponent(BoutonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(280, Short.MAX_VALUE))
        );
        CreationScenarioPanelLayout.setVerticalGroup(
            CreationScenarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreationScenarioPanelLayout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addComponent(BackgroundArrondi, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BoutonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        add(CreationScenarioPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void SimulationInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SimulationInputKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.requestFocusInWindow();
        }
    }//GEN-LAST:event_SimulationInputKeyReleased

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // Ne pas retirer l'event pour bloquer les inputs dans les autres panels
        this.getRootPane().repaint();
    }//GEN-LAST:event_formMouseMoved

    private void AnnulerBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AnnulerBoutonMouseReleased
        this.setVisible(false);
    }//GEN-LAST:event_AnnulerBoutonMouseReleased

    private void CreerBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreerBoutonMouseReleased
        if (!SimulationInput.getText().isEmpty()){ 
        Object[] args = { SimulationInput.getText(), ongletScenarioCarte.getIndexCourant(), ongletScenarioMaladie.getIndexCourant() };
        Scenario scenario = GestionnaireScenario.getInstance().creer(args);
        ongletScenario.ajouterCard(scenario);
        
        this.setVisible(false);   
        }
        else { 
          SimulationInput.requestFocus();
        }
    }//GEN-LAST:event_CreerBoutonMouseReleased

    private void SimulationInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimulationInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SimulationInputActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AnnulerBouton;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi BackgroundArrondi;
    private javax.swing.JPanel BoutonsPanel;
    private javax.swing.JScrollPane CartesScrollPane;
    private javax.swing.JPanel ConteneurCartesPanel;
    private javax.swing.JPanel ConteneurMaladiesPanel;
    private javax.swing.JPanel CreationScenarioPanel;
    private javax.swing.JPanel CreationScenarioScrollPanes;
    private javax.swing.JButton CreerBouton;
    private javax.swing.JScrollPane MaladiesScrollPane;
    private javax.swing.JPanel NomPanel;
    private javax.swing.JLabel SelectNomLabel;
    private javax.swing.JLabel SelectObjetLabel;
    private javax.swing.JTextField SimulationInput;
    // End of variables declaration//GEN-END:variables

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        this.getRootPane().repaint();
    }
}
