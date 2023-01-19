/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author Srdjan
 */
public interface Operation {

    public static final int LOGIN = 0;

    public static final int GET_ALL_ADMINISTRATOR = 1;

    public static final int ADD_GLUMAC = 2;
    public static final int DELETE_GLUMAC = 3;
    public static final int UPDATE_GLUMAC = 4;
    public static final int GET_ALL_GLUMAC = 5;

    public static final int ADD_PREDSTAVA = 6;
    public static final int DELETE_PREDSTAVA = 7;
    public static final int UPDATE_PREDSTAVA = 8;
    public static final int GET_ALL_PREDSTAVA = 9;

    public static final int ADD_ULOGA = 10;
    public static final int DELETE_ULOGA = 11;
    public static final int GET_ALL_ULOGA = 12;
    public static final int GET_ALL_ULOGAG = 18;

    
    public static final int GET_ALL_ZANR = 13;
    
    public static final int ADD_SALA = 14;
    public static final int DELETE_SALA = 15;
    public static final int UPDATE_SALA = 16;
    public static final int GET_ALL_SALA = 17;

}
