/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.utilsUI;
import java.awt.Color;

/**
 *
 * @author henri
 */
public final class Couleurs {
//    
//          | Nord | https://www.nordtheme.com/
//    
//    | Dark - Polar Night |    HEX        RGB
//    
//    BackGround                #2E3440    46, 66, 82
//    Title Bar                 #3B4252    59, 66, 82
//        |                     #434C5E    67, 76, 94
//    Side Menu                 #4C566A    76, 86, 106
//    Pannel Arrondi            #D8DEE9    216, 222, 233, 30
//
//    | Light - Snow Storm |    HEX        RGB
//   
//    BackGround                #ECEFF4    236, 239, 244
//    TitleBar                  #E5E9F0    229, 233, 240
//    Side Menu                 #D8DEE9    216, 222, 233
//    Pannel Arrondi            #4C566A    76, 86, 106, 30
//    
//    | Frost & Aurora |        HEX        RGB   
//    
//    Infections                #BF616A    191, 97, 106
//    Sains                     #A3BE8C    163, 190, 140
//    Immunisations             #88C0D0    136, 192, 208
//    Morts                     #B48EAD    180, 142, 173  
//
//    Liens Terrestres          #D08770    208, 135, 112
//    Liens Maritimes           #5E81AC    94, 129, 172
//    Liens Aériens             #8FBCBB    143, 188, 187
//
//    Mesures                   #EBCB8B    235, 203, 139   
//    

    public static Color background = new Color(46, 66, 82);
    public static Color titleBar = new Color(59, 66, 82);
    public static Color sideMenu = new Color(76, 86, 106);
    public static Color sideMenuTransp = new Color(76, 86, 106, 100);
    public static Color sideMenuLessTransp = new Color(76, 86, 106, 150);
    public static Color sideMenuNoTransp = new Color(64,73,90);
    public static Color pannelArrondi = new Color(216, 222, 233, 30);
    public static Color pannelArrondiNoTransp = new Color(66, 72, 83);
    public static Color pannelArrondiNo = new Color(90, 95, 104);
    
    public static Color fondNoir = new Color(0, 0, 0, 150);
    public static Color invisible = new Color(0, 0, 0, 0);
    public static Color blanc = new Color(236, 239, 244);
    public static Color bg = new Color(71, 76, 88);
   
    public static Color selectionne = new Color(104,125,135);
    public static Color selectionneBorder = new Color(136, 192, 208);
    public static Color remplissage = new Color(216, 222, 233, 100);
    public static Color remplissageNoTransp = new Color(112, 118, 130);

    public static Color infections = new Color(191, 97, 106);
    public static Color sains = new Color(163, 190, 140);
    public static Color immunisations = new Color(136, 192, 208);
    public static Color morts = new Color(180, 142, 173);

    public static Color terrestres = new Color(208, 135, 112);
    public static Color maritimes = new Color(94, 129, 172);
    public static Color aeriens = new Color(143, 188, 187);

    public static Color mesures = new Color(235, 203, 139);  

}
