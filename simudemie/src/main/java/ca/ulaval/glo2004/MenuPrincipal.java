package ca.ulaval.glo2004;

import static com.formdev.flatlaf.FlatClientProperties.TABBED_PANE_TAB_AREA_ALIGNMENT;
import static com.formdev.flatlaf.FlatClientProperties.TABBED_PANE_TAB_HEIGHT;
import static com.formdev.flatlaf.FlatClientProperties.TABBED_PANE_TAB_ICON_PLACEMENT;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.UIManager;

public class MenuPrincipal extends javax.swing.JFrame {
    private static final String ICON_PATH = "/icon_virus_64px.png";
    private static final String LOGO_PATH = "/simudemie_logo_100px_63px.png";
    
    public MenuPrincipal() {
        initComponents();
        initTabbedPaneProperties();
        
        ProjectPanelContainer.setLayout(new WrapLayout(0, 50, 50));
        
        for (int i = 0; i < 4; i++) {
            ProjectPanelContainer.add(new ProjetPanel());
        }
        
        // todo, trouver meilleure facon de caller ca
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
    }
    
    private void initTabbedPaneProperties() {
        TabbedPane.putClientProperty(TABBED_PANE_TAB_AREA_ALIGNMENT, "leading");
        TabbedPane.putClientProperty(TABBED_PANE_TAB_HEIGHT, 75);
        TabbedPane.putClientProperty(TABBED_PANE_TAB_ICON_PLACEMENT, 1);
        TabbedPane.setSelectedIndex(1);
        
        TabbedPane.setEnabledAt(0, false);
        TabbedPane.setDisabledIconAt(0, new ImageIcon(getClass().getResource(LOGO_PATH)));
        QuitButton.setPreferredSize(new Dimension(TabbedPane.getBoundsAt(0).width, QuitButton.getHeight()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        QuitPannel = new javax.swing.JPanel();
        QuitButton = new javax.swing.JPanel();
        QuitLabel = new javax.swing.JLabel();
        Main = new javax.swing.JPanel();
        TabbedPane = new javax.swing.JTabbedPane();
        Title = new javax.swing.JLabel();
        Projets = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ProjectPanelContainer = new javax.swing.JPanel();
        Creation = new javax.swing.JPanel();
        TitleMenuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simudémie");
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        QuitPannel.setOpaque(false);

        QuitButton.setToolTipText("Quitter l'application");
        QuitButton.setPreferredSize(new java.awt.Dimension(120, 75));
        QuitButton.setLayout(new java.awt.BorderLayout());

        QuitLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        QuitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_exit_25px.png"))); // NOI18N
        QuitLabel.setToolTipText("Quitter l'application");
        QuitLabel.setPreferredSize(new java.awt.Dimension(120, 75));
        QuitLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuitLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QuitLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QuitLabelMouseExited(evt);
            }
        });
        QuitButton.add(QuitLabel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout QuitPannelLayout = new javax.swing.GroupLayout(QuitPannel);
        QuitPannel.setLayout(QuitPannelLayout);
        QuitPannelLayout.setHorizontalGroup(
            QuitPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuitPannelLayout.createSequentialGroup()
                .addComponent(QuitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 973, Short.MAX_VALUE))
        );
        QuitPannelLayout.setVerticalGroup(
            QuitPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuitPannelLayout.createSequentialGroup()
                .addContainerGap(517, Short.MAX_VALUE)
                .addComponent(QuitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(QuitPannel);

        Main.setOpaque(false);
        Main.setLayout(new java.awt.BorderLayout());

        TabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        TabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        TabbedPane.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        Title.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        Title.setAlignmentY(0.0F);
        Title.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Title.setOpaque(true);
        TabbedPane.addTab("", new javax.swing.ImageIcon(getClass().getResource("/simudemie_logo_100px_63px.png")), Title); // NOI18N

        Projets.setBackground(new java.awt.Color(59, 66, 82));
        Projets.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Projets.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        Projets.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Scénarios");
        Projets.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBackground(new java.awt.Color(59, 66, 82));
        jScrollPane1.setMaximumSize(new java.awt.Dimension(100, 100));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 100));

        ProjectPanelContainer.setBackground(new java.awt.Color(59, 66, 82));
        jScrollPane1.setViewportView(ProjectPanelContainer);

        Projets.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        TabbedPane.addTab("", new javax.swing.ImageIcon(getClass().getResource("/icon_play_25px.png")), Projets, "Scénarios"); // NOI18N

        Creation.setBackground(new java.awt.Color(59, 66, 82));
        Creation.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Creation.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        javax.swing.GroupLayout CreationLayout = new javax.swing.GroupLayout(Creation);
        Creation.setLayout(CreationLayout);
        CreationLayout.setHorizontalGroup(
            CreationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 972, Short.MAX_VALUE)
        );
        CreationLayout.setVerticalGroup(
            CreationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 588, Short.MAX_VALUE)
        );

        TabbedPane.addTab("", new javax.swing.ImageIcon(getClass().getResource("/icon_add_25px.png")), Creation, "Création"); // NOI18N

        Main.add(TabbedPane, java.awt.BorderLayout.CENTER);

        getContentPane().add(Main);
        setJMenuBar(TitleMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void QuitLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuitLabelMouseEntered
        Object color = UIManager.get("TabbedPane.hoverColor");
        QuitButton.setBackground((Color)color);
    }//GEN-LAST:event_QuitLabelMouseEntered

    private void QuitLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuitLabelMouseExited
        Object color = UIManager.get("TabbedPane.background");
        QuitButton.setBackground((Color)color);
    }//GEN-LAST:event_QuitLabelMouseExited

    private void QuitLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuitLabelMouseClicked
        System.exit(0);
    }//GEN-LAST:event_QuitLabelMouseClicked

    public static void main(String args[]) {
        FlatNordIJTheme.install();

        UIManager.put("TabbedPane.tabSelectionHeight", 5);
        UIManager.put("TabbedPane.showTabSeparators", true);
        UIManager.put("TabbedPane.tabSeparatorsFullHeight", true);
        UIManager.put("TabbedPane.hasFullBorder", true);
        UIManager.put("TabbedPane.disableForeground", Color.WHITE);
        //UIManager.put("TabbedPane.underlineColor", new Color(167, 209, 221));

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new MenuPrincipal();
                
		frame.dispose();
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		frame.setVisible(true);

		// enable/disable window decoration for later created frames/dialogs
		frame.setDefaultLookAndFeelDecorated(true);
                frame.setIconImage(new javax.swing.ImageIcon(getClass().getResource(ICON_PATH)).getImage());
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Creation;
    private javax.swing.JPanel Main;
    private javax.swing.JPanel ProjectPanelContainer;
    private javax.swing.JPanel Projets;
    private javax.swing.JPanel QuitButton;
    private javax.swing.JLabel QuitLabel;
    private javax.swing.JPanel QuitPannel;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JLabel Title;
    private javax.swing.JMenuBar TitleMenuBar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
