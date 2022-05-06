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

    /**
     * Conecta con la pagina de la Clasificacion
     */
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

    /**
     * Conecta con el caldendario
     */
    public static void conectar2() {
        try {
            b = new ArrayList<>();
            doc = Jsoup.connect("http://www.rffm.es/competiciones/calendario?season=17&type=1&competition=13564513&group=13564514&round=29&").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Conecta con la Pagina de detalles del equipo
     */
    public static void conectar3() {
        try {
            doc = Jsoup.connect(a.getLinkDetalle()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Conecta con la Página "personal" del Jugador o Acta de un partido
     * @param link
     */
    public static void conectar4(String link) {
        try {
            doc = Jsoup.connect(link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pide los datos de un equipo
     * @return el objeto que contiene dichos datos
     */
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
                    a.setLinkDetalle(headline.child(0).child(1).child(1).child(0).child(0).attr("href"));
                   contador++;
               }
            }
            a.getClasificacion().add(headline.child(0).child(1).child(1).text() + ";" + headline.child(1).child(0).text());
        }
        return a;
    }
    public static ArrayList<DatosPartido> pedirDatosPartido(){
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
        return (ArrayList<DatosPartido>) b;
    }
    public static DetalleEquipo pedirDatosPlantilla(){
        c = new DetalleEquipo();
        DatosJugador aux;
        Elements newsHeadlines = doc.getElementsByClass("table-row");
        for (Element headline : newsHeadlines) {
            try {
                aux = new DatosJugador();
                aux.setLinkJugador(headline.child(0).child(0).child(0).attr("href"));
                aux.setNombreCompleto(headline.child(0).child(0).text());
                c.getJugadoresEquipo().add(aux);
            }catch (Exception e){

            }
        }
        return c;
    }
    public static DatosJugador pedirDatosJugador(DatosJugador jug){
        datoJugador1(jug);
        datoJugador2(jug);
        datoJugador3(jug);
        return jug;
    }
    public static void datoJugador1 (DatosJugador jug){
        int i = 0;
        Element headline = doc.getElementsByClass("ficha-equipo-container").get(1);
        if (i == 0){
            jug.setCodigoJugador(Integer.parseInt(headline.child(2).child(1).text()));
            jug.setEdad(headline.child(1).child(1).text());
            i++;
        }
    }
    public static void datoJugador2 (DatosJugador jug){
        Elements newsHeadlines = doc.getElementsByClass("widget-simple-stat");
        Element el = newsHeadlines.get(0);
        jug.setPartidosConvocados(el.child(0).child(0).child(1).text());
        jug.setPartidosJugados(el.child(0).child(1).child(1).text());
        jug.setPartidosSuplente(el.child(0).child(2).child(1).text());
        jug.setPartidosTitular(el.child(0).child(3).child(1).text());
        el = newsHeadlines.get(1);
        jug.setGoles(el.child(0).child(0).child(1).text());
    }
    public static void datoJugador3 (DatosJugador jug){
        Elements newsHeadlines = doc.getElementsByClass("medal-list");
        Element el = newsHeadlines.get(0);
        jug.setTarjetasAmarillas((el.child(0).child(1).child(0).text()));
        jug.setTarjetasRojas(el.child(1).child(1).child(0).text());
    }

    public static DatosPartido pedirGoles (DatosPartido part){
        Elements newsHeadlines = doc.getElementsByClass("table-row-item goles-cronologicos");
        for (Element headline : newsHeadlines) {
            part.getGoles().add(headline.child(1).text() + ";" + headline.child(2).text() );
        }
        Element el = doc.getElementsByClass("nombre-campo").get(0);
        part.setCampo(el.child(0).text());
        return part;
    }
    public static DatosPartido pedirAlineacionLocal(DatosPartido part){
        Element newsHeadlines = doc.getElementsByClass("acta-table-team local-team").get(0);
        for (Element headline : newsHeadlines.children()) {
            part.getAlineacionLocal().add(headline.child(0).child(0).child(0).text() + " - " + headline.child(0).child(1).child(0).text() );
        }
        return part;
    }
    public static DatosPartido pedirAlineacionVisitante(DatosPartido part){
        Element newsHeadlines = doc.getElementsByClass("acta-table-team visitor-team").get(0);
        for (Element headline : newsHeadlines.children()) {
            part.getAlineacionVisi().add(headline.child(0).child(0).child(0).text() + " - " + headline.child(0).child(1).child(0).text() );
        }
        return part;
    }


}
