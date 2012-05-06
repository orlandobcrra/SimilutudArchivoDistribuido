package com.orlandobcrra.similitud.messages;

import java.io.Serializable;

/**
 *
 * @author orlandobcrra
 */
public class Solicitud implements Serializable {

    private Integer longitudDeBusqueda;
    private String pequeno;
    private String grande;

    public Solicitud(Integer longitudDeBusqueda, String pequeno, String grande) {
        this.longitudDeBusqueda = longitudDeBusqueda;
        this.pequeno = pequeno;
        this.grande = grande;
    }

    public String getGrande() {
        return grande;
    }

    public Integer getLongitudDeBusqueda() {
        return longitudDeBusqueda;
    }

    public String getPequeno() {
        return pequeno;
    }
}
