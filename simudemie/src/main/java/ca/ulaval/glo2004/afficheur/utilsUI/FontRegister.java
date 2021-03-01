/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.utilsUI;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

/**
 *
 * @author Jonathan
 */
public final class FontRegister {
    public static Font RobotoLight, RobotoThin, RobotoRegular;
    
    public static void register() {
        try {
            RobotoLight = Font.createFont(Font.TRUETYPE_FONT, FontRegister.class.getResourceAsStream("/fonts/Roboto-Light.ttf"));
            RobotoThin = Font.createFont(Font.TRUETYPE_FONT, FontRegister.class.getResourceAsStream("/fonts/Roboto-Thin.ttf"));
            RobotoRegular = Font.createFont(Font.TRUETYPE_FONT, FontRegister.class.getResourceAsStream("/fonts/Roboto-Regular.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }
}
