/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur;

import ca.ulaval.glo2004.afficheur.carteActions.ActionCarte;
import ca.ulaval.glo2004.afficheur.carteActions.AjouterPointAction;
import ca.ulaval.glo2004.afficheur.carteActions.DessinerPolygoneAction;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Jonathan
 */
public class CreationCarte extends javax.swing.JPanel {

    private Stack<ActionCarte> actionsFaites = new Stack<>();
    private Stack<ActionCarte> actionsUndo = new Stack<>();
    private Polygon courant = new Polygon();
    private ArrayList<Polygon> polygones = new ArrayList<>();
    
    public CreationCarte() {
        initComponents();
    }
    
    private void placerPoint(int x, int y) {
        AjouterPointAction action = new AjouterPointAction(courant, x, y);
        ajouterAction(action);
    }
    
    private void dessinerPolygone() {
        DessinerPolygoneAction action = new DessinerPolygoneAction(courant, polygones);
        ajouterAction(action);
    }
    
    private void ajouterAction(ActionCarte action) {
        actionsFaites.push(action);
        action.Executer();
        // On veut pas modifier le futur
        actionsUndo.clear();
        repaint();
    }
    
    private void undoAction() {
        if (!actionsFaites.isEmpty()) {
            ActionCarte action = actionsFaites.pop();
            action.Undo();
            actionsUndo.push(action);
            repaint();
        }
    }
    
