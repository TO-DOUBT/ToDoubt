package com.aafs.todoubt.wsdatos;

import android.util.Log;

import com.aafs.todoubt.Home;

import java.util.ArrayList;
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
        ArrayList<DatosPartido> b = PeticionDatos.pedirDatosPartido();
        context.devolverDatosPartido(b);

        PeticionDatos.conectar3();
        DetalleEquipo c = PeticionDatos.pedirDatosPlantilla();

        // LLamar desde el perfil del jugador, No va aqui.
        PeticionDatos.conectar4(c.getJugadoresEquipo().get(3).getLinkJugador()); // link de un jugador que esta en la lista
        DatosJugador d = PeticionDatos.pedirDatosJugador(c.getJugadoresEquipo().get(3)); // estadisticas del jugador en cuestion

    }

    public interface InterfazDatos {
        public void devolverDatos(EstadiscasEquipo data);
        public void devolverDatosPartido(ArrayList<DatosPartido> data);
    }

}
