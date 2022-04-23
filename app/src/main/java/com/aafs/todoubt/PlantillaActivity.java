package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class PlantillaActivity extends AppCompatActivity implements androidx.appcompat.widget.SearchView.OnQueryTextListener {


    private ListAdapter listAdapter;
    private RecyclerView recyclerView;
    private List<ListElement> items;
    private androidx.appcompat.widget.SearchView buscador_plantilla_jugadores;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantilla);

        initViews();
        initValues();
    }

    private void initViews(){
        //buscador_plantilla_jugadores.setOnQueryTextListener(this);
        recyclerView = findViewById(R.id.recycler_jugadores_plantilla);
    }
    private void initValues(){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        items = getItems();
        listAdapter = new ListAdapter(items);
       //recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);

        buscador_plantilla_jugadores = findViewById(R.id.buscador_plantilla);
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
        elements.add(new ListElement("Luis Enrique Alcarazzzzzzzzz", "20"));
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
        //listAdapter.filter(newText);
        return false;
    }
}