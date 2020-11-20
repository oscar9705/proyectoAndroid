package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RegistrarAlumno extends AppCompatActivity {
    private TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_alumno);
        tableLayout =  (TableLayout)findViewById(R.id.tableAlumnos);
        tableLayout.setStretchAllColumns(true);
        cargarFilas();



    }

    public TextView definirTV(String mensaje, String color){
        final TextView tv = new TextView(this);
        tv.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv.setText(mensaje);
        tv.setBackgroundColor(Color.parseColor(color));
        tv.setTextColor(Color.parseColor("#000000"));
        tv.setGravity(Gravity.LEFT);
        tv.setPadding(5, 15, 0, 15);
        return tv;
    }
    public TableRow definirTR(int i){
        final TableRow tr = new TableRow(this);
        tr.setId(i+1);
        TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        trParams.setMargins(0,0,0,0);
        tr.setPadding(0,0,0,0);
        tr.setLayoutParams(trParams);

        return tr;
    }
    public void cargarFilas(){
        final TextView tv = definirTV("oscar salazar","#ffffff");
        final TextView tv1 = definirTV("secundaria","#ffffff");
        final TextView tv2 = definirTV("eliminar","#ffffff");
        final TableRow tr = definirTR(1);
        tr.addView(tv);
        tr.addView(tv1);
        tr.addView(tv2);
        tableLayout.addView(tr);
    }
}