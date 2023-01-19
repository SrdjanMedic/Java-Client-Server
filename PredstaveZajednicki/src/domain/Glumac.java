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
public class Glumac extends AbstractDomainObject implements Serializable {
    
    private Long glumacID;
    private String imeGlumca;
    private String prezimeGlumca;
    private String email;
    private String telefon;

    @Override
    public String toString() {
        return imeGlumca + " " + prezimeGlumca;
    }

    public Glumac(Long glumacID, String imeGlumca, String prezimeGlumca, String email, String telefon) {
        this.glumacID = glumacID;
        this.imeGlumca = imeGlumca;
        this.prezimeGlumca = prezimeGlumca;
        this.email = email;
        this.telefon = telefon;
    }

    public Glumac() {
    }
    
    @Override
    public String nazivTabele() {
        return " glumac ";
    }

    @Override
    public String alijas() {
        return " g ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Glumac g = new Glumac(rs.getLong("glumacID"), 
                    rs.getString("imeGlumca"), rs.getString("prezimeGlumca"), 
                    rs.getString("email"), rs.getString("telefon"));

            lista.add(g);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (imeGlumca, prezimeGlumca, email, telefon) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " glumacID = " + glumacID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + imeGlumca + "', '" + prezimeGlumca + "', "
                + "'" + email + "', '" + telefon + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "email = '" + email + "', telefon = '" + telefon + "' ";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getGlumacID() {
        return glumacID;
    }

    public void setGlumacID(Long glumacID) {
        this.glumacID = glumacID;
    }

    public String getImeGlumca() {
        return imeGlumca;
    }

    public void setImeGlumca(String imeGlumca) {
        this.imeGlumca = imeGlumca;
    }

    public String getPrezimeGlumca() {
        return prezimeGlumca;
    }

    public void setPrezimeGlumca(String prezimeGlumca) {
        this.prezimeGlumca = prezimeGlumca;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
    
}
