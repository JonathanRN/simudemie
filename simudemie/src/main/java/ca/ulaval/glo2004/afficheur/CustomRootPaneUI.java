/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur;

import com.formdev.flatlaf.ui.FlatRootPaneUI;
import com.formdev.flatlaf.ui.FlatTitlePane;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

/**
 *
 * @author Jonathan
 */
public class CustomRootPaneUI extends FlatRootPaneUI {
    public static ComponentUI createUI( JComponent c ) {
	return new CustomRootPaneUI();
    }
    
    @Override
    protected FlatTitlePane createTitlePane() {
        return new CustomTitlePaneUI(rootPane);
    }
}
