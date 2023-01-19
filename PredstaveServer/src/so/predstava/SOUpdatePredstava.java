/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.predstava;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Predstava;
import domain.Uloga;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author Srdjan
 */
public class SOUpdatePredstava extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Predstava)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Predstava!");
        }
        
        Predstava p = (Predstava) ado;

        if (!p.getDatumVremeOdrzavanja().after(new Date())) {
            throw new Exception("Predstava mora biti posle danasnjeg datuma!");
        }

        if (p.getUloge().isEmpty()) {
            throw new Exception("Predstava mora imati barem jednu ulogu!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
   
        
        DBBroker.getInstance().update(ado);
        
        
        Predstava p = (Predstava) ado;
     
        DBBroker.getInstance().delete(p.getUloge().get(0));
        
      
        for (Uloga uloga : p.getUloge()) {
            DBBroker.getInstance().insert(uloga);
        }
        
    }

}
