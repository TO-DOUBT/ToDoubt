package com.aafs.todoubt;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {
    private static FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference myDatabaseReference = myDatabase.getReference("Users");

    public static void crearRegistro(FirebaseAuth mAuth, String contrasenia, User user, Context context) {
        mAuth.createUserWithEmailAndPassword(user.getEmail(), contrasenia).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    String userUid = firebaseUser.getUid();
                    user.setUserId(userUid);
                    guardarUsuario(userUid,user);
                    RegisterActivity registerActivity = new RegisterActivity();
                    Intent accessIntent = new Intent(context, Home.class);
                    accessIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    accessIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(accessIntent);
                }else{
                    Toast.makeText(context,"No se pudo registrar",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static void guardarUsuario(String id, User user) {
        myDatabaseReference.child(id).setValue(user);
    }
}
