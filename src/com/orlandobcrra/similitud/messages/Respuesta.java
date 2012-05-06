package com.orlandobcrra.similitud.messages;

import java.io.Serializable;

/**
 *
 * @author orlandobcrra
 */
public class Respuesta implements Serializable {

    private Boolean solucionado;
    private String textoEncontrado;
    private Integer tam;

    public Respuesta(Boolean solucionado, Integer i) {
        this.solucionado = solucionado;
        this.tam = i;
    }

    public Respuesta(Boolean solucionado, String textoEncontrado) {
        this.solucionado = solucionado;
        this.textoEncontrado = textoEncontrado;
        this.tam = textoEncontrado.length();
    }

    public Boolean getSolucionado() {
        return solucionado;
    }

    public String getTextoEncontrado() {
        return textoEncontrado;
    }

    public Integer getTam() {
        return tam;
    }
}
