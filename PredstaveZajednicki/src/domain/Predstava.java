/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Srdjan
 */
public class Predstava extends AbstractDomainObject implements Serializable {

    private Long predstavaID;
    private String nazivPredstave;
    private Date datumVremeOdrzavanja;
    private String opis;
    private int brojCinova;
    private String reditelj;
    private Sala sala;
    private Zanr zanr;
    private Administrator administrator;
    private ArrayList<Uloga> uloge;

    public Predstava(Long predstavaID, String nazivPredstave, Date datumVremeOdrzavanja, String opis, int brojCinova, String reditelj, Sala sala, Zanr zanr, Administrator administrator, ArrayList<Uloga> uloge) {
        this.predstavaID = predstavaID;
        this.nazivPredstave = nazivPredstave;
        this.datumVremeOdrzavanja = datumVremeOdrzavanja;
        this.opis = opis;
        this.brojCinova = brojCinova;
        this.reditelj = reditelj;
        this.sala = sala;
        this.zanr = zanr;
        this.administrator = administrator;
        this.uloge = uloge;
    }

    public Predstava() {
    }

    @Override
    public String nazivTabele() {
        return " predstava ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return " JOIN SALA S ON (S.SALAID = P.SALAID) "
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

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (nazivPredstave, datumVremeOdrzavanja, opis, brojCinova, reditelj,"
                + " salaID, zanrID, administratorID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " predstavaID = " + predstavaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivPredstave + "', '" + new Timestamp(datumVremeOdrzavanja.getTime()) + "', "
                + "'" + opis + "', " + brojCinova + ", '" + reditelj + "', "
                + sala.getSalaID() + ", " + zanr.getZanrID() + ", " + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumVremeOdrzavanja = '" + new Timestamp(datumVremeOdrzavanja.getTime()) + "', "
                + "Opis = '" + opis + "', "
                + "salaID = " + sala.getSalaID() + " ";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getPredstavaID() {
        return predstavaID;
    }

    public void setPredstavaID(Long predstavaID) {
        this.predstavaID = predstavaID;
    }

    public String getNazivPredstave() {
        return nazivPredstave;
    }

    public void setNazivPredstave(String nazivPredstave) {
        this.nazivPredstave = nazivPredstave;
    }

    public Date getDatumVremeOdrzavanja() {
        return datumVremeOdrzavanja;
    }

    public void setDatumVremeOdrzavanja(Date datumVremeOdrzavanja) {
        this.datumVremeOdrzavanja = datumVremeOdrzavanja;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getBrojCinova() {
        return brojCinova;
    }

    public void setBrojCinova(int brojCinova) {
        this.brojCinova = brojCinova;
    }

    public String getReditelj() {
        return reditelj;
    }

    public void setReditelj(String reditelj) {
        this.reditelj = reditelj;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<Uloga> getUloge() {
        return uloge;
    }

    public void setUloge(ArrayList<Uloga> uloge) {
        this.uloge = uloge;
    }

}
