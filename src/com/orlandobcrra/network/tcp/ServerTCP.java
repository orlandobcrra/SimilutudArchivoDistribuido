package com.orlandobcrra.network.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author orlandobcrra
 */
public class ServerTCP {

    private ServerSocket serverSocket;
    private List<ClientTCP> clients = new ArrayList<ClientTCP>();
    private Integer port = 20000;
    private ProcesaMensajesTCP cP;
    private ProcesaNuevaConexionTCP nuevaConexionTCP;
    private Boolean log = true;

    public ServerTCP(ProcesaMensajesTCP cP, ProcesaNuevaConexionTCP nuevaConexionTCP) {
        this.cP = cP;
        this.nuevaConexionTCP = nuevaConexionTCP;
    }

    public void init() throws IOException {
        if (log) {
            System.out.println("Montando Servidor");
        }
        serverSocket = new ServerSocket(port);
        if (log) {
            System.out.println("Servidor Montado");
        }
        waitForClients();
    }

    public void waitForClients() {
        new Thread(new Runnable() {

            public void run() {
                while (true) {
                    waitForClient();
                }
            }
        }).start();
    }

    public void waitForClient() {
        try {
            if (log) {
                System.out.println("Esperando cliente...");
            }
            ClientTCP sc = new ClientTCP(serverSocket.accept(), cP);
            if (log) {
                System.out.println("Nuevo cliente aceptado.");
            }
            clients.add(sc);
            nuevaConexionTCP.nuevaConexion(sc);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendToAllClients(Object o) {
        for (ClientTCP sc : clients) {
            if (sc.socket != null && sc.socket.isConnected()) {
                try {
                    sc.out.writeObject(o);
                    sc.out.flush();
                    sc.out.reset();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void closeSockets() {
        for (ClientTCP sc : clients) {
            if (sc.socket != null && sc.socket.isConnected()) {
                try {
                    sc.socket.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public Integer getPort() {
        return port;
    }
}
