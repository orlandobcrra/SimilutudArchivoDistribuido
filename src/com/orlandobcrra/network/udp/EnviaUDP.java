package com.orlandobcrra.network.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 
 * @author orlandobcrra
 */
public class EnviaUDP extends Thread {

    Thread t;
    private Integer numeroDeEnvios;
    private Integer puerto = 6666;
    DatagramSocket socket;
    DatagramPacket packet;
    InetAddress address;
    String string = "??";
    byte[] stringBytes = string.getBytes();

    public EnviaUDP() {
        this.numeroDeEnvios = 2;
    }

    public EnviaUDP(Integer numeroDeEnvios) {
        this.numeroDeEnvios = numeroDeEnvios;
    }

    public synchronized void run() {
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("255.255.255.255");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        while (true) {
            try {
                packet = new DatagramPacket(stringBytes, string.length(),
                        address, puerto);
                socket.send(packet);
                System.out.println("send udp");
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
            }
            if (numeroDeEnvios-- == 0) {
                break;
            }
            try {
                Thread.currentThread().sleep(500);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}