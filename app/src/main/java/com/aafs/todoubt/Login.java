package com.aafs.todoubt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Login extends AppCompatActivity {
    private EditText editMail, editPassword;
    private TextView olvidarCont;
    private Button btnLogin;
    private FirebaseAuth mauth;
    private TextView cambiarAregistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        olvidarCont = findViewById(R.id.olvidarContraseña);
        editMail = findViewById(R.id.et_login_correo);
        editPassword = findViewById(R.id.et_login_contrasena);
        btnLogin = findViewById(R.id.btn_login);
        cambiarAregistro = findViewById(R.id.pregunta2_registro);
        mauth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = editMail.getText().toString();
                String password = editPassword.getText().toString();
                if (!isValidEmail(correo)){
                    editMail.setError("Este Correo no existe");
                }else {
                    if(password.length() >= 6){
                        loginUsuario(correo, password);
                    }else{
                        editPassword.setError("Contraseña demasiado insegura");
                    }
                }
            }
        });

        olvidarCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialogForgotPassword();
            }
        });

        //Metodo de "signUpTextview" para realizar transicion de "LoginActivity" a "RegistroActivity"
        cambiarAregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = mauth.getCurrentUser();
        if (firebaseUser != null){
            Intent accessIntent = new Intent(getApplicationContext(), Home.class);
            accessIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            accessIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(accessIntent);
        }
    }

    private void loginUsuario(String correo, String password) {
        mauth.signInWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent accessIntent = new Intent(Login.this, Home.class);
                    accessIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    accessIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(accessIntent);
                }
            }
        });
    }
    /**
     * En este método mostramos la alerta de recuperar la contraseña
     */
    private void showAlertDialogForgotPassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.activity_forgot_password,
                null);
        builder.setView(view);
        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText correo = view.findViewById(R.id.gmailOlvidado);
                String emailRecupera = Objects.requireNonNull(correo.getText()).toString().trim();
                mauth.setLanguageCode("es");
                mauth.sendPasswordResetEmail(emailRecupera).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Mensaje Enviado", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Fallo en la Operación", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        // Here we create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setBackgroundColor(getResources().getColor(R.color.orange_detail));
    }

    private boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}