package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.aafs.todoubt.plantilla.EditarFilasPlantillaActivity;
import com.aafs.todoubt.plantilla.ListAdapter;
import com.aafs.todoubt.wsdatos.DatosJugador;
import com.aafs.todoubt.wsdatos.DetalleEquipo;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class PlantillaActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, ListAdapter.RecyclerItemClick {

    private ListAdapter listAdapter;
    private RecyclerView recyclerView;
    private List<DatosJugador> items;
    private SearchView buscador_plantilla_jugadores;
    private Button btn_aniadir_jugador;
    private DetalleEquipo plantilla;
    private ImageButton bck_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantilla);
        btn_aniadir_jugador = findViewById(R.id.btn_aniadir_jugador_plantilla);
        bck_btn = findViewById(R.id.plan_bck_btn);

        plantilla = (DetalleEquipo) getIntent().getSerializableExtra("data");

        btn_aniadir_jugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        PlantillaActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.bottom_sheet,
                                (ConstraintLayout)findViewById(R.id.bottomSheetContainer)
                        );
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        bck_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initViews();
        initValues();
        initListener();
    }



    private void initViews(){
        recyclerView = findViewById(R.id.recycler_jugadores_plantilla);
        buscador_plantilla_jugadores = findViewById(R.id.buscador_plantilla);


    }
    private void initValues(){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        items = getItems();
        listAdapter = new ListAdapter(items, this);
       //recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);

       }

       private void initListener(){
        buscador_plantilla_jugadores.setOnQueryTextListener(this);
       }
    public List<DatosJugador> getItems(){
        List<DatosJugador> elements = new ArrayList<>();
        for (DatosJugador as : plantilla.getJugadoresEquipo()) {
          elements.add(as);
        }

        return elements;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filter(newText);
        return false;
    }

    @Override
    public void itemClick(DatosJugador item) {
        Intent intent = new Intent(this, EditarFilasPlantillaActivity.class);
        intent.putExtra("listElement", item);
        startActivity(intent);
    }




}