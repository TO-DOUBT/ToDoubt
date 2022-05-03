package com.aafs.todoubt.plantilla;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.aafs.todoubt.R;
import com.aafs.todoubt.wsdatos.DatosJugador;

public class EditarFilasPlantillaActivity extends AppCompatActivity {

    private DatosJugador itemEditarJugadorFila;
    private TextView nombre_jugador;
    private TextView dorsal_jugador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_filas_plantilla);
        initView();
        initValues();
    }

    private void initView(){
        nombre_jugador = findViewById(R.id.et_nombre_jugador_editar_fila);
        dorsal_jugador = findViewById(R.id.et_dorsal_jugador_editar_fila);
    }
    private void initValues(){
        itemEditarJugadorFila = (DatosJugador) getIntent().getExtras().getSerializable("listElement");

        nombre_jugador.setText(itemEditarJugadorFila.getNombreCompleto());

        /**
         * todo: Cambiar por el dorsal sacado del Firebase
         */
        dorsal_jugador.setText(String.valueOf(itemEditarJugadorFila.getCodigoJugador()));


    }
}