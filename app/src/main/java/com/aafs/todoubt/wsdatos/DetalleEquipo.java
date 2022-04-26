package com.aafs.todoubt.wsdatos;

import java.util.ArrayList;
import java.util.List;

public class DetalleEquipo {

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


}
