package com.aafs.todoubt.wsdatos;

public class DatosJugador {

    private int codigoJugador;
    private String nombreCompleto, edad, partidosConvocados,
            partidosJugados, partidosSuplente, partidosTitular, goles,
            tarjetasAmarillas, tarjetasRojas, linkJugador;

    public DatosJugador() {
    }

    public int getCodigoJugador() {
        return codigoJugador;
    }

    public void setCodigoJugador(int codigoJugador) {
        this.codigoJugador = codigoJugador;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPartidosConvocados() {
        return partidosConvocados;
    }

    public void setPartidosConvocados(String partidosConvocados) {
        this.partidosConvocados = partidosConvocados;
    }

    public String getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(String partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public String getPartidosSuplente() {
        return partidosSuplente;
    }

    public void setPartidosSuplente(String partidosSuplente) {
        this.partidosSuplente = partidosSuplente;
    }

    public String getPartidosTitular() {
        return partidosTitular;
    }

    public void setPartidosTitular(String partidosTitular) {
        this.partidosTitular = partidosTitular;
    }

    public String getGoles() {
        return goles;
    }

    public void setGoles(String goles) {
        this.goles = goles;
    }

    public String getTarjetasAmarillas() {
        return tarjetasAmarillas;
    }

    public void setTarjetasAmarillas(String tarjetasAmarillas) {
        this.tarjetasAmarillas = tarjetasAmarillas;
    }

    public String getTarjetasRojas() {
        return tarjetasRojas;
    }

    public void setTarjetasRojas(String tarjetasRojas) {
        this.tarjetasRojas = tarjetasRojas;
    }

    public String getLinkJugador() {
        return linkJugador;
    }

    public void setLinkJugador(String linkJugador) {
        this.linkJugador = linkJugador;
    }
}
