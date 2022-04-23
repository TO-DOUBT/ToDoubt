package com.aafs.todoubt;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.RecyclerHolder> {
    private List<ListElement> items;
    private LinearLayout fila_jugadores;

    private int contador = 0;

    public ListAdapter(List<ListElement> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);
        fila_jugadores = view.findViewById(R.id.linear_fila_jugador_plantilla);

        //Anchura fila del RecyclerView
        int miAltura = 100;
        view.getLayoutParams().height = miAltura;
        contador++;

        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
         ListElement item = items.get(position);
         holder.nombreJugador.setText(item.getNombreJugador());
        holder.dorsalJugador.setText(item.getDorsalJugador());

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





