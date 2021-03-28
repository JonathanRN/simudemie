/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.io.Serializable;


public class VoieLiaison implements Serializable {
    
    private Pays paysOrigine;
    private Pays paysDestination;
    private TypeVoie type;
    private Path2D.Double ligneCourbe;
    private Point centre;
    private boolean accessible;
    
    public VoieLiaison(TypeVoie type, Pays origine, Pays destination, Path2D.Double ligneCourbe, Point centre)
    {
        this.type = type;
        this.paysOrigine = origine;
        this.paysDestination = destination;
        this.ligneCourbe = ligneCourbe;
        this.centre = centre;
        accessible = true;
    }
        
    public Pays getPaysOrigine(){ return paysOrigine; }
    
    public Pays getPaysDestination() { return paysDestination; }
    
    public TypeVoie getType() { return type; }
    
    public void setType(TypeVoie type) {
        this.type = type;
    }
    
    public boolean getAccessible() { return accessible; }
    
    public void setPaysOrigine(Pays paysOrigine)
    {
        this.paysOrigine = paysOrigine;
    }
    
    public void setPaysDestination(Pays paysDestination)
    {
        this.paysDestination = paysDestination;
    }
    
    public void setAccessible(boolean accessible)
    {
        this.accessible = accessible;
    }
    
    public Path2D.Double getLigne() {
        return ligneCourbe;
    }
    
    public Point getCentre() {
        return centre;
    }
    
    public void setLigne(Path2D.Double ligne) {
        ligneCourbe.reset();
        ligneCourbe = ligne;
    }
    
    public Color getCouleur() {
        switch (type) {
            case Terrestre:
                return Color.orange;
            case Aerien:
                return Color.white;
            case Maritime:
                return Color.cyan;
            default:
                return Color.red;
        }
    }
    
    public enum TypeVoie {
        Terrestre,
        Maritime,
        Aerien
    }
}
