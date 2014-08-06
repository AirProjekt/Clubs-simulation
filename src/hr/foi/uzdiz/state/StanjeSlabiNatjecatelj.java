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
public class StanjeSlabiNatjecatelj implements State{
    
    private int brojLosih = 1;
    
    @Override
    public void provjeriStanje(Klub klub, int brojDozvoljenih) {
        
        if (klub.getPozicija() < klub.getProslaPozicija()) {
            klub.setState(new StanjeNatjecatelj());
//            System.out.println(klub.getIme()+" +- "+klub.getPozicija()+" +- "+klub.getProslaPozicija());
        }
        else{
//            System.out.println(klub.getIme()+" +----- "+klub.getPozicija()+" +------ "+klub.getProslaPozicija());
            brojLosih++;
            if (brojLosih == brojDozvoljenih) {
                klub.setFlag(true);
            }
        }
    }

    
    
}
