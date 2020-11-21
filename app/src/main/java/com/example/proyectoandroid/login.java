package com.example.proyectoandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    EditText et1, et2;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        et1 = (EditText)findViewById(R.id.emailEditText);  // email
        et2 = (EditText)findViewById(R.id.passwordEditText); // password

    }
    public Intent redireccion(Class<?> cls){
        return  new Intent(this,cls);
    }
    public void redirigirRegistro(View v){
        Intent i = redireccion(Registro.class);
        i.putExtra("email",mAuth.getCurrentUser().getEmail());
        startActivity(i);
    }
    public void redirigirMenu(){
        startActivity(redireccion(Menu.class));
    }
    public void registrar(){
        System.out.println("entrando al metodo registrar");
        mAuth.createUserWithEmailAndPassword(et1.getText().toString(),et2.getText().toString()).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                System.out.println("entrando al oncomplete, el correo: "+et1.getText().toString());
                if(task.isSuccessful()){


                    /*Toast.makeText(login.this, "Authentication successful",
                            Toast.LENGTH_SHORT).show();*/
                    toast("Autenticación exitosa");

                    et1.setText("");
                    et2.setText("");
                } else {
                    toast("Autenticación fallida");
                }
            }
        });
    }
    public void ingresar(View v){
        mAuth.signInWithEmailAndPassword(et1.getText().toString(),et2.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(login.this, "Autenticación exitosa",
                            Toast.LENGTH_SHORT).show();
                    System.out.println("login "+mAuth.getCurrentUser().getEmail());
                    Intent i = new Intent(login.this,Menu.class);
                    i.putExtra("correo",mAuth.getCurrentUser().getEmail());
                    startActivity(i);
                    //redirigirMenu();
                } else {
                    Toast.makeText(login.this, "Autenticación fallida",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void toast(String mjs){
        Toast.makeText(this, mjs, Toast.LENGTH_SHORT).show();
    }

}