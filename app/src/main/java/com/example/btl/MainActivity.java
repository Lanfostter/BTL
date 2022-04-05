package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.Product;

public class MainActivity extends AppCompatActivity {

    Button login, register;
    ListView products;
    ArrayList<Product> productArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.btn_sign_in);
        register = (Button) findViewById(R.id.btn_sign_up);
        ClickLogin();
        ClickRegister();
        Sqlite sqlite = new Sqlite(this, "App Electronics Devices Sale", null, 1);
        sqlite.QueryData("CREATE TABLE IF NOT EXISTS PRODUCT(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(50), IMAGE VARCHAR(255), " +
                "QUANTITY INTEGER, PRICE DOUBLE)");
//        sqlite.QueryData("INSERT INTO PRODUCT VALUES(1, 'GTX2060', 100, 2001), (2, 'GTX20601', 100, 2000)");
        Cursor dataProduct = sqlite.getData("SELECT * FROM PRODUCT");
        while (dataProduct.moveToNext()) {
            String name = dataProduct.getString(1);
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        }
    }

    // bắt sự kiện onclick login
    public void ClickLogin() {
        login.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
    }

    // bắt sự kiện onclick register
    public void ClickRegister() {
        register.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));
    }
}