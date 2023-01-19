/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Administrator;
import domain.Glumac;
import domain.Predstava;
import domain.Sala;
import domain.Uloga;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author Srdjan
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {

                case Operation.ADD_SALA:
                    ServerController.getInstance().addSala((Sala) request.getData());
                    break;
                case Operation.ADD_GLUMAC:
                    ServerController.getInstance().addGlumac((Glumac) request.getData());
                    break;
                case Operation.ADD_PREDSTAVA:
                    ServerController.getInstance().addPredstava((Predstava) request.getData());
                    break;
                case Operation.ADD_ULOGA:
                    ServerController.getInstance().addUloga((Uloga) request.getData());
                    break;
                case Operation.DELETE_SALA:
                    ServerController.getInstance().deleteSala((Sala) request.getData());
                    break;
                case Operation.DELETE_GLUMAC:
                    ServerController.getInstance().deleteGlumac((Glumac) request.getData());
                    break;
                case Operation.DELETE_PREDSTAVA:
                    ServerController.getInstance().deletePredstava((Predstava) request.getData());
                    break;
                case Operation.DELETE_ULOGA:
                    ServerController.getInstance().deleteUloga((Uloga) request.getData());
                    break;
                case Operation.UPDATE_GLUMAC:
                    ServerController.getInstance().updateGlumac((Glumac) request.getData());
                    break;
                case Operation.UPDATE_SALA:
                    ServerController.getInstance().updateSala((Sala) request.getData());
                    break;
                case Operation.UPDATE_PREDSTAVA:
                    ServerController.getInstance().updatePredstava((Predstava) request.getData());
                    break;
                case Operation.GET_ALL_ADMINISTRATOR:
                    response.setData(ServerController.getInstance().getAllAdministrator());
                    break;
                case Operation.GET_ALL_GLUMAC:
                    response.setData(ServerController.getInstance().getAllGlumac());
                    break;
                case Operation.GET_ALL_PREDSTAVA:
                    response.setData(ServerController.getInstance().getAllPredstava());
                    break;
                case Operation.GET_ALL_ULOGA:
                    response.setData(ServerController.getInstance().getAllUloga((Predstava) request.getData()));
                    break;

                case Operation.GET_ALL_ULOGAG:
                    response.setData(ServerController.getInstance().getAllUlogaG((Glumac) request.getData()));
                    break;

                case Operation.GET_ALL_ZANR:
                    response.setData(ServerController.getInstance().getAllZanr());
                    break;
                case Operation.GET_ALL_SALA:
                    response.setData(ServerController.getInstance().getAllSala());
                    break;
                case Operation.LOGIN:
                    Administrator administrator = (Administrator) request.getData();
                    Administrator ulogovani = ServerController.getInstance().login(administrator);
                    response.setData(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setError(e);
            response.setResponseStatus(ResponseStatus.Error);
        }
        return response;
    }

}
