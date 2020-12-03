package com.example.proyectoandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoandroid.entity.Student;
import com.example.proyectoandroid.entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registro extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText cedula, nombre, correo, telefono, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mAuth = FirebaseAuth.getInstance();
        cedula =(EditText)findViewById(R.id.cedulaEditText);
        nombre =(EditText)findViewById(R.id.nameEditText);
        correo =(EditText)findViewById(R.id.correoEditText);
        telefono =(EditText)findViewById(R.id.celularEditText);
        password =(EditText)findViewById(R.id.editTextPassword);

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
    public void registrarUser(View v){
        User user = crearUser();
        Map<String, Object> userMap = hashMapFirebase(user);
        db.collection("users").document(user.getCorreo()).set(userMap);
        registrar(user).addOnCompleteListener(Registro.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    toast("registro exitoso");
                } else {
                    toast("registro fallido");
                }
            }
        });
        redirigirLogin();

    }
    public Task<AuthResult> registrar(User user){
        return mAuth.createUserWithEmailAndPassword(user.getCorreo(),user.getPassword());
    }
    public User crearUser(){
        User user = new User();
        user.setCedula(Integer.parseInt(cedula.getText().toString()));
        user.setNombre(nombre.getText().toString());
        user.setCorreo(correo.getText().toString());
        user.setTelefono(telefono.getText().toString());
        user.setPassword(password.getText().toString());

        return user;
    }
    public Map<String, Object>  hashMapFirebase(User user){
        List<Student> studentList = new ArrayList<>();
        Map<String, Object> userMap =new HashMap<>();
        userMap.put("cedula",user.getCedula());
        userMap.put("nombre",user.getNombre());
        userMap.put("correo",user.getCorreo());
        userMap.put("telefono",user.getTelefono());
        userMap.put("password",user.getPassword());
        userMap.put("alumnos",studentList);
        return userMap;
    }
    public void limpiarCampos(){
        nombre.setText("");
        cedula.setText("");
        correo.setText("");
        telefono.setText("");
        password.setText("");
    }
    public void toast(String mjs){
        Toast.makeText(this, mjs, Toast.LENGTH_SHORT).show();
    }
    public Intent redireccion(Class<?> cls){
        return  new Intent(this,cls);
    }
    public void redirigirLogin(){
        startActivity(redireccion(login.class));
    }

}