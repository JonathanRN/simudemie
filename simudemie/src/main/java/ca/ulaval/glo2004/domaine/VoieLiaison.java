/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine;

import ca.ulaval.glo2004.afficheur.utilsUI.Couleurs;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class VoieLiaison implements Externalizable {
    
    private Pays paysOrigine;
    private Pays paysDestination;
    private double tauxPropag = 0.1;
    private TypeVoie type;
    private Path2D.Double ligneCourbe;
    private Point centre;
    private boolean accessible;
    protected transient final Color Ter = Couleurs.terrestres;
    protected transient final Color Mar = Couleurs.maritimes;
    protected transient final Color Aer = Couleurs.aeriens;

    public VoieLiaison() {}
    
    public VoieLiaison(TypeVoie type, Pays origine, Pays destination, Path2D.Double ligneCourbe, Point centre)
    {
        this.type = type;
        this.paysOrigine = origine;
        this.paysDestination = destination;
        this.ligneCourbe = ligneCourbe;
        this.centre = centre;
        accessible = true;
    }
    
    public VoieLiaison(TypeVoie type, Pays origine, Pays destination, Path2D.Double ligneCourbe, Point centre, double tauxPropag)
    {
        this.type = type;
        this.paysOrigine = origine;
        this.paysDestination = destination;
        this.ligneCourbe = ligneCourbe;
        this.centre = centre;
        this.tauxPropag = tauxPropag;
        accessible = true;
    }
    
    public VoieLiaison(VoieLiaison voie)
    {
        this.type = voie.type;
        this.paysOrigine = new Pays(voie.paysOrigine);
        this.paysDestination = new Pays(voie.paysDestination);
        this.tauxPropag = voie.tauxPropag;
        
        // todo voir si modifications dans carte change ici
        this.ligneCourbe = voie.ligneCourbe;
        this.centre = voie.centre;
        this.accessible = voie.accessible;
    }
        
    public Pays getPaysOrigine(){ return paysOrigine; }
    
    public Pays getPaysDestination() { return paysDestination; }
    
    public TypeVoie getType() { return type; }
    
    public void setType(TypeVoie type) {this.type = type;}
        
    public double getTauxPropag() { return tauxPropag; }
    
    public boolean getAccessible() { return accessible; }
    
    public void setPaysOrigine(Pays paysOrigine) { this.paysOrigine = paysOrigine; }
    
    public void setPaysDestination(Pays paysDestination) {this.paysDestination = paysDestination;}
    
    public void setAccessible(boolean accessible){this.accessible = accessible;}
    
    public void setTauxPropag(double tauxPropag) {this.tauxPropag = tauxPropag;}
    
    public Path2D.Double getLigne() {return ligneCourbe;}
    
    public void setLigne(Path2D.Double ligne) { ligneCourbe.reset(); ligneCourbe = ligne; }
    
    public Point getCentre() {return centre;}
    
    public void setCentre(Point centre) {this.centre = centre;}
  
    public Color getCouleur() {
        switch (type) {
            case Terrestre:
                return Ter;
            case Aerien:
                return Aer;
            case Maritime:
                return Mar;
            default:
                return Color.red;
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(paysOrigine);
        out.writeObject(paysDestination);
        out.writeObject(type);
        out.writeObject(ligneCourbe);
        out.writeObject(centre);
        out.writeBoolean(accessible);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        paysOrigine = (Pays) in.readObject();
        paysDestination = (Pays) in.readObject();
        type = (TypeVoie) in.readObject();
        ligneCourbe = (Path2D.Double) in.readObject();
        centre = (Point) in.readObject();
        accessible = in.readBoolean();
    }
    
    public enum TypeVoie {
        Terrestre,
        Maritime,
        Aerien
    }
}
