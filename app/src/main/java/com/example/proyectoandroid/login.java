package com.example.proyectoandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    public void registrar(View v){
        System.out.println("entrando al metodo registrar");
        mAuth.createUserWithEmailAndPassword(et1.getText().toString(),et2.getText().toString()).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                System.out.println("entrando al oncomplete, el correo: "+et1.getText().toString());
                if(task.isSuccessful()){

                    System.out.println(et1.toString());
                    Toast.makeText(login.this, "Authentication successful",
                            Toast.LENGTH_SHORT).show();
                    et1.setText("");
                    et2.setText("");
                } else {
                    Toast.makeText(login.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void ingresar(View v){
        mAuth.signInWithEmailAndPassword(et1.toString(),et2.toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    System.out.println(user.getEmail());
                    Toast.makeText(login.this, "Authentication successful",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(login.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}