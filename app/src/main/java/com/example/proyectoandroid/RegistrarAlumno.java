package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class RegistrarAlumno extends AppCompatActivity {
    String correo="", seccion="";

    private RadioGroup radioGroup;
    private TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_alumno);
        tableLayout =  (TableLayout)findViewById(R.id.tableAlumnos);
        tableLayout.setStretchAllColumns(true);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroupSeccion);
        radioGroup.getCheckedRadioButtonId();
        Bundle dato = getIntent().getExtras();
        correo = dato.getString("correo");
        System.out.println("Registrar alumnos "+correo);
        cargarFilas();
        cargarFilas();
        cargarFilas();
        cargarFilas();



    }
    public void guardarAlumno(View v){
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        System.out.println(radioButtonId);

        View radioButton = radioGroup.findViewById(radioButtonId);
        System.out.println(radioButton.toString());

        int indice = radioGroup.indexOfChild(radioButton);
        System.out.println("Indice "+indice);
        RadioButton rbs= (RadioButton)findViewById(radioButtonId);
        System.out.println("texto "+rbs.getText().toString());

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
    public TableRow separador(){
        final TableRow trSep = new TableRow(this);
        TableLayout.LayoutParams trParamsSep = new
                TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        trParamsSep.setMargins(0,0,0,0);
        trSep.setLayoutParams(trParamsSep);
        TextView tvSep = new TextView(this);
        TableRow.LayoutParams tvSepLay = new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        tvSepLay.span = 3;
        tvSep.setLayoutParams(tvSepLay);
        tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
        tvSep.setHeight(1);
        trSep.addView(tvSep);
        return  trSep;
    }
    public void cargarFilas(){
        final TextView tv = definirTV("oscar salazar","#ffffff");
        final TextView tv1 = definirTV("secundaria","#ffffff");
        final TextView tv2 = definirTV("eliminar","#ffffff");
        final TableRow tr = definirTR(1);
        final TableRow ts = separador();
        TableLayout.LayoutParams trParamsSep = new
                TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        tr.addView(tv);
        tr.addView(tv1);
        tr.addView(tv2);
        tableLayout.addView(tr);
        tableLayout.addView(ts);
    }
}