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
        context.devolverDatosPlantilla(c);
    }

    public interface InterfazDatos {
        public void devolverDatos(EstadiscasEquipo data);
        public void devolverDatosPartido(ArrayList<DatosPartido> data);
        public void devolverDatosPlantilla(DetalleEquipo data);
    }

}
