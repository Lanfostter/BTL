package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button login, register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.btn_sign_in);
        register = (Button) findViewById(R.id.btn_sign_up);
        ClickLogin();
        ClickRegister();
    }
    // bắt sự kiện onclick login
    public void ClickLogin(){
        login.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
    }
    // bắt sự kiện onclick register
    public void ClickRegister(){
        register.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));
    }
}