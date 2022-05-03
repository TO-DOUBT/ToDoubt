package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.aafs.todoubt.databinding.ActivityDetallePartidoBinding;
import com.aafs.todoubt.wsdatos.DatosPartido;
import com.aafs.todoubt.wsdatos.DetalleEquipo;
import com.aafs.todoubt.wsdatos.HiloPeticionActa;
import com.aafs.todoubt.wsdatos.HiloPeticionDatos;
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
    private TextView equipoLocal, equipoVisitante, resultado;

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
        resultado.setText(data.getResultado());*/
        /**
         * Todo: Marcador del mapa con localizacion [Geocoder] https://www.tabnine.com/code/java/classes/android.location.Geocoder
         */

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    public void devolverCampo(DatosPartido dataPartido) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String localizacionCampo = dataPartido.getCampo();
                sacarLatitudLongitud(localizacionCampo);
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
}
