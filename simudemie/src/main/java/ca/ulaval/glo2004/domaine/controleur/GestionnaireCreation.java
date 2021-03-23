/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine.controleur;

import ca.ulaval.glo2004.domaine.helper.FileHelper;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Mick
 * 
 */
public abstract class GestionnaireCreation<T extends Serializable> {
    protected FileHelper<T> fileHelper;
    protected Collection<T> collection;
    
    protected abstract T creer(Object ... parametre);
    protected abstract T getElement(String nom);
    
    public void ajouter(T object) {
        collection.add(object);
        sauvegarder();
    }
    
    public void charger() {
        collection = fileHelper.charger();
    }
    
    public void supprimer(String nom) {
        T element = getElement(nom);
        collection.remove(element);
        sauvegarder();
    }
    
    private void sauvegarder() {
        fileHelper.sauvegarder(collection);
    }
    
    public T importer(String path) {
        T object = fileHelper.importer(path);
        collection.add(object);
        sauvegarder();
        return object;
    }
    
    public void exporter(String nom, String path) {
        T element = getElement(nom);
        fileHelper.exporter(element, path);
    }
    
    public Collection<T> getCollection() {
        return collection;
    }
    
    /* ONGLET CARTE */
}
