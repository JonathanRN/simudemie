/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.utilsUI;

import com.formdev.flatlaf.ui.FlatSliderUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

/**
 *
 * @author Jonathan
 */
public class CustomSliderUI extends FlatSliderUI {
    public static ComponentUI createUI( JComponent c ) {
	return new CustomSliderUI();
    }
    
    @Override
    public void paintThumb( Graphics g ) {
        this.thumbSize = new Dimension(25,25);
        super.paintThumb(g);
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.white);
        g2d.setFont(FontRegister.RobotoLight.deriveFont(15f));
        
        String jour = String.format("Jour %s", this.slider.getValue());
        Rectangle2D jourBounds = g2d.getFontMetrics().getStringBounds(jour, g);
        
        int offsetX = thumbRect.x - 5;
        int halfWidth = (int)jourBounds.getWidth() / 2;
        
        if (offsetX - halfWidth <= 0) {
            offsetX += halfWidth;
        }
        else if (offsetX + halfWidth >= trackRect.width) {
            offsetX -= halfWidth;
        }
        g2d.drawString(jour, offsetX, thumbRect.y - 6);
    }
    
    @Override
    public void paintTrack( Graphics g ) {
        super.paintTrack(g);
        this.trackWidth = 5;
    }
}
