/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Mick
 */
public class FileHelper<T> {
    
    private String path;
    
    public FileHelper(String path) {
        this.path = path;
    }
    
    public <T extends Serializable> ArrayList<T> charger() {
        ArrayList<T> list = new ArrayList<>();
        try {
            File file = new File(path);
            
            try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
                list = (ArrayList<T>) ois.readObject();
            }
                
        } catch(IOException | ClassNotFoundException e) {
             // TODO: Afficher erreur de chargement avec petite boîte cute
        }
        
        return list;
    }
    
    public <T extends Serializable> T importer(String path) {
        T object = null;
        
        try {
            // TODO: Afficher boîte de choix pour décider de l'emplacement
            File file = new File(path);
            
            try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
                object = (T) ois.readObject();
            }
                
        } catch(IOException | ClassNotFoundException e) {
             // TODO: Afficher erreur de chargement avec petite boîte cute
        }
        
        return object;
    }
    
    public <T extends Serializable> void sauvegarder(Collection<T> object) {
        try {
            try (FileOutputStream fos = new FileOutputStream(path); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(object);
            }
        } catch(IOException ioe) {
            // TODO: Afficher erreur de sauvegarde avec petite boîte cute
        }
    }
    
    public <T extends Serializable> void exporter(T object, String path) {
        try {
            // TODO: Afficher boîte de choix pour décider de l'emplacement
            
            try (FileOutputStream fos = new FileOutputStream(path); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(object);
            }
        } catch(IOException ioe) {
            // TODO: Afficher erreur de sauvegarde avec petite boîte cute
        }
    }
    
}
