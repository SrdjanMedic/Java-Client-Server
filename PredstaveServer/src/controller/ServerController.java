/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.Glumac;
import domain.Predstava;
import domain.Sala;
import domain.Uloga;
import domain.Zanr;
import java.util.ArrayList;
import so.administrator.SOGetAllAdministrator;
import so.glumac.SOAddGlumac;
import so.glumac.SODeleteGlumac;
import so.glumac.SOGetAllGlumac;
import so.glumac.SOUpdateGlumac;
import so.login.SOLogin;
import so.predstava.SOAddPredstava;
import so.predstava.SODeletePredstava;
import so.predstava.SOGetAllPredstava;
import so.predstava.SOUpdatePredstava;
import so.sala.SOAddSala;
import so.sala.SODeleteSala;
import so.sala.SOGetAllSala;
import so.sala.SOUpdateSala;
import so.uloga.SOAddUloga;
import so.uloga.SODeleteUloga;
import so.uloga.SOGetAllUloga;
import so.zanr.SOGetAllZanr;

/**
 *
 * @author Srdjan
 */
public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getAdmin();
    }

    public void addSala(Sala sala) throws Exception {
        (new SOAddSala()).templateExecute(sala);
    }

    public void addGlumac(Glumac glumac) throws Exception {
        (new SOAddGlumac()).templateExecute(glumac);
    }

    public void addPredstava(Predstava predstava) throws Exception {
        (new SOAddPredstava()).templateExecute(predstava);
    }

    public void addUloga(Uloga uloga) throws Exception {
        (new SOAddUloga()).templateExecute(uloga);
    }

    public void deleteSala(Sala sala) throws  Exception{
        (new SODeleteSala()).templateExecute(sala);
    }

    public void deleteGlumac(Glumac glumac) throws Exception {
        (new SODeleteGlumac()).templateExecute(glumac);
    }

    public void deletePredstava(Predstava predstava) throws Exception {
        (new SODeletePredstava()).templateExecute(predstava);
    }

    public void deleteUloga(Uloga uloga) throws Exception {
        (new SODeleteUloga()).templateExecute(uloga);
    }

    public void updateGlumac(Glumac glumac) throws Exception {
        (new SOUpdateGlumac()).templateExecute(glumac);
    }

    public void updatePredstava(Predstava predstava) throws Exception {
        (new SOUpdatePredstava()).templateExecute(predstava);
    }

    public void updateSala(Sala sala) throws Exception {
        (new SOUpdateSala()).templateExecute(sala);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        SOGetAllAdministrator so = new SOGetAllAdministrator();
        so.templateExecute(new Administrator());
        return so.getLista();
    }

    public ArrayList<Glumac> getAllGlumac() throws Exception {
        SOGetAllGlumac so = new SOGetAllGlumac();
        so.templateExecute(new Glumac());
        return so.getLista();
    }

    public ArrayList<Predstava> getAllPredstava() throws Exception {
        SOGetAllPredstava so = new SOGetAllPredstava();
        so.templateExecute(new Predstava());
        return so.getLista();
    }

    public ArrayList<Uloga> getAllUloga(Predstava p) throws Exception {
        SOGetAllUloga so = new SOGetAllUloga();

        Uloga u = new Uloga();
        u.setPredstava(p);

        so.templateExecute(u);
        return so.getLista();
    }
    
     public ArrayList<Uloga> getAllUlogaG (Glumac g) throws Exception {
        SOGetAllUloga so = new SOGetAllUloga();

        Uloga u = new Uloga();
        u.setGlumac(g);

        so.templateExecute(u);
        return so.getLista();
    }

    public ArrayList<Sala> getAllSala() throws Exception {
        SOGetAllSala so = new SOGetAllSala();
        so.templateExecute(new Sala());
        return so.getLista();
    }

    public ArrayList<Zanr> getAllZanr() throws Exception {
        SOGetAllZanr so = new SOGetAllZanr();
        so.templateExecute(new Zanr());
        return so.getLista();
    }

}
