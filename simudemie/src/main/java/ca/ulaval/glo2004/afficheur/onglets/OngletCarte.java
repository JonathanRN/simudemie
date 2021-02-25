/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.onglets;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.objetsUI.ObjetCarte;
import ca.ulaval.glo2004.afficheur.objetsUI.ObjetUI;
import java.awt.Color;

/**
 *
 * @author Jonathan
 */
public class OngletCarte extends OngletUI {

    /**
     * Creates new form ScenarioTab
     */
    public OngletCarte() {
        initComponents();
        
        try {
            ajouterObjetUI();
            mapStatsPanel1.setOnglet(this);
            
            MapsScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
            MapsLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            Map_InformationsLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            AddMapButton.setBackground(new Color(216, 222, 233, 38));
            ImportMapButton.setBackground(new Color(216, 222, 233, 38));
            BoutonExport.setBackground(new Color(216, 222, 233, 38));
            
            Layout.setOpaque(false);
        }
        catch (Exception e) {
        }
    }
    
    @Override
    public void ajouterObjetUI() {
        super.ajouterObjetUI();
        ObjetCarte card = new ObjetCarte(this);
        card.setMapName("Carte: " + objets.size());
        objets.add(card);
        if (objets.size() == 1) {
            onClickObjetUI(card);
        }
        MapPanelContainer.add(card);
        updateUI();
    }

    @Override
    public void retirerCourant() {
        MapPanelContainer.remove(courant);
        updateUI();
        
        super.retirerCourant();
    }

