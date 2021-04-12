/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.afficheur.carteActions;

import java.awt.Point;
import java.awt.Polygon;

/**
 *
 * @author Jonathan
 */
public class DragPointAction extends ActionCarte {

    private final Polygon polygone;
    private final int indexPoint;
    private final Point initiale, finale;

    public DragPointAction(Polygon polygone, int indexPoint, Point initiale, Point finale) {
        this.polygone = polygone;
        this.indexPoint = indexPoint;
        this.initiale = initiale;
        this.finale = finale;
    }
    
    @Override
    public void Executer() {
        polygone.xpoints[indexPoint] = finale.x;
        polygone.ypoints[indexPoint] = finale.y;
    }

    @Override
    public void Undo() {
        polygone.xpoints[indexPoint] = initiale.x;
        polygone.ypoints[indexPoint] = initiale.y;
    }
}
