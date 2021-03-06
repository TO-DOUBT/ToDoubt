package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aafs.todoubt.wsdatos.User;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText editContrasenia,editEmail;
    private Button registro;
    private String contrasenia,email;
    private FirebaseAuth mAuth;
    private TextView cambiarAlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editContrasenia = findViewById(R.id.et_registro_contrasena);
        editEmail = findViewById(R.id.et_registro_email);
        registro = findViewById(R.id.btn_registro_aceptar);
        mAuth = FirebaseAuth.getInstance();
        cambiarAlogin = findViewById(R.id.pregunta2_registro);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contrasenia = editContrasenia.getText().toString();
                email = editEmail.getText().toString();
                crearRegistro();
            }
        });

        //Metodo de "signUpTextview" para realizar transicion de "LoginActivity" a "RegistroActivity"
        cambiarAlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void crearRegistro() {
        if(!contrasenia.isEmpty() && !email.isEmpty()){
            if(!isValidEmail(email)){
                editEmail.setError("Este Correo no existe");
            }else {
                if(contrasenia.length() >= 6){
                    String userUid = "";
                    User user = new User(userUid,email);
                    Firebase.crearRegistro(mAuth,contrasenia,user,RegisterActivity.this);
                }else{
                    editContrasenia.setError("Contrase??a demasiado insegura");
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