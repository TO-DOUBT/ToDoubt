package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        openApp(true);

    }

    //Metodo para establecer la transicion a "LoginActivity"
    //y el tiempo de ejecucion
    private void openApp(boolean locationPermission) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity
                        .this, Login.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}