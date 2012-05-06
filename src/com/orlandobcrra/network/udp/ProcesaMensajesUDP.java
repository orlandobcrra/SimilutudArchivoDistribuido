package com.orlandobcrra.network.udp;

import java.net.InetAddress;

/**
 *
 * @author orlandobcrra
 */
public interface ProcesaMensajesUDP {

    public void recibeUDP(InetAddress address, String mensaje);
}
