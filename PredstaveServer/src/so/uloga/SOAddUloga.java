/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.uloga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Uloga;
import so.AbstractSO;

/**
 *
 * @author Srdjan
 */
public class SOAddUloga extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Uloga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Uloga!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
