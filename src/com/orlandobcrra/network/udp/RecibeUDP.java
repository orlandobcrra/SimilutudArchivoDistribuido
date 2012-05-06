package com.orlandobcrra.network.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
/**
 * 
 * @author orlandobcrra
 */
public class RecibeUDP extends Thread {

    Thread t;
    DatagramSocket socket;
    String mensaje = "";
    private Integer puerto = 6666;
    ProcesaMensajesUDP procesaMensajesUDP;
    public RecibeUDP(ProcesaMensajesUDP procesaMensajesUDP) {
        this.procesaMensajesUDP=procesaMensajesUDP;
    }

    public synchronized void run() {
        try {
            socket = new DatagramSocket(puerto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        do {
            try {
                System.out.println("espera msj udp");
                byte[] mensaje_bytes = new byte[256];
                DatagramPacket paquete = new DatagramPacket(mensaje_bytes, 256);
                socket.receive(paquete);
                
                System.out.print("recivo " + paquete.getAddress() + " :");
                mensaje = "";
                mensaje = new String(mensaje_bytes);
                //System.out.println(mensaje);
                procesaMensajesUDP.recibeUDP(paquete.getAddress(),mensaje);
                break;
//                if (mensaje.startsWith("fin")) {
//                    break;
//                }
            } catch (Exception e) {
            }

            try {
                Thread.currentThread().sleep(200);
            } catch (Exception e) {
                // TODO: handle exception
            }
        } while (!mensaje.startsWith("fin"));
    }
}
