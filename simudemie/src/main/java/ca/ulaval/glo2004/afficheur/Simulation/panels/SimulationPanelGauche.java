/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.Simulation.panels;

import ca.ulaval.glo2004.afficheur.Simulation.objetsUI.ObjetSimulationMesure;
import ca.ulaval.glo2004.afficheur.Simulation.objetsUI.ObjetSimulationVoieLiaison;
import ca.ulaval.glo2004.afficheur.Simulation.Simulation;
import ca.ulaval.glo2004.afficheur.Simulation.objetsUI.ObjetSimulationVaccin;
import ca.ulaval.glo2004.afficheur.boutons.ToggleBouton;
import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import ca.ulaval.glo2004.domaine.Carte;
import ca.ulaval.glo2004.domaine.Mesure;
import ca.ulaval.glo2004.domaine.Pays;
import ca.ulaval.glo2004.domaine.Scenario;
import ca.ulaval.glo2004.domaine.Vaccin;
import ca.ulaval.glo2004.domaine.VoieLiaison;
import ca.ulaval.glo2004.domaine.VoieLiaison.TypeVoie;
import ca.ulaval.glo2004.domaine.controleur.GestionnaireScenario;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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
public class SimulationPanelGauche extends PanelArrondi implements AdjustmentListener {
    
    private ToggleBouton toggleCourant;
    private Simulation simulation;
    private int indexPays;
    private ArrayList<TypeVoie> voies = new ArrayList<>();
    
    // Stats
    private JFreeChart stats;
    private XYSeries infectes, sains, decedes, immunises;
    private int ancienJour;
    private JFileChooser fileChooser;
        
