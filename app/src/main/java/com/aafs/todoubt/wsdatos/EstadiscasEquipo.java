package com.aafs.todoubt.wsdatos;

public class EstadiscasEquipo {

    private String nombreEquipo, lider, linkDetalle;
    private int partidosJugados, partidosEmpatados, partidosPerdidos, partidosGanados,
            posicion, puntos_lider, puntos;

    public EstadiscasEquipo(){}

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getLider() {
        return lider;
    }

    public void setLider(String lider) {
        this.lider = lider;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getPuntos_lider() {
        return puntos_lider;
    }

    public void setPuntos_lider(int puntos_lider) {
        this.puntos_lider = puntos_lider;
    }

    public String getLinkDetalle() {
        return linkDetalle;
    }

    public void setLinkDetalle(String linkDetalle) {
        this.linkDetalle = linkDetalle;
    }
}
