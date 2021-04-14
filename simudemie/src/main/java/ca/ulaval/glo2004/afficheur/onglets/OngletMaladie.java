/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.onglets;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.objetsUI.ObjetMaladie;
import ca.ulaval.glo2004.afficheur.objetsUI.ObjetUI;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import ca.ulaval.glo2004.domaine.Maladie;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireMaladie;
import java.awt.Color;
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
            ScenariosScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
            MaladieLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            Mal_InformationsLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            AddMaladieButton.setBackground(Couleurs.sains);
            DeleteMaladieButton.setBackground(Couleurs.infections);
            ImportMaladieButton.setBackground(Couleurs.pannelArrondi);
            BoutonExport.setBackground(Couleurs.pannelArrondi);
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

            Object[] args = { objets.size() - 1, card.getNom(), 0.0, 0.0, 0.0 };
            controller.creer(args);

            onClickObjetUI(card);
            MaladiesContainer.add(card);

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
        MaladiesContainer.add(card);
        onClickObjetUI(card);
        
        updateUI();
    }
    
    @Override
    public void retirerCourant() {
        if(objets.size() > 0 && courant != null) {
            int result = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer cette maladie?", "", JOptionPane.WARNING_MESSAGE);
            
            if(result == JOptionPane.YES_OPTION) {
                controller.supprimer(getIndexCourant());

                MaladiesContainer.remove(courant);
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
        MaladieTitle = new javax.swing.JPanel();
        MaladieLabel = new javax.swing.JLabel();
        AddMaladieButton = new javax.swing.JButton();
        DeleteMaladieButton = new javax.swing.JButton();
        ImportMaladieButton = new javax.swing.JButton();
        BoutonExport = new javax.swing.JButton();
        ScenariosScrollPane = new javax.swing.JScrollPane();
        MaladiesContainer = new javax.swing.JPanel();
        Mal_Informations = new javax.swing.JPanel();
        Mal_InformationsLabel = new javax.swing.JLabel();
        Layout = new ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi();
        statsMaladiePanel1 = new ca.ulaval.glo2004.afficheur.panels.StatsMaladiePanel();

        setBackground(new java.awt.Color(46, 52, 64));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 35, 35));
        setLayout(new java.awt.BorderLayout());

        Maladies.setBackground(new java.awt.Color(46, 52, 64));
        Maladies.setBorder(javax.swing.BorderFactory.createEmptyBorder(35, 0, 0, 0));
        Maladies.setLayout(new java.awt.BorderLayout());

        MaladieTitle.setBackground(new java.awt.Color(46, 52, 64));
        MaladieTitle.setMaximumSize(new java.awt.Dimension(974, 50));
        MaladieTitle.setMinimumSize(new java.awt.Dimension(974, 50));
        MaladieTitle.setPreferredSize(new java.awt.Dimension(974, 35));

        MaladieLabel.setBackground(new java.awt.Color(46, 52, 64));
        MaladieLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        MaladieLabel.setText("Maladies");
        MaladieLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        AddMaladieButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        AddMaladieButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_plus_math_20px.png"))); // NOI18N
        AddMaladieButton.setFocusable(false);
        AddMaladieButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AddMaladieButton.setMaximumSize(new java.awt.Dimension(75, 30));
        AddMaladieButton.setMinimumSize(new java.awt.Dimension(75, 30));
        AddMaladieButton.setPreferredSize(new java.awt.Dimension(100, 36));
        AddMaladieButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AddMaladieButtonMouseReleased(evt);
            }
        });

        DeleteMaladieButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        DeleteMaladieButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_trash_can_20px.png"))); // NOI18N
        DeleteMaladieButton.setFocusable(false);
        DeleteMaladieButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DeleteMaladieButton.setMaximumSize(new java.awt.Dimension(75, 30));
        DeleteMaladieButton.setMinimumSize(new java.awt.Dimension(75, 30));
        DeleteMaladieButton.setPreferredSize(new java.awt.Dimension(100, 36));
        DeleteMaladieButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                DeleteMaladieButtonMouseReleased(evt);
            }
        });

        ImportMaladieButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ImportMaladieButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_installing_updates_20px.png"))); // NOI18N
        ImportMaladieButton.setFocusable(false);
        ImportMaladieButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ImportMaladieButton.setMaximumSize(new java.awt.Dimension(75, 30));
        ImportMaladieButton.setMinimumSize(new java.awt.Dimension(75, 30));
        ImportMaladieButton.setPreferredSize(new java.awt.Dimension(100, 36));
        ImportMaladieButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ImportMaladieButtonMouseReleased(evt);
            }
        });

        BoutonExport.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        BoutonExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_upload_20px.png"))); // NOI18N
        BoutonExport.setToolTipText("Exporter la maladie courante");
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

        javax.swing.GroupLayout MaladieTitleLayout = new javax.swing.GroupLayout(MaladieTitle);
        MaladieTitle.setLayout(MaladieTitleLayout);
        MaladieTitleLayout.setHorizontalGroup(
            MaladieTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaladieTitleLayout.createSequentialGroup()
                .addComponent(MaladieLabel)
                .addGap(101, 101, 101)
                .addComponent(AddMaladieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DeleteMaladieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 892, Short.MAX_VALUE)
                .addComponent(ImportMaladieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BoutonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        MaladieTitleLayout.setVerticalGroup(
            MaladieTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaladieTitleLayout.createSequentialGroup()
                .addGroup(MaladieTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MaladieTitleLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(MaladieLabel))
                    .addComponent(BoutonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ImportMaladieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddMaladieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteMaladieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        Maladies.add(MaladieTitle, java.awt.BorderLayout.NORTH);

        ScenariosScrollPane.setBackground(new java.awt.Color(46, 52, 64));
        ScenariosScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        ScenariosScrollPane.setMaximumSize(new java.awt.Dimension(75, 250));
        ScenariosScrollPane.setMinimumSize(new java.awt.Dimension(75, 250));
        ScenariosScrollPane.setPreferredSize(new java.awt.Dimension(75, 250));

        MaladiesContainer.setBackground(new java.awt.Color(46, 52, 64));
        MaladiesContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, -25, 0, 0));
        MaladiesContainer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 25, 5));
        ScenariosScrollPane.setViewportView(MaladiesContainer);

        Maladies.add(ScenariosScrollPane, java.awt.BorderLayout.CENTER);

        add(Maladies, java.awt.BorderLayout.NORTH);

        Mal_Informations.setBorder(javax.swing.BorderFactory.createEmptyBorder(35, 0, 0, 0));
        Mal_Informations.setOpaque(false);
        Mal_Informations.setLayout(new java.awt.BorderLayout());

        Mal_InformationsLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        Mal_InformationsLabel.setText("Informations");
        Mal_InformationsLabel.setMaximumSize(new java.awt.Dimension(146, 35));
        Mal_InformationsLabel.setMinimumSize(new java.awt.Dimension(146, 35));
        Mal_InformationsLabel.setPreferredSize(new java.awt.Dimension(146, 35));
        Mal_Informations.add(Mal_InformationsLabel, java.awt.BorderLayout.NORTH);

        Layout.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        Layout.setOpaque(false);
        Layout.setLayout(new java.awt.GridLayout(1, 1));
        Layout.add(statsMaladiePanel1);

        Mal_Informations.add(Layout, java.awt.BorderLayout.CENTER);

        add(Mal_Informations, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void AddScenarioButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddScenarioButtonMouseClicked
  
    }//GEN-LAST:event_AddScenarioButtonMouseClicked

    private void ImportMaladieButtonMouseReleased(java.awt.event.MouseEvent evt) {                                                   
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

    private void BoutonExportMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoutonExportMouseReleased
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
    }//GEN-LAST:event_BoutonExportMouseReleased

    private void AddMaladieButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddMaladieButtonMouseReleased
        this.ajouterObjetUI();
    }//GEN-LAST:event_AddMaladieButtonMouseReleased

    private void DeleteMaladieButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteMaladieButtonMouseReleased
        retirerCourant();
    }//GEN-LAST:event_DeleteMaladieButtonMouseReleased
    
    public GestionnaireMaladie getController() {
        return controller;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddMaladieButton;
    private javax.swing.JButton BoutonExport;
    private javax.swing.JButton DeleteMaladieButton;
    private javax.swing.JButton ImportMaladieButton;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi Layout;
    private javax.swing.JPanel Mal_Informations;
    private javax.swing.JLabel Mal_InformationsLabel;
    private javax.swing.JLabel MaladieLabel;
    private javax.swing.JPanel MaladieTitle;
    private javax.swing.JPanel Maladies;
    private javax.swing.JPanel MaladiesContainer;
    private javax.swing.JScrollPane ScenariosScrollPane;
    private ca.ulaval.glo2004.afficheur.panels.StatsMaladiePanel statsMaladiePanel1;
    // End of variables declaration//GEN-END:variables
}
