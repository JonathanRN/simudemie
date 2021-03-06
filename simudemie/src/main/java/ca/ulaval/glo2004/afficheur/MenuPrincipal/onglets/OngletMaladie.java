/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.MenuPrincipal.onglets;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.objetsUI.ObjetMaladie;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.objetsUI.ObjetUI;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import ca.ulaval.glo2004.domaine.Maladie;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireMaladie;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonathan
 */
public class OngletMaladie extends OngletUI {
    
    private final GestionnaireMaladie controller;
    
    public OngletMaladie() {
        initComponents();
        
        controller = GestionnaireMaladie.getInstance();
        try {        
            MaladiesScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
            MaladieLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            InformationsMaladieLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            AjoutMaladieBouton.setBackground(Couleurs.sains);
            SupprimeMaladieBouton.setBackground(Couleurs.infections);
            ImportMaladieBouton.setBackground(Couleurs.pannelArrondi);
            ExportMaladieBouton.setBackground(Couleurs.pannelArrondi);
        } catch (Exception e) {
        }
    }
    
    public void init() {
        statsMaladiePanel1.setOnglet(this);
        
        for(Maladie maladie : controller.getList()) {
            ajouterCard(maladie);
        }
    }
    
    @Override
    public void ajouterObjetUI() {
        String nomMaladie = JOptionPane.showInputDialog(this, "Entrez le nom de la nouvelle maladie", "", JOptionPane.QUESTION_MESSAGE);
        
        if (nomMaladie != null && !nomMaladie.isEmpty()) {
            super.ajouterObjetUI();        
            ObjetMaladie card = new ObjetMaladie(this);

            card.setNom(nomMaladie);
            objets.add(card);

            Object[] args = { objets.size() - 1, card.getNom(), 0.0, 0.0, 0.0, 14, false};
            controller.creer(args);

            onClickObjetUI(card);
            ConteneurMaladiePanel.add(card);

            updateUI();
        }
    }

    private void ajouterCard(Maladie maladie) {
        ObjetMaladie card = new ObjetMaladie(this);
        card.setNom(maladie.getNom());
        card.setInfectedProgressBar(maladie.getTauxInfection());
        card.setCuredProgressBar(maladie.getTauxGuerison());
        card.setDeadProgressBar(maladie.getTauxMortalite());
        objets.add(card);
        ConteneurMaladiePanel.add(card);
        onClickObjetUI(card);
        
        updateUI();
    }
    
    @Override
    public void retirerCourant() {
        if(objets.size() > 0 && courant != null) {
            int result = JOptionPane.showConfirmDialog(this, "??tes-vous s??r de vouloir supprimer cette maladie?", "", JOptionPane.WARNING_MESSAGE);
            
            if(result == JOptionPane.YES_OPTION) {
                controller.supprimer(getIndexCourant());

                ConteneurMaladiePanel.remove(courant);
                objets.remove(courant);
                updateUI();

                super.retirerCourant();
            }
        }
        
        if(objets.isEmpty()){
            statsMaladiePanel1.showFields(false);
        }
    }

    @Override
    public void onClickObjetUI(ObjetUI objet) {
        super.onClickObjetUI(objet);
        Maladie maladie = new Maladie(controller.getElement(getIndexCourant()));
        statsMaladiePanel1.setDeadInput(maladie.getTauxMortalite());
        statsMaladiePanel1.setNomMaladie(maladie.getNom());
        statsMaladiePanel1.setInfectionInput(maladie.getTauxInfection());
        statsMaladiePanel1.setCuredInput(maladie.getTauxGuerison());
        statsMaladiePanel1.setIncubationInput(maladie.getIncubation());
        statsMaladiePanel1.setImmunizationCheckbox(maladie.getImmunitePossible());
        statsMaladiePanel1.showFields(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Maladies = new javax.swing.JPanel();
        TitreMaladiePanel = new javax.swing.JPanel();
        MaladieLabel = new javax.swing.JLabel();
        AjoutMaladieBouton = new javax.swing.JButton();
        SupprimeMaladieBouton = new javax.swing.JButton();
        ImportMaladieBouton = new javax.swing.JButton();
        ExportMaladieBouton = new javax.swing.JButton();
        MaladiesScrollPane = new javax.swing.JScrollPane();
        ConteneurMaladiePanel = new javax.swing.JPanel();
        InfoMaladiePanel = new javax.swing.JPanel();
        InformationsMaladieLabel = new javax.swing.JLabel();
        Layout = new ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi();
        statsMaladiePanel1 = new ca.ulaval.glo2004.afficheur.MenuPrincipal.panels.StatsMaladiePanel();

        setBackground(new java.awt.Color(46, 52, 64));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 35, 35));
        setLayout(new java.awt.BorderLayout());

