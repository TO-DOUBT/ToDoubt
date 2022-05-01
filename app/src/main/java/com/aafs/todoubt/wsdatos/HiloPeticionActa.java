package com.aafs.todoubt.wsdatos;

import com.aafs.todoubt.DetallePartido;

public class HiloPeticionActa implements Runnable{

    private DetallePartido context;
    private DatosPartido acta;

    public HiloPeticionActa(DetallePartido context, DatosPartido acta) {
        this.context = context;
        this.acta = acta;
    }

    @Override
    public void run() {
        // LLamar desde otro lado para ver las actas de los diferentes partidos
        PeticionDatos.conectar4(acta.getActaPartido());
        PeticionDatos.pedirGoles(acta);
        PeticionDatos.pedirAlineacionLocal(acta);
        PeticionDatos.pedirAlineacionVisitante(acta);
        context.devolverActa(acta);
    }
    public interface InterfazDatos {
        public void devolverActa(DatosPartido dataPartido);
    }
}
