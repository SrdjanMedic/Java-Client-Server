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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author Srdjan
 */
public class SOAddPredstava extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Predstava)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Predstava!");
        }

        Predstava p = (Predstava) ado;

        if (p.getBrojCinova() < 1 || p.getBrojCinova() > 12) {
            throw new Exception("Predstava mora imati 1 - 12 cinova!");
        }

        if (!p.getDatumVremeOdrzavanja().after(new Date())) {
            throw new Exception("Predstava mora biti posle danasnjeg datuma!");
        }

        if (p.getUloge().isEmpty()) {
            throw new Exception("Predstava mora imati barem jednu ulogu!");
        }

        ArrayList<Predstava> predstave = (ArrayList<Predstava>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Predstava predstava : predstave) {
            if(predstava.getNazivPredstave().equals(p.getNazivPredstave())){
                throw new Exception("Predstava sa tim nazivom vec postoji!");
            }
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        

        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
       
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long predstavaID = tableKeys.getLong(1);

        
        Predstava p = (Predstava) ado;
        p.setPredstavaID(predstavaID);
        

        for (Uloga uloga : p.getUloge()) {
            uloga.setPredstava(p);
            DBBroker.getInstance().insert(uloga);
        }
        
    }

}
