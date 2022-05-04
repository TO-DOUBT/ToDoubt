package com.aafs.todoubt.wsdatos;

import com.aafs.todoubt.ProfileActivity;

public class HiloPeticionJugador implements Runnable {

    private ProfileActivity context;
    private DatosJugador data;

    public HiloPeticionJugador(ProfileActivity prof, DatosJugador data){
        this.context = prof;
        this.data = data;
    }

    @Override
    public void run() {
        PeticionDatos.conectar4(data.getLinkJugador()); // link de un jugador que esta en la lista
        data = PeticionDatos.pedirDatosJugador(data); // estadisticas del jugador en cuestion
        context.devolverDatos(data);
    }

    public interface InterfazJugador{
        public void devolverDatos(DatosJugador data);
    }
}
