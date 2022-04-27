package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class PlantillaActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ListAdapter listAdapter;
    private RecyclerView recyclerView;
    private List<ListElement> items;
    private SearchView buscador_plantilla_jugadores;
   private Button btn_prueba1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantilla);
        btn_prueba1 = findViewById(R.id.btn_aniadir_jugador_plantilla);

        btn_prueba1.setOnClickListener(new View.OnClickListener() {
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
        listAdapter = new ListAdapter(items);
       //recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);

       }

       private void initListener(){
        buscador_plantilla_jugadores.setOnQueryTextListener(this);
       }
    public List<ListElement> getItems(){

        List<ListElement> elements = new ArrayList<>();
        elements.add(new ListElement("Sebastián Huete Londoño", "11"));
        elements.add(new ListElement("Álvaro Tuñón Berlanga", "2"));
        elements.add(new ListElement("Fran Ruiz Joya", "10"));
        elements.add(new ListElement("Andrés Parra Esteberanz", "3"));
        elements.add(new ListElement("Juan Antonio Valenciaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "7"));
        elements.add(new ListElement("Aaron Tome Martín", "4"));
        elements.add(new ListElement("Luis Enrique Alcaraz", "20"));
        elements.add(new ListElement("Sebastián Huete Londoño", "11"));
        elements.add(new ListElement("Álvaro Tuñón Berlanga", "2"));
        elements.add(new ListElement("Fran Ruiz Joya", "10"));
        elements.add(new ListElement("Andrés Parra Esteberanz", "3"));
        elements.add(new ListElement("Juan Antonio Valencia", "7"));
        elements.add(new ListElement("Aaron Tome Martín", "4"));
        elements.add(new ListElement("Luis Enrique Alcarazzzzzzzzzzzzzzzzzz", "20"));
        elements.add(new ListElement("Sebastián Huete Londoño", "11"));
        elements.add(new ListElement("Álvaro Tuñón Berlanga", "2"));
        elements.add(new ListElement("Fran Ruiz Joya", "10"));
        elements.add(new ListElement("Andrés Parra Esteberanz", "3"));
        elements.add(new ListElement("Juan Antonio Valencia", "7"));
        elements.add(new ListElement("Aaron Tome Martín", "4"));
        elements.add(new ListElement("Luis Enrique Alcaraz", "20"));

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
}