package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aafs.todoubt.wsdatos.DatosJugador;
import com.aafs.todoubt.wsdatos.HiloPeticionJugador;

public class ProfileActivity extends AppCompatActivity implements HiloPeticionJugador.InterfazJugador {

    private DatosJugador datos;
    private TextView TV_nombreCompleto, TV_edad, TV_partidosConvocados,
            TV_partidosJugados, TV_partidosTitular, TV_goles,
            TV_tarjetasAmarillas, TV_tarjetasRojas, TV_cod, TV_username;
    private ImageButton bck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        datos = (DatosJugador) getIntent().getSerializableExtra("listElement");
        TV_nombreCompleto = findViewById(R.id.txt_name);
        TV_edad = findViewById(R.id.txt_Edad);
        TV_partidosConvocados = findViewById(R.id.numConvProfile);
        TV_partidosJugados = findViewById(R.id.numTitularProfile);
        TV_partidosTitular = findViewById(R.id.numJugaProfile);
        TV_goles = findViewById(R.id.numGolesProfile);
        TV_tarjetasAmarillas = findViewById(R.id.txtAmarillaProf);
        TV_tarjetasRojas = findViewById(R.id.txtRojaProf);
        TV_cod = findViewById(R.id.txt_phone);
        TV_username = findViewById(R.id.textViewUserNameProfile);
        bck = findViewById(R.id.dp_bck_button_profile);
        // Webscrapping
        HiloPeticionJugador h = new HiloPeticionJugador(ProfileActivity.this, datos);
        Thread t = new Thread(h);
        t.start();

        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public String upperLetter(String str){
        char [] chars = str.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }
    @Override
    public void devolverDatos(DatosJugador data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TV_nombreCompleto.setText(data.getNombreCompleto());
                TV_edad.setText(data.getEdad());
                TV_partidosConvocados.setText(data.getPartidosConvocados());
                TV_partidosJugados.setText(data.getPartidosJugados());
                TV_partidosTitular.setText(data.getPartidosTitular());
                TV_goles.setText(data.getGoles());
                TV_tarjetasAmarillas.setText(data.getTarjetasAmarillas());
                TV_tarjetasRojas.setText(data.getTarjetasRojas());
                TV_cod.setText(String.valueOf(data.getCodigoJugador()));
                TV_username.setText(upperLetter(data.getNombreCompleto().split(",")[1].replace(" ", "").toLowerCase()) + " " + upperLetter(data.getNombreCompleto().split(",")[0].split(" ")[0].toLowerCase()));
            }
        });
    }

}