/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.MenuPrincipal.panels;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.onglets.OngletCarte;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Pays;
import java.awt.BorderLayout;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Jonathan
 */
public class StatsCartePanel extends javax.swing.JPanel {
    
    private OngletCarte onglet;
    private Color bgColor = Couleurs.pannelArrondiNoTransp;
    
    public StatsCartePanel() {
        initComponents();
        
        try {
            StatsLabel.setFont(FontRegister.RobotoThin.deriveFont(21f));
            Main.setBackground(Couleurs.pannelArrondi);
            ModifieBouton.setBackground(Couleurs.pannelArrondi);
            ModifieBouton.setFont(FontRegister.RobotoRegular.deriveFont(15f));
        }
        catch(Exception e) {
        }
    }
    
    public void setOnglet(OngletCarte onglet) {
        this.onglet = onglet;
    }
    
    public void setDataset(Carte carte) {
        StatsPanel.removeAll();
        
        if (carte == null) {
            return;
        }
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Pays pays : carte.getListePays()) {
            dataset.setValue(pays.getPopTotale(), "", pays.getNom());
        }
        
        JFreeChart stats = ChartFactory.createStackedBarChart(null, "", "Population", dataset, PlotOrientation.VERTICAL, false, true, false);
        stats.setBackgroundPaint(bgColor);

        CategoryPlot plot = stats.getCategoryPlot();
        plot.setBackgroundPaint(bgColor);
        plot.getDomainAxis().setLabelFont(FontRegister.RobotoRegular.deriveFont(14f));
        plot.getDomainAxis().setTickLabelPaint(Couleurs.blanc);
        plot.getDomainAxis().setLabelPaint(Couleurs.blanc);
        plot.getRangeAxis().setLabelFont(FontRegister.RobotoRegular.deriveFont(14f));
        plot.getRangeAxis().setTickLabelPaint(Couleurs.blanc);
        plot.getRangeAxis().setLabelPaint(Couleurs.blanc);
        
        BarRenderer br = (BarRenderer) plot.getRenderer();
        br.setMaximumBarWidth(0.2);
        br.setItemMargin(-1);
        br.setBarPainter(new StandardBarPainter());
        br.setSeriesPaint(0, Couleurs.sains);

        ChartPanel chartPanel = new ChartPanel(stats);
        chartPanel.setPopupMenu(null);
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        
        StatsPanel.add(chartPanel, BorderLayout.CENTER);
        StatsPanel.validate();
        
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Main = new ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi();
        TitreStatsPanel = new javax.swing.JPanel();
        StatsLabel = new javax.swing.JLabel();
        StatsPanel = new javax.swing.JPanel();
        BoutonsPanel = new javax.swing.JPanel();
        ModifieBouton = new javax.swing.JButton();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout(0, 25));

        Main.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        Main.setLayout(new java.awt.BorderLayout(0, 10));

        TitreStatsPanel.setOpaque(false);
        TitreStatsPanel.setLayout(new java.awt.BorderLayout());

        StatsLabel.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        StatsLabel.setText("Statistiques");
        TitreStatsPanel.add(StatsLabel, java.awt.BorderLayout.CENTER);

        Main.add(TitreStatsPanel, java.awt.BorderLayout.NORTH);

        StatsPanel.setOpaque(false);
        StatsPanel.setLayout(new java.awt.BorderLayout());
        Main.add(StatsPanel, java.awt.BorderLayout.CENTER);

        add(Main, java.awt.BorderLayout.CENTER);

        BoutonsPanel.setOpaque(false);
        BoutonsPanel.setPreferredSize(new java.awt.Dimension(100, 50));
        BoutonsPanel.setLayout(new java.awt.GridLayout(1, 0, 25, 0));

        ModifieBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_pencil_20px.png"))); // NOI18N
        ModifieBouton.setText("Modifier");
        ModifieBouton.setToolTipText("Modifier la carte courante");
        ModifieBouton.setFocusable(false);
        ModifieBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ModifieBoutonMouseReleased(evt);
            }
        });
        BoutonsPanel.add(ModifieBouton);

        add(BoutonsPanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void ModifieBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModifieBoutonMouseReleased
        if(onglet.getCourant() != null) {
            onglet.goToCreationCarte();
        }
    }//GEN-LAST:event_ModifieBoutonMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BoutonsPanel;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi Main;
    private javax.swing.JButton ModifieBouton;
    private javax.swing.JLabel StatsLabel;
    private javax.swing.JPanel StatsPanel;
    private javax.swing.JPanel TitreStatsPanel;
    // End of variables declaration//GEN-END:variables
}
