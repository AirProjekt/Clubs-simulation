/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.uzdiz.memento;

import hr.foi.uzdiz.objekti.Klub;
import hr.foi.uzdiz.objekti.Utakmica;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Winner
 */
public class Originator {
    
    private Object state;
    
    public void set(Object state){
        this.state = state;
    }
    
    public Object saveToMemento(){
        return new Memento(state);
    }
    
    public void restoreFromMementoTablica(Object m){
        if (m instanceof Memento) {
            Memento memento = (Memento) m;
            state = memento.getSavedState();
            printajTablicu((List<Utakmica>) state);
        }
    }
    
    public void restoreFromMementoKolo(Object m){
        if (m instanceof Memento) {
            Memento memento = (Memento) m;
            state = memento.getSavedState();
            printajKolo((List<Utakmica>) state);
        }
    }
    
    public Object restoreMemento(Object m){
        if (m instanceof Memento) {
            Memento memento = (Memento) m;
            state = memento.getSavedState();
            
        }
        return state;
    }
    
    public void printajTablicu(List<Utakmica> lista){
        List<Klub> listaKlubova = new ArrayList<>();
        for (Utakmica utk : lista) {
            listaKlubova.add(utk.getDomaci());
            if(utk.getGosti() != null){
                listaKlubova.add(utk.getGosti());
            }
        }
        Collections.sort(listaKlubova);
        int zadnjiPozicija = 1;
        int zadnjiBodovi = 0;
        int brojac = 1;
        boolean flag = true;
        for (Klub klub : listaKlubova) {
            if (flag) {
                System.out.println(zadnjiPozicija+" "+klub.getIme()+" "+klub.getBodovi());
                flag = false;
            }
            else if(zadnjiBodovi == klub.getBodovi()){
                System.out.println(zadnjiPozicija+" "+klub.getIme()+" "+klub.getBodovi());
            }
            else{
                System.out.println(brojac+" "+klub.getIme()+" "+klub.getBodovi());
                zadnjiPozicija = brojac;
            }
            zadnjiBodovi = klub.getBodovi();
            brojac++;
        }
    }
    
    public void printajKolo(List<Utakmica> list){
        for (Utakmica utakmica : list) {
            if (utakmica.getRezultat() == -1) {
                System.out.println(utakmica.getDomaci().getIme()+" - SLOBODAN!");
            }
            else{
                System.out.println(utakmica.getDomaci().getIme()+" - "+utakmica.getGosti().getIme()+" "+utakmica.getRezultat());
            }        }
    }
    
    
    private static class Memento {

        private Object state;

        public Memento(Object stateToSave) {
            state = stateToSave;
        }

        public Object getSavedState() {
            return state;
        }
    }

}
