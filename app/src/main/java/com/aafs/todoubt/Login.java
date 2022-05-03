package com.aafs.todoubt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText editMail, editPassword;
    private Button btnLogin;
    private FirebaseAuth mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editMail = findViewById(R.id.editTextTextEmailAddress3);
        editPassword = findViewById(R.id.editTextTextEmailAddress2);
        btnLogin = findViewById(R.id.button2);
        mauth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = editMail.getText().toString();
                String password = editPassword.getText().toString();

                loginUsuario(correo, password);

            }
        });
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
}