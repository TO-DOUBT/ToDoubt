package com.aafs.todoubt;


import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.RecyclerHolder> {
    private List<ListElement> items;
    private List<ListElement> originalItems;
    private LinearLayout fila_jugadores;

    private int contador = 0;

    public ListAdapter(List<ListElement> items) {
        this.items = items;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);
        fila_jugadores = view.findViewById(R.id.linear_fila_jugador_plantilla);

        //Filas del RecyclerView: Anchura
        int miHeight = 120;
        view.getLayoutParams().height = miHeight;
        contador++;

        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
         ListElement item = items.get(position);
         holder.nombreJugador.setText(item.getNombreJugador());
        holder.dorsalJugador.setText(item.getDorsalJugador());

        //Pintar fondo de las filas
        if (contador % 2 == 0) {
            fila_jugadores.setBackgroundColor(Color.rgb(251, 251, 251));

        }else {
            fila_jugadores.setBackgroundColor(Color.rgb(255, 255, 255));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(String strSearch){
        String strSearchLowerCase = strSearch.toLowerCase();

        if(strSearchLowerCase.length() == 0){
            items.clear();
            items.addAll(originalItems);
        }
        else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                items.clear();
                List<ListElement> collect = originalItems.stream()
                        .filter(i -> i.getNombreJugador().toLowerCase().contains(strSearchLowerCase))
                        .collect(Collectors.toList());

                items.addAll(collect);
            }
            else {
                for (ListElement i : originalItems) {
                    if (i.getNombreJugador().toLowerCase().contains(strSearchLowerCase)){
                        items.add(i);
                    }

                }
            }

        }
        notifyDataSetChanged();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder{

        private TextView nombreJugador;
        private TextView dorsalJugador;



        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            nombreJugador = itemView.findViewById(R.id.txt_nombre_completo_jugador);
            dorsalJugador = itemView.findViewById(R.id.txt_dorsal_jugador);

        }
    }

}





