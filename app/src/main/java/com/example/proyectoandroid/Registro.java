package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.proyectoandroid.entity.Student;
import com.example.proyectoandroid.entity.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registro extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        cargarDatosFirebase();
    }
    public void cargarDatosFirebase(){
        List<Student> studentList = new ArrayList<>();
        Student stu = new Student("andres ","bachilleraton");
        Student stu1 = new Student("andrea ","primaria");
        studentList.add(stu);
        studentList.add(stu1);
        User  usuario = new User(1234,"oscar salazar","salazaroscar9705@gmail.com","3104674785","123455",studentList);
        Map<String, Object> user = new HashMap<>();
        user.put("cedula",usuario.getCedula());
        user.put("nombre",usuario.getNombre());
        user.put("correo",usuario.getCorreo());
        user.put("telefono",usuario.getTelefono());
        user.put("password",usuario.getPassword());
        user.put("alumnos",studentList);

        db.collection("users").document("salazaroscar9705@gmail.com").set(user);
    }
}