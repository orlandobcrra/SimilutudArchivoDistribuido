package com.orlandobcrra.similitud.view;

import com.orlandobcrra.network.tcp.ClientTCP;
import com.orlandobcrra.network.tcp.ProcesaMensajesTCP;
import com.orlandobcrra.network.udp.ProcesaMensajesUDP;
import com.orlandobcrra.network.udp.RecibeUDP;
import com.orlandobcrra.similitud.messages.Respuesta;
import com.orlandobcrra.similitud.messages.Solicitud;
import java.net.InetAddress;

/**
 *
 * @author orlandobcrra
 */
public class Trabajador extends javax.swing.JFrame
        implements ProcesaMensajesUDP, ProcesaMensajesTCP {

    ClientTCP clientTCP;

    /**
     * Creates new form Trabajador
     */
    public Trabajador() {
        initComponents();
        setLocationRelativeTo(null);
        RecibeUDP recibeUDP = new RecibeUDP(this);
        recibeUDP.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Esperando Trabajo...");

        jLabel2.setText("Tamaño: ");

        jLabel3.setText("Buscando:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void recibeUDP(InetAddress address, String mensaje) {
        if (clientTCP == null) {
            System.out.println("Peticion de :: " + address);
            clientTCP = new ClientTCP(address, this);
            jLabel1.setText("Trabajando con " + address);
        }
    }

    @Override
    public void recibeTCP(Object o, ClientTCP conex) {
        if (o instanceof Solicitud) {
            Solicitud solicitud = (Solicitud) o;
            System.out.println("Recibe Solicitud i=" + solicitud.getLongitudDeBusqueda());
            jTextField1.setText("" + solicitud.getLongitudDeBusqueda());
            procesar(solicitud);
            jLabel4.setText("...");
        }
    }

    private void procesar(Solicitud solicitud) {
        String textoPequeno = solicitud.getPequeno();
        String textoGrande = solicitud.getGrande();
        int i = solicitud.getLongitudDeBusqueda();
        boolean solucion = false;
        String buscaActual = null;

        // i = la longitud del fragmento del texto pequeño a buscar en el grande. y va decreciendo
        // j = desde que posicion del texto pequeño va a cortar hasta el valor de i "sub(j,i+j)"
        for1:
        for (int j = 0; j <= textoPequeno.length() - i; j++) {
            buscaActual = textoPequeno.substring(j, i + j);
            jTextField2.setText(buscaActual);
            //System.out.println(buscaActual);
            //System.out.println(textoGrande.indexOf(buscaActual));
            if (textoGrande.indexOf(buscaActual) != -1) {
                solucion = true;
                break for1;
            }
        }
        if (solucion) {
            jLabel4.setText("listo :D");  
            System.out.println(":))) Listo: " + i + " " + buscaActual);
            clientTCP.send(new Respuesta(true, buscaActual));            
        } else {
            System.out.println(":( Nada aun: " + i);
            clientTCP.send(new Respuesta(false, i));
            jLabel4.setText("nada para esta solicitud");  

        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
