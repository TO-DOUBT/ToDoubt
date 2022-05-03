package com.aafs.todoubt;

import java.io.Serializable;

public class ListElement implements Serializable {

    public String nombreJugador;
    public String dorsalJugador;

    //Constructor


    public ListElement(String nombreJugador, String dorsalJugador) {
        this.nombreJugador = nombreJugador;
        this.dorsalJugador = dorsalJugador;

    }

    //Getter y Setter
    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getDorsalJugador() {
        return dorsalJugador;
    }

    public void setDorsalJugador(String dorsalJugador) {
        this.dorsalJugador = dorsalJugador;
    }


}
