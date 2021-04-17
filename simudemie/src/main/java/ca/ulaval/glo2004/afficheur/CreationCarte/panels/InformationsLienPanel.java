/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.CreationCarte.panels;

import ca.ulaval.glo2004.afficheur.CreationCarte.mode.LienPays;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.VoieLiaison;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author Jonathan
 */
public class InformationsLienPanel extends javax.swing.JPanel implements ActionListener {
    
    private VoieLiaison lien;
    private final Carte carte;
    private JButton terrestre, maritime, aerien;
    
    private final LienPays lienPays;
    private final CreationCartePanel panel;
    
    public InformationsLienPanel(Carte carte, LienPays lienPays, CreationCartePanel panel) {
        this.carte = carte;
        this.panel = panel;
        this.lienPays = lienPays;
        initComponents();
        
        InformationsLienLabel.setFont(FontRegister.RobotoLight.deriveFont(15f));
        OrigineLienLabel.setFont(FontRegister.RobotoLight.deriveFont(13f));
        DestinationLienLabel.setFont(FontRegister.RobotoLight.deriveFont(13f));
        TypeLienLabel.setFont(FontRegister.RobotoLight.deriveFont(13f));
        ChoisirLienLabel.setFont(FontRegister.RobotoLight.deriveFont(13f));
    }
    
    public void setLien(VoieLiaison lien) {
        this.lien = lien;
        OrigineLienLabel.setText("Origine : " + lien.getPaysOrigine().getNom());
        DestinationLienLabel.setText("Dest. : " + lien.getPaysDestination().getNom());
        TypeLienLabel.setText("Type : " + lien.getType());
        
        // Supprime tous anciens
        BoutonsLiens.removeAll();
        
        ArrayList<VoieLiaison.TypeVoie> nonUtilisees = carte.getVoiesDisponibles(lien.getPaysOrigine(), lien.getPaysDestination());
        for (VoieLiaison.TypeVoie voie : nonUtilisees) {
            ajouterBoutonRadio(voie);
        }
        
        BoutonsLiens.revalidate();
    }
    
    private void ajouterBoutonRadio(VoieLiaison.TypeVoie voie) {       
        switch (voie) {
            case Terrestre:
                terrestre = new JButton();
                terrestre.setText("Lien Terrestre");
                terrestre.addActionListener(this);
                terrestre.setFont(FontRegister.RobotoLight.deriveFont(13f));
                terrestre.setActionCommand("Terrestre");
                terrestre.setFocusable(false);
                BoutonsLiens.add(terrestre);
                break;
            case Maritime:
                maritime = new JButton();
                maritime.setText("Lien Maritime");
                maritime.addActionListener(this);
                maritime.setFont(FontRegister.RobotoLight.deriveFont(13f));
                maritime.setActionCommand("Maritime");
                maritime.setFocusable(false);
                BoutonsLiens.add(maritime);
                break;
            case Aerien:
                aerien = new JButton();
                aerien.setText("Lien Aerien");
                aerien.addActionListener(this);
                aerien.setFont(FontRegister.RobotoLight.deriveFont(13f));
                aerien.setActionCommand("Aerien");
                aerien.setFocusable(false);
                BoutonsLiens.add(aerien);
                break;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        setVoie(VoieLiaison.TypeVoie.valueOf(e.getActionCommand()));
        setLien(lien);
        this.getRootPane().repaint();
    }
    
    private void setVoie(VoieLiaison.TypeVoie voie) {
        lien.setType(voie);
        panel.sauvegarderEtat("Changement voie " + voie.name());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InformationsLienLabel = new javax.swing.JLabel();
        InformationsLiens = new javax.swing.JPanel();
        OrigineLienLabel = new javax.swing.JLabel();
        DestinationLienLabel = new javax.swing.JLabel();
        TypeLienLabel = new javax.swing.JLabel();
        ChangerTypePanel = new javax.swing.JPanel();
        ChoisirLienLabel = new javax.swing.JLabel();
        BoutonsLiens = new javax.swing.JPanel();
        BoutonRetirerPanel = new javax.swing.JPanel();
        SupprimeBouton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 7, 7, 7));
        setMinimumSize(new java.awt.Dimension(113, 310));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(113, 310));
        setLayout(new java.awt.GridLayout(5, 1, 0, 10));

        InformationsLienLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        InformationsLienLabel.setText("Informations Lien");
        add(InformationsLienLabel);

        InformationsLiens.setOpaque(false);
        InformationsLiens.setLayout(new java.awt.GridLayout(3, 0));

        OrigineLienLabel.setText("Origine : ");
        InformationsLiens.add(OrigineLienLabel);

        DestinationLienLabel.setText("Dest. :");
        InformationsLiens.add(DestinationLienLabel);

        TypeLienLabel.setText("Type :");
        InformationsLiens.add(TypeLienLabel);

        add(InformationsLiens);

        ChangerTypePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));
        ChangerTypePanel.setOpaque(false);
        ChangerTypePanel.setLayout(new java.awt.BorderLayout());

        ChoisirLienLabel.setText("Changer de type :");
        ChangerTypePanel.add(ChoisirLienLabel, java.awt.BorderLayout.CENTER);

        add(ChangerTypePanel);

        BoutonsLiens.setFocusable(false);
        BoutonsLiens.setOpaque(false);
        BoutonsLiens.setLayout(new java.awt.GridLayout(2, 0, 0, 5));
        add(BoutonsLiens);

        BoutonRetirerPanel.setOpaque(false);
        BoutonRetirerPanel.setLayout(new java.awt.BorderLayout());

        SupprimeBouton.setText("Retirer Lien");
        SupprimeBouton.setFocusable(false);
        SupprimeBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SupprimeBoutonMouseReleased(evt);
            }
        });
        BoutonRetirerPanel.add(SupprimeBouton, java.awt.BorderLayout.SOUTH);

        add(BoutonRetirerPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void SupprimeBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupprimeBoutonMouseReleased
        lienPays.onLienSupprime(lien);
    }//GEN-LAST:event_SupprimeBoutonMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BoutonRetirerPanel;
    private javax.swing.JPanel BoutonsLiens;
    private javax.swing.JPanel ChangerTypePanel;
    private javax.swing.JLabel ChoisirLienLabel;
    private javax.swing.JLabel DestinationLienLabel;
    private javax.swing.JLabel InformationsLienLabel;
    private javax.swing.JPanel InformationsLiens;
    private javax.swing.JLabel OrigineLienLabel;
    private javax.swing.JButton SupprimeBouton;
    private javax.swing.JLabel TypeLienLabel;
    // End of variables declaration//GEN-END:variables
}
