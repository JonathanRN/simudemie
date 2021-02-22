/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author Jonathan
 */
public final class FontRegister {
    public static Font RobotoLight, RobotoThin, RobotoRegular;
    
    public static void register() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            RobotoLight = Font.createFont(Font.TRUETYPE_FONT, new File(Thread.currentThread().getContextClassLoader().getResource("fonts\\Roboto-Light.ttf").toURI()));
            RobotoThin = Font.createFont(Font.TRUETYPE_FONT, new File(Thread.currentThread().getContextClassLoader().getResource("fonts\\Roboto-Thin.ttf").toURI()));
            RobotoRegular = Font.createFont(Font.TRUETYPE_FONT, new File(Thread.currentThread().getContextClassLoader().getResource("fonts\\Roboto-Regular.ttf").toURI()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        } catch(URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
