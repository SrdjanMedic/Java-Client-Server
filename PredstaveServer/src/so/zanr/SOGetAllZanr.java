/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zanr;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Zanr;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Srdjan
 */
public class SOGetAllZanr extends AbstractSO {

    private ArrayList<Zanr> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Zanr)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Zanr!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> zanrovi = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Zanr>) (ArrayList<?>) zanrovi;
    }

    public ArrayList<Zanr> getLista() {
        return lista;
    }

}
