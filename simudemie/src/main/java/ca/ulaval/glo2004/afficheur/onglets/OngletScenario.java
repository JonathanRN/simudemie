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
    private JFileChooser fileChooser;
    
    public OngletScenario() {
        initComponents();
        
        fileChooser = new JFileChooser();
        try {
            ScenariosScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
            ScenariosLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            Sce_InformationsLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            AddScenarioButton.setBackground(new Color(216, 222, 233, 38));
            ImportScenarioButton.setBackground(new Color(216, 222, 233, 38));
            BoutonExport.setBackground(new Color(216, 222, 233, 38));
            
            // TODO
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
    
    @Override
    public void ajouterObjetUI() {
        super.ajouterObjetUI();
        ObjetScenario card = new ObjetScenario(this);
        card.setSimulationName("Simulation: " + objets.size());
        objets.add(card);
        if (objets.size() == 1) {
            onClickObjetUI(card);
        }
        ProjectPanelContainer.add(card);
        updateUI();
        
        // TODO: Afficher panneau information pour créer scénario
        Object[] args = {"Simulation: " + objets.size()};
        GestionnaireScenario.getInstance().creer(args);
    }

    private void ajouterCard(Scenario scenario) {
        ObjetScenario card = new ObjetScenario(this);
        card.setSimulationName(scenario.getNom());
        card.setDays("NaN"); // TODO: Changer pour afficher le vrai jour de la simulation
        //card.setMapName(scenario.getCarteJourCourant().getNom());
        //card.setVirusName(scenario.getCarteJourCourant().getMaladie().getNom());
        card.setInfectedPercent(0);
        card.setCuredPercent(0);
        card.setImmunedPercent(0);
        card.setDeadPercent(0);
        
        objets.add(card);
        ProjectPanelContainer.add(card);
        
        if(objets.size() == 1) {
            onClickObjetUI(card);
        }
        
        updateUI();
    }
    
    @Override
    public void retirerCourant() {
        int result = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer ce scénario?", "", JOptionPane.WARNING_MESSAGE);
        
        if(result == JOptionPane.YES_OPTION && objets.size() > 0) {
            ProjectPanelContainer.remove(courant);
            updateUI();

            super.retirerCourant();
        }
    }

    @Override
    public void onClickObjetUI(ObjetUI objet) {
        super.onClickObjetUI(objet);
        ObjetScenario objetSimulation = (ObjetScenario)courant;
        scenarioMapPanel2.setMapName(objetSimulation.getMapName());
    }
    
    public void onStartSimulation() {
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("Voulez-vous commencer cette simulation?");
        optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        optionPane.setOptionType(JOptionPane.YES_NO_CANCEL_OPTION);
        
        int result = JOptionPane.showOptionDialog(
            SwingUtilities.windowForComponent(this),
            optionPane.getMessage(),
            "Commencer la simulation?",
            optionPane.getOptionType(),
            optionPane.getMessageType(),
            optionPane.getIcon(),
            optionPane.getOptions(),
            optionPane.getInitialValue());
        
        if (result == JOptionPane.YES_OPTION) {
            FramePrincipal frame = (FramePrincipal)SwingUtilities.windowForComponent(this);
            frame.startSimulation();
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

        Scenarios = new javax.swing.JPanel();
        ScenariosTitle = new javax.swing.JPanel();
        ScenariosLabel = new javax.swing.JLabel();
        AddScenarioButton = new javax.swing.JButton();
        ImportScenarioButton = new javax.swing.JButton();
        BoutonExport = new javax.swing.JButton();
        ScenariosScrollPane = new javax.swing.JScrollPane();
        ProjectPanelContainer = new javax.swing.JPanel();
        Sce_Informations = new javax.swing.JPanel();
        Sce_InformationsLabel = new javax.swing.JLabel();
        Layout = new ca.ulaval.glo2004.afficheur.PanelArrondi();
        scenarioMapPanel2 = new ca.ulaval.glo2004.afficheur.panels.CarteScenarioPanel();
        scenarioStatsPanel1 = new ca.ulaval.glo2004.afficheur.panels.StatsScenarioPanel();

        setBackground(new java.awt.Color(46, 52, 64));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 35, 35));
        setLayout(new java.awt.BorderLayout());

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
        ImportScenarioButton.setToolTipText("Importer un scénario");
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

        javax.swing.GroupLayout ScenariosTitleLayout = new javax.swing.GroupLayout(ScenariosTitle);
        ScenariosTitle.setLayout(ScenariosTitleLayout);
        ScenariosTitleLayout.setHorizontalGroup(
            ScenariosTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ScenariosTitleLayout.createSequentialGroup()
                .addComponent(ScenariosLabel)
                .addGap(20, 20, 20)
                .addComponent(AddScenarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ImportScenarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 609, Short.MAX_VALUE)
                .addComponent(BoutonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        ScenariosTitleLayout.setVerticalGroup(
            ScenariosTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ScenariosTitleLayout.createSequentialGroup()
                .addGroup(ScenariosTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ScenariosTitleLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(ScenariosLabel))
                    .addComponent(BoutonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddScenarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ImportScenarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        add(Scenarios, java.awt.BorderLayout.NORTH);

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
        Layout.add(scenarioMapPanel2);
        Layout.add(scenarioStatsPanel1);

        Sce_Informations.add(Layout, java.awt.BorderLayout.CENTER);

        add(Sce_Informations, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void AddScenarioButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddScenarioButtonMouseReleased
        this.ajouterObjetUI();
    }//GEN-LAST:event_AddScenarioButtonMouseReleased

    private void ImportScenarioButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImportScenarioButtonMouseReleased
        fileChooser.showOpenDialog(null);
        if(fileChooser.getSelectedFile() != null) {
            Scenario scenario = GestionnaireScenario.getInstance().importer(fileChooser.getSelectedFile().toString());
            ajouterCard(scenario);
            fileChooser.setSelectedFile(null);
        }
    }//GEN-LAST:event_ImportScenarioButtonMouseReleased

    private void BoutonExportMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoutonExportMouseReleased
        fileChooser.showOpenDialog(null);
        if(fileChooser.getSelectedFile() != null) {
            GestionnaireScenario.getInstance().exporter(getIndexCourant(), fileChooser.getSelectedFile().toString());
            fileChooser.setSelectedFile(null);
        }
    }//GEN-LAST:event_BoutonExportMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddScenarioButton;
    private javax.swing.JButton BoutonExport;
    private javax.swing.JButton ImportScenarioButton;
    private ca.ulaval.glo2004.afficheur.PanelArrondi Layout;
    private javax.swing.JPanel ProjectPanelContainer;
    private javax.swing.JPanel Sce_Informations;
    private javax.swing.JLabel Sce_InformationsLabel;
    private javax.swing.JPanel Scenarios;
    private javax.swing.JLabel ScenariosLabel;
    private javax.swing.JScrollPane ScenariosScrollPane;
    private javax.swing.JPanel ScenariosTitle;
    private ca.ulaval.glo2004.afficheur.panels.CarteScenarioPanel scenarioMapPanel2;
    private ca.ulaval.glo2004.afficheur.panels.StatsScenarioPanel scenarioStatsPanel1;
    // End of variables declaration//GEN-END:variables
}
