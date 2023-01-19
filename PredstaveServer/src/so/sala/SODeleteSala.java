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
import so.AbstractSO;

/**
 *
 * @author Srdjan
 */
public class SODeleteSala extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Sala)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Sala!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
