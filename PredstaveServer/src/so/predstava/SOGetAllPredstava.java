/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.predstava;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Predstava;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Srdjan
 */
public class SOGetAllPredstava extends AbstractSO {

    private ArrayList<Predstava> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Predstava)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Predstava!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> predstave = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Predstava>) (ArrayList<?>) predstave;
    }

    public ArrayList<Predstava> getLista() {
        return lista;
    }

}
