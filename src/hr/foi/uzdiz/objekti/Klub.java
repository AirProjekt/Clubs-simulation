/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.uzdiz.objekti;

import hr.foi.uzdiz.observer.Observer;
import hr.foi.uzdiz.observer.Subject;
import hr.foi.uzdiz.state.StanjeNatjecatelj;
import hr.foi.uzdiz.state.State;
import java.util.List;

/**
 *
 * @author Winner
 */
public class Klub implements Cloneable, Comparable<Klub>, Observer{
    
    
    private int sifra;
    private String ime;
    private int bodovi;
    private int brojOdigranih;
    private double efikasnost;
    private double efikasnostProsla;
    private State stanje;
    private int pozicija;
    private int proslaPozicija;
    private int brojSlabih;
    private boolean flag;
    
    public Klub(int sifra, String ime){
        this.sifra = sifra;
        this.ime = ime;
        this.bodovi = 0;
        this.brojOdigranih = 0;
        this.efikasnost = 0;
        this.efikasnostProsla = 0;
        this.brojSlabih = 0;
        this.pozicija = 1;
        this.proslaPozicija = 1;
        flag = false;
        stanje = new StanjeNatjecatelj();
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getBodovi() {
        return bodovi;
    }

    public void setBodovi(int bodovi) {
        this.bodovi = bodovi;
    }

    public int getBrojOdigranih() {
        return brojOdigranih;
    }

    public void setBrojOdigranih(int brojOdigranih) {
        this.brojOdigranih = brojOdigranih;
    }

    public double getEfikasnost() {
        return efikasnost;
    }

    public void setEfikasnost(double efikasnost) {
        this.efikasnost = efikasnost;
    }

    public double getEfikasnostProsla() {
        return efikasnostProsla;
    }

    public void setEfikasnostProsla(double efikasnostProsla) {
        this.efikasnostProsla = efikasnostProsla;
    }

    public int getPozicija() {
        return pozicija;
    }

    public void setPozicija(int pozicija) {
        this.pozicija = pozicija;
    }

    public int getProslaPozicija() {
        return proslaPozicija;
    }

    public void setProslaPozicija(int proslaPozicija) {
        this.proslaPozicija = proslaPozicija;
    }

    public int getBrojSlabih() {
        return brojSlabih;
    }

    public void setBrojSlabih(int brojSlabih) {
        this.brojSlabih = brojSlabih;
    }
    
    

    public void setState(State stanje){
        this.stanje = stanje;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Klub k) {
        return k.getBodovi() - this.bodovi;
    }

    @Override
    public void update(Subject o) {
        if (efikasnost != efikasnostProsla) {
            System.out.println("Klub : " + this.ime +"došlo je do promjene efikasnosti.");
            System.out.println("stara efikasnost = " + efikasnostProsla + 
                ";nova efikasnost = " + efikasnost + ";razlika" +(efikasnost-efikasnostProsla));
        }
        
    }
    
    public void provjeriStanje(int brojDozvoljenih){
        this.stanje.provjeriStanje(this, brojDozvoljenih);
    }

    
    
}
