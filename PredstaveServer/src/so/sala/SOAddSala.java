/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.sala;

import so.glumac.*;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Glumac;
import domain.Sala;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Srdjan
 */
public class SOAddSala extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Sala)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Sala!");
        }

        Sala s = (Sala) ado;

        ArrayList<Sala> sale = (ArrayList<Sala>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
        
        for (Sala sala : sale) {
            if (sala.getNazivSale().equals(s.getNazivSale())) {
                throw new Exception("Sala sa tim imenom vec postoji!");
            }
          
        }
       
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
