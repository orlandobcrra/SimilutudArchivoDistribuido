package com.orlandobcrra.network.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orlandobcrra
 */
public class ClientTCP {

    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    private Integer port = 20000;
    ProcesaMensajesTCP procesaMensajesTCP;

    public ClientTCP(InetAddress serverAddress, ProcesaMensajesTCP procesaMensajesTCP) {
        this.procesaMensajesTCP = procesaMensajesTCP;
        try {
            socket = new Socket(serverAddress, port);
        } catch (IOException ex) {
            if (ex instanceof java.net.ConnectException) {
                System.out.println("Tiempo para conectar al server agotado");
            } else {
                Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        init();
        read();
    }

    public ClientTCP(Socket socket, ProcesaMensajesTCP procesaMensajesTCP) {
        this.socket = socket;
        this.procesaMensajesTCP = procesaMensajesTCP;
        init();
        read();
    }

    private void init() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void read() {
        new Thread() {

            @Override
            public void run() {
                while (true) {
                    try {
                        ClientTCP.this.procesaMensajesTCP.recibeTCP(in.readObject(), ClientTCP.this);
                    } catch (IOException ex) {
                        if (socket.isClosed() || ex instanceof java.io.EOFException) {
                            System.out.println("Conexion cerrada");
                        } else {
                            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
                        break;
                    }
                }
            }
        }.start();
    }

    public boolean send(Object o) {
        if (socket != null && socket.isConnected()) {
            try {
                out.writeObject(o);
                out.flush();
                out.reset();
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean closeSocket() {
        if (socket != null && socket.isConnected()) {
            try {
                socket.close();
                socket = null;
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
                socket = null;
                return false;
            }
        } else {
            return false;
        }
    }

    public String getIP() {
        return socket.getInetAddress().getHostAddress();
    }
}
