package com.aafs.todoubt.wsdatos;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PeticionDatos {

    private static Document doc;
    private static DatosPartido aux;
    private static EstadiscasEquipo a;
    private static List<DatosPartido> b;
    private static DetalleEquipo c;
    private static DatosJugador d;


    public static void conectar1() {
        try {
            a = new EstadiscasEquipo();
            // Cambiar dependiendo del Equipo
            a.setNombreEquipo("A.D. VILLAVICIOSA DE ODON 'B'");
            doc = Jsoup.connect("http://www.rffm.es/competiciones/clasificaciones?season=17&type=1&grouping=1&competition=13564513&group=13564514&round=").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void conectar2() {
        try {
            b = new ArrayList<>();
            doc = Jsoup.connect("http://www.rffm.es/competiciones/calendario?season=17&type=1&competition=13564513&group=13564514&round=29&").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void conectar3() {
        try {
            doc = Jsoup.connect(a.getLinkDetalle()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void conectar4(String link) {
        try {
            doc = Jsoup.connect(link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static EstadiscasEquipo pedirDatos() {
        int contador = 0;
        int lider = 0;
        Elements newsHeadlines = doc.getElementsByClass("table-row");
        for (Element headline : newsHeadlines){
            if(contador != 1){
                if(lider == 0){
                    a.setLider(headline.child(0).child(1).child(1).text());
                    a.setPuntos_lider(Integer.parseInt(headline.child(1).child(0).text()));
                    lider++;
                }
               if (a.getNombreEquipo().equals(headline.child(0).child(1).child(1).text())){
                    Log.d("TAG", "pedirDatos: " + headline.child(0).child(1).child(1).text());
                    a.setPartidosJugados(Integer.parseInt(headline.child(2).child(0).text()));
                    a.setPartidosGanados(Integer.parseInt(headline.child(3).child(0).text()));
                    a.setPartidosEmpatados(Integer.parseInt(headline.child(4).child(0).text()));
                    a.setPartidosPerdidos(Integer.parseInt(headline.child(5).child(0).text()));
                    a.setPuntos(Integer.parseInt(headline.child(1).child(0).text()));
                    a.setPosicion(Integer.parseInt(headline.child(0).child(0).text()));
                    a.setLinkDetalle(headline.child(0).child(1).child(1).attr("href"));
                   contador++;
               }
            }
        }
        return a;
    }
    public static List<DatosPartido> pedirDatosPartido(){
        Elements newsHeadlines = doc.getElementsByClass("table matches session calendario");
        for (Element headline : newsHeadlines) {
            aux = new DatosPartido();
            aux.setJornada(headline.child(0).child(0).text());
            aux.setFecha(headline.child(0).child(2).text());
            for (int i = 0; i < 9; i++) {
                if (headline.child(1).child(i).child(0).child(0).child(1).text().equals(a.getNombreEquipo())
                        || headline.child(1).child(i).child(2).child(0).child(1).text().equals(a.getNombreEquipo()) ){
                    // Datos Local
                    aux.setEquipoLocal(headline.child(1).child(i).child(0).child(0).child(1).child(0).text());
                    aux.setLogoLocal(headline.child(1).child(i).child(0).child(0).child(0).child(0).attr("src"));
                    // Datos Visitante
                    aux.setEquipoVisitante(headline.child(1).child(i).child(2).child(0).child(1).child(0).text());
                    aux.setLogoVisitante(headline.child(1).child(i).child(2).child(0).child(0).child(0).attr("src"));
                    // Acta
                    aux.setResultado(headline.child(1).child(i).child(1).child(0).child(0).text());
                    aux.setActaPartido(headline.child(1).child(i).child(1).child(0).child(1).child(0).attr("href"));
                }
            }
            b.add(aux);
        }
        return b;
    }
    public static DetalleEquipo pedirDatosPlantilla(){
        c = new DetalleEquipo();
        c.getJugadoresEquipo();
        DatosJugador aux;
        Elements newsHeadlines = doc.getElementsByClass("table-row");
        for (Element headline : newsHeadlines) {
           aux = new DatosJugador();
           aux.setLinkJugador(headline.child(0).child(0).attr("href"));
           aux.setNombreCompleto(headline.child(0).child(0).text());
           c.getJugadoresEquipo().add(aux);
        }
        Element el = doc.getElementById("nombre-campo");
        c.setCampo(el.child(0).text());
        return c;
    }
    public static DatosJugador pedirDatosJugador(){
        Elements newsHeadlines = doc.getElementsByClass("table-row");
        for (Element headline : newsHeadlines) {

        }
        Element el = doc.getElementById("nombre-campo");
        c.setCampo(el.child(0).text());

        return d;
    }


}
