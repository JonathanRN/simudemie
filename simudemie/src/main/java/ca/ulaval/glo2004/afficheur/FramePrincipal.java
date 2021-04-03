package ca.ulaval.glo2004.afficheur;

import ca.ulaval.glo2004.afficheur.Simulation.Simulation;
import ca.ulaval.glo2004.afficheur.CreationCarte.CreationCarte;
import ca.ulaval.glo2004.afficheur.onglets.OngletCarte;
import ca.ulaval.glo2004.afficheur.onglets.OngletScenario;
import ca.ulaval.glo2004.afficheur.utilsUI.FontRegister;
import com.formdev.flatlaf.IntelliJTheme;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.UIManager;

public class FramePrincipal extends javax.swing.JFrame {  
    private MenuPrincipal menu;
    private Simulation simulation;
    private CreationCarte creationCarte;
    
    private static final String ICON_PATH = "/icons/icon_virus_64px.png";
    
    public FramePrincipal() {
        initTabbedPaneProperties();
        initComponents();
        
        menu = MenuPrincipal;
    }
    
    /*
    * Doit être appelé avant la création du TabbedPane
    */
    private void initTabbedPaneProperties() {
        UIManager.put("TabbedPaneUI", "ca.ulaval.glo2004.afficheur.utilsUI.CustomTabbedPaneUI");
        
        UIManager.put("TabbedPane.tabSelectionHeight", 5);
        UIManager.put("TabbedPane.tabAreaAlignment", "leading");
        UIManager.put("TabbedPane.tabHeight", 100);
        UIManager.put("TabbedPane.minimumTabWidth", 130);
    }
    
    public void startSimulation(int index, OngletScenario onglet) {
        remove(menu);
        simulation = new Simulation(index, onglet);
        add(simulation);
        revalidate();
        repaint();
    }
    
    public void startCreationCarte(int index, OngletCarte onglet) {
        remove(menu);
        creationCarte = new CreationCarte(index, onglet);
        add(creationCarte);
        revalidate();
        repaint();
    }
    
    public void returnToHome() {
        if (simulation != null) {
            remove(simulation);
            simulation = null;
            add(menu);
        }
        else if (creationCarte != null) {
            remove(creationCarte);
            creationCarte = null;
            add(menu);
        }
        revalidate();
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

        MenuPrincipal = new ca.ulaval.glo2004.afficheur.MenuPrincipal();
        TitleMenuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simudémie");
        setBackground(new java.awt.Color(0, 255, 0));
        setMinimumSize(new java.awt.Dimension(640, 550));
        setPreferredSize(new java.awt.Dimension(1300, 720));
        getContentPane().add(MenuPrincipal, java.awt.BorderLayout.CENTER);
        setJMenuBar(TitleMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {        
        FontRegister.register();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                IntelliJTheme.install(FramePrincipal.class.getResourceAsStream("/themes/nord.theme.json"));
                UIManager.put("RootPaneUI", "ca.ulaval.glo2004.afficheur.utilsUI.CustomRootPaneUI");
                UIManager.put("SliderUI", "ca.ulaval.glo2004.afficheur.utilsUI.CustomSliderUI");

                UIManager.put("Button.arc", 15);
                UIManager.put("ProgressBar.arc", 999);
                
                JFrame frame = new FramePrincipal();
		frame.dispose();
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		frame.setVisible(true);

		// enable/disable window decoration for later created frames/dialogs
		frame.setDefaultLookAndFeelDecorated(true);
                frame.setIconImage(new javax.swing.ImageIcon(getClass().getResource(ICON_PATH)).getImage());
                frame.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ca.ulaval.glo2004.afficheur.MenuPrincipal MenuPrincipal;
    private javax.swing.JMenuBar TitleMenuBar;
    // End of variables declaration//GEN-END:variables

}
