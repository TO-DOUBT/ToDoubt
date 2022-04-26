package com.aafs.todoubt.wsdatos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DatosPartido implements Serializable {
    private String equipoLocal, equipoVisitante;
    private String resultado;
    private String fecha, actaPartido, jornada;
    private String logoLocal, logoVisitante;
    private List<String> goles;
    private String campo;

    public DatosPartido() {
        this.goles = new ArrayList<>();
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(String equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getActaPartido() {
        return actaPartido;
    }

    public void setActaPartido(String actaPartido) {
        this.actaPartido = actaPartido;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getLogoLocal() {
        return logoLocal;
    }

    public void setLogoLocal(String logoLocal) {
        this.logoLocal = logoLocal;
    }

    public String getLogoVisitante() {
        return logoVisitante;
    }

    public void setLogoVisitante(String logoVisitante) {
        this.logoVisitante = logoVisitante;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public List<String> getGoles() {
        return goles;
    }

    public void setGoles(List<String> goles) {
        this.goles = goles;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }
}
