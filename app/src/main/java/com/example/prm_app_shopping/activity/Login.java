package com.example.prm_app_shopping.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.prm_app_shopping.R;


public class Login extends AppCompatActivity {
    AppCompatButton login, register;

    private EditText username;
    private EditText password;
    public static final String NAME = "NAME";

//    @SuppressLint("MissingInflatedId");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);

        login = (AppCompatButton) findViewById(R.id.ButtonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("123456")) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra(NAME, username.getText().toString());
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(Login.this, "LOGIN FAILED !!!", Toast.LENGTH_SHORT).show();
            }
        });

        register = (AppCompatButton) findViewById(R.id.buttomRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("This is the pause");
        username.getText().clear();
        password.getText().clear();
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("This is the START");
    }
    }
