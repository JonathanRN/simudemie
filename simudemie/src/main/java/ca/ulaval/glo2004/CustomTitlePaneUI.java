/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004;

import com.formdev.flatlaf.ui.FlatTitlePane;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Objects;
import javax.swing.JRootPane;

/**
 *
 * @author Jonathan
 */
public class CustomTitlePaneUI extends FlatTitlePane {
    
    public CustomTitlePaneUI(JRootPane rootPane) {
        super(rootPane);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
	g.fillRect(0, 0, getWidth(), getHeight());
    }
    
    @Override
    protected void updateIcon() {
    }
}
