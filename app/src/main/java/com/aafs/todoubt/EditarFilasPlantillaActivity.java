package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.aafs.todoubt.R;

public class EditarFilasPlantillaActivity extends AppCompatActivity {

    private ListElement itemEditarJugadorFila;
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
        itemEditarJugadorFila = (ListElement) getIntent().getExtras().getSerializable("listElement");

        nombre_jugador.setText(itemEditarJugadorFila.getNombreJugador());

        dorsal_jugador.setText(itemEditarJugadorFila.getDorsalJugador());


    }
}