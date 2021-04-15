/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.MenuPrincipal.onglets;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.FramePrincipal;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.objetsUI.ObjetScenario;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.objetsUI.ObjetUI;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Scenario;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireCarte;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireMaladie;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireScenario;
import java.awt.Color;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jonathan
 */
public class OngletScenario extends OngletUI {
        
    public OngletScenario() {
        initComponents();
        
        try {
            CreationScenarioPanel.setVisible(false);
            CreationScenarioPanel.setOngletScenario(this);
            
            ScenariosScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
            TitreScenariosLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            InformationsScenarioLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            AjoutScenarionBouton.setBackground(Couleurs.sains);
            SupprimeScenarioBouton.setBackground(Couleurs.infections);
            ImportScenarioBouton.setBackground(Couleurs.pannelArrondi);
            ExportScenarionBouton.setBackground(Couleurs.pannelArrondi);
            
            StatsScenarioPanel1.setOnglet(this);
        } catch (Exception e) {
        }
    }
    
    public void init() {
        StatsScenarioPanel1.setOnglet(this);
        
        for(Scenario scenario : GestionnaireScenario.getInstance().getList()) {
            ajouterCard(scenario);
        }
    }
    
    private Scenario getScenarioCourant() {
        return GestionnaireScenario.getInstance().getElement(this.getIndexCourant());
    }

    @Override
    public void onClickObjetUI(ObjetUI objet) {
        super.onClickObjetUI(objet);
        Scenario scenario = GestionnaireScenario.getInstance().getElement(this.getIndexCourant());
        
        StatsScenarioPanel1.getResumeButton().setText(getScenarioCourant().estCommence() ? "Résumer" : "Commencer");
        StatsScenarioPanel1.setDataset(scenario);
        
        if (scenario != null) {
            ApercuScenarioCartePanel.setPreviewVisibility(true);
            ApercuScenarioCartePanel.setCarte(scenario.getCarteJourCourant());
            ObjetScenario obj = (ObjetScenario) objet;
            obj.setInfectedPercent((float)scenario.getCarteDernierJour().getPourcentageInfectee() * 100f);
            obj.setCuredPercent((float)scenario.getCarteDernierJour().getPourcentageSaine() * 100f);
            obj.setDeadPercent((float)scenario.getCarteDernierJour().getPourcentageDecedee() * 100f);
        }
    }
    
    @Override
    public void ajouterObjetUI() {
        CreationScenarioPanel.setVisible(true);
        CreationScenarioPanel.loadElements();
    }

    public void ajouterCard(Scenario scenario) {
        ObjetScenario card = new ObjetScenario(this);
        card.setSimulationName(scenario.getNom());
        card.setDays(1);
        card.setMapName(scenario.getCarteJourCourant().getNom());
        card.setVirusName(scenario.getCarteJourCourant().getMaladie().getNom());
        card.setInfectedPercent(0);
        card.setCuredPercent(0);
        //card.setImmunedPercent(0);
        card.setDeadPercent(0);
        
        objets.add(card);
        ConteneurScenarioPanel.add(card);
        
        onClickObjetUI(card);
        
        updateUI();
    }
    
    @Override
    public void retirerCourant() {
        if(objets.size() > 0) {
            int result = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer ce scénario?", "", JOptionPane.WARNING_MESSAGE);

            if(result == JOptionPane.YES_OPTION && objets.size() > 0) {
                GestionnaireScenario.getInstance().supprimer(getIndexCourant());
                ConteneurScenarioPanel.remove(courant);
                updateUI();
                
                super.retirerCourant();
            }
            
            if (objets.isEmpty()) {
                ApercuScenarioCartePanel.setPreviewVisibility(false);
                StatsScenarioPanel1.setDataset(null);
            }
        }
    }
    
    public void onStartSimulation() {
        Scenario scenario = getScenarioCourant();
        String texte = scenario.estCommence() ? "Voulez-vous résumer cette simulation?" : "Voulez-vous commencer cette simulation?";
        String titre = scenario.estCommence() ? "Résumer la simulation?" : "Commencer la simulation?";
        
        int result = JOptionPane.showConfirmDialog(this, texte, titre, JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            startSimulation();
        }
    }
    
