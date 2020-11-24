package com.example.proyectoandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RegistrarAlumno extends AppCompatActivity {
    String correo="", seccion="";
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
        radioGroup.getCheckedRadioButtonId();
        Bundle dato = getIntent().getExtras();
        correo = dato.getString("correo");
        System.out.println("Registrar alumnos "+correo);
       cargarEstudiantesRealTime();

      /*  cargarFilas();
        cargarFilas();
        cargarFilas();*/
        /*cargarDatosFirebase();*/



    }
    public void cargarDatosFirebase(){
        List<Student> studentList = new ArrayList<>();
        List<Student> studentLista = new ArrayList<>();
        Student stu = new Student("andres ","bachilleraton");
        Student stu1 = new Student("andrea ","primaria");
        Student stu2 = new Student("andrew ","pre-escolar");
        Student stu3 = new Student("oscar ","pre-escolar");
        Student stu4 = new Student("alberto ","bachillerato");
        studentList.add(stu);
        studentList.add(stu1);
        studentList.add(stu2);
        studentList.add(stu3);
        studentLista.add(stu4);
        User usuario = new User(1234,"oscar salazar","salazaroscar9705@gmail.com","3104674785","Osc@r9705",studentList);
        Map<String, Object> user = new HashMap<>();
        user.put("cedula",usuario.getCedula());
        user.put("nombre",usuario.getNombre());
        user.put("correo",usuario.getCorreo());
        user.put("telefono",usuario.getTelefono());
        user.put("password",usuario.getPassword());
        user.put("alumnos",studentList);

        Map<String, Object> prueba = new HashMap<>();
        prueba.put("alumnos", studentLista);
         db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        System.out.println(document.getData());
                        System.out.println(document.getId());
                        User user = document.toObject(User.class);
                        System.out.println(user.getCedula()+ " "+user.getNombre()+" "+user.getPassword()+" "+ user.getCorreo()  );
                        List<Student> studentList1= user.getAlumnos();
                       for(int i=0; i<studentList1.size();i++){
                           System.out.println(studentList1.get(i).getNombre() +" "+studentList1.get(i).getSeccion());

                       }
                    }
                } else {
                    System.out.println("error");
                }
            }
        });


        //db.collection("users").document("salazaroscar9705@gmail.com").collection("alumnos").document().set(stu3);
    }
    public void cargarEstudiantes(){
        DocumentReference documentReference = db.collection("users").document(correo);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                listaEstudiantes = user.getAlumnos();
                System.out.println("cargar estudiantes "+listaEstudiantes.get(0).getNombre());
                cargarFilas(user.getAlumnos());
            }
        });


    }
    public void cargarEstudiantesRealTime(){
        DocumentReference documentReference = db.collection("users").document(correo);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                User user =value.toObject(User.class);
                cargarFilas(user.getAlumnos());
            }
        });
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
    public void cargarFilas(List<Student>students){
        for(int i=0; i<students.size();i++){
            final TextView tv = definirTV(students.get(i).getNombre(),"#ffffff");
            final TextView tv1 = definirTV(students.get(i).getSeccion(),"#ffffff");
            final TextView tv2 = definirTV("eliminar","#ffffff");
            final TableRow tr = definirTR(i);
            final TableRow ts = separador();
            tr.addView(tv);
            tr.addView(tv1);
            tr.addView(tv2);
            tableLayout.addView(tr);
            tableLayout.addView(ts);
        }
       /* final TextView tv = definirTV("oscar salazar","#ffffff");
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
        tableLayout.addView(ts);*/
    }
}