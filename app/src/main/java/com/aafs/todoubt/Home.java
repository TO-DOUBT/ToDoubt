package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aafs.todoubt.wsdatos.DatosEquipo;
import com.aafs.todoubt.wsdatos.DatosPartido;
import com.aafs.todoubt.wsdatos.HiloPeticionDatos;

import java.util.List;

public class Home extends AppCompatActivity implements HiloPeticionDatos.InterfazLanzamientos {
    private TextView TV_partidosJugados, TV_partidosEmpatados,
            TV_partidosPerdidos, TV_partidosGanados, TV_posicion,
            TV_puntos, TV_lider, TV_proximoPartido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // INICIALIZACION
        TV_partidosJugados = findViewById(R.id.home_partidosJugados);
        TV_partidosEmpatados = findViewById(R.id.home_partidosEmpatados);
        TV_partidosPerdidos = findViewById(R.id.home_partidosPerdidos);
        TV_partidosGanados = findViewById(R.id.home_partidosGanados);
        TV_posicion = findViewById(R.id.home_posicion);
        TV_puntos = findViewById(R.id.home_puntos);
        TV_lider = findViewById(R.id.home_lider);
        TV_proximoPartido = findViewById(R.id.home_ProximoPartido);

        // Webscrapping
        HiloPeticionDatos h = new HiloPeticionDatos(Home.this);
        Thread t = new Thread(h);
        t.start();


    }

    @Override
    public void devolverDatos(DatosEquipo data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TV_partidosJugados.setText(String.valueOf(data.getPartidosJugados()));
                TV_partidosEmpatados.setText(String.valueOf(data.getPartidosEmpatados()));
                TV_partidosPerdidos.setText(String.valueOf(data.getPartidosPerdidos()));
                TV_partidosGanados.setText(String.valueOf(data.getPartidosGanados()));
                TV_posicion.setText(String.valueOf(data.getPosicion()));
                TV_puntos.setText(String.valueOf(data.getPuntos()));
                TV_lider.setText(data.getLider().toLowerCase());
            }
        });

    }

    @Override
    public void devolverDatosPartido(List<DatosPartido> data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int cent = 0;
                for (DatosPartido datosPartido : data) {
                    if (datosPartido.getResultado().equals("-") && cent == 0){
                        if(datosPartido.getEquipoLocal().equals("A.D. VILLAVICIOSA DE ODON 'B'")){
                            TV_proximoPartido.setText(String.valueOf(datosPartido.getEquipoVisitante()));
                        }else{
                            TV_proximoPartido.setText(String.valueOf(datosPartido.getEquipoLocal()));
                        }
                        cent++;
                    }
                }
            }
        });
    }
}