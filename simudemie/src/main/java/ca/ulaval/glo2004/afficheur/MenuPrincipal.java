package ca.ulaval.glo2004.afficheur;

import com.formdev.flatlaf.IntelliJTheme;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.UIManager;

public class MenuPrincipal extends javax.swing.JFrame {    
    private static final String ICON_PATH = "/icons/icon_virus_64px.png";
    
    public MenuPrincipal() {
        initTabbedPaneProperties();
        initComponents();
    }
    
    /*
    * Doit être appelé avant la création du TabbedPane
    */
    private void initTabbedPaneProperties() {
        UIManager.put("TabbedPaneUI", "ca.ulaval.glo2004.afficheur.CustomTabbedPaneUI");
        
        UIManager.put("TabbedPane.tabSelectionHeight", 5);
        UIManager.put("TabbedPane.tabAreaAlignment", "leading");
        UIManager.put("TabbedPane.tabHeight", 100);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainMenu = new javax.swing.JPanel();
        TabMenu = new javax.swing.JTabbedPane();
        TitleMenu = new javax.swing.JPanel();
        ScenarioTab = new ca.ulaval.glo2004.afficheur.ScenarioTab();
        MapTab = new ca.ulaval.glo2004.afficheur.MapTab();
        jPanel2 = new javax.swing.JPanel();
        QuitButton = new javax.swing.JPanel();
        TitleMenuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simudémie");
        setBackground(new java.awt.Color(0, 255, 0));
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(1280, 720));

        MainMenu.setBackground(new java.awt.Color(46, 52, 64));
        MainMenu.setOpaque(false);
        MainMenu.setLayout(new java.awt.BorderLayout());

        TabMenu.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        TabMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabMenu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TabMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TabMenuMousePressed(evt);
            }
        });

        TitleMenu.setBackground(new java.awt.Color(46, 52, 64));
        TitleMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TitleMenu.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        javax.swing.GroupLayout TitleMenuLayout = new javax.swing.GroupLayout(TitleMenu);
        TitleMenu.setLayout(TitleMenuLayout);
        TitleMenuLayout.setHorizontalGroup(
            TitleMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        TitleMenuLayout.setVerticalGroup(
            TitleMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        TabMenu.addTab("", new javax.swing.ImageIcon(getClass().getResource("/icons/Logo3.130FINAL.png")), TitleMenu, "Accueil"); // NOI18N
        TabMenu.addTab("", new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_play_40px.png")), ScenarioTab); // NOI18N
        TabMenu.addTab("", new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_country_40px.png")), MapTab); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1279, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1162, Short.MAX_VALUE)
        );

        TabMenu.addTab("", new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_microorganisms_40px.png")), jPanel2); // NOI18N

        javax.swing.GroupLayout QuitButtonLayout = new javax.swing.GroupLayout(QuitButton);
        QuitButton.setLayout(QuitButtonLayout);
        QuitButtonLayout.setHorizontalGroup(
            QuitButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        QuitButtonLayout.setVerticalGroup(
            QuitButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        TabMenu.addTab("", new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_exit_35px.png")), QuitButton); // NOI18N

        MainMenu.add(TabMenu, java.awt.BorderLayout.CENTER);

        getContentPane().add(MainMenu, java.awt.BorderLayout.CENTER);
        setJMenuBar(TitleMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TabMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabMenuMousePressed
        if (TabMenu.getSelectedIndex() == TabMenu.getTabCount() - 1) {
            System.exit(0);
        }
    }//GEN-LAST:event_TabMenuMousePressed

    public static void main(String args[]) {
        IntelliJTheme.install(MenuPrincipal.class.getResourceAsStream("/themes/nord.theme.json"));
        UIManager.put("RootPaneUI", "ca.ulaval.glo2004.afficheur.CustomRootPaneUI");
        UIManager.put("Button.arc", 15);
        
        // TODO NE PAS CALL CA ICI
        FontRegister.register();

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
    private javax.swing.JPanel MainMenu;
    private ca.ulaval.glo2004.afficheur.MapTab MapTab;
    private javax.swing.JPanel QuitButton;
    private ca.ulaval.glo2004.afficheur.ScenarioTab ScenarioTab;
    private javax.swing.JTabbedPane TabMenu;
    private javax.swing.JPanel TitleMenu;
    private javax.swing.JMenuBar TitleMenuBar;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
