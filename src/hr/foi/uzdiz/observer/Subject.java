/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.uzdiz.observer;

/**
 *
 * @author Winner
 */
public interface Subject {
    
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public String getState();
    public void setState(String state);


}
