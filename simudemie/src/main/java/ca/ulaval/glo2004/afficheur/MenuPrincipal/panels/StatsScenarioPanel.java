/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.MenuPrincipal.panels;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.MenuPrincipal.onglets.OngletScenario;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import ca.ulaval.glo2004.domaine.Scenario;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Jonathan
 */
public class StatsScenarioPanel extends javax.swing.JPanel {

    private OngletScenario onglet;
    private Color bgColor = Couleurs.pannelArrondiNoTransp;
    private Color pannel = Couleurs.pannelArrondi;
    
    private JFreeChart stats;
    private JFileChooser fileChooser;
    
    public StatsScenarioPanel() {
        initComponents();
        
        try {
            StatsLabel.setFont(FontRegister.RobotoThin.deriveFont(21f));
            Main.setBackground(pannel);
            
            ResumeBouton.setBackground(pannel);
            ResumeBouton.setFont(FontRegister.RobotoRegular.deriveFont(15f));
            
            fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("png", "png");
            fileChooser.setFileFilter(filter);
            fileChooser.setAcceptAllFileFilterUsed(false);
        }
        catch(Exception e) {
        }
    }
    
    public void setDataset(Scenario scenario) {
        StatsPanel.removeAll();
        
        if (scenario == null) {
            return;
        }
        
        XYSeries infectes = new XYSeries("Infectés");
        for (int i = 0; i < scenario.getTotalJours(); i++) {
            infectes.add(i, scenario.getListeCartes().get(i).getPopulationInfectee());
        }
        
        XYSeries sains = new XYSeries("Sains");
        for (int i = 0; i < scenario.getTotalJours(); i++) {
            sains.add(i, scenario.getListeCartes().get(i).getPopulationSaine());
        }
        
        XYSeries decedes = new XYSeries("Décédés");
        for (int i = 0; i < scenario.getTotalJours(); i++) {
            decedes.add(i, scenario.getListeCartes().get(i).getPopulationDecedee());
        }
        
        XYSeries immunises = new XYSeries("Immunisés");
        for (int i = 0; i < scenario.getTotalJours(); i++) {
            immunises.add(i, scenario.getListeCartes().get(i).getPopulationImmune());
        }
        
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(infectes);
        collection.addSeries(sains);
        collection.addSeries(decedes);
        collection.addSeries(immunises);
                
        stats = ChartFactory.createXYLineChart(null, "Jours", "Population", collection, PlotOrientation.VERTICAL, true, true, false);
        stats.setBackgroundPaint(bgColor);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Couleurs.infections);
        renderer.setSeriesPaint(1, Couleurs.sains);
        renderer.setSeriesPaint(2, Couleurs.morts);
        renderer.setSeriesPaint(3, Couleurs.immunisations);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        renderer.setSeriesStroke(3, new BasicStroke(2.0f));

        XYPlot plot = stats.getXYPlot();
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(bgColor);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Couleurs.blanc);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Couleurs.blanc);
        plot.getDomainAxis().setLabelFont(FontRegister.RobotoRegular.deriveFont(14f));
        plot.getDomainAxis().setTickLabelPaint(Couleurs.blanc);
        plot.getDomainAxis().setLabelPaint(Couleurs.blanc);
        plot.getRangeAxis().setLabelFont(FontRegister.RobotoRegular.deriveFont(14f));
        plot.getRangeAxis().setTickLabelPaint(Couleurs.blanc);
        plot.getRangeAxis().setLabelPaint(Couleurs.blanc);

        stats.getLegend().setFrame(BlockBorder.NONE);
        stats.getLegend().setItemPaint(Couleurs.blanc);
        stats.getLegend().setBackgroundPaint(bgColor);

        ChartPanel chartPanel = new ChartPanel(stats);
        chartPanel.setPopupMenu(null);
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        chartPanel.setDisplayToolTips(false);
        
        StatsPanel.add(chartPanel, BorderLayout.CENTER);
        StatsPanel.validate();
        
        repaint();
    }
    
    public JButton getResumeButton() {
        return ResumeBouton;
    }
    
    public void setOnglet(OngletScenario onglet) {
        this.onglet = onglet;
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
        ExportStatsLabel = new javax.swing.JLabel();
        StatsPanel = new javax.swing.JPanel();
        BoutonsPanel = new javax.swing.JPanel();
        ResumeBouton = new javax.swing.JButton();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout(0, 25));

        Main.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        Main.setLayout(new java.awt.BorderLayout(0, 10));

        TitreStatsPanel.setOpaque(false);
        TitreStatsPanel.setLayout(new java.awt.BorderLayout());

        StatsLabel.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        StatsLabel.setText("Statistiques");
        TitreStatsPanel.add(StatsLabel, java.awt.BorderLayout.CENTER);

        ExportStatsLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_upload_20px.png"))); // NOI18N
        ExportStatsLabel.setToolTipText("Exporter les statistiques du scénario");
        ExportStatsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ExportStatsLabelMouseReleased(evt);
            }
        });
        TitreStatsPanel.add(ExportStatsLabel, java.awt.BorderLayout.EAST);

        Main.add(TitreStatsPanel, java.awt.BorderLayout.NORTH);

        StatsPanel.setOpaque(false);
        StatsPanel.setLayout(new java.awt.BorderLayout());
        Main.add(StatsPanel, java.awt.BorderLayout.CENTER);

        add(Main, java.awt.BorderLayout.CENTER);

        BoutonsPanel.setOpaque(false);
        BoutonsPanel.setPreferredSize(new java.awt.Dimension(100, 50));
        BoutonsPanel.setLayout(new java.awt.GridLayout(1, 0, 25, 0));

        ResumeBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_play_20px.png"))); // NOI18N
        ResumeBouton.setText("Commencer");
        ResumeBouton.setToolTipText("Commencer le scénario");
        ResumeBouton.setFocusable(false);
        ResumeBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ResumeBoutonMouseReleased(evt);
            }
        });
        BoutonsPanel.add(ResumeBouton);

        add(BoutonsPanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void ResumeBoutonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResumeBoutonMouseReleased
        if(onglet.getCourant() != null) {
            onglet.onStartSimulation();
        }
    }//GEN-LAST:event_ResumeBoutonMouseReleased

    private void ExportStatsLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExportStatsLabelMouseReleased
        int result = fileChooser.showDialog(null, "Exporter statistiques");
        if(fileChooser.getSelectedFile() != null  && result == JFileChooser.OPEN_DIALOG) {
            try {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                if(!path.endsWith(".png")) {
                    path += ".png";
                }

                ChartUtilities.saveChartAsPNG(new File(path), stats, 600, 400);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_ExportStatsLabelMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BoutonsPanel;
    private javax.swing.JLabel ExportStatsLabel;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi Main;
    private javax.swing.JButton ResumeBouton;
    private javax.swing.JLabel StatsLabel;
    private javax.swing.JPanel StatsPanel;
    private javax.swing.JPanel TitreStatsPanel;
    // End of variables declaration//GEN-END:variables
}
