/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.panels;

import ca.ulaval.glo2004.afficheur.objetsScenario.ObjetScenarioCarte;
import ca.ulaval.glo2004.afficheur.objetsScenario.ObjetScenarioMaladie;
import ca.ulaval.glo2004.afficheur.objetsUI.ObjetScenario;
import ca.ulaval.glo2004.afficheur.onglets.OngletScenario;
import ca.ulaval.glo2004.afficheur.onglets.OngletScenarioCarte;
import ca.ulaval.glo2004.afficheur.onglets.OngletScenarioMaladie;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Maladie;
import ca.ulaval.glo2004.domaine.Scenario;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireCarte;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireMaladie;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireScenario;
import java.awt.Color;
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
    
    private OngletScenarioCarte ongletScenarioCarte;
    private OngletScenarioMaladie ongletScenarioMaladie;
    
    private OngletScenario ongletScenario;
    
    public CreationScenarioPanel() {
        initComponents();
        
        ongletScenarioCarte = new OngletScenarioCarte();
        ongletScenarioMaladie = new OngletScenarioMaladie();
        
        setBackground(new Color(0, 0, 0, 150));
        Color bg = new Color(71, 76, 88);
        BackgroundArrondi.setBackground(bg);
        CartesContainer.setBackground(bg);
        MaladiesContainer.setBackground(bg);
        
        Annuler.setFont(FontRegister.RobotoRegular.deriveFont(15f));
        Annuler.setBackground(bg);
        Creer.setFont(FontRegister.RobotoRegular.deriveFont(15f));
        Creer.setBackground(bg);
        
        CartesScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        MaladiesScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        
        // Gestion pour le bogue de refresh sur le scroll
        CartesScrollPane.getVerticalScrollBar().addAdjustmentListener(this);
        MaladiesScrollPane.getVerticalScrollBar().addAdjustmentListener(this);
    }
    
    public void loadElements() {
        loadCartes();
        loadMaladies();
    }

    public JPanel getCartesContainer() {
        return CartesContainer;
    }

    public void setOngletScenario(OngletScenario ongletScenario) {
        this.ongletScenario = ongletScenario;
    }
    
    public void addCarte(ObjetScenarioCarte objetScenarioCarte) {
        if(CartesContainer.getComponentCount() != 0) {
            CartesContainer.add(getFiller(25));
        }
        CartesContainer.add(objetScenarioCarte);
        CartesScrollPane.revalidate();
        CartesScrollPane.repaint();
    }
    
    public void addMaladie(ObjetScenarioMaladie objetScenarioMaladie) {
        if(MaladiesContainer.getComponentCount() != 0) {
            MaladiesContainer.add(getFiller(25));
        }
        MaladiesContainer.add(objetScenarioMaladie);
        MaladiesScrollPane.revalidate();
        MaladiesScrollPane.repaint();
    }
    
    private Component getFiller(int y) {
        return Box.createRigidArea(new Dimension(0, y));
    }
    
    private void loadCartes() {
        CartesContainer.removeAll();
        ongletScenarioCarte.clear();
        
        List<Carte> cartes = GestionnaireCarte.getInstance().getList();
        for(int index = 0; index < cartes.size(); index++) {
            Carte carte = cartes.get(index);
            ObjetScenarioCarte osc = new ObjetScenarioCarte(ongletScenarioCarte, index,
                                            carte.getNom(), carte.getListePays().size(), carte.getPopulationTotal());
            addCarte(osc);
            ongletScenarioCarte.ajouterObjetUI(osc);
        }
    }
    
    private void loadMaladies() {
        MaladiesContainer.removeAll();
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

        Parent = new javax.swing.JPanel();
        BackgroundArrondi = new ca.ulaval.glo2004.afficheur.PanelArrondi();
        SimulationInput = new javax.swing.JTextField();
        ScrollPanes = new javax.swing.JPanel();
        CartesScrollPane = new javax.swing.JScrollPane();
        CartesContainer = new javax.swing.JPanel();
        MaladiesScrollPane = new javax.swing.JScrollPane();
        MaladiesContainer = new javax.swing.JPanel();
        Boutons = new javax.swing.JPanel();
        Annuler = new javax.swing.JButton();
        Creer = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 0));
        setRequestFocusEnabled(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        setLayout(new javax.swing.OverlayLayout(this));

        Parent.setOpaque(false);

        BackgroundArrondi.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        BackgroundArrondi.setLayout(new java.awt.BorderLayout(15, 0));

        SimulationInput.setBackground(new java.awt.Color(71, 76, 88));
        SimulationInput.setFont(new java.awt.Font("Dialog", 0, 21)); // NOI18N
        SimulationInput.setText("Nom de la simulation");
        SimulationInput.setToolTipText("");
        SimulationInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SimulationInputKeyReleased(evt);
            }
        });
        BackgroundArrondi.add(SimulationInput, java.awt.BorderLayout.NORTH);

        ScrollPanes.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        ScrollPanes.setOpaque(false);
        ScrollPanes.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        CartesScrollPane.setBackground(new java.awt.Color(46, 52, 64));
        CartesScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        CartesScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        CartesScrollPane.setMaximumSize(new java.awt.Dimension(75, 250));
        CartesScrollPane.setMinimumSize(new java.awt.Dimension(75, 250));
        CartesScrollPane.setOpaque(false);
        CartesScrollPane.setPreferredSize(new java.awt.Dimension(75, 250));

        CartesContainer.setBackground(new java.awt.Color(46, 52, 64));
        CartesContainer.setLayout(new javax.swing.BoxLayout(CartesContainer, javax.swing.BoxLayout.Y_AXIS));
        CartesScrollPane.setViewportView(CartesContainer);

        ScrollPanes.add(CartesScrollPane);

        MaladiesScrollPane.setBackground(new java.awt.Color(46, 52, 64));
        MaladiesScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        MaladiesScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        MaladiesScrollPane.setMaximumSize(new java.awt.Dimension(75, 250));
        MaladiesScrollPane.setMinimumSize(new java.awt.Dimension(75, 250));
        MaladiesScrollPane.setOpaque(false);
        MaladiesScrollPane.setPreferredSize(new java.awt.Dimension(75, 250));

        MaladiesContainer.setBackground(new java.awt.Color(46, 52, 64));
        MaladiesContainer.setLayout(new javax.swing.BoxLayout(MaladiesContainer, javax.swing.BoxLayout.Y_AXIS));
        MaladiesScrollPane.setViewportView(MaladiesContainer);

        ScrollPanes.add(MaladiesScrollPane);

        BackgroundArrondi.add(ScrollPanes, java.awt.BorderLayout.CENTER);

        Boutons.setOpaque(false);
        Boutons.setLayout(new java.awt.GridLayout(1, 2, 15, 0));

        Annuler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_multiply_20px.png"))); // NOI18N
        Annuler.setText("Annuler");
        Annuler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AnnulerMouseReleased(evt);
            }
        });
        Boutons.add(Annuler);

        Creer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_checkmark_20px.png"))); // NOI18N
        Creer.setText("Créer");
        Creer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                CreerMouseReleased(evt);
            }
        });
        Boutons.add(Creer);

        javax.swing.GroupLayout ParentLayout = new javax.swing.GroupLayout(Parent);
        Parent.setLayout(ParentLayout);
        ParentLayout.setHorizontalGroup(
            ParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ParentLayout.createSequentialGroup()
                .addContainerGap(280, Short.MAX_VALUE)
                .addGroup(ParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BackgroundArrondi, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                    .addComponent(Boutons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(280, Short.MAX_VALUE))
        );
        ParentLayout.setVerticalGroup(
            ParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ParentLayout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(BackgroundArrondi, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Boutons, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        add(Parent);
    }// </editor-fold>//GEN-END:initComponents

    private void SimulationInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SimulationInputKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.requestFocusInWindow();
            updateUI();
        }
    }//GEN-LAST:event_SimulationInputKeyReleased

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // Ne pas retirer l'event pour bloquer les inputs dans les autres panels
        this.getRootPane().repaint();
    }//GEN-LAST:event_formMouseMoved

    private void AnnulerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AnnulerMouseReleased
        this.setVisible(false);
    }//GEN-LAST:event_AnnulerMouseReleased

    private void CreerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreerMouseReleased
        Object[] args = { SimulationInput.getText(), ongletScenarioCarte.getIndexCourant(), ongletScenarioMaladie.getIndexCourant() };
        Scenario scenario = GestionnaireScenario.getInstance().creer(args);
        ongletScenario.ajouterCard(scenario);
        
        this.setVisible(false);
    }//GEN-LAST:event_CreerMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annuler;
    private ca.ulaval.glo2004.afficheur.PanelArrondi BackgroundArrondi;
    private javax.swing.JPanel Boutons;
    private javax.swing.JPanel CartesContainer;
    private javax.swing.JScrollPane CartesScrollPane;
    private javax.swing.JButton Creer;
    private javax.swing.JPanel MaladiesContainer;
    private javax.swing.JScrollPane MaladiesScrollPane;
    private javax.swing.JPanel Parent;
    private javax.swing.JButton ResumeButton;
    private javax.swing.JButton ResumeButton1;
    private javax.swing.JPanel ScrollPanes;
    private javax.swing.JTextField SimulationInput;
    // End of variables declaration//GEN-END:variables

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        this.getRootPane().repaint();
    }
}
