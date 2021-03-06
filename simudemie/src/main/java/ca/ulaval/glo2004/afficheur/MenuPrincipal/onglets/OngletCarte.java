/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.MenuPrincipal.onglets;

import ca.ulaval.glo2004.afficheur.FramePrincipal;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.objetsUI.ObjetCarte;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.objetsUI.ObjetUI;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireCarte;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

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
            CartesScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
            CartesLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            InformationCarteLabel.setFont(FontRegister.RobotoThin.deriveFont(25f));
            AjoutCarteBouton.setBackground(Couleurs.sains);
            SupprimeCarteBouton.setBackground(Couleurs.infections);
            ImportCarteBouton.setBackground(Couleurs.pannelArrondi);
            ExportCarteBouton.setBackground(Couleurs.pannelArrondi);
            
            Layout.setOpaque(false);
        }
        catch (Exception e) {
        }
    }
    
    public void init() {
        StatsCartePanel1.setOnglet(this);
        for(Carte carte : GestionnaireCarte.getInstance().getList()) {
            ajouterCard(carte);
        }
    }
    
    @Override
    public void ajouterObjetUI() {
        String nomCarte = JOptionPane.showInputDialog(this, "Entrez le nom de la nouvelle carte", "", JOptionPane.QUESTION_MESSAGE);

        if(nomCarte != null && !nomCarte.isEmpty()) {
            super.ajouterObjetUI();
            ObjetCarte card = new ObjetCarte(this);
            card.setMapName(nomCarte);

            GestionnaireCarte.getInstance().creer(card.getNomCarte());

            objets.add(card);
            onClickObjetUI(card);

            ConteneurCartePanel.add(card);
            updateUI();
            
            goToCreationCarte();
        }
    }

    private void ajouterCard(Carte carte) {
        ObjetCarte card = new ObjetCarte(this);
        card.setMapName(carte.getNom());
        objets.add(card);
        ConteneurCartePanel.add(card);
        
        if(objets.size() == 1) {
            onClickObjetUI(card);
        }
        
        updateUI();
    }
    
    public void retirerSansConfirmation() {
        GestionnaireCarte.getInstance().supprimer(getIndexCourant());
        objets.remove(courant);
        ConteneurCartePanel.remove(courant);
        updateUI();
        super.retirerCourant();
        
        if (objets.isEmpty()) {
            ApercuCartePanel.setPreviewVisibility(false);
            StatsCartePanel1.setDataset(null);
        }
    }
    
    @Override
    public void retirerCourant() {
        if(objets.size() > 0) {
            int result = JOptionPane.showConfirmDialog(this, "??tes-vous s??r de vouloir supprimer cette carte?", "", JOptionPane.WARNING_MESSAGE);

            if(result == JOptionPane.YES_OPTION) {
                retirerSansConfirmation();
            }
        }
    }

    @Override
    public void onClickObjetUI(ObjetUI objet) {
        super.onClickObjetUI(objet);
        
        if (objet != null) {
            ApercuCartePanel.setPreviewVisibility(true);
            Carte carte = GestionnaireCarte.getInstance().getElement(this.getIndexCourant());
            ApercuCartePanel.setCarte(carte, false);
            StatsCartePanel1.setDataset(carte);
        }
    }
    
    public void goToCreationCarte() {
        FramePrincipal frame = (FramePrincipal)SwingUtilities.windowForComponent(this);
        frame.startCreationCarte(getIndexCourant(), this);
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
        TitreCartePanel = new javax.swing.JPanel();
        CartesLabel = new javax.swing.JLabel();
        AjoutCarteBouton = new javax.swing.JButton();
        SupprimeCarteBouton = new javax.swing.JButton();
        ImportCarteBouton = new javax.swing.JButton();
        ExportCarteBouton = new javax.swing.JButton();
        CartesScrollPane = new javax.swing.JScrollPane();
        ConteneurCartePanel = new javax.swing.JPanel();
        InformationsOngletCartePanel = new javax.swing.JPanel();
        InformationCarteLabel = new javax.swing.JLabel();
        Layout = new ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi();
        ApercuCartePanel = new ca.ulaval.glo2004.afficheur.MenuPrincipal.panels.ApercuPanel();
        StatsCartePanel1 = new ca.ulaval.glo2004.afficheur.MenuPrincipal.panels.StatsCartePanel();

        setBackground(new java.awt.Color(46, 52, 64));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 35, 35));
        setLayout(new java.awt.BorderLayout());

        Cartes.setBackground(new java.awt.Color(46, 52, 64));
        Cartes.setBorder(javax.swing.BorderFactory.createEmptyBorder(35, 0, 0, 0));
        Cartes.setLayout(new java.awt.BorderLayout());

        TitreCartePanel.setBackground(new java.awt.Color(46, 52, 64));
        TitreCartePanel.setMaximumSize(new java.awt.Dimension(974, 50));
        TitreCartePanel.setMinimumSize(new java.awt.Dimension(974, 50));
        TitreCartePanel.setPreferredSize(new java.awt.Dimension(974, 35));

        CartesLabel.setBackground(new java.awt.Color(46, 52, 64));
        CartesLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        CartesLabel.setText("Cartes");
        CartesLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        AjoutCarteBouton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        AjoutCarteBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_plus_math_20px.png"))); // NOI18N
        AjoutCarteBouton.setToolTipText("Cr??er une nouvelle carte");
        AjoutCarteBouton.setFocusable(false);
        AjoutCarteBouton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AjoutCarteBouton.setMaximumSize(new java.awt.Dimension(75, 30));
        AjoutCarteBouton.setMinimumSize(new java.awt.Dimension(75, 30));
        AjoutCarteBouton.setPreferredSize(new java.awt.Dimension(100, 36));
        AjoutCarteBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AjoutCarteBoutonMouseReleased(evt);
            }
        });

        SupprimeCarteBouton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        SupprimeCarteBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_trash_can_20px.png"))); // NOI18N
        SupprimeCarteBouton.setToolTipText("Supprimer la carte courante");
        SupprimeCarteBouton.setFocusable(false);
        SupprimeCarteBouton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SupprimeCarteBouton.setMaximumSize(new java.awt.Dimension(75, 30));
        SupprimeCarteBouton.setMinimumSize(new java.awt.Dimension(75, 30));
        SupprimeCarteBouton.setPreferredSize(new java.awt.Dimension(100, 36));
        SupprimeCarteBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SupprimeCarteBoutonMouseReleased(evt);
            }
        });

        ImportCarteBouton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ImportCarteBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_installing_updates_20px.png"))); // NOI18N
        ImportCarteBouton.setToolTipText("Importer une carte existante");
        ImportCarteBouton.setFocusable(false);
        ImportCarteBouton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ImportCarteBouton.setMaximumSize(new java.awt.Dimension(75, 30));
        ImportCarteBouton.setMinimumSize(new java.awt.Dimension(75, 30));
        ImportCarteBouton.setPreferredSize(new java.awt.Dimension(100, 36));
        ImportCarteBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ImportCarteBoutonMouseReleased(evt);
            }
        });

        ExportCarteBouton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ExportCarteBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_upload_20px.png"))); // NOI18N
        ExportCarteBouton.setToolTipText("Exporter la carte courante");
        ExportCarteBouton.setFocusable(false);
        ExportCarteBouton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ExportCarteBouton.setMaximumSize(new java.awt.Dimension(75, 30));
        ExportCarteBouton.setMinimumSize(new java.awt.Dimension(75, 30));
        ExportCarteBouton.setPreferredSize(new java.awt.Dimension(100, 36));
        ExportCarteBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ExportCarteBoutonMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout TitreCartePanelLayout = new javax.swing.GroupLayout(TitreCartePanel);
        TitreCartePanel.setLayout(TitreCartePanelLayout);
        TitreCartePanelLayout.setHorizontalGroup(
            TitreCartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitreCartePanelLayout.createSequentialGroup()
                .addComponent(CartesLabel)
                .addGap(127, 127, 127)
                .addComponent(AjoutCarteBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SupprimeCarteBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 462, Short.MAX_VALUE)
                .addComponent(ImportCarteBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ExportCarteBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        TitreCartePanelLayout.setVerticalGroup(
            TitreCartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitreCartePanelLayout.createSequentialGroup()
                .addGroup(TitreCartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TitreCartePanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(CartesLabel))
                    .addComponent(AjoutCarteBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ImportCarteBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExportCarteBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SupprimeCarteBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        Cartes.add(TitreCartePanel, java.awt.BorderLayout.NORTH);

        CartesScrollPane.setBackground(new java.awt.Color(46, 52, 64));
        CartesScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        CartesScrollPane.setMaximumSize(new java.awt.Dimension(75, 250));
        CartesScrollPane.setMinimumSize(new java.awt.Dimension(75, 250));
        CartesScrollPane.setPreferredSize(new java.awt.Dimension(75, 250));

        ConteneurCartePanel.setBackground(new java.awt.Color(46, 52, 64));
        ConteneurCartePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, -25, 0, 0));
        ConteneurCartePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 25, 5));
        CartesScrollPane.setViewportView(ConteneurCartePanel);

        Cartes.add(CartesScrollPane, java.awt.BorderLayout.CENTER);

        add(Cartes, java.awt.BorderLayout.NORTH);

        InformationsOngletCartePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(35, 0, 0, 0));
        InformationsOngletCartePanel.setOpaque(false);
        InformationsOngletCartePanel.setLayout(new java.awt.BorderLayout());

        InformationCarteLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        InformationCarteLabel.setText("Informations");
        InformationCarteLabel.setMaximumSize(new java.awt.Dimension(146, 35));
        InformationCarteLabel.setMinimumSize(new java.awt.Dimension(146, 35));
        InformationCarteLabel.setPreferredSize(new java.awt.Dimension(146, 35));
        InformationsOngletCartePanel.add(InformationCarteLabel, java.awt.BorderLayout.NORTH);

        Layout.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        Layout.setOpaque(false);
        Layout.setLayout(new java.awt.GridLayout(1, 2, 25, 0));
        Layout.add(ApercuCartePanel);
        Layout.add(StatsCartePanel1);

        InformationsOngletCartePanel.add(Layout, java.awt.BorderLayout.CENTER);

        add(InformationsOngletCartePanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void ImportCarteBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImportCarteBoutonMouseReleased
        int result = fileChooser.showDialog(null, "Importer");
        if(fileChooser.getSelectedFile() != null && result == JFileChooser.OPEN_DIALOG) {
            try {
                Carte carte = GestionnaireCarte.getInstance().importer(fileChooser.getSelectedFile().toString());
                ajouterCard(carte);
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, "Une erreur est survenue lors de l'exportation d'une carte.", "", JOptionPane.ERROR_MESSAGE);
            }
            fileChooser.setSelectedFile(null);
        }
    }//GEN-LAST:event_ImportCarteBoutonMouseReleased

    private void ExportCarteBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExportCarteBoutonMouseReleased
        if(getCourant() != null) {
            int result = fileChooser.showDialog(null, "Exporter");
            if(fileChooser.getSelectedFile() != null && result == JFileChooser.OPEN_DIALOG) {
                try {
                    GestionnaireCarte.getInstance().exporter(getIndexCourant(), fileChooser.getSelectedFile().toString());
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(this, "Une erreur est survenue lors de l'exportation d'une carte.", "", JOptionPane.ERROR_MESSAGE);
                }
                fileChooser.setSelectedFile(null);
            }
        }
    }//GEN-LAST:event_ExportCarteBoutonMouseReleased

    private void AjoutCarteBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjoutCarteBoutonMouseReleased
        ajouterObjetUI();
    }//GEN-LAST:event_AjoutCarteBoutonMouseReleased

    private void SupprimeCarteBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupprimeCarteBoutonMouseReleased
        retirerCourant();
    }//GEN-LAST:event_SupprimeCarteBoutonMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AjoutCarteBouton;
    private ca.ulaval.glo2004.afficheur.MenuPrincipal.panels.ApercuPanel ApercuCartePanel;
    private javax.swing.JPanel Cartes;
    private javax.swing.JLabel CartesLabel;
    private javax.swing.JScrollPane CartesScrollPane;
    private javax.swing.JPanel ConteneurCartePanel;
    private javax.swing.JButton ExportCarteBouton;
    private javax.swing.JButton ImportCarteBouton;
    private javax.swing.JLabel InformationCarteLabel;
    private javax.swing.JPanel InformationsOngletCartePanel;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi Layout;
    private ca.ulaval.glo2004.afficheur.MenuPrincipal.panels.StatsCartePanel StatsCartePanel1;
    private javax.swing.JButton SupprimeCarteBouton;
    private javax.swing.JPanel TitreCartePanel;
    // End of variables declaration//GEN-END:variables
}
