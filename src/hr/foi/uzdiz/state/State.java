/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.uzdiz.state;

import hr.foi.uzdiz.objekti.Klub;
import hr.foi.uzdiz.observer.Subject;
import java.util.List;

/**
 *
 * @author Winner
 */
public interface State {
    public void provjeriStanje(Klub klub, int brojDozvoljenih);
}
