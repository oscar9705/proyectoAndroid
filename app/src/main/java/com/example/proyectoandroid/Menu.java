package com.example.proyectoandroid;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.proyectoandroid.entity.Llegada;
import com.example.proyectoandroid.entity.Student;
import com.example.proyectoandroid.entity.User;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu extends AppCompatActivity {
    String email ="";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RadioGroup rg;
    List<Student> listaEstudiantes= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        rg = (RadioGroup)findViewById(R.id.carrilRadioGroup);
        Bundle dato = this.getIntent().getExtras();
        email = dato.getString("correo");
        System.out.println("menu "+ email);
        cargarEstudiantesTimepoReal();

    }
    public Intent redireccion(Class<?> cls){
        Intent intent= new Intent(this, cls);
        intent.putExtra("correo",email);
        return  intent;
    }
    public void redirigirAlumno(View v){
        startActivity(redireccion(RegistrarAlumno.class));
    }
    public void redirigirListo(){
        startActivity(redireccion(Recoger.class));
    }
    public void cargarEstudiantesTimepoReal(){
        DocumentReference documentReference = db.collection("users").document(email);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                User user =value.toObject(User.class);
                 listaEstudiantes = user.getAlumnos();
                System.out.println(listaEstudiantes.toString());
            }
        });
    }
    public void recogerAlumnos(View v){
        DocumentReference documentReference = db.collection("recogidas").document(email);
        documentReference.set(hashMapFirebase());
        rg.clearCheck();
        redirigirListo();
    }
    public String radioSeccion(){
        int radioButtonId = rg.getCheckedRadioButtonId();
        RadioButton rbs = (RadioButton)findViewById(radioButtonId);
        System.out.println(rbs.getText().toString());
        return rbs.getText().toString();
    }

    public Llegada crear() {
        Llegada llegada = new Llegada(email, "pendiente", radioSeccion(), listaEstudiantes);
        System.out.println(llegada.getFecha());
        return llegada;
    }
    public Map<String, Object> hashMapFirebase(){
        Llegada llegada = crear();
        System.out.println("hashmap" +llegada.getFecha());
        Map<String, Object> llegadaMap =new HashMap<>();
        llegadaMap.put("correo",llegada.getCorreo());
        llegadaMap.put("estado",llegada.getEstado());
        llegadaMap.put("carril",llegada.getCarril());
        llegadaMap.put("fecha",llegada.getFecha());
        llegadaMap.put("alumnos",listaEstudiantes);

        return llegadaMap;
    }
    public void redirigirLogin(View v){
        Intent intent= new Intent(this, login.class );
        startActivity(intent);
    }
}