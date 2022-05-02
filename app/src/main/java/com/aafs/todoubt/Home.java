package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aafs.todoubt.calendario.FullActivity;
import com.aafs.todoubt.wsdatos.EstadiscasEquipo;
import com.aafs.todoubt.wsdatos.DatosPartido;
import com.aafs.todoubt.wsdatos.HiloPeticionDatos;

import java.util.List;

public class Home extends AppCompatActivity implements HiloPeticionDatos.InterfazDatos {
    private TextView TV_partidosJugados, TV_partidosEmpatados,
            TV_partidosPerdidos, TV_partidosGanados, TV_posicion,
            TV_puntos, TV_lider, TV_proximoPartido;
    private CardView CV_proximoPartido, CV_calendario;
    private DatosPartido prox_partido;


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
        CV_proximoPartido = findViewById(R.id.home_CV_proximoPartido);
        CV_calendario = findViewById(R.id.home_CV_proximosEventos);
        // Webscrapping
        HiloPeticionDatos h = new HiloPeticionDatos(Home.this);
        Thread t = new Thread(h);
        t.start();
        // Intent
        CV_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, FullActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void devolverDatos(EstadiscasEquipo data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TV_partidosJugados.setText(String.valueOf(data.getPartidosJugados()));
                TV_partidosEmpatados.setText(String.valueOf(data.getPartidosEmpatados()));
                TV_partidosPerdidos.setText(String.valueOf(data.getPartidosPerdidos()));
                TV_partidosGanados.setText(String.valueOf(data.getPartidosGanados()));
                TV_posicion.setText(String.valueOf(data.getPosicion()));
                TV_puntos.setText(String.valueOf(data.getPuntos_lider() - data.getPuntos()));
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
                        prox_partido = datosPartido;
                    }
                }
                CV_proximoPartido.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Home.this, DetallePartido.class);
                        i.putExtra("partido", prox_partido);
                        startActivity(i);
                    }
                });
            }
        });
    }
}