/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.utilsUI;

import com.formdev.flatlaf.ui.FlatTabbedPaneUI;
import static com.formdev.flatlaf.util.UIScale.scale;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

/**
 *
 * @author Jonathan
 */
public class CustomTabbedPaneUI extends FlatTabbedPaneUI {
    public static ComponentUI createUI( JComponent c ) {
	return new CustomTabbedPaneUI();
    }
    
    @Override
    protected void paintTabSelection( Graphics g, int tabPlacement, int x, int y, int w, int h ) {
        g.setColor( tabPane.isEnabled() ? underlineColor : disabledUnderlineColor );
        // paint underline selection
        Insets contentInsets = getContentBorderInsets( tabPlacement );
        int tabSelectionHeight = scale( this.tabSelectionHeight );
        switch( tabPlacement ) {
            case TOP:
            default:
                    int sy = y + h + contentInsets.top - tabSelectionHeight;
                    g.fillRect( x, sy, w, tabSelectionHeight );
                    break;

            case BOTTOM:
                    g.fillRect( x, y - contentInsets.bottom, w, tabSelectionHeight );
                    break;

            case RIGHT:
                    int sx = x + w + contentInsets.left - tabSelectionHeight;
                    g.fillRect( sx, y, tabSelectionHeight, h );
                    break;
                    
            case LEFT:
                    g.fillRect( x - contentInsets.right, y, tabSelectionHeight, h );
                    break;
        }
    }
    
    @Override
    protected Insets getContentBorderInsets( int tabPlacement ) {
        return new Insets(0, 0, 0, 0);
    }
}