        Maladies.setBackground(new java.awt.Color(46, 52, 64));
        Maladies.setBorder(javax.swing.BorderFactory.createEmptyBorder(35, 0, 0, 0));
        Maladies.setLayout(new java.awt.BorderLayout());

        TitreMaladiePanel.setBackground(new java.awt.Color(46, 52, 64));
        TitreMaladiePanel.setMaximumSize(new java.awt.Dimension(974, 50));
        TitreMaladiePanel.setMinimumSize(new java.awt.Dimension(974, 50));
        TitreMaladiePanel.setPreferredSize(new java.awt.Dimension(974, 35));

        MaladieLabel.setBackground(new java.awt.Color(46, 52, 64));
        MaladieLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        MaladieLabel.setText("Maladies");
        MaladieLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        AjoutMaladieBouton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        AjoutMaladieBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_plus_math_20px.png"))); // NOI18N
        AjoutMaladieBouton.setToolTipText("Cr??er une nouvelle maladie");
        AjoutMaladieBouton.setFocusable(false);
        AjoutMaladieBouton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AjoutMaladieBouton.setMaximumSize(new java.awt.Dimension(75, 30));
        AjoutMaladieBouton.setMinimumSize(new java.awt.Dimension(75, 30));
        AjoutMaladieBouton.setPreferredSize(new java.awt.Dimension(100, 36));
        AjoutMaladieBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AjoutMaladieBoutonMouseReleased(evt);
            }
        });

        SupprimeMaladieBouton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        SupprimeMaladieBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_trash_can_20px.png"))); // NOI18N
        SupprimeMaladieBouton.setFocusable(false);
        SupprimeMaladieBouton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SupprimeMaladieBouton.setMaximumSize(new java.awt.Dimension(75, 30));
        SupprimeMaladieBouton.setMinimumSize(new java.awt.Dimension(75, 30));
        SupprimeMaladieBouton.setPreferredSize(new java.awt.Dimension(100, 36));
        SupprimeMaladieBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SupprimeMaladieBoutonMouseReleased(evt);
            }
        });

        ImportMaladieBouton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ImportMaladieBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_installing_updates_20px.png"))); // NOI18N
        ImportMaladieBouton.setToolTipText("Importer une maladie existante");
        ImportMaladieBouton.setFocusable(false);
        ImportMaladieBouton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ImportMaladieBouton.setMaximumSize(new java.awt.Dimension(75, 30));
        ImportMaladieBouton.setMinimumSize(new java.awt.Dimension(75, 30));
        ImportMaladieBouton.setPreferredSize(new java.awt.Dimension(100, 36));
        ImportMaladieBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ImportMaladieBoutonMouseReleased(evt);
            }
        });

        ExportMaladieBouton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ExportMaladieBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_upload_20px.png"))); // NOI18N
        ExportMaladieBouton.setToolTipText("Exporter la maladie courante");
        ExportMaladieBouton.setFocusable(false);
        ExportMaladieBouton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ExportMaladieBouton.setMaximumSize(new java.awt.Dimension(75, 30));
        ExportMaladieBouton.setMinimumSize(new java.awt.Dimension(75, 30));
        ExportMaladieBouton.setPreferredSize(new java.awt.Dimension(100, 36));
        ExportMaladieBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ExportMaladieBoutonMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout TitreMaladiePanelLayout = new javax.swing.GroupLayout(TitreMaladiePanel);
        TitreMaladiePanel.setLayout(TitreMaladiePanelLayout);
        TitreMaladiePanelLayout.setHorizontalGroup(
            TitreMaladiePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitreMaladiePanelLayout.createSequentialGroup()
                .addComponent(MaladieLabel)
                .addGap(101, 101, 101)
                .addComponent(AjoutMaladieBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SupprimeMaladieBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 892, Short.MAX_VALUE)
                .addComponent(ImportMaladieBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ExportMaladieBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        TitreMaladiePanelLayout.setVerticalGroup(
            TitreMaladiePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitreMaladiePanelLayout.createSequentialGroup()
                .addGroup(TitreMaladiePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TitreMaladiePanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(MaladieLabel))
                    .addComponent(ExportMaladieBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ImportMaladieBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AjoutMaladieBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SupprimeMaladieBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        Maladies.add(TitreMaladiePanel, java.awt.BorderLayout.NORTH);

        MaladiesScrollPane.setBackground(new java.awt.Color(46, 52, 64));
        MaladiesScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        MaladiesScrollPane.setMaximumSize(new java.awt.Dimension(75, 250));
        MaladiesScrollPane.setMinimumSize(new java.awt.Dimension(75, 250));
        MaladiesScrollPane.setPreferredSize(new java.awt.Dimension(75, 250));

        ConteneurMaladiePanel.setBackground(new java.awt.Color(46, 52, 64));
        ConteneurMaladiePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, -25, 0, 0));
        ConteneurMaladiePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 25, 5));
        MaladiesScrollPane.setViewportView(ConteneurMaladiePanel);

        Maladies.add(MaladiesScrollPane, java.awt.BorderLayout.CENTER);

        add(Maladies, java.awt.BorderLayout.NORTH);

        InfoMaladiePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(35, 0, 0, 0));
        InfoMaladiePanel.setOpaque(false);
        InfoMaladiePanel.setLayout(new java.awt.BorderLayout());

        InformationsMaladieLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        InformationsMaladieLabel.setText("Informations");
        InformationsMaladieLabel.setMaximumSize(new java.awt.Dimension(146, 35));
        InformationsMaladieLabel.setMinimumSize(new java.awt.Dimension(146, 35));
        InformationsMaladieLabel.setPreferredSize(new java.awt.Dimension(146, 35));
        InfoMaladiePanel.add(InformationsMaladieLabel, java.awt.BorderLayout.NORTH);

        Layout.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        Layout.setOpaque(false);
        Layout.setLayout(new java.awt.GridLayout(1, 1));
        Layout.add(statsMaladiePanel1);

        InfoMaladiePanel.add(Layout, java.awt.BorderLayout.CENTER);

        add(InfoMaladiePanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void AddScenarioButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddScenarioButtonMouseClicked
  
    }//GEN-LAST:event_AddScenarioButtonMouseClicked

    private void ImportMaladieBoutonMouseReleased(java.awt.event.MouseEvent evt) {                                                   
        int result = fileChooser.showDialog(null, "Importer");
        if(fileChooser.getSelectedFile() != null  && result == JFileChooser.OPEN_DIALOG) {
            try {
                Maladie maladie = controller.importer(fileChooser.getSelectedFile().toString());
                ajouterCard(maladie);
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, "Une erreur est survenue lors de l'importation d'une maladie.", "", JOptionPane.ERROR_MESSAGE);
            }
            fileChooser.setSelectedFile(null);
        }
    }                                                 

    private void ExportMaladieBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExportMaladieBoutonMouseReleased
        if (getCourant() != null) {
            int result = fileChooser.showDialog(null, "Exporter");
            if(fileChooser.getSelectedFile() != null  && result == JFileChooser.OPEN_DIALOG) {
                try {
                controller.exporter(getIndexCourant(), fileChooser.getSelectedFile().toString());
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(this, "Une erreur est survenue lors de l'exportation d'une maladie.", "", JOptionPane.ERROR_MESSAGE);
                }
                fileChooser.setSelectedFile(null);
            }
        }
    }//GEN-LAST:event_ExportMaladieBoutonMouseReleased

    private void AjoutMaladieBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjoutMaladieBoutonMouseReleased
        this.ajouterObjetUI();
    }//GEN-LAST:event_AjoutMaladieBoutonMouseReleased

    private void SupprimeMaladieBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupprimeMaladieBoutonMouseReleased
        retirerCourant();
    }//GEN-LAST:event_SupprimeMaladieBoutonMouseReleased
    
    public GestionnaireMaladie getController() {
        return controller;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AjoutMaladieBouton;
    private javax.swing.JPanel ConteneurMaladiePanel;
    private javax.swing.JButton ExportMaladieBouton;
    private javax.swing.JButton ImportMaladieBouton;
    private javax.swing.JPanel InfoMaladiePanel;
    private javax.swing.JLabel InformationsMaladieLabel;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi Layout;
    private javax.swing.JLabel MaladieLabel;
    private javax.swing.JPanel Maladies;
    private javax.swing.JScrollPane MaladiesScrollPane;
    private javax.swing.JButton SupprimeMaladieBouton;
    private javax.swing.JPanel TitreMaladiePanel;
    private ca.ulaval.glo2004.afficheur.MenuPrincipal.panels.StatsMaladiePanel statsMaladiePanel1;
    // End of variables declaration//GEN-END:variables
}
