/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.glumac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Glumac;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Srdjan
 */
public class SOUpdateGlumac extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Glumac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Glumac!");
        }

        Glumac g = (Glumac) ado;

        ArrayList<Glumac> glumci = (ArrayList<Glumac>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Glumac glumac : glumci) {
            if (!glumac.getGlumacID().equals(g.getGlumacID())) {
                if (glumac.getEmail().equals(g.getEmail())) {
                    throw new Exception("Glumac sa tim emailom vec postoji!");
                }
                if (glumac.getTelefon().equals(g.getTelefon())) {
                    throw new Exception("Glumac sa tim telefonom vec postoji!");
                }
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