    public SimulationPanelGauche() {
        try {
            initComponents();
            updateBoutonAjouter(false, AjouterMesure);
            updateBoutonAjouter(false, AjouterVaccins);
            super.setBackground(Couleurs.sideMenuTransp);
            SidePanel.setBackground(Couleurs.sideMenuLessTransp);
            
            fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("png", "png");
            fileChooser.setFileFilter(filter);
            fileChooser.setAcceptAllFileFilterUsed(false);
            
            BoutonMesures.init(this, "icons8_wash_your_hands_30px");
            MesuresActives.getViewport().setOpaque(false);
            MesuresActives.getVerticalScrollBar().setUnitIncrement(15);
            MesuresActives.getVerticalScrollBar().addAdjustmentListener(this);
            MesuresTitre.setFont(FontRegister.RobotoLight.deriveFont(14f));
            
            BoutonLiens.init(this, "icons8_chain_25px");
            LiensTitre.setFont(FontRegister.RobotoLight.deriveFont(14f));
            LiensScrollPane.getViewport().setOpaque(false);
            
            BoutonVaccins.init(this, "icons8_syringe_30px");
            VaccinsScrollPane.getViewport().setOpaque(false);
            VaccinsScrollPane.getVerticalScrollBar().setUnitIncrement(15);
            VaccinsScrollPane.getVerticalScrollBar().addAdjustmentListener(this);
            VaccinsTitre.setFont(FontRegister.RobotoLight.deriveFont(14f));
            
            BoutonStats.init(this, "icons8_bar_chart_30px");
            StatsTitreLabel.setFont(FontRegister.RobotoLight.deriveFont(14f));
            
            onToggleClick(BoutonMesures);
        }
        catch(Exception e) {
        }
    }
    
    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
        ancienJour = simulation.getScenario().getIndexJourCourant();
    }
    
    public int getIndexPays() {
        return indexPays;
    }
    
    public void setIndexPays(int indexPays) {
        this.indexPays = indexPays;
        
        loadElements();
    }
    
    public void onAvancerJour(int jour) {
        // Ne rien faire si l'on est pas sur l'onglet stats
        if (!toggleCourant.equals(BoutonStats)) {
            return;
        }
        
        // Dans le cas ou l'on revient dans le temps, puis play
        if (jour - ancienJour != 0) {
            for (int i = ancienJour; i <= jour; i++) {
                ajouterStats(i);
            }
        }
        else {
            ajouterStats(jour);
        }
        
        ancienJour = jour;
    }
    
    public void onChargerJour(int jour) {
        // Ne rien faire si l'on est pas sur l'onglet stats
        if (!toggleCourant.equals(BoutonStats)) {
            return;
        }
        
        if (ancienJour > jour) {
            // On doit retirer tout jour entre l'ancien et le jour charge
            for (int i = ancienJour; i > jour; i--) {
                infectes.remove(infectes.getItemCount() - 1);
                sains.remove(sains.getItemCount() - 1);
                decedes.remove(decedes.getItemCount() - 1);
                immunises.remove(immunises.getItemCount() - 1);
            }
        }
        else if (ancienJour < jour) {
            for (int i = ancienJour; i < jour; i++) {
                ajouterStats(i);
            }
        }
        stats.fireChartChanged();
        ancienJour = jour;
    }
    
    private void ajouterStats(int jour) {
        Carte carte = simulation.getScenario().getListeCartes().get(jour);
        Pays pays = carte.getListePays().get(indexPays);

        infectes.add(jour, pays.getPourcentageInfectee());
        sains.add(jour, pays.getPourcentageSaine());
        decedes.add(jour, pays.getPourcentageDecedee());
        immunises.add(jour, pays.getPourcentageImmunisee());
    }
    
    public void loadElements() {
        if (toggleCourant.equals(BoutonMesures)) {
            loadMesures();
        }
        else if (toggleCourant.equals(BoutonLiens)) {
            loadLiens();
        }
        else if (toggleCourant.equals(BoutonVaccins)) {
            loadVaccins();
        }
        else if (toggleCourant.equals(BoutonStats)) {
            loadStats();
        }
    }
    
    public void loadMesures() {
        ConteneurMesuresPanel.removeAll();
        ConteneurMesuresPanel.getParent().validate();
        ConteneurMesuresPanel.getRootPane().repaint();

        for (Mesure m : simulation.getScenario().getCarteJourCourant().getPays(indexPays).getMesures()) {
            addMesure(m);
        }
    }
    
    public void updateMesures() {
        ArrayList<Mesure> mesures = simulation.getScenario().getCarteJourCourant().getPays(indexPays).getMesures();
        for(int index = 0; index < mesures.size(); index++) {
            ObjetSimulationMesure osm = (ObjetSimulationMesure) ConteneurMesuresPanel.getComponent(index);
            osm.setActif(mesures.get(index).getActive());
        }
    }
    
    public void loadLiens() {
        voies.clear();
        ConteneurLiensPanel.removeAll();
        ConteneurLiensPanel.getParent().validate();
        ConteneurLiensPanel.getRootPane().repaint();
        
        Carte carte = simulation.getScenario().getCarteJourCourant();
        for (VoieLiaison voie : carte.getVoies()) {
            System.out.println("Pays origine: " + voie.getPaysOrigine().getNom());
            System.out.println(voie.getPaysOrigine().equals(carte.getPays(indexPays)));
            System.out.println("Pays destination: " + voie.getPaysOrigine().getNom());
            System.out.println(voie.getPaysDestination().equals(carte.getPays(indexPays)));
            if (voie.getPaysOrigine().equals(carte.getPays(indexPays)) ||
                voie.getPaysDestination().equals(carte.getPays(indexPays))) {
                
                if (!voies.contains(voie.getType())) {
                    ObjetSimulationVoieLiaison sml = new ObjetSimulationVoieLiaison();
                    sml.setTypeVoie(voie.getType());
                    ConteneurLiensPanel.add(sml);
                    voies.add(voie.getType());
                }
            }
        }
        
        ConteneurLiensPanel.getParent().validate();
        ConteneurLiensPanel.getRootPane().repaint();
    }
    
    public void loadVaccins() {
        ConteneurVaccinsPanel.removeAll();
        ConteneurVaccinsPanel.getParent().validate();
        ConteneurVaccinsPanel.getRootPane().repaint();

        for (Vaccin v : GestionnaireScenario.getInstance().getVaccins(indexPays)) {
            addVaccin(v);
        }
    }
    
    public void loadStats() {
        ConteneurStatsPanel.removeAll();
        
        Scenario scenario = simulation.getScenario();
        
        // Ajout des stats jusqu'au jour courant
        infectes = new XYSeries("Infectés");
        sains = new XYSeries("Sains");
        decedes = new XYSeries("Décédés");
        immunises = new XYSeries("Immunisés");
        
        for (int i = 0; i < scenario.getIndexJourCourant(); i++) {
            ajouterStats(i);
        }
        
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(infectes);
        collection.addSeries(sains);
        collection.addSeries(decedes);
        collection.addSeries(immunises);
        
        stats = ChartFactory.createXYLineChart(null, "Jours", "", collection, PlotOrientation.VERTICAL, true, true, false);
        stats.setBackgroundPaint(Couleurs.titleBar);
        stats.getLegend().setFrame(BlockBorder.NONE);
        stats.getLegend().setItemPaint(Couleurs.blanc);
        stats.getLegend().setBackgroundPaint(Couleurs.titleBar);

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
        plot.setBackgroundPaint(Couleurs.titleBar);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Couleurs.blanc);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Couleurs.blanc);
        plot.getDomainAxis().setLabelFont(FontRegister.RobotoRegular.deriveFont(14f));
        plot.getDomainAxis().setTickLabelPaint(Couleurs.blanc);
        plot.getDomainAxis().setLabelPaint(Couleurs.blanc);
        plot.getRangeAxis().setTickLabelPaint(Couleurs.blanc);

        ChartPanel chartPanel = new ChartPanel(stats);
        chartPanel.setPopupMenu(null);
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        chartPanel.setDisplayToolTips(false);
        
        ConteneurStatsPanel.add(chartPanel, BorderLayout.CENTER);
        
        ConteneurStatsPanel.validate();
        ConteneurStatsPanel.getRootPane().repaint();
    }
    
    public void onToggleClick(ToggleBouton toggle) {        
        if (toggleCourant != null) {
            toggleCourant.setToggle(false);
        }
        
        toggleCourant = toggle;
        
        if (toggleCourant != null) {
            toggleCourant.setToggle(true);
        }
        
        MesuresPanel.setVisible(toggle.equals(BoutonMesures));
        LiensPanel.setVisible(toggle.equals(BoutonLiens));
        VaccinsPanel.setVisible(toggle.equals(BoutonVaccins));
        StatsPanel.setVisible(toggle.equals(BoutonStats));
        
        loadElements();
        
        repaint();
    }
    
    private void updateBoutonAjouter(boolean actif, JLabel bouton) {
        if (actif) {
            bouton.setFont(FontRegister.RobotoRegular.deriveFont(25f));
        }
        else {
            bouton.setFont(FontRegister.RobotoLight.deriveFont(25f));
        }
    }

    private ObjetSimulationMesure addMesure(Mesure mesure) {
        ObjetSimulationMesure panel = new ObjetSimulationMesure(ConteneurMesuresPanel, this, ConteneurMesuresPanel.getComponentCount());
        if(mesure != null) {
            panel.chargerMesure(new Mesure(mesure));
        }
        ConteneurMesuresPanel.add(panel);
        ConteneurMesuresPanel.getParent().validate();
        ConteneurMesuresPanel.getRootPane().repaint();
        return panel;
    }
    
    private ObjetSimulationVaccin addVaccin(Vaccin vaccin) {
        ObjetSimulationVaccin panel = new ObjetSimulationVaccin(ConteneurVaccinsPanel, this, ConteneurVaccinsPanel.getComponentCount());
        if(vaccin != null) {
            panel.chargerVaccin(new Vaccin(vaccin));
        }
        ConteneurVaccinsPanel.add(panel);
        ConteneurVaccinsPanel.getParent().validate();
        ConteneurVaccinsPanel.getRootPane().repaint();
        return panel;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SidePanelParent = new javax.swing.JPanel();
        SidePanel = new ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi();
        BoutonMesures = new ca.ulaval.glo2004.afficheur.boutons.ToggleBouton();
        BoutonVaccins = new ca.ulaval.glo2004.afficheur.boutons.ToggleBouton();
        BoutonLiens = new ca.ulaval.glo2004.afficheur.boutons.ToggleBouton();
        BoutonStats = new ca.ulaval.glo2004.afficheur.boutons.ToggleBouton();
        MesuresPanel = new javax.swing.JPanel();
        Titre = new javax.swing.JPanel();
        MesuresTitre = new javax.swing.JLabel();
        AjouterMesure = new javax.swing.JLabel();
        MesuresActives = new javax.swing.JScrollPane();
        ConteneurMesuresPanel = new javax.swing.JPanel();
        LiensPanel = new javax.swing.JPanel();
        TitreOngletPanel = new javax.swing.JPanel();
        LiensTitre = new javax.swing.JLabel();
        TransmissionInterRegionPanel = new javax.swing.JPanel();
        TransmissionInterRegionLabel = new javax.swing.JLabel();
        TransmissionInterRegion = new javax.swing.JSpinner();
        LiensScrollPane = new javax.swing.JScrollPane();
        ConteneurLiensPanel = new javax.swing.JPanel();
        VaccinsPanel = new javax.swing.JPanel();
        TitrePanel = new javax.swing.JPanel();
        VaccinsTitre = new javax.swing.JLabel();
        AjouterVaccins = new javax.swing.JLabel();
        VaccinsScrollPane = new javax.swing.JScrollPane();
        ConteneurVaccinsPanel = new javax.swing.JPanel();
        StatsPanel = new javax.swing.JPanel();
        TitreStatsPanel = new javax.swing.JPanel();
        StatsTitreLabel = new javax.swing.JLabel();
        ExportStats = new javax.swing.JLabel();
        ConteneurStatsPanel = new javax.swing.JPanel();

        setLayout(new javax.swing.OverlayLayout(this));

        SidePanelParent.setOpaque(false);
        SidePanelParent.setLayout(new java.awt.BorderLayout());

        SidePanel.setMaximumSize(new java.awt.Dimension(100, 200));
        SidePanel.setMinimumSize(new java.awt.Dimension(100, 200));
        SidePanel.setPreferredSize(new java.awt.Dimension(50, 200));
        SidePanel.setLayout(new java.awt.GridLayout(4, 1));
        SidePanel.add(BoutonMesures);
        SidePanel.add(BoutonVaccins);
        SidePanel.add(BoutonLiens);
        SidePanel.add(BoutonStats);

        SidePanelParent.add(SidePanel, java.awt.BorderLayout.WEST);

        add(SidePanelParent);

        MesuresPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 0));
        MesuresPanel.setOpaque(false);
        MesuresPanel.setLayout(new java.awt.BorderLayout());

        Titre.setOpaque(false);
        Titre.setLayout(new java.awt.BorderLayout());

        MesuresTitre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        MesuresTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MesuresTitre.setText("Mesures");
        MesuresTitre.setPreferredSize(new java.awt.Dimension(62, 30));
        Titre.add(MesuresTitre, java.awt.BorderLayout.CENTER);

        AjouterMesure.setFont(new java.awt.Font("Dialog", 0, 25)); // NOI18N
        AjouterMesure.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AjouterMesure.setText("+");
        AjouterMesure.setPreferredSize(new java.awt.Dimension(40, 30));
        AjouterMesure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AjouterMesureMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AjouterMesureMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AjouterMesureMouseReleased(evt);
            }
        });
        Titre.add(AjouterMesure, java.awt.BorderLayout.EAST);

        MesuresPanel.add(Titre, java.awt.BorderLayout.PAGE_START);

        MesuresActives.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        MesuresActives.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        MesuresActives.setOpaque(false);

        ConteneurMesuresPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ConteneurMesuresPanel.setOpaque(false);
        ConteneurMesuresPanel.setLayout(new javax.swing.BoxLayout(ConteneurMesuresPanel, javax.swing.BoxLayout.Y_AXIS));
        MesuresActives.setViewportView(ConteneurMesuresPanel);

        MesuresPanel.add(MesuresActives, java.awt.BorderLayout.CENTER);

        add(MesuresPanel);

        LiensPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 0));
        LiensPanel.setOpaque(false);
        LiensPanel.setLayout(new java.awt.BorderLayout());

        TitreOngletPanel.setOpaque(false);
        TitreOngletPanel.setLayout(new java.awt.BorderLayout());

        LiensTitre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LiensTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LiensTitre.setText("Fermeture des voies");
        LiensTitre.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 40));
        LiensTitre.setPreferredSize(new java.awt.Dimension(62, 30));
        TitreOngletPanel.add(LiensTitre, java.awt.BorderLayout.CENTER);

        TransmissionInterRegionPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 10, 10));
        TransmissionInterRegionPanel.setOpaque(false);
        TransmissionInterRegionPanel.setLayout(new javax.swing.BoxLayout(TransmissionInterRegionPanel, javax.swing.BoxLayout.LINE_AXIS));

        TransmissionInterRegionLabel.setFont(FontRegister.RobotoLight.deriveFont(14f));
        TransmissionInterRegionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TransmissionInterRegionLabel.setText("Prob. de transmission inter-région :");
        TransmissionInterRegionLabel.setAlignmentX(0.5F);
        TransmissionInterRegionLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        TransmissionInterRegionLabel.setMaximumSize(new java.awt.Dimension(200, 19));
        TransmissionInterRegionLabel.setMinimumSize(new java.awt.Dimension(200, 19));
        TransmissionInterRegionLabel.setPreferredSize(new java.awt.Dimension(200, 19));
        TransmissionInterRegionPanel.add(TransmissionInterRegionLabel);

        TransmissionInterRegion.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 99.9d, 0.5d));
        TransmissionInterRegionPanel.add(TransmissionInterRegion);

        TitreOngletPanel.add(TransmissionInterRegionPanel, java.awt.BorderLayout.PAGE_END);

        LiensPanel.add(TitreOngletPanel, java.awt.BorderLayout.PAGE_START);

        LiensScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        LiensScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        ConteneurLiensPanel.setOpaque(false);
        ConteneurLiensPanel.setLayout(new java.awt.GridLayout(3, 0));
        LiensScrollPane.setViewportView(ConteneurLiensPanel);

        LiensPanel.add(LiensScrollPane, java.awt.BorderLayout.CENTER);

        add(LiensPanel);

        VaccinsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 0));
        VaccinsPanel.setOpaque(false);
        VaccinsPanel.setLayout(new java.awt.BorderLayout());

        TitrePanel.setMinimumSize(new java.awt.Dimension(77, 33));
        TitrePanel.setOpaque(false);
        TitrePanel.setPreferredSize(new java.awt.Dimension(102, 30));
        TitrePanel.setLayout(new java.awt.BorderLayout());

        VaccinsTitre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        VaccinsTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VaccinsTitre.setText("Vaccins");
        VaccinsTitre.setPreferredSize(new java.awt.Dimension(62, 30));
        TitrePanel.add(VaccinsTitre, java.awt.BorderLayout.CENTER);

        AjouterVaccins.setFont(new java.awt.Font("Dialog", 0, 25)); // NOI18N
        AjouterVaccins.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AjouterVaccins.setText("+");
        AjouterVaccins.setPreferredSize(new java.awt.Dimension(40, 30));
        AjouterVaccins.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AjouterVaccinsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AjouterVaccinsMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AjouterVaccinsMouseReleased(evt);
            }
        });
        TitrePanel.add(AjouterVaccins, java.awt.BorderLayout.EAST);

        VaccinsPanel.add(TitrePanel, java.awt.BorderLayout.PAGE_START);

        VaccinsScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        VaccinsScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        VaccinsScrollPane.setOpaque(false);

        ConteneurVaccinsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ConteneurVaccinsPanel.setOpaque(false);
        ConteneurVaccinsPanel.setLayout(new javax.swing.BoxLayout(ConteneurVaccinsPanel, javax.swing.BoxLayout.Y_AXIS));
        VaccinsScrollPane.setViewportView(ConteneurVaccinsPanel);

        VaccinsPanel.add(VaccinsScrollPane, java.awt.BorderLayout.CENTER);

        add(VaccinsPanel);

        StatsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 0));
        StatsPanel.setOpaque(false);
        StatsPanel.setLayout(new java.awt.BorderLayout());

        TitreStatsPanel.setOpaque(false);
        TitreStatsPanel.setLayout(new java.awt.BorderLayout());

        StatsTitreLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        StatsTitreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StatsTitreLabel.setText("Statistiques");
        StatsTitreLabel.setPreferredSize(new java.awt.Dimension(62, 30));
        TitreStatsPanel.add(StatsTitreLabel, java.awt.BorderLayout.CENTER);

        ExportStats.setFont(new java.awt.Font("Dialog", 0, 25)); // NOI18N
        ExportStats.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ExportStats.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_upload_20px.png"))); // NOI18N
        ExportStats.setPreferredSize(new java.awt.Dimension(40, 30));
        ExportStats.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ExportStatsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ExportStatsMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ExportStatsMouseReleased(evt);
            }
        });
        TitreStatsPanel.add(ExportStats, java.awt.BorderLayout.EAST);

        StatsPanel.add(TitreStatsPanel, java.awt.BorderLayout.PAGE_START);

        ConteneurStatsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ConteneurStatsPanel.setOpaque(false);
        ConteneurStatsPanel.setLayout(new java.awt.BorderLayout());
        StatsPanel.add(ConteneurStatsPanel, java.awt.BorderLayout.CENTER);

        add(StatsPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void AjouterMesureMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjouterMesureMouseEntered
        updateBoutonAjouter(true, (JLabel)evt.getSource());
        this.getRootPane().repaint();
    }//GEN-LAST:event_AjouterMesureMouseEntered

    private void AjouterMesureMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjouterMesureMouseExited
        updateBoutonAjouter(false, (JLabel)evt.getSource());
        this.getRootPane().repaint();
    }//GEN-LAST:event_AjouterMesureMouseExited

    private void AjouterMesureMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjouterMesureMouseReleased
        ObjetSimulationMesure panel = addMesure(null);
        
        // On veut créer la mesure immédiatement, mais seulement quand on appuie +
        panel.sauvegarderMesure();
    }//GEN-LAST:event_AjouterMesureMouseReleased

    private void AjouterVaccinsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjouterVaccinsMouseEntered
        updateBoutonAjouter(true, (JLabel)evt.getSource());
        this.getRootPane().repaint();     
    }//GEN-LAST:event_AjouterVaccinsMouseEntered

    private void AjouterVaccinsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjouterVaccinsMouseExited
        updateBoutonAjouter(false, (JLabel)evt.getSource());
        this.getRootPane().repaint();        
    }//GEN-LAST:event_AjouterVaccinsMouseExited

    private void AjouterVaccinsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjouterVaccinsMouseReleased
        ObjetSimulationVaccin panel = addVaccin(null);
        
        // On veut créer le vaccin immédiatement, mais seulement quand on appuie +
        //panel.sauvegarderVaccin();
    }//GEN-LAST:event_AjouterVaccinsMouseReleased

    private void ExportStatsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExportStatsMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ExportStatsMouseEntered

    private void ExportStatsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExportStatsMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ExportStatsMouseExited

    private void ExportStatsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExportStatsMouseReleased
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
    }//GEN-LAST:event_ExportStatsMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AjouterMesure;
    private javax.swing.JLabel AjouterVaccins;
    private ca.ulaval.glo2004.afficheur.boutons.ToggleBouton BoutonLiens;
    private ca.ulaval.glo2004.afficheur.boutons.ToggleBouton BoutonMesures;
    private ca.ulaval.glo2004.afficheur.boutons.ToggleBouton BoutonStats;
    private ca.ulaval.glo2004.afficheur.boutons.ToggleBouton BoutonVaccins;
    private javax.swing.JPanel ConteneurLiensPanel;
    private javax.swing.JPanel ConteneurMesuresPanel;
    private javax.swing.JPanel ConteneurStatsPanel;
    private javax.swing.JPanel ConteneurVaccinsPanel;
    private javax.swing.JLabel ExportStats;
    private javax.swing.JPanel LiensPanel;
    private javax.swing.JScrollPane LiensScrollPane;
    private javax.swing.JLabel LiensTitre;
    private javax.swing.JScrollPane MesuresActives;
    private javax.swing.JPanel MesuresPanel;
    private javax.swing.JLabel MesuresTitre;
    private ca.ulaval.glo2004.afficheur.utilsUI.PanelArrondi SidePanel;
    private javax.swing.JPanel SidePanelParent;
    private javax.swing.JPanel StatsPanel;
    private javax.swing.JLabel StatsTitreLabel;
    private javax.swing.JPanel Titre;
    private javax.swing.JPanel TitreOngletPanel;
    private javax.swing.JPanel TitrePanel;
    private javax.swing.JPanel TitreStatsPanel;
    private javax.swing.JSpinner TransmissionInterRegion;
    private javax.swing.JLabel TransmissionInterRegionLabel;
    private javax.swing.JPanel TransmissionInterRegionPanel;
    private javax.swing.JPanel VaccinsPanel;
    private javax.swing.JScrollPane VaccinsScrollPane;
    private javax.swing.JLabel VaccinsTitre;
    // End of variables declaration//GEN-END:variables

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        this.getRootPane().repaint();
    }
}
