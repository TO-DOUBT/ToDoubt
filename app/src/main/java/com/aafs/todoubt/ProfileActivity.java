package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.aafs.todoubt.wsdatos.DatosJugador;

public class ProfileActivity extends AppCompatActivity {

    private DatosJugador datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        datos = (DatosJugador) getIntent().getSerializableExtra("listElement");
        Log.d("TAG", "onCreate: ");

    }
}