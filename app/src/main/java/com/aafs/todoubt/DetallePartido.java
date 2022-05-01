package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aafs.todoubt.databinding.ActivityDetallePartidoBinding;
import com.aafs.todoubt.wsdatos.DatosPartido;
import com.aafs.todoubt.wsdatos.HiloPeticionActa;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DetallePartido extends AppCompatActivity implements OnMapReadyCallback, HiloPeticionActa.InterfazDatos {
    private GoogleMap mMap;
    private ActivityDetallePartidoBinding binding;
    private DatosPartido data;
    private ImageView imagenLocal, imagenVisitante;
    private TextView equipoLocal, equipoVisitante, resultado, golesLocal, golesVisi, alinLocal, alinVisi, disp;
    private ScrollView sc;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_partido);
        // Inicializacion
        imagenLocal = findViewById(R.id.dp_equipoLocal);
        imagenVisitante = findViewById(R.id.dp_equipoVisitante);
        equipoLocal = findViewById(R.id.dp_nombreLocal);
        equipoVisitante = findViewById(R.id.dp_nombreVisitante);
        resultado = findViewById(R.id.dp_resultado);
        golesLocal= findViewById(R.id.dp_golesLocal);
        golesVisi= findViewById(R.id.dp_golesVisitante);
        alinLocal= findViewById(R.id.dp_alineacionLocal);
        alinVisi= findViewById(R.id.dp_alineacionVisit);
        sc = findViewById(R.id.dp_scrollView2);
        disp = findViewById(R.id.dp_noDisp);
        back = findViewById(R.id.dp_bck_button);
        // Intents
        data = (DatosPartido) getIntent().getSerializableExtra("partido");
        HiloPeticionActa h = new HiloPeticionActa(DetallePartido.this, data);
        Thread t = new Thread(h);
        t.start();

        // Mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.dp_Mapa);
        mapFragment.getMapAsync(this);

        // Datos
        Glide.with(DetallePartido.this).load(data.getLogoLocal())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            imagenLocal.setBackground(resource);
                        }
                    }
                });
        Glide.with(DetallePartido.this).load(data.getLogoVisitante())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            imagenVisitante.setBackground(resource);
                        }
                    }
                });
        equipoLocal.setText(data.getEquipoLocal());
        equipoVisitante.setText(data.getEquipoVisitante());
        resultado.setText(data.getResultado());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    public void devolverActa(DatosPartido dataPartido) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String localizacionCampo = dataPartido.getCampo();
                sacarLatitudLongitud(localizacionCampo);
                if (!dataPartido.getResultado().equals("-")) {
                    String aux;
                    aux = formatGoles(dataPartido.getGoles(), 0);
                    golesLocal.setText(aux);
                    aux = formatGoles(dataPartido.getGoles(), 1);
                    golesVisi.setText(aux);
                    aux = formatPlantilla(dataPartido.getAlineacionLocal());
                    alinLocal.setText(aux);
                    aux = formatPlantilla(dataPartido.getAlineacionVisi());
                    alinVisi.setText(aux);
                    resultado.setText(dataPartido.getResultado());
                }else {
                    sc.setVisibility(View.INVISIBLE);
                    disp.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void sacarLatitudLongitud(String localizacionCampo) {
        Geocoder geocoder = new Geocoder(DetallePartido.this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(localizacionCampo.replaceAll("[^a-zA-Z]+",""),1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        double latitud = addresses.get(0).getLatitude();
        double longitud = addresses.get(0).getLongitude();
        dibujarCampo(latitud,longitud);
    }

    private void dibujarCampo(double latitud, double longitud) {
        LatLng campo = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(campo).title("Campo"));
        float zoomLevel = 16.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(campo, zoomLevel));
    }
    private String formatGoles(List<String> golesPartido, int a){
        String ext = "";
        String aux = "";
        int gols, gols_au  = 0;
        for (int i = 0 ; i< golesPartido.size() ; i++){
            aux = golesPartido.get(i);
            gols = Integer.parseInt(aux.split(";")[0].split("-")[a].replace(" ", ""));
            if (gols > gols_au){
                ext = ext + aux.split(";")[1] + "\n";
            }
            gols_au = gols;
        }
        return ext;
    }
    // "1 - ELIZAGA MAÑAS, IGNACIO"
    private String formatPlantilla(List<String> plantillaEquipo){
        String ext = "";
        for (int i = 0 ; i< plantillaEquipo.size() ; i++){
            ext = ext + plantillaEquipo.get(i) + "\n";
        }
        return ext;
    }
}
