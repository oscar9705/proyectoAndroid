package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.proyectoandroid.entity.Llegada;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Recoger extends AppCompatActivity {
    String email="";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recoger);
        Bundle dato = this.getIntent().getExtras();
        email = dato.getString("correo");
        System.out.println(email);
    }
    public Intent redireccion(Class<?> cls){
        Intent intent= new Intent(this, cls);
        intent.putExtra("correo",email);
        return  intent;
    }
    public void redirigirMenu(){
        startActivity(redireccion(Menu.class));
    }
    public void redirigirLogin(View v){
        Intent intent= new Intent(this, login.class );
        startActivity(intent);
    }
    public void listo(View v){
        DocumentReference documentReference = db.collection("recogidas").document(email);
        documentReference.update(hashMapFirebase());
        redirigirMenu();
    }

    public Map<String, Object> hashMapFirebase(){
        Map<String, Object> llegadaMap =new HashMap<>();
        llegadaMap.put("estado","Recogido");
        return llegadaMap;
    }
}