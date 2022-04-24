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
        Log.d("TAG", "run: " + a.getPosicion());
    }
    public interface InterfazDatos {
        public void devolverDatos(EstadiscasEquipo data);
        public void devolverDatosPartido(List<DatosPartido> data);
    }
    public interface InterfazDatos2 {

    }
}
