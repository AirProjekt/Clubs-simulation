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
import hr.foi.uzdiz.observer.SubjectImpl;
import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Winner
 */
public class Aplikacija {
    
    public static void main(String[] args){
        try {
            Originator originator = new Originator();
            CareTaker caretakerUtk = new CareTaker();
            CareTaker caretaker = new CareTaker();
            Subject s = new SubjectImpl();
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line = reader.readLine();
            List<Klub> listaKlubova = new ArrayList<>();
            while(line != null){
                String sifra = line.substring(0, 5);
                String replaceAll = sifra.replaceAll("\\s+","");
                Klub klub = new Klub(Integer.parseInt(replaceAll), line.substring(5).trim());
                listaKlubova.add(klub);
                s.addObserver(klub);
                line = reader.readLine();
            }
            Thread t = new Thread(new Dretva(listaKlubova,s, args,caretaker,caretakerUtk));
            t.start();
            Console c = System.console();
            if (c == null) {
                System.err.println("No console.");
                System.exit(1);
            }
            
            String input = c.readLine();
            if (input.equals("q")) {
                t.interrupt();
                System.out.println("****OPERACIJE****");
                System.out.println("Rezultati kola: score brojKola(npr. score 10)");
                System.out.println("Raspored kola: order brojKola(npr. order 10)");
                System.out.println("Broj kola: number(npr. number)");
                System.out.println("Povijest kluba: history klubName(npr. history DINAMO)");
                while (true) {                    
                    input = c.readLine();
                    String split[] = input.split(" ");
                    switch(split[0]){
                        case "score":
                            System.out.println("---Rezultati kola---");
                            originator.restoreFromMementoKolo(caretaker.getMemento(Integer.parseInt(split[1])));
                            break;
                        case "order":
                            System.out.println("---Rang lista---");
                            originator.restoreFromMementoTablica(caretaker.getMemento(Integer.parseInt(split[1])));
                            break;
                        case "number":
                            System.out.println("Broj arhiviranih je:"+ caretaker.getSize());
                            break;
                        case "history":
                            System.out.println("Povijest kluba ");
                            ispisiPovijest(caretakerUtk,split[1]);
                            break;
                           
                    }
                }
                
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Aplikacija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Aplikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ispisiPovijest(CareTaker c, String ime){
        Originator orig = new Originator();
        for (int i = 0; i < c.getSize(); i++) {
            Utakmica utk = (Utakmica)orig.restoreMemento(c.getMemento(i));
            if (utk.getDomaci().getIme().equals(ime) || utk.getGosti().getIme().equals(ime)) {
                if (utk.getGosti() == null) {
                    System.out.println(utk.getDomaci().getIme()+" - SLOBODAN!");
                }
                else {
                    System.out.println(utk.getDomaci().getIme()+" - "+ utk.getGosti().getIme());
                }
            }
            
        }
    }
}
