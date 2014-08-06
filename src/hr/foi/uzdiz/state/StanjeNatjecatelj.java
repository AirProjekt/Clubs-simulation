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
public class StanjeNatjecatelj implements State{

    @Override
    public void provjeriStanje(Klub klub, int brojDozvoljenih) {
//        System.out.println(klub.getIme()+" + "+klub.getPozicija()+" + "+klub.getProslaPozicija());
        if (klub.getPozicija() > klub.getProslaPozicija()) {
            klub.setState(new StanjeSlabiNatjecatelj());
        }
    }
    
}
