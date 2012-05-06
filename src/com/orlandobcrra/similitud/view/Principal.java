package com.orlandobcrra.similitud.view;

import com.orlandobcrra.network.tcp.ClientTCP;
import com.orlandobcrra.network.tcp.ProcesaMensajesTCP;
import com.orlandobcrra.network.tcp.ProcesaNuevaConexionTCP;
import com.orlandobcrra.network.tcp.ServerTCP;
import com.orlandobcrra.network.udp.EnviaUDP;
import com.orlandobcrra.similitud.messages.Respuesta;
import com.orlandobcrra.similitud.messages.Solicitud;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;

/**
 *
 * @author orlandobcrra
 */
public class Principal extends javax.swing.JFrame
        implements ProcesaMensajesTCP, ProcesaNuevaConexionTCP {

    private JFileChooser fileChooser = new JFileChooser(".");
    private String textoGrande = null, textoPequeno = null;
    private Integer longitudAcutal;
    private boolean solucionado, sinSolucion;
    private ServerTCP serverTCP;

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        setLocationRelativeTo(null);
        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
        jButton3.setEnabled(false);
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Similutud de Archivos");

        jLabel1.setText("Archivo 1:");

        jLabel2.setText("Archivo 2:");

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("...");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Procesar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextPane1.setEditable(false);
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Archivo 1", jPanel1);

        jTextPane2.setEditable(false);
        jScrollPane2.setViewportView(jTextPane2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Archivo 2", jPanel2);

        jLabel3.setText("Nodos:");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, 999, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))
                        .addContainerGap(12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel3)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        textoGrande = mostarArchivo(jTextField1);
        jTextPane1.setText(textoGrande);
        if (textoGrande != null && textoPequeno != null) {
            jButton3.setEnabled(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        textoPequeno = mostarArchivo(jTextField2);
        jTextPane2.setText(textoPequeno);
        if (textoGrande != null && textoPequeno != null) {
            jButton3.setEnabled(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jButton3.setEnabled(false);
        if (textoGrande.length() < textoPequeno.length()) {
            String temp = textoGrande;
            textoGrande = textoPequeno;
            textoPequeno = temp;
        }
        longitudAcutal = textoPequeno.length();

        serverTCP = new ServerTCP(this, this);
        try {
            serverTCP.init();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        System.out.println("server tcp montado");
        EnviaUDP enviaUDP = new EnviaUDP();
        enviaUDP.start();
        System.out.println("enviar udp");


        //textoPequeno = "abcd";
        //textoGrande = "bcdaaaaa";

//        for1:
//        for (int i = textoPequeno.length(); i >= 0; i--) {
//            for (int j = 0; j <= textoPequeno.length() - i && i != 0; j++) {
//                String buscar = textoPequeno.substring(j, i + j);
//                System.out.println(buscar);
//                System.out.println(textoGrande.indexOf(buscar));
//                if (textoGrande.indexOf(buscar) != -1) {
//                    System.out.println("listo");
//                    break for1;
//                }
//            }
//        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private String mostarArchivo(JTextField textField) {
        int r = fileChooser.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            textField.setText(fileChooser.getSelectedFile().getPath());

            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return "";
            }

            String texto = "", linea = null;
            try {
                while ((linea = reader.readLine()) != null) {
                    linea += "\n";
                    texto += linea;// + "\n";
                }
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return texto;
        }
        return null;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void nuevaConexion(ClientTCP cP) {
        System.out.println("Nueva conexion: " + cP.getIP());
        nuevaSolicitud(cP);
    }

    @Override
    public void recibeTCP(Object o, ClientTCP conex) {
        synchronized (this) {
            if (o instanceof Respuesta) {
                Respuesta respuesta = (Respuesta) o;
                nuevaRespuesta(respuesta, conex);
            }
        }
    }

    public void addText(JTextPane pane, String t, boolean negrita) {
        String color="#00ff00";
        if(negrita)
            color="#00ffff";
        String html="<font color=\""+color+"\">"
                + t
                + " </font>";
        
        try {
            if (t == null || t.equals("")) {
                return;
            }
            Reader r = new StringReader(html);
            Document doc = pane.getDocument();
            EditorKit kit = pane.getEditorKit();
            kit.read(r, doc, doc.getLength());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void nuevaRespuesta(Respuesta respuesta, ClientTCP conex) {
        if (respuesta.getSolucionado()) {
            System.out.println(":))) Respuesta de "
                    + conex.getIP() + " i="
                    + respuesta.getTam()
                    + " " + respuesta.getTextoEncontrado());
            solucionado = true;
            serverTCP.closeSockets();
            JOptionPane.showMessageDialog(this,
                    "Texto encontrado: " + respuesta.getTextoEncontrado(),
                    "Respuesta Encontrada", JOptionPane.INFORMATION_MESSAGE);
//            String t1 = jTextPane1.getText();
//            jTextPane1.setText("");
//            jTextPane1.setContentType("text/html");
//            jTextPane1.setText("<html> <head></head> <body> <style=\"margin-top: 0\">");
//            String t1a = t1.substring(0, t1.indexOf(respuesta.getTextoEncontrado()));
//            String t1b = t1.substring(t1.indexOf(respuesta.getTextoEncontrado()) + respuesta.getTam(), t1.length());
//            addText(jTextPane1, t1a, false);
//            addText(jTextPane1, respuesta.getTextoEncontrado(), true);
//            addText(jTextPane1, t1b, false);
        } else {
            System.out.println(":( Respuesta de "
                    + conex.getIP() + " i="
                    + respuesta.getTam());
            nuevaSolicitud(conex);
        }
    }

    private void nuevaSolicitud(ClientTCP cP) {
        if (!solucionado && !sinSolucion) {
            synchronized (this) {
                System.out.println("Solicitud a " + cP.getIP() + " i=" + longitudAcutal);
                cP.send(new Solicitud(longitudAcutal--, textoPequeno, textoGrande));
                if (longitudAcutal == 0) {
                    sinSolucion = true;
                }
            }
        }
    }
}