    @Override
    public void onClickObjetUI(ObjetUI objet) {
        super.onClickObjetUI(objet);
        ObjetCarte objetCarte = (ObjetCarte)objet;
        scenarioMapPanel1.setMapName(objetCarte.getNomCarte());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cartes = new javax.swing.JPanel();
        MapTitlePanel = new javax.swing.JPanel();
        MapsLabel = new javax.swing.JLabel();
        AddMapButton = new javax.swing.JButton();
        ImportMapButton = new javax.swing.JButton();
        BoutonExport = new javax.swing.JButton();
        MapsScrollPane = new javax.swing.JScrollPane();
        MapPanelContainer = new javax.swing.JPanel();
        Map_Informations = new javax.swing.JPanel();
        Map_InformationsLabel = new javax.swing.JLabel();
        Layout = new ca.ulaval.glo2004.afficheur.PanelArrondi();
        scenarioMapPanel1 = new ca.ulaval.glo2004.afficheur.panels.CarteScenarioPanel();
        mapStatsPanel1 = new ca.ulaval.glo2004.afficheur.panels.StatsCartePanel();

        setBackground(new java.awt.Color(46, 52, 64));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 35, 35));
        setLayout(new java.awt.BorderLayout());

        Cartes.setBackground(new java.awt.Color(46, 52, 64));
        Cartes.setBorder(javax.swing.BorderFactory.createEmptyBorder(35, 0, 0, 0));
        Cartes.setLayout(new java.awt.BorderLayout());

        MapTitlePanel.setBackground(new java.awt.Color(46, 52, 64));
        MapTitlePanel.setMaximumSize(new java.awt.Dimension(974, 50));
        MapTitlePanel.setMinimumSize(new java.awt.Dimension(974, 50));
        MapTitlePanel.setPreferredSize(new java.awt.Dimension(974, 35));

        MapsLabel.setBackground(new java.awt.Color(46, 52, 64));
        MapsLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        MapsLabel.setText("Cartes");
        MapsLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        AddMapButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        AddMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_plus_math_20px.png"))); // NOI18N
        AddMapButton.setFocusable(false);
        AddMapButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AddMapButton.setMaximumSize(new java.awt.Dimension(75, 30));
        AddMapButton.setMinimumSize(new java.awt.Dimension(75, 30));
        AddMapButton.setPreferredSize(new java.awt.Dimension(100, 36));
        AddMapButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddMapButtonMouseClicked(evt);
            }
        });

        ImportMapButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ImportMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_installing_updates_20px.png"))); // NOI18N
        ImportMapButton.setFocusable(false);
        ImportMapButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ImportMapButton.setMaximumSize(new java.awt.Dimension(75, 30));
        ImportMapButton.setMinimumSize(new java.awt.Dimension(75, 30));
        ImportMapButton.setPreferredSize(new java.awt.Dimension(100, 36));

        BoutonExport.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        BoutonExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_upload_20px.png"))); // NOI18N
        BoutonExport.setFocusable(false);
        BoutonExport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BoutonExport.setMaximumSize(new java.awt.Dimension(75, 30));
        BoutonExport.setMinimumSize(new java.awt.Dimension(75, 30));
        BoutonExport.setPreferredSize(new java.awt.Dimension(100, 36));

        javax.swing.GroupLayout MapTitlePanelLayout = new javax.swing.GroupLayout(MapTitlePanel);
        MapTitlePanel.setLayout(MapTitlePanelLayout);
        MapTitlePanelLayout.setHorizontalGroup(
            MapTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MapTitlePanelLayout.createSequentialGroup()
                .addComponent(MapsLabel)
                .addGap(20, 20, 20)
                .addComponent(AddMapButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ImportMapButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 650, Short.MAX_VALUE)
                .addComponent(BoutonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        MapTitlePanelLayout.setVerticalGroup(
            MapTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MapTitlePanelLayout.createSequentialGroup()
                .addGroup(MapTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MapTitlePanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(MapsLabel))
                    .addComponent(AddMapButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ImportMapButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BoutonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        Cartes.add(MapTitlePanel, java.awt.BorderLayout.NORTH);

        MapsScrollPane.setBackground(new java.awt.Color(46, 52, 64));
        MapsScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        MapsScrollPane.setMaximumSize(new java.awt.Dimension(75, 250));
        MapsScrollPane.setMinimumSize(new java.awt.Dimension(75, 250));
        MapsScrollPane.setPreferredSize(new java.awt.Dimension(75, 250));

        MapPanelContainer.setBackground(new java.awt.Color(46, 52, 64));
        MapPanelContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, -25, 0, 0));
        MapPanelContainer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 25, 5));
        MapsScrollPane.setViewportView(MapPanelContainer);

        Cartes.add(MapsScrollPane, java.awt.BorderLayout.CENTER);

        add(Cartes, java.awt.BorderLayout.NORTH);

        Map_Informations.setBorder(javax.swing.BorderFactory.createEmptyBorder(35, 0, 0, 0));
        Map_Informations.setOpaque(false);
        Map_Informations.setLayout(new java.awt.BorderLayout());

        Map_InformationsLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        Map_InformationsLabel.setText("Informations");
        Map_InformationsLabel.setMaximumSize(new java.awt.Dimension(146, 35));
        Map_InformationsLabel.setMinimumSize(new java.awt.Dimension(146, 35));
        Map_InformationsLabel.setPreferredSize(new java.awt.Dimension(146, 35));
        Map_Informations.add(Map_InformationsLabel, java.awt.BorderLayout.NORTH);

        Layout.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        Layout.setOpaque(false);
        Layout.setLayout(new java.awt.GridLayout(1, 2, 25, 0));
        Layout.add(scenarioMapPanel1);
        Layout.add(mapStatsPanel1);

        Map_Informations.add(Layout, java.awt.BorderLayout.CENTER);

        add(Map_Informations, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void AddMapButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddMapButtonMouseClicked
        ajouterObjetUI();
    }//GEN-LAST:event_AddMapButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddMapButton;
    private javax.swing.JButton BoutonExport;
    private javax.swing.JPanel Cartes;
    private javax.swing.JButton ImportMapButton;
    private ca.ulaval.glo2004.afficheur.PanelArrondi Layout;
    private javax.swing.JPanel MapPanelContainer;
    private javax.swing.JPanel MapTitlePanel;
    private javax.swing.JPanel Map_Informations;
    private javax.swing.JLabel Map_InformationsLabel;
    private javax.swing.JLabel MapsLabel;
    private javax.swing.JScrollPane MapsScrollPane;
    private ca.ulaval.glo2004.afficheur.panels.StatsCartePanel mapStatsPanel1;
    private ca.ulaval.glo2004.afficheur.panels.CarteScenarioPanel scenarioMapPanel1;
    // End of variables declaration//GEN-END:variables
}