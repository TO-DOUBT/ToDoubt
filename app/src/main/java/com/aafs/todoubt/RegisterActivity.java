package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText editNombre,editContrasenia,editEmail;
    private Button registro;
    private String nombre,contrasenia,email;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editNombre = findViewById(R.id.campo_registro_nombre);
        editContrasenia = findViewById(R.id.campo_registro_contrasena);
        editEmail = findViewById(R.id.campo_registro_gmail);
        registro = findViewById(R.id.btn_registro_aceptar);
        mAuth = FirebaseAuth.getInstance();

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = editNombre.getText().toString();
                contrasenia = editContrasenia.getText().toString();
                email = editEmail.getText().toString();
                crearRegistro();
            }
        });
    }

    private void crearRegistro() {
        if(!nombre.isEmpty() && !contrasenia.isEmpty() && !email.isEmpty()){
            if(!isValidEmail(email)){
                editEmail.setError("Este Correo no existe");
            }else {
                if(contrasenia.length() >= 6){
                    String userUid = "";
                    User user = new User(userUid,nombre,contrasenia,email);
                    Firebase.crearRegistro(mAuth,user,RegisterActivity.this);
                }else{
                    editContrasenia.setError("Contraseña demasiado insegura");
                }
            }
        }else{
            Toast.makeText(RegisterActivity.this,"Rellena todos los campos",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}