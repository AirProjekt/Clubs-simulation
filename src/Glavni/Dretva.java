/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Glavni;

import hr.foi.uzdiz.memento.CareTaker;
import hr.foi.uzdiz.memento.Originator;
import hr.foi.uzdiz.objekti.Klub;
import hr.foi.uzdiz.objekti.Utakmica;
import hr.foi.uzdiz.observer.Subject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Winner
 */
public class Dretva implements Runnable{
    
    private List<Klub> lista;
    private Subject s;
    private String[] args;
    private CareTaker caretaker;
    private CareTaker caretakerUtk;
    
    public Dretva(List lista,Subject s, String[] args,CareTaker caretaker,CareTaker caretakerUtk){
        this.lista = lista;
        this.s = s;
        this.args = args;
        this.caretaker = caretaker;
        this.caretakerUtk = caretakerUtk;
    }

    @Override
    public void run() {
        int kontrola = 0;
        int prag = 0;
        int odigranaKola = -1;
        double efikasnost;
        int rndTemp;
        int brojac = 0;
        List tempKolo;
        Originator originator = new Originator();
        Klub domaci = null, gosti = null;
        int rezultat;
        Random rnd = new Random();
        while(true){

            long start = new Date().getTime();
            long razlika;
            List<Utakmica> kolo = new ArrayList<>();
            tempKolo = klonirajListu(lista);
            ArrayList<Integer> brojevi = new ArrayList<>();
            for (int i = 0; i < lista.size(); i++) {
                brojevi.add(i);
            }
            if(lista.size() % 2 != 0){
                try {
                    rndTemp = brojevi.get(brojevi.size()-1);
                    domaci = (Klub)((Klub)lista.get(rndTemp)).clone();
                    Utakmica utk = new Utakmica();
                    utk.setDomaci(domaci);
                    utk.setRezultat(-1);
                    kolo.add(utk);
                    brojevi.remove(brojevi.size()-1);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Dretva.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Collections.shuffle(brojevi);
            for (int i = 0; i < brojevi.size()-1; i+=2) {
                try {
                    ((Klub)lista.get(brojevi.get(i))).setBrojOdigranih(((Klub)lista.get(brojevi.get(i))).getBrojOdigranih()+1);
                    ((Klub)lista.get(brojevi.get(i+1))).setBrojOdigranih(((Klub)lista.get(brojevi.get(i+1))).getBrojOdigranih()+1);
                    domaci = (Klub)((Klub)lista.get(brojevi.get(i))).clone();
                    gosti = (Klub)((Klub)lista.get(brojevi.get(i+1))).clone();
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Dretva.class.getName()).log(Level.SEVERE, null, ex);
                }
                rezultat = rnd.nextInt(3);
                Utakmica utk = new Utakmica();
                utk.setDomaci(domaci);
                utk.setGosti(gosti);
                utk.setRezultat(rezultat);
                originator.set(utk);
                caretakerUtk.addMemento(originator.saveToMemento());
                kolo.add(utk);
            }
            
            for (Klub klub : lista) {
                klub.setEfikasnostProsla(klub.getEfikasnost());
            }
            
            for (Utakmica utk : kolo) {
                switch(utk.getRezultat()){
                    case 0:
                        ((Klub)lista.get(brojevi.get(brojac))).setBodovi(utk.getDomaci().getBodovi() + 1);
                        ((Klub)lista.get(brojevi.get(brojac+1))).setBodovi(utk.getGosti().getBodovi() + 1);
                        efikasnost = ((Klub)lista.get(brojevi.get(brojac))).getBodovi()*1.0/((Klub)lista.get(brojevi.get(brojac))).getBrojOdigranih();
                        ((Klub)lista.get(brojevi.get(brojac))).setEfikasnost(efikasnost);
                        efikasnost = ((Klub)lista.get(brojevi.get(brojac+1))).getBodovi()*1.0/((Klub)lista.get(brojevi.get(brojac+1))).getBrojOdigranih();
                        ((Klub)lista.get(brojevi.get(brojac+1))).setEfikasnost(efikasnost);
                        utk.getDomaci().setBodovi(utk.getDomaci().getBodovi() + 1);
                        utk.getGosti().setBodovi(utk.getGosti().getBodovi() + 1);
                        brojac +=2;
                        break;
                    case 1:
                        ((Klub)lista.get(brojevi.get(brojac))).setBodovi(utk.getDomaci().getBodovi() + 3);
                        efikasnost = ((Klub)lista.get(brojevi.get(brojac))).getBodovi()*1.0/((Klub)lista.get(brojevi.get(brojac))).getBrojOdigranih();
                        ((Klub)lista.get(brojevi.get(brojac))).setEfikasnost(efikasnost);
                        efikasnost = ((Klub)lista.get(brojevi.get(brojac+1))).getBodovi()*1.0/((Klub)lista.get(brojevi.get(brojac+1))).getBrojOdigranih();
                        ((Klub)lista.get(brojevi.get(brojac+1))).setEfikasnost(efikasnost);
                        utk.getDomaci().setBodovi(utk.getDomaci().getBodovi() + 3);
                        brojac +=2;
                        break;
                    case 2:
                        ((Klub)lista.get(brojevi.get(brojac+1))).setBodovi(utk.getGosti().getBodovi() + 3);
                        efikasnost = ((Klub)lista.get(brojevi.get(brojac+1))).getBodovi()*1.0/((Klub)lista.get(brojevi.get(brojac+1))).getBrojOdigranih();
                        ((Klub)lista.get(brojevi.get(brojac+1))).setEfikasnost(efikasnost);
                        efikasnost = ((Klub)lista.get(brojevi.get(brojac))).getBodovi()*1.0/((Klub)lista.get(brojevi.get(brojac))).getBrojOdigranih();
                        ((Klub)lista.get(brojevi.get(brojac))).setEfikasnost(efikasnost);
                        utk.getGosti().setBodovi(utk.getGosti().getBodovi() + 3);
                        brojac +=2;
                        break;
                    default:
                        break;
                }
            }
            brojac = 0;
            Collections.sort(tempKolo);
            Collections.sort(lista);
            postaviPozicije(lista);
            if (!provjeriRedoslijed(lista, tempKolo)) {
                originator.set(kolo);
                caretaker.addMemento(originator.saveToMemento());
                odigranaKola++;
            }
            s.setState("promjena");
            
            kontrola++;
            if (kontrola == Integer.parseInt(args[2])) {
                kontrola = 0;
                for (Klub k : lista) {
                    k.provjeriStanje(Integer.parseInt(args[3]));
                    k.setProslaPozicija(k.getPozicija());
                }
                for (int i = 0; i < lista.size(); i++) {
                    Klub k = (Klub)lista.get(i);
                    if (k.isFlag()) {
                        lista.remove(k);
                    }
                }
            }
            razlika = new Date().getTime() - start;
            try {
                Thread.sleep(Integer.parseInt(args[1]) * 1000 - razlika);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dretva.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public List klonirajListu(List<Klub> oldList){
        List<Klub> novaLista = new ArrayList<>();
        for (Klub klub : oldList) {
            try {
                novaLista.add((Klub)klub.clone());
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(Dretva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return novaLista;
    }
    
    public boolean provjeriRedoslijed(List<Klub> listaPrva, List<Klub> listaDruga){
        if (listaPrva.size() != listaDruga.size())  {
            return false;
        }
        for (int i = 0; i < listaPrva.size(); i++) {
            Klub klub1 = listaPrva.get(i);
            Klub klub2 =listaDruga.get(i);
            if ((klub1.getPozicija() != klub2.getPozicija()) || (!klub1.getIme().equals(klub2.getIme()))) {
                return false;
            }
        }
        return true;
    }
    
    public void postaviPozicije(List<Klub> listaKlubova){
        int zadnjiPozicija = 1;
        int zadnjiBodovi = 0;
        int brojac = 1;
        boolean flag = true;
        for (Klub klub : listaKlubova) {
            if (flag) {
                klub.setPozicija(zadnjiPozicija);
                flag = false;
            }
            else if(zadnjiBodovi == klub.getBodovi()){
                klub.setPozicija(zadnjiPozicija);
            }
            else{
                klub.setPozicija(brojac);
                zadnjiPozicija = brojac;
            }
            zadnjiBodovi = klub.getBodovi();
            brojac++;
        }
    }
    
    
    
}
