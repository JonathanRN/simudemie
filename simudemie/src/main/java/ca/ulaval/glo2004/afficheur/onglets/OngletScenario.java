/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.onglets;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.FramePrincipal;
import ca.ulaval.glo2004.afficheur.objetsUI.ObjetScenario;
import ca.ulaval.glo2004.afficheur.objetsUI.ObjetUI;
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
            ScenariosLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            Sce_InformationsLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            AddScenarioButton.setBackground(new Color(163,190,140));
            BoutonDelete.setBackground(new Color(191, 97, 106));
            ImportScenarioButton.setBackground(new Color(216, 222, 233, 38));
            BoutonExport.setBackground(new Color(216, 222, 233, 38));
            
            scenarioStatsPanel1.setOnglet(this);
        } catch (Exception e) {
        }
    }
    
    public void init() {
        scenarioStatsPanel1.setOnglet(this);
        
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
        
        scenarioStatsPanel1.getResumeButton().setText(getScenarioCourant().estCommence() ? "Résumer" : "Commencer");
        
        Scenario scenario = GestionnaireScenario.getInstance().getElement(this.getIndexCourant());
        if (scenario != null) {
            scenarioMapPanel2.setPreviewVisibility(true);
            scenarioMapPanel2.setCarte(scenario.getCarteJourCourant());
            
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
        ProjectPanelContainer.add(card);
        
        onClickObjetUI(card);
        
        updateUI();
    }
    
    @Override
    public void retirerCourant() {
        if(objets.size() > 0) {
            int result = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer ce scénario?", "", JOptionPane.WARNING_MESSAGE);

            if(result == JOptionPane.YES_OPTION && objets.size() > 0) {
                GestionnaireScenario.getInstance().supprimer(getIndexCourant());
                ProjectPanelContainer.remove(courant);
                updateUI();
                
                super.retirerCourant();
            }
            
            if (objets.isEmpty()) {
                scenarioMapPanel2.setPreviewVisibility(false);
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
                GestionnaireCarte.getInstance().getList().size() > 0;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CreationScenarioPanel = new ca.ulaval.glo2004.afficheur.panels.CreationScenarioPanel();
        Main = new javax.swing.JPanel();
        Scenarios = new javax.swing.JPanel();
        ScenariosTitle = new javax.swing.JPanel();
        ScenariosLabel = new javax.swing.JLabel();
        AddScenarioButton = new javax.swing.JButton();
        ImportScenarioButton = new javax.swing.JButton();
        BoutonExport = new javax.swing.JButton();
        BoutonDelete = new javax.swing.JButton();
        ScenariosScrollPane = new javax.swing.JScrollPane();
        ProjectPanelContainer = new javax.swing.JPanel();
        Sce_Informations = new javax.swing.JPanel();
        Sce_InformationsLabel = new javax.swing.JLabel();
        Layout = new ca.ulaval.glo2004.afficheur.PanelArrondi();
        jPanel1 = new javax.swing.JPanel();
        scenarioMapPanel2 = new ca.ulaval.glo2004.afficheur.panels.CarteScenarioPanel();
        scenarioStatsPanel1 = new ca.ulaval.glo2004.afficheur.panels.StatsScenarioPanel();

        setBackground(new java.awt.Color(46, 52, 64));
        setOpaque(false);
        setLayout(new javax.swing.OverlayLayout(this));
        add(CreationScenarioPanel);

        Main.setBackground(new java.awt.Color(46, 52, 64));
        Main.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 35, 35));
        Main.setLayout(new java.awt.BorderLayout());

        Scenarios.setBackground(new java.awt.Color(46, 52, 64));
        Scenarios.setBorder(javax.swing.BorderFactory.createEmptyBorder(35, 0, 0, 0));
        Scenarios.setLayout(new java.awt.BorderLayout());

        ScenariosTitle.setBackground(new java.awt.Color(46, 52, 64));
        ScenariosTitle.setMaximumSize(new java.awt.Dimension(974, 50));
        ScenariosTitle.setMinimumSize(new java.awt.Dimension(974, 50));
        ScenariosTitle.setPreferredSize(new java.awt.Dimension(974, 35));

        ScenariosLabel.setBackground(new java.awt.Color(46, 52, 64));
        ScenariosLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        ScenariosLabel.setText("Scénarios");
        ScenariosLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        AddScenarioButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        AddScenarioButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_plus_math_20px.png"))); // NOI18N
        AddScenarioButton.setToolTipText("Créer un nouveau scénario");
        AddScenarioButton.setFocusable(false);
        AddScenarioButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AddScenarioButton.setMaximumSize(new java.awt.Dimension(75, 30));
        AddScenarioButton.setMinimumSize(new java.awt.Dimension(75, 30));
        AddScenarioButton.setPreferredSize(new java.awt.Dimension(100, 36));
        AddScenarioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AddScenarioButtonMouseReleased(evt);
            }
        });

        ImportScenarioButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ImportScenarioButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_installing_updates_20px.png"))); // NOI18N
        ImportScenarioButton.setToolTipText("Importer un scénario existant");
        ImportScenarioButton.setFocusable(false);
        ImportScenarioButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ImportScenarioButton.setMaximumSize(new java.awt.Dimension(75, 30));
        ImportScenarioButton.setMinimumSize(new java.awt.Dimension(75, 30));
        ImportScenarioButton.setPreferredSize(new java.awt.Dimension(100, 36));
        ImportScenarioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ImportScenarioButtonMouseReleased(evt);
            }
        });

        BoutonExport.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        BoutonExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_upload_20px.png"))); // NOI18N
        BoutonExport.setToolTipText("Exporter le scénario courant");
        BoutonExport.setFocusable(false);
        BoutonExport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BoutonExport.setMaximumSize(new java.awt.Dimension(75, 30));
        BoutonExport.setMinimumSize(new java.awt.Dimension(75, 30));
        BoutonExport.setPreferredSize(new java.awt.Dimension(100, 36));
        BoutonExport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BoutonExportMouseReleased(evt);
            }
        });

        BoutonDelete.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        BoutonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_trash_can_20px.png"))); // NOI18N
        BoutonDelete.setToolTipText("Supprimer le scénario courant");
        BoutonDelete.setFocusable(false);
        BoutonDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BoutonDelete.setMaximumSize(new java.awt.Dimension(75, 30));
        BoutonDelete.setMinimumSize(new java.awt.Dimension(75, 30));
        BoutonDelete.setPreferredSize(new java.awt.Dimension(100, 36));
        BoutonDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BoutonDeleteMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout ScenariosTitleLayout = new javax.swing.GroupLayout(ScenariosTitle);
        ScenariosTitle.setLayout(ScenariosTitleLayout);
        ScenariosTitleLayout.setHorizontalGroup(
            ScenariosTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ScenariosTitleLayout.createSequentialGroup()
                .addComponent(ScenariosLabel)
                .addGap(90, 90, 90)
                .addComponent(AddScenarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BoutonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 915, Short.MAX_VALUE)
                .addComponent(ImportScenarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BoutonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        ScenariosTitleLayout.setVerticalGroup(
            ScenariosTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ScenariosTitleLayout.createSequentialGroup()
                .addGroup(ScenariosTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ScenariosTitleLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(ScenariosLabel))
                    .addComponent(BoutonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddScenarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ImportScenarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BoutonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        Scenarios.add(ScenariosTitle, java.awt.BorderLayout.NORTH);

        ScenariosScrollPane.setBackground(new java.awt.Color(46, 52, 64));
        ScenariosScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        ScenariosScrollPane.setMaximumSize(new java.awt.Dimension(75, 250));
        ScenariosScrollPane.setMinimumSize(new java.awt.Dimension(75, 250));
        ScenariosScrollPane.setPreferredSize(new java.awt.Dimension(75, 250));

        ProjectPanelContainer.setBackground(new java.awt.Color(46, 52, 64));
        ProjectPanelContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, -25, 0, 0));
        ProjectPanelContainer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 25, 5));
        ScenariosScrollPane.setViewportView(ProjectPanelContainer);

        Scenarios.add(ScenariosScrollPane, java.awt.BorderLayout.CENTER);

        Main.add(Scenarios, java.awt.BorderLayout.NORTH);

        Sce_Informations.setBorder(javax.swing.BorderFactory.createEmptyBorder(35, 0, 0, 0));
        Sce_Informations.setOpaque(false);
        Sce_Informations.setLayout(new java.awt.BorderLayout());

        Sce_InformationsLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        Sce_InformationsLabel.setText("Informations");
        Sce_InformationsLabel.setMaximumSize(new java.awt.Dimension(146, 35));
        Sce_InformationsLabel.setMinimumSize(new java.awt.Dimension(146, 35));
        Sce_InformationsLabel.setPreferredSize(new java.awt.Dimension(146, 35));
        Sce_Informations.add(Sce_InformationsLabel, java.awt.BorderLayout.NORTH);

        Layout.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        Layout.setOpaque(false);
        Layout.setLayout(new java.awt.GridLayout(1, 2, 25, 0));

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(207, 58));
        jPanel1.setLayout(new javax.swing.OverlayLayout(jPanel1));
        jPanel1.add(scenarioMapPanel2);

        Layout.add(jPanel1);
        Layout.add(scenarioStatsPanel1);

        Sce_Informations.add(Layout, java.awt.BorderLayout.CENTER);

        Main.add(Sce_Informations, java.awt.BorderLayout.CENTER);

        add(Main);
    }// </editor-fold>//GEN-END:initComponents

    private void AddScenarioButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddScenarioButtonMouseReleased
        if (contientMaladieEtCarte()) {
            this.ajouterObjetUI();
        } else {
            JOptionPane.showMessageDialog(this, "Vous devez avoir au moins une maladie et une carte dans votre répertoire.",
                    "", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_AddScenarioButtonMouseReleased

    private void ImportScenarioButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImportScenarioButtonMouseReleased
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
    }//GEN-LAST:event_ImportScenarioButtonMouseReleased

    private void BoutonDeleteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoutonDeleteMouseReleased
        retirerCourant();
    }//GEN-LAST:event_BoutonDeleteMouseReleased

    private void BoutonExportMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoutonExportMouseReleased
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
    }//GEN-LAST:event_BoutonExportMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddScenarioButton;
    private javax.swing.JButton BoutonDelete;
    private javax.swing.JButton BoutonExport;
    private ca.ulaval.glo2004.afficheur.panels.CreationScenarioPanel CreationScenarioPanel;
    private javax.swing.JButton ImportScenarioButton;
    private ca.ulaval.glo2004.afficheur.PanelArrondi Layout;
    private javax.swing.JPanel Main;
    private javax.swing.JPanel ProjectPanelContainer;
    private javax.swing.JPanel Sce_Informations;
    private javax.swing.JLabel Sce_InformationsLabel;
    private javax.swing.JPanel Scenarios;
    private javax.swing.JLabel ScenariosLabel;
    private javax.swing.JScrollPane ScenariosScrollPane;
    private javax.swing.JPanel ScenariosTitle;
    private javax.swing.JPanel jPanel1;
    private ca.ulaval.glo2004.afficheur.panels.CarteScenarioPanel scenarioMapPanel2;
    private ca.ulaval.glo2004.afficheur.panels.StatsScenarioPanel scenarioStatsPanel1;
    // End of variables declaration//GEN-END:variables
}
