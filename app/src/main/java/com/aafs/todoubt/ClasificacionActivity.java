package com.aafs.todoubt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import java.util.ArrayList;

public class ClasificacionActivity extends AppCompatActivity {
    private TableLayout clasif;
    private ArrayList<String> clasifica;
    private ImageButton bck_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion);
        clasif = findViewById(R.id.clf_clasificacion);
        bck_btn = findViewById(R.id.clsf_bck_btn);

        // Cargar Datos Tabla
        try{
            clasifica = (ArrayList<String>)( getIntent().getBundleExtra("LIST").getSerializable("key"));
            int flag=1;

            // when i=-1, loop will display heading of each column
            // then usually data will be display from i=0 to jArray.length()
            for(int i = -1 ; i < clasifica.size() ; i++){

                TableRow tr = new TableRow(ClasificacionActivity.this);

                tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

                // this will be executed once
                if(flag==1){

                    TextView b3=new TextView(ClasificacionActivity.this);
                    b3.setText("Posicion\n");
                    b3.setTextSize(20);
                    tr.addView(b3);

                    TextView b4=new TextView(ClasificacionActivity.this);
                    b4.setPadding(10, 0, 0, 0);
                    b4.setTextSize(20);
                    b4.setText("Equipo\n");
                    tr.addView(b4);
                    clasif.addView(tr);
                    final View vline = new View(ClasificacionActivity.this);
                    vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
                    clasif.addView(vline); // add line below heading
                    flag=0;
                } else {
                    TextView b = new TextView(ClasificacionActivity.this);
                    String str = String.valueOf(i+1);
                    b.setText(str);
                    b.setTextSize(15);
                    tr.addView(b);

                    TextView b1 = new TextView(ClasificacionActivity.this);
                    b1.setPadding(10, 0, 0, 0);
                    b1.setTextSize(15);
                    String str1 = clasifica.get(i);
                    b1.setText(str1);
                    tr.addView(b1);
                    clasif.addView(tr);

                    final View vline1 = new View(ClasificacionActivity.this);
                    vline1.setLayoutParams(new
                            TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));

                    clasif.addView(vline1);  // add line below each row
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        bck_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}