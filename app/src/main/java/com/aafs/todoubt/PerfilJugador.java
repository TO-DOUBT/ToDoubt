package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

public class PerfilJugador extends AppCompatActivity {

    private CheckBox jugadorBox, entrenadorBox, chicaBox, chicoBox;
    private Button aceptarButton;
    private EditText editNombre, editApellido, editDni, editDate;
    private FirebaseAuth mauth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_jugador);

        //CHECKBOX
        jugadorBox = findViewById(R.id.RadioButtonJugador);
        entrenadorBox = findViewById(R.id.RadioButtonEntrenador);
        chicaBox = findViewById(R.id.RadioButtonMujer);
        chicoBox = findViewById(R.id.RadioButtonHombre);

        //BUTTON
        aceptarButton = findViewById(R.id.button);

        //EDITTEXT
        editNombre = findViewById(R.id.editTextTextPersonName);
        editApellido = findViewById(R.id.editTextTextPersonName2);
        editDni = findViewById(R.id.editTextTextPersonName3);
        editDate = findViewById(R.id.editTextDate);

    }

    /*private void perfilJugador(boolean tipoUsuario, boolean genero,  String nombre, String apellido, String dni, String fecha){
        mauth
    }*/
}