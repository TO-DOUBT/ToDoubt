package com.aafs.todoubt.wsdatos;

import android.util.Log;

import com.aafs.todoubt.Home;

import java.util.List;

public class HiloPeticionDatos implements Runnable {

    private Home context;
    public HiloPeticionDatos(Home context){
        this.context = context;
    }

    @Override
    public void run() {
        PeticionDatos.conectar1();
        EstadiscasEquipo a = PeticionDatos.pedirDatos();
        context.devolverDatos(a);

        PeticionDatos.conectar2();
        List<DatosPartido> b = PeticionDatos.pedirDatosPartido();
        context.devolverDatosPartido(b);

        PeticionDatos.conectar3();
        DetalleEquipo c = PeticionDatos.pedirDatosPlantilla();

        // LLamar desde el perfil del jugador, No va aqui.
        PeticionDatos.conectar4(c.getJugadoresEquipo().get(3).getLinkJugador()); // link de un jugador que esta en la lista
        DatosJugador d = PeticionDatos.pedirDatosJugador(c.getJugadoresEquipo().get(3)); // estadisticas del jugador en cuestion

        // LLamar desde otro lado para ver las actas de los diferentes partidos
        PeticionDatos.conectar4(b.get(4).getActaPartido());
        PeticionDatos.pedirGoles(b.get(4));

        Log.d("TAG", "run: " + a.getPosicion());
    }
    public interface InterfazDatos {
        public void devolverDatos(EstadiscasEquipo data);
        public void devolverDatosPartido(List<DatosPartido> data);
    }
    public interface InterfazDatos2 {
        public void devolverCampo(DetalleEquipo dataEquipo);
    }
}
