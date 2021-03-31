/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.domaine.controleur;

import ca.ulaval.glo2004.domaine.helper.FileHelper;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mick
 * 
 */
public abstract class GestionnaireOnglet<T extends Serializable> {
    protected FileHelper<T> fileHelper;
    protected List<T> list;
    
    public abstract T creer(Object ... arguments);
    
    protected void ajouter(T object) {
        list.add(object);
        sauvegarder();
    }

    public void sauvegarder() {
        fileHelper.sauvegarder(list);
    }
    
    public void charger() {
        list = fileHelper.charger();
    }
    
    public void supprimer(int index) {
        list.remove(index);
        sauvegarder();
    }
    
    public T importer(String path) throws Exception {
        T object = fileHelper.importer(path);
        list.add(object);
        sauvegarder();
        return object;
    }
    
    public void exporter(int index, String path) throws Exception {
        T element = getElement(index);
        fileHelper.exporter(element, path);
    }
    
    public List<T> getList() {
        return list;
    }
    
    public T getElement(int index) {
        if (list.isEmpty() || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }
}
