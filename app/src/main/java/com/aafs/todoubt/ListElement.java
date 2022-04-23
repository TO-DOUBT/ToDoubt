package com.aafs.todoubt;

import android.widget.LinearLayout;

public class ListElement {

    public String nombreJugador;
    public String dorsalJugador;
    //public String filaJugador;

    //Constructor


    public ListElement(String nombreJugador, String dorsalJugador/*, String filaJugador*/) {
        this.nombreJugador = nombreJugador;
        this.dorsalJugador = dorsalJugador;
       // this.filaJugador = filaJugador;
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

    /*public String getFilaJugador() {
        return filaJugador;
    }

    public void setFilaJugador(String filaJugador) {
        this.filaJugador = filaJugador;
    }*/
}
