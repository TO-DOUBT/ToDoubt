package com.aafs.todoubt.plantilla;


import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aafs.todoubt.R;
import com.aafs.todoubt.wsdatos.DatosJugador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.RecyclerHolder> {
    private List<DatosJugador> items;
    private List<DatosJugador> originalItems;
    private LinearLayout fila_jugadores;
    private RecyclerItemClick itemClick;

    private int contador = 0;

    public ListAdapter(List<DatosJugador> items, RecyclerItemClick itemClick) {
        this.items = items;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
        this.itemClick = itemClick;
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
        DatosJugador item = items.get(position);
        holder.nombreJugador.setText(item.getNombreCompleto());

        //holder.dorsalJugador.setText(String.valueOf(item.getCodigoJugador()));

        holder.fila_jugadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(item);
            }
        });
        holder.nombreJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(item);
            }
        });

        //Pintar fondo de las filas
        if (contador % 2 == 0) {
            fila_jugadores.setBackgroundColor(Color.rgb(251, 251, 251));

        }else {
            fila_jugadores.setBackgroundColor(Color.rgb(255, 255, 255));
        }

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(),EditarFilasPlantillaActivity.class);
                intent.putExtra("listElement", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });*/
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
                List<DatosJugador> collect = originalItems.stream()
                        .filter(i -> i.getNombreCompleto().toLowerCase().contains(strSearchLowerCase))
                        .collect(Collectors.toList());

                items.addAll(collect);
            }
            else {
                for (DatosJugador i : originalItems) {
                    if (i.getNombreCompleto().toLowerCase().contains(strSearchLowerCase)){
                        items.add(i);
                    }

                }
            }

        }
        notifyDataSetChanged();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder{

        private TextView nombreJugador;
        private LinearLayout fila_jugadores;
        //private TextView dorsalJugador;



        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            nombreJugador = itemView.findViewById(R.id.txt_nombre_completo_jugador);
            fila_jugadores = itemView.findViewById(R.id.linear_fila_jugador_plantilla);

            //dorsalJugador = itemView.findViewById(R.id.txt_dorsal_jugador);

        }
    }

    public interface RecyclerItemClick {
        void itemClick(DatosJugador item);
    }

}





