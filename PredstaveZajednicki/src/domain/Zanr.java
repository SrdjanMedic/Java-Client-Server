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
public class Zanr extends AbstractDomainObject implements Serializable {
    
    private Long zanrID;
    private String nazivZanra;

    @Override
    public String toString() {
        return nazivZanra;
    }

    public Zanr(Long zanrID, String nazivZanra) {
        this.zanrID = zanrID;
        this.nazivZanra = nazivZanra;
    }

    public Zanr() {
    }
    
    @Override
    public String nazivTabele() {
        return " zanr ";
    }

    @Override
    public String alijas() {
        return " z ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Zanr z = new Zanr(rs.getLong("zanrID"), rs.getString("nazivZanra"));

            lista.add(z);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " zanrID = " + zanrID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getZanrID() {
        return zanrID;
    }

    public void setZanrID(Long zanrID) {
        this.zanrID = zanrID;
    }

    public String getNazivZanra() {
        return nazivZanra;
    }

    public void setNazivZanra(String nazivZanra) {
        this.nazivZanra = nazivZanra;
    }
    
}
