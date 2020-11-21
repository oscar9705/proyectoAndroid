package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {
    String email ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle dato = this.getIntent().getExtras();
        email = dato.getString("correo");
        System.out.println("menu "+ email);

    }
    public Intent redireccion(Class<?> cls){
        Intent intent= new Intent(this, cls);
        intent.putExtra("correo",email);
        return  intent;
    }
    public void redirigirAlumno(View v){
        startActivity(redireccion(RegistrarAlumno.class));
    }
}