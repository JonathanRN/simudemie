/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.MenuPrincipal.onglets;

import ca.ulaval.glo2004.afficheur.MenuPrincipal.objetsUI.ObjetUI;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jonathan
 */
public abstract class OngletUI extends javax.swing.JPanel {

    protected ArrayList<ObjetUI> objets = new ArrayList<>();
    protected ObjetUI courant;
    protected JFileChooser fileChooser;
    
    public OngletUI() {
        initComponents();
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serializable java file", "ser");
        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(filter);
    }
    
    public int getIndexCourant() {
        if (courant != null) {
            return objets.indexOf(courant);
        }
        return -1;
    }
    
    public ObjetUI getCourant() {
        return courant;
    }
    
    public void setCourant(ObjetUI courant) {
        this.courant = courant;
    }

    public void ajouterObjetUI() {
    }
    
    public void retirerCourant() {
        objets.remove(courant);
        if (!objets.isEmpty()) {
            onClickObjetUI(objets.get(0));
        } else {
            setCourant(null);
        }
    }
    
    public void onClickObjetUI(ObjetUI objet) {
        if (courant != null &&
            objet != courant) {
            courant.setToggled(false);
        }
        
        if (objet != null) {
            courant = objet;

            // Voir dans la liste et toggle le bon avec le bon index
            courant.setToggled(true);
        }
    }
    
    public void onRevenirSurOnglet() {
        onClickObjetUI(courant);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
