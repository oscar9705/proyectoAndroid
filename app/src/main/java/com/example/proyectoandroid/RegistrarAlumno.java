package com.example.proyectoandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.proyectoandroid.entity.Student;
import com.example.proyectoandroid.entity.User;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RegistrarAlumno extends AppCompatActivity {
    String correo="";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RadioGroup radioGroup;
    private EditText nombre;
    private TableLayout tableLayout;
    List<Student> listaEstudiantes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_alumno);
        tableLayout =  (TableLayout)findViewById(R.id.tableAlumnos);
        tableLayout.setStretchAllColumns(true);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroupSeccion);
        nombre = (EditText)findViewById(R.id.editTextStudent);
        Bundle dato = getIntent().getExtras();
        correo = dato.getString("correo");
        System.out.println("Registrar alumnos "+correo);
       cargarEstudiantesTimepoReal();
    }
    public Intent redireccion(Class<?> cls){
        Intent intent= new Intent(this, cls);
        return  intent;
    }
    public void redirigirMenu(View v){
        startActivity(redireccion(Menu.class));
    }

    public void cargarEstudiantesTimepoReal(){
        DocumentReference documentReference = db.collection("users").document(correo);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                User user =value.toObject(User.class);
                removerFilas();
                cargarFilas(user.getAlumnos());
                listaEstudiantes = user.getAlumnos();
            }
        });
    }
    public void removerFilas(){
        int count = tableLayout.getChildCount();
        System.out.println("cantidad de hijos " +count);
        for (int i = 1; i < count; i++) {
            View child = tableLayout.getChildAt(i);
            tableLayout.removeView(child);
        }
    }
    public void redirigirLogin(View v){
        Intent intent= new Intent(this, login.class );
        startActivity(intent);
    }

    public void guardarAlumno(View v){
        System.out.println(listaEstudiantes.size()+"  tamaño de la lista");
        Student student = crearStudent();
        listaEstudiantes.add(student);
        DocumentReference documentReference = db.collection("users").document(correo);
        documentReference.update(hashMapFirebase());
        removerFilas();
        limpiarCampos();
    }
    public String radioSeccion(){
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton rbs = (RadioButton)findViewById(radioButtonId);
        return rbs.getText().toString();
    }
    public Student crearStudent(){
        Student student = new Student();
        student.setNombre(nombre.getText().toString());
        student.setSeccion(radioSeccion());

        return student;
    }
    public Map<String, Object>  hashMapFirebase(){
        Map<String, Object> userMap =new HashMap<>();
        userMap.put("alumnos",listaEstudiantes);

        return userMap;
    }
    public void limpiarCampos(){
        nombre.setText("");
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
    public TextView definirTVDelete(String mensaje, String color, int i){
        final TextView tv = new TextView(this);
        tv.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv.setText(mensaje);
        tv.setBackgroundColor(Color.parseColor(color));
        tv.setTextColor(Color.parseColor("#000000"));
        tv.setGravity(Gravity.LEFT);
        tv.setPadding(5, 15, 0, 15);
        tv.setClickable(true);
        tv.setId(i);

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
    public void cargarFilas(List<Student>students){
        for(int i=0; i<students.size();i++) {
            final TextView tv = definirTV(students.get(i).getNombre(), "#ffffff");
            final TextView tv1 = definirTV(students.get(i).getSeccion(), "#ffffff");
            TextView tv2 = definirTVDelete("eliminar", "#ffffff",i);
            tv2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    System.out.println(v.getId());
                    listaEstudiantes.remove(v.getId());
                    DocumentReference documentReference = db.collection("users").document(correo);
                    documentReference.update(hashMapFirebase());
                    removerFilas();

                }
            });
            final TableRow tr = definirTR(i);
            final TableRow ts = separador();
            tr.addView(tv);
            tr.addView(tv1);
            tr.addView(tv2);
            tableLayout.addView(tr);
            tableLayout.addView(ts);


        }
    }
    public void crearFila(Student student, int i){
        final TextView tv = definirTV(student.getNombre(), "#ffffff");
        final TextView tv1 = definirTV(student.getSeccion(), "#ffffff");
        final TextView tv2 = definirTV("eliminar", "#ffffff");
        final TableRow tr = definirTR(i);
        final TableRow ts = separador();
        tr.addView(tv);
        tr.addView(tv1);
        tr.addView(tv2);
        tableLayout.addView(tr);
        tableLayout.addView(ts);
    }
}