    private void startSimulation() {
        FramePrincipal frame = (FramePrincipal)SwingUtilities.windowForComponent(this);
        frame.startSimulation(getIndexCourant(), this);
    }
    
    private boolean contientMaladieEtCarte() {
        return GestionnaireMaladie.getInstance().getList().size() > 0 &&
                GestionnaireCarte.getInstance().getList().size() > 0 ;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CreationScenarioPanel = new ca.ulaval.glo2004.afficheur.MenuPrincipal.panels.CreationScenarioPanel();
        Main = new javax.swing.JPanel();
        ScenariosPanel = new javax.swing.JPanel();
        TitreScenarioPanel = new javax.swing.JPanel();
        TitreScenariosLabel = new javax.swing.JLabel();
        AjoutScenarionBouton = new javax.swing.JButton();
        ImportScenarioBouton = new javax.swing.JButton();
        ExportScenarionBouton = new javax.swing.JButton();
        SupprimeScenarioBouton = new javax.swing.JButton();
        ScenariosScrollPane = new javax.swing.JScrollPane();
        ConteneurScenarioPanel = new javax.swing.JPanel();
        InformationsScenarioPanel = new javax.swing.JPanel();
        InformationsScenarioLabel = new javax.swing.JLabel();
        Layout = new ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi();
        ApercuScenarioPanel = new javax.swing.JPanel();
        ApercuScenarioCartePanel = new ca.ulaval.glo2004.afficheur.MenuPrincipal.panels.ApercuPanel();
        StatsScenarioPanel1 = new ca.ulaval.glo2004.afficheur.MenuPrincipal.panels.StatsScenarioPanel();

        setBackground(new java.awt.Color(46, 52, 64));
        setOpaque(false);
        setLayout(new javax.swing.OverlayLayout(this));
        add(CreationScenarioPanel);

        Main.setBackground(new java.awt.Color(46, 52, 64));
        Main.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 35, 35));
        Main.setLayout(new java.awt.BorderLayout());

        ScenariosPanel.setBackground(new java.awt.Color(46, 52, 64));
        ScenariosPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(35, 0, 0, 0));
        ScenariosPanel.setLayout(new java.awt.BorderLayout());

        TitreScenarioPanel.setBackground(new java.awt.Color(46, 52, 64));
        TitreScenarioPanel.setMaximumSize(new java.awt.Dimension(974, 50));
        TitreScenarioPanel.setMinimumSize(new java.awt.Dimension(974, 50));
        TitreScenarioPanel.setPreferredSize(new java.awt.Dimension(974, 35));

        TitreScenariosLabel.setBackground(new java.awt.Color(46, 52, 64));
        TitreScenariosLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        TitreScenariosLabel.setText("Scénarios");
        TitreScenariosLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        AjoutScenarionBouton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        AjoutScenarionBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_plus_math_20px.png"))); // NOI18N
        AjoutScenarionBouton.setToolTipText("Créer un nouveau scénario");
        AjoutScenarionBouton.setFocusable(false);
        AjoutScenarionBouton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AjoutScenarionBouton.setMaximumSize(new java.awt.Dimension(75, 30));
        AjoutScenarionBouton.setMinimumSize(new java.awt.Dimension(75, 30));
        AjoutScenarionBouton.setPreferredSize(new java.awt.Dimension(100, 36));
        AjoutScenarionBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AjoutScenarionBoutonMouseReleased(evt);
            }
        });

        ImportScenarioBouton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ImportScenarioBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_installing_updates_20px.png"))); // NOI18N
        ImportScenarioBouton.setToolTipText("Importer un scénario existant");
        ImportScenarioBouton.setFocusable(false);
        ImportScenarioBouton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ImportScenarioBouton.setMaximumSize(new java.awt.Dimension(75, 30));
        ImportScenarioBouton.setMinimumSize(new java.awt.Dimension(75, 30));
        ImportScenarioBouton.setPreferredSize(new java.awt.Dimension(100, 36));
        ImportScenarioBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ImportScenarioBoutonMouseReleased(evt);
            }
        });

        ExportScenarionBouton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ExportScenarionBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_upload_20px.png"))); // NOI18N
        ExportScenarionBouton.setToolTipText("Exporter le scénario courant");
        ExportScenarionBouton.setFocusable(false);
        ExportScenarionBouton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ExportScenarionBouton.setMaximumSize(new java.awt.Dimension(75, 30));
        ExportScenarionBouton.setMinimumSize(new java.awt.Dimension(75, 30));
        ExportScenarionBouton.setPreferredSize(new java.awt.Dimension(100, 36));
        ExportScenarionBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ExportScenarionBoutonMouseReleased(evt);
            }
        });

        SupprimeScenarioBouton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        SupprimeScenarioBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_trash_can_20px.png"))); // NOI18N
        SupprimeScenarioBouton.setToolTipText("Supprimer le scénario courant");
        SupprimeScenarioBouton.setFocusable(false);
        SupprimeScenarioBouton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SupprimeScenarioBouton.setMaximumSize(new java.awt.Dimension(75, 30));
        SupprimeScenarioBouton.setMinimumSize(new java.awt.Dimension(75, 30));
        SupprimeScenarioBouton.setPreferredSize(new java.awt.Dimension(100, 36));
        SupprimeScenarioBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SupprimeScenarioBoutonMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout TitreScenarioPanelLayout = new javax.swing.GroupLayout(TitreScenarioPanel);
        TitreScenarioPanel.setLayout(TitreScenarioPanelLayout);
        TitreScenarioPanelLayout.setHorizontalGroup(
            TitreScenarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitreScenarioPanelLayout.createSequentialGroup()
                .addComponent(TitreScenariosLabel)
                .addGap(90, 90, 90)
                .addComponent(AjoutScenarionBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SupprimeScenarioBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 915, Short.MAX_VALUE)
                .addComponent(ImportScenarioBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ExportScenarionBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        TitreScenarioPanelLayout.setVerticalGroup(
            TitreScenarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitreScenarioPanelLayout.createSequentialGroup()
                .addGroup(TitreScenarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TitreScenarioPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(TitreScenariosLabel))
                    .addComponent(SupprimeScenarioBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AjoutScenarionBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ImportScenarioBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExportScenarionBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        ScenariosPanel.add(TitreScenarioPanel, java.awt.BorderLayout.NORTH);

        ScenariosScrollPane.setBackground(new java.awt.Color(46, 52, 64));
        ScenariosScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        ScenariosScrollPane.setMaximumSize(new java.awt.Dimension(75, 250));
        ScenariosScrollPane.setMinimumSize(new java.awt.Dimension(75, 250));
        ScenariosScrollPane.setPreferredSize(new java.awt.Dimension(75, 250));

        ConteneurScenarioPanel.setBackground(new java.awt.Color(46, 52, 64));
        ConteneurScenarioPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, -25, 0, 0));
        ConteneurScenarioPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 25, 5));
        ScenariosScrollPane.setViewportView(ConteneurScenarioPanel);

        ScenariosPanel.add(ScenariosScrollPane, java.awt.BorderLayout.CENTER);

        Main.add(ScenariosPanel, java.awt.BorderLayout.NORTH);

        InformationsScenarioPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(35, 0, 0, 0));
        InformationsScenarioPanel.setOpaque(false);
        InformationsScenarioPanel.setLayout(new java.awt.BorderLayout());

        InformationsScenarioLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        InformationsScenarioLabel.setText("Informations");
        InformationsScenarioLabel.setMaximumSize(new java.awt.Dimension(146, 35));
        InformationsScenarioLabel.setMinimumSize(new java.awt.Dimension(146, 35));
        InformationsScenarioLabel.setPreferredSize(new java.awt.Dimension(146, 35));
        InformationsScenarioPanel.add(InformationsScenarioLabel, java.awt.BorderLayout.NORTH);

        Layout.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        Layout.setOpaque(false);
        Layout.setLayout(new java.awt.GridLayout(1, 2, 25, 0));

        ApercuScenarioPanel.setOpaque(false);
        ApercuScenarioPanel.setPreferredSize(new java.awt.Dimension(207, 58));
        ApercuScenarioPanel.setLayout(new javax.swing.OverlayLayout(ApercuScenarioPanel));
        ApercuScenarioPanel.add(ApercuScenarioCartePanel);

        Layout.add(ApercuScenarioPanel);
        Layout.add(StatsScenarioPanel1);

        InformationsScenarioPanel.add(Layout, java.awt.BorderLayout.CENTER);

        Main.add(InformationsScenarioPanel, java.awt.BorderLayout.CENTER);

        add(Main);
    }// </editor-fold>//GEN-END:initComponents

    private void AjoutScenarionBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjoutScenarionBoutonMouseReleased
        if (contientMaladieEtCarte()) {
            this.ajouterObjetUI();
        } else {
            JOptionPane.showMessageDialog(this, "Vous devez avoir au moins une maladie et une carte dans votre répertoire.",
                    "", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_AjoutScenarionBoutonMouseReleased

    private void ImportScenarioBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImportScenarioBoutonMouseReleased
        int result = fileChooser.showDialog(null, "Importer");
        if(fileChooser.getSelectedFile() != null && result == JFileChooser.OPEN_DIALOG) {
            try {
                Scenario scenario = GestionnaireScenario.getInstance().importer(fileChooser.getSelectedFile().toString());
                ajouterCard(scenario);
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, "Une erreur est survenue lors de l'importation d'un scénario.", "", JOptionPane.ERROR_MESSAGE);
            }
            fileChooser.setSelectedFile(null);
        }
    }//GEN-LAST:event_ImportScenarioBoutonMouseReleased

    private void SupprimeScenarioBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupprimeScenarioBoutonMouseReleased
        retirerCourant();
    }//GEN-LAST:event_SupprimeScenarioBoutonMouseReleased

    private void ExportScenarionBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExportScenarionBoutonMouseReleased
        if(getCourant() != null) {
            int result = fileChooser.showDialog(null, "Exporter");
            if(fileChooser.getSelectedFile() != null && result == JFileChooser.OPEN_DIALOG) {
                try {
                    GestionnaireScenario.getInstance().exporter(getIndexCourant(), fileChooser.getSelectedFile().toString());
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(this, "Une erreur est survenue lors de l'exportation d'un scénario.", "", JOptionPane.ERROR_MESSAGE);
                }
                fileChooser.setSelectedFile(null);
            }
        }
    }//GEN-LAST:event_ExportScenarionBoutonMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AjoutScenarionBouton;
    private ca.ulaval.glo2004.afficheur.MenuPrincipal.panels.ApercuPanel ApercuScenarioCartePanel;
    private javax.swing.JPanel ApercuScenarioPanel;
    private javax.swing.JPanel ConteneurScenarioPanel;
    private ca.ulaval.glo2004.afficheur.MenuPrincipal.panels.CreationScenarioPanel CreationScenarioPanel;
    private javax.swing.JButton ExportScenarionBouton;
    private javax.swing.JButton ImportScenarioBouton;
    private javax.swing.JLabel InformationsScenarioLabel;
    private javax.swing.JPanel InformationsScenarioPanel;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi Layout;
    private javax.swing.JPanel Main;
    private javax.swing.JPanel ScenariosPanel;
    private javax.swing.JScrollPane ScenariosScrollPane;
    private ca.ulaval.glo2004.afficheur.MenuPrincipal.panels.StatsScenarioPanel StatsScenarioPanel1;
    private javax.swing.JButton SupprimeScenarioBouton;
    private javax.swing.JPanel TitreScenarioPanel;
    private javax.swing.JLabel TitreScenariosLabel;
    // End of variables declaration//GEN-END:variables
}
