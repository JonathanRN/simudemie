/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.panels;

import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.afficheur.onglets.OngletScenario;
import ca.ulaval.glo2004.domaine.Scenario;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
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
    private Color bgColor = new Color(71, 76, 88);
    
    public StatsScenarioPanel() {
        initComponents();
        
        try {
            StatsLabel.setFont(FontRegister.RobotoThin.deriveFont(21f));
            Main.setBackground(new Color(216, 222, 233, 38));
            
            ResumeButton.setBackground(new Color(216, 222, 233, 38));
            ResumeButton.setFont(FontRegister.RobotoRegular.deriveFont(15f));
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
        
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(infectes);
        collection.addSeries(sains);
        collection.addSeries(decedes);
                
        JFreeChart stats = ChartFactory.createXYLineChart(null, "Jours", "Population", collection, PlotOrientation.VERTICAL, true, true, false);
        stats.setBackgroundPaint(bgColor);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(191,97,106));
        renderer.setSeriesPaint(1, new Color(163,190,140));
        renderer.setSeriesPaint(2, new Color(180,142,173));
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));

        XYPlot plot = stats.getXYPlot();
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(bgColor);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.white);
        plot.getDomainAxis().setLabelFont(FontRegister.RobotoRegular.deriveFont(14f));
        plot.getDomainAxis().setTickLabelPaint(Color.white);
        plot.getDomainAxis().setLabelPaint(Color.white);
        plot.getRangeAxis().setLabelFont(FontRegister.RobotoRegular.deriveFont(14f));
        plot.getRangeAxis().setTickLabelPaint(Color.white);
        plot.getRangeAxis().setLabelPaint(Color.white);

        stats.getLegend().setFrame(BlockBorder.NONE);
        stats.getLegend().setItemPaint(Color.white);
        stats.getLegend().setBackgroundPaint(bgColor);

        ChartPanel chartPanel = new ChartPanel(stats);
        chartPanel.setPopupMenu(null);
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        chartPanel.setDisplayToolTips(false);
        
        StatsPanel.add(chartPanel, BorderLayout.CENTER);
        StatsPanel.validate();
    }
    
    public JButton getResumeButton() {
        return ResumeButton;
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

        Main = new ca.ulaval.glo2004.afficheur.PanelArrondi();
        StatsHeader = new javax.swing.JPanel();
        StatsLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        StatsPanel = new javax.swing.JPanel();
        Buttons = new javax.swing.JPanel();
        ResumeButton = new javax.swing.JButton();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout(0, 25));

        Main.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        Main.setLayout(new java.awt.BorderLayout(0, 10));

        StatsHeader.setOpaque(false);
        StatsHeader.setLayout(new java.awt.BorderLayout());

        StatsLabel.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        StatsLabel.setText("Statistiques");
        StatsHeader.add(StatsLabel, java.awt.BorderLayout.CENTER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_upload_20px.png"))); // NOI18N
        jLabel1.setToolTipText("Exporter les statistiques du scénario");
        StatsHeader.add(jLabel1, java.awt.BorderLayout.EAST);

        Main.add(StatsHeader, java.awt.BorderLayout.NORTH);

        StatsPanel.setOpaque(false);
        StatsPanel.setLayout(new java.awt.BorderLayout());
        Main.add(StatsPanel, java.awt.BorderLayout.CENTER);

        add(Main, java.awt.BorderLayout.CENTER);

        Buttons.setOpaque(false);
        Buttons.setPreferredSize(new java.awt.Dimension(100, 50));
        Buttons.setLayout(new java.awt.GridLayout(1, 0, 25, 0));

        ResumeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_play_20px.png"))); // NOI18N
        ResumeButton.setText("Commencer");
        ResumeButton.setToolTipText("Commencer le scénario");
        ResumeButton.setFocusable(false);
        ResumeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ResumeButtonMouseReleased(evt);
            }
        });
        Buttons.add(ResumeButton);

        add(Buttons, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void ResumeButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResumeButtonMouseReleased
        if(onglet.getCourant() != null) {
            onglet.onStartSimulation();
        }
    }//GEN-LAST:event_ResumeButtonMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Buttons;
    private ca.ulaval.glo2004.afficheur.PanelArrondi Main;
    private javax.swing.JButton ResumeButton;
    private javax.swing.JPanel StatsHeader;
    private javax.swing.JLabel StatsLabel;
    private javax.swing.JPanel StatsPanel;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