    private void redoAction() {
        if (!actionsUndo.isEmpty()) {
            ActionCarte action = actionsUndo.pop();
            action.Executer();
            actionsFaites.push(action);
            repaint();
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D graphics = (Graphics2D) g;
        for (int i = 0; i < courant.npoints; i++) {
            graphics.fillOval(courant.xpoints[i], courant.ypoints[i], 10, 10);
        }
        
        for (Polygon p : polygones) {
            graphics.fillPolygon(p);
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

        ToolBar = new javax.swing.JPanel();
        BoutonSelection = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        BoutonCréation = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        IndexPays = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BoutonAjout = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        BoutonEfface = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        BoutonEdition = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        Undo = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        Redo = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        BoutonAide = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        BoutonRetour = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(46, 52, 64));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        ToolBar.setBackground(new java.awt.Color(67, 76, 94));

        BoutonSelection.setBackground(new java.awt.Color(67, 76, 94));
        BoutonSelection.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BoutonSelectionMouseEntered(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_cursor_25px.png"))); // NOI18N

        javax.swing.GroupLayout BoutonSelectionLayout = new javax.swing.GroupLayout(BoutonSelection);
        BoutonSelection.setLayout(BoutonSelectionLayout);
        BoutonSelectionLayout.setHorizontalGroup(
            BoutonSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        BoutonSelectionLayout.setVerticalGroup(
            BoutonSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        BoutonCréation.setBackground(new java.awt.Color(67, 76, 94));
        BoutonCréation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BoutonSelectionMouseEntered(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_circle_25px.png"))); // NOI18N

        javax.swing.GroupLayout BoutonCréationLayout = new javax.swing.GroupLayout(BoutonCréation);
        BoutonCréation.setLayout(BoutonCréationLayout);
        BoutonCréationLayout.setHorizontalGroup(
            BoutonCréationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        BoutonCréationLayout.setVerticalGroup(
            BoutonCréationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        IndexPays.setBackground(new java.awt.Color(67, 76, 94));
        IndexPays.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 25, 5, 5));
        IndexPays.setPreferredSize(new java.awt.Dimension(200, 50));

        jLabel1.setText("Pays : N/A");

        javax.swing.GroupLayout IndexPaysLayout = new javax.swing.GroupLayout(IndexPays);
        IndexPays.setLayout(IndexPaysLayout);
        IndexPaysLayout.setHorizontalGroup(
            IndexPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        IndexPaysLayout.setVerticalGroup(
            IndexPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel1.getAccessibleContext().setAccessibleName("");

        BoutonAjout.setBackground(new java.awt.Color(67, 76, 94));
        BoutonAjout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BoutonSelectionMouseEntered(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_add_new_25px.png"))); // NOI18N

        javax.swing.GroupLayout BoutonAjoutLayout = new javax.swing.GroupLayout(BoutonAjout);
        BoutonAjout.setLayout(BoutonAjoutLayout);
        BoutonAjoutLayout.setHorizontalGroup(
            BoutonAjoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        BoutonAjoutLayout.setVerticalGroup(
            BoutonAjoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        BoutonEfface.setBackground(new java.awt.Color(67, 76, 94));
        BoutonEfface.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BoutonSelectionMouseEntered(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_eraser_25px.png"))); // NOI18N

        javax.swing.GroupLayout BoutonEffaceLayout = new javax.swing.GroupLayout(BoutonEfface);
        BoutonEfface.setLayout(BoutonEffaceLayout);
        BoutonEffaceLayout.setHorizontalGroup(
            BoutonEffaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        BoutonEffaceLayout.setVerticalGroup(
            BoutonEffaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        BoutonEdition.setBackground(new java.awt.Color(67, 76, 94));
        BoutonEdition.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BoutonSelectionMouseEntered(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_pencil_25px.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel6MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout BoutonEditionLayout = new javax.swing.GroupLayout(BoutonEdition);
        BoutonEdition.setLayout(BoutonEditionLayout);
        BoutonEditionLayout.setHorizontalGroup(
            BoutonEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        BoutonEditionLayout.setVerticalGroup(
            BoutonEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        Undo.setBackground(new java.awt.Color(67, 76, 94));
        Undo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                UndoMouseReleased(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_up_left_25px.png"))); // NOI18N

        javax.swing.GroupLayout UndoLayout = new javax.swing.GroupLayout(Undo);
        Undo.setLayout(UndoLayout);
        UndoLayout.setHorizontalGroup(
            UndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        UndoLayout.setVerticalGroup(
            UndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        Redo.setBackground(new java.awt.Color(67, 76, 94));
        Redo.setPreferredSize(new java.awt.Dimension(50, 50));
        Redo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                RedoMouseReleased(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_up_right_25px.png"))); // NOI18N

        javax.swing.GroupLayout RedoLayout = new javax.swing.GroupLayout(Redo);
        Redo.setLayout(RedoLayout);
        RedoLayout.setHorizontalGroup(
            RedoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        RedoLayout.setVerticalGroup(
            RedoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        BoutonAide.setBackground(new java.awt.Color(67, 76, 94));
        BoutonAide.setPreferredSize(new java.awt.Dimension(50, 50));
        BoutonAide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BoutonSelectionMouseEntered(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_help_25px.png"))); // NOI18N

        javax.swing.GroupLayout BoutonAideLayout = new javax.swing.GroupLayout(BoutonAide);
        BoutonAide.setLayout(BoutonAideLayout);
        BoutonAideLayout.setHorizontalGroup(
            BoutonAideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        BoutonAideLayout.setVerticalGroup(
            BoutonAideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        BoutonRetour.setBackground(new java.awt.Color(67, 76, 94));
        BoutonRetour.setPreferredSize(new java.awt.Dimension(50, 50));
        BoutonRetour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BoutonSelectionMouseEntered(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_home_25px_1.png"))); // NOI18N

        javax.swing.GroupLayout BoutonRetourLayout = new javax.swing.GroupLayout(BoutonRetour);
        BoutonRetour.setLayout(BoutonRetourLayout);
        BoutonRetourLayout.setHorizontalGroup(
            BoutonRetourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        BoutonRetourLayout.setVerticalGroup(
            BoutonRetourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ToolBarLayout = new javax.swing.GroupLayout(ToolBar);
        ToolBar.setLayout(ToolBarLayout);
        ToolBarLayout.setHorizontalGroup(
            ToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ToolBarLayout.createSequentialGroup()
                .addComponent(BoutonSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BoutonCréation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(IndexPays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BoutonAjout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BoutonEfface, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BoutonEdition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(Undo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Redo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 426, Short.MAX_VALUE)
                .addComponent(BoutonAide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BoutonRetour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ToolBarLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BoutonAide, BoutonRetour, Redo});

        ToolBarLayout.setVerticalGroup(
            ToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ToolBarLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(ToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(BoutonAjout, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(IndexPays, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BoutonCréation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BoutonSelection, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BoutonEfface, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BoutonEdition, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BoutonRetour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BoutonAide, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Undo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Redo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        ToolBarLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {BoutonAide, BoutonAjout, BoutonCréation, BoutonEdition, BoutonEfface, BoutonRetour, BoutonSelection, Redo, Undo});

        add(ToolBar, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonSelectionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoutonSelectionMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_BoutonSelectionMouseEntered

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        placerPoint(evt.getX(), evt.getY());
    }//GEN-LAST:event_formMouseReleased

    private void UndoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UndoMouseReleased
        undoAction();
    }//GEN-LAST:event_UndoMouseReleased

    private void RedoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RedoMouseReleased
        redoAction();
    }//GEN-LAST:event_RedoMouseReleased

    private void jLabel6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseReleased
        // TODO TEMPORAIREMENT, A CHANGER
        dessinerPolygone();
        courant.reset();
    }//GEN-LAST:event_jLabel6MouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BoutonAide;
    private javax.swing.JPanel BoutonAjout;
    private javax.swing.JPanel BoutonCréation;
    private javax.swing.JPanel BoutonEdition;
    private javax.swing.JPanel BoutonEfface;
    private javax.swing.JPanel BoutonRetour;
    private javax.swing.JPanel BoutonSelection;
    private javax.swing.JPanel IndexPays;
    private javax.swing.JPanel Redo;
    private javax.swing.JPanel ToolBar;
    private javax.swing.JPanel Undo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
