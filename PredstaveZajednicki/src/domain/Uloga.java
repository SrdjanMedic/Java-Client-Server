/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Srdjan
 */
public class Uloga extends AbstractDomainObject implements Serializable {

    private Predstava predstava;
    private int rbUloge;
    private String nazivUloge;
    private String opisUloge;
    private Glumac glumac;

    public Uloga(Predstava predstava, int rbUloge, String nazivUloge, String opisUloge, Glumac glumac) {
        this.predstava = predstava;
        this.rbUloge = rbUloge;
        this.nazivUloge = nazivUloge;
        this.opisUloge = opisUloge;
        this.glumac = glumac;
    }

    public Uloga() {
    }

    @Override
    public String nazivTabele() {
        return " uloga ";
    }

    @Override
    public String alijas() {
        return " u ";
    }

    @Override
    public String join() {
        return " JOIN PREDSTAVA P USING (PREDSTAVAID) "
                + "JOIN GLUMAC G USING (GLUMACID) "
                + "JOIN SALA S ON (S.SALAID = P.SALAID) "
                + "JOIN ZANR Z ON (Z.ZANRID = P.ZANRID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = P.ADMINISTRATORID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));
            
            Zanr z = new Zanr(rs.getLong("zanrID"), rs.getString("nazivZanra"));
            
            Sala s = new Sala(rs.getLong("salaID"), rs.getString("nazivSale"), rs.getInt("kapacitet"));
            
            Predstava p = new Predstava(rs.getLong("predstavaID"), rs.getString("nazivPredstave"), 
                    rs.getTimestamp("datumVremeOdrzavanja"), rs.getString("opis"), 
                    rs.getInt("brojCinova"), rs.getString("reditelj"), s, z, a, null);
            
            Glumac g = new Glumac(rs.getLong("glumacID"), 
                    rs.getString("imeGlumca"), rs.getString("prezimeGlumca"), 
                    rs.getString("email"), rs.getString("telefon"));
            
            Uloga u = new Uloga(p, rs.getInt("rbUloge"), rs.getString("nazivUloge"), 
                    rs.getString("opisUloge"), g);

            lista.add(u);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (predstavaID, rbUloge, nazivUloge, opisUloge, glumacID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " predstavaID = " + predstava.getPredstavaID();
    }

    @Override
    public String vrednostiZaInsert() {
        return predstava.getPredstavaID() + ", " + rbUloge + ", "
                + "'" + nazivUloge + "', '" + opisUloge + "', " + glumac.getGlumacID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " WHERE P.PREDSTAVAID = " + predstava.getPredstavaID();
    }

    public Predstava getPredstava() {
        return predstava;
    }

    public void setPredstava(Predstava predstava) {
        this.predstava = predstava;
    }

    public int getRbUloge() {
        return rbUloge;
    }

    public void setRbUloge(int rbUloge) {
        this.rbUloge = rbUloge;
    }

    public String getNazivUloge() {
        return nazivUloge;
    }

    public void setNazivUloge(String nazivUloge) {
        this.nazivUloge = nazivUloge;
    }

    public String getOpisUloge() {
        return opisUloge;
    }

    public void setOpisUloge(String opisUloge) {
        this.opisUloge = opisUloge;
    }

    public Glumac getGlumac() {
        return glumac;
    }

    public void setGlumac(Glumac glumac) {
        this.glumac = glumac;
    }

}
