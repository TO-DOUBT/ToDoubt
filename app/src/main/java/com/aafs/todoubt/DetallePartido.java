package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.aafs.todoubt.databinding.ActivityDetallePartidoBinding;
import com.aafs.todoubt.wsdatos.DatosPartido;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetallePartido extends AppCompatActivity /*implements OnMapReadyCallback*/ {
    private GoogleMap mMap;
    //private ActivityDetallePartidoBinding binding;
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


        // Mapa
        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.dp_Mapa);
        mapFragment.getMapAsync(this);*/

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
        /**
         * Todo: Marcador del mapa con localizacion [Geocoder] https://www.tabnine.com/code/java/classes/android.location.Geocoder
         */

    }

    /*@Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }*/
}
