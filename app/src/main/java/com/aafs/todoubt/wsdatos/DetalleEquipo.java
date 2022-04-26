package com.aafs.todoubt.wsdatos;

import java.util.ArrayList;
import java.util.List;

public class DetalleEquipo {

    private String campo;

    private List<DatosJugador> jugadoresEquipo;

    public DetalleEquipo() {
        jugadoresEquipo = new ArrayList<>();
    }

    public List<DatosJugador> getJugadoresEquipo() {
        return jugadoresEquipo;
    }

    public void setJugadoresEquipo(List<DatosJugador> jugadoresEquipo) {
        this.jugadoresEquipo = jugadoresEquipo;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }
}
