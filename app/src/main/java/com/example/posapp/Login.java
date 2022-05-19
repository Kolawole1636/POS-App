package com.example.posapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText ed1,ed2;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ed1 = findViewById(R.id.user);
        ed2 = findViewById(R.id.pass);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }
    public void Login(){
        String username = ed1.getText().toString();
        String password = ed2.getText().toString();

        if(username.equals("") || password.equals("")){
            Toast.makeText(this,"Username or Password Blank",Toast.LENGTH_LONG).show();
        }
        else if(username.equals("kola") && password.equals("123")){

            Intent i = new Intent(Login.this, MainActivity.class );
            startActivity(i);
        }
        else{
            Toast.makeText(this,"Username or Password do not match",Toast.LENGTH_LONG).show();
        }


    }









}