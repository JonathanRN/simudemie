/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.onglets;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.FramePrincipal;
import ca.ulaval.glo2004.afficheur.objetsUI.ObjetMaladie;
import ca.ulaval.glo2004.afficheur.objetsUI.ObjetUI;
import ca.ulaval.glo2004.domaine.Maladie;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireMaladie;
import java.awt.Color;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jonathan
 */
public class OngletMaladie extends OngletUI {
    
    private final JFileChooser fileChooser = new JFileChooser();
    private GestionnaireMaladie controller;
    // Si à true, on ne peut pas sélectionner d'autre cartes maladie
    private boolean cardLocked;
    
    public OngletMaladie() {
        initComponents();
        
        cardLocked = false;
        controller = new GestionnaireMaladie();
        try {        
            ScenariosScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
            MaladieLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            Mal_InformationsLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            AddScenarioButton.setBackground(new Color(216, 222, 233, 38));
            ImportScenarioButton.setBackground(new Color(216, 222, 233, 38));
            BoutonExport.setBackground(new Color(216, 222, 233, 38));
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
        if(!cardLocked) {
            super.ajouterObjetUI();
            ObjetMaladie card = new ObjetMaladie(this);
            card.setNom("Maladie: " + objets.size());
            objets.add(card);
            onClickObjetUI(card);
            MaladiesContainer.add(card);

            // On débloque les champs pour modification de la nouvelle maladie
            statsMaladiePanel1.setModifying(true);

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
        
        if(objets.size() == 1) {
            onClickObjetUI(card);
        }
        
        updateUI();
    }
    
    @Override
    public void retirerCourant() {
        int result = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer cette maladie?", "", JOptionPane.WARNING_MESSAGE);

        if(result == JOptionPane.YES_OPTION && objets.size() > 0) {
            if(!cardLocked) {
                controller.supprimer(getIndexCourant());
            }
            
            MaladiesContainer.remove(courant);
            objets.remove(courant);
            setCardLocked(false);
            statsMaladiePanel1.setModifying(false);
            updateUI();


            super.retirerCourant();
        }
    }

    @Override
    public void onClickObjetUI(ObjetUI objet) {
        if(!cardLocked) {
            super.onClickObjetUI(objet);
            ObjetMaladie objetMaladie = (ObjetMaladie)courant;
            statsMaladiePanel1.setNomMaladie(objetMaladie.getNom());
            statsMaladiePanel1.setInfectionInput(objetMaladie.getInfectionRate());
            statsMaladiePanel1.setCuredInput(objetMaladie.getCuredRate());
            statsMaladiePanel1.setDeadInput(objetMaladie.getDeadRate());
        }
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

        Maladies = new javax.swing.JPanel();
        MaladieTitle = new javax.swing.JPanel();
        MaladieLabel = new javax.swing.JLabel();
        AddScenarioButton = new javax.swing.JButton();
        ImportScenarioButton = new javax.swing.JButton();
        BoutonExport = new javax.swing.JButton();
        ScenariosScrollPane = new javax.swing.JScrollPane();
        MaladiesContainer = new javax.swing.JPanel();
        Mal_Informations = new javax.swing.JPanel();
        Mal_InformationsLabel = new javax.swing.JLabel();
        Layout = new ca.ulaval.glo2004.afficheur.PanelArrondi();
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

        AddScenarioButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        AddScenarioButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_plus_math_20px.png"))); // NOI18N
        AddScenarioButton.setFocusable(false);
        AddScenarioButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AddScenarioButton.setMaximumSize(new java.awt.Dimension(75, 30));
        AddScenarioButton.setMinimumSize(new java.awt.Dimension(75, 30));
        AddScenarioButton.setPreferredSize(new java.awt.Dimension(100, 36));
        AddScenarioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddScenarioButtonMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AddScenarioButtonMouseReleased(evt);
            }
        });

        ImportScenarioButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ImportScenarioButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_installing_updates_20px.png"))); // NOI18N
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

        javax.swing.GroupLayout MaladieTitleLayout = new javax.swing.GroupLayout(MaladieTitle);
        MaladieTitle.setLayout(MaladieTitleLayout);
        MaladieTitleLayout.setHorizontalGroup(
            MaladieTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaladieTitleLayout.createSequentialGroup()
                .addComponent(MaladieLabel)
                .addGap(20, 20, 20)
                .addComponent(AddScenarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ImportScenarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1054, Short.MAX_VALUE)
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
                    .addComponent(ImportScenarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddScenarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void ImportScenarioButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImportScenarioButtonMouseReleased
        fileChooser.showOpenDialog(null);
        if(fileChooser.getSelectedFile() != null) {
            Maladie maladie = controller.importer(fileChooser.getSelectedFile().toString());
            ajouterCard(maladie);
            fileChooser.setSelectedFile(null);
        }
    }//GEN-LAST:event_ImportScenarioButtonMouseReleased

    private void BoutonExportMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoutonExportMouseReleased
        fileChooser.showOpenDialog(null);
        if(fileChooser.getSelectedFile() != null) {
            controller.exporter(getIndexCourant(), fileChooser.getSelectedFile().toString());
            fileChooser.setSelectedFile(null);
        }
    }//GEN-LAST:event_BoutonExportMouseReleased

    private void AddScenarioButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddScenarioButtonMouseReleased
             this.ajouterObjetUI();
    }//GEN-LAST:event_AddScenarioButtonMouseReleased

    public boolean getCardLocked() {
        return cardLocked;
    }
    
    public void setCardLocked(boolean cardLocked) {
        this.cardLocked = cardLocked;
    }
    
    public GestionnaireMaladie getController() {
        return controller;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddScenarioButton;
    private javax.swing.JButton BoutonExport;
    private javax.swing.JButton ImportScenarioButton;
    private ca.ulaval.glo2004.afficheur.PanelArrondi Layout;
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
