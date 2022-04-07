package controller;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;

import com.example.btl.R;
import com.example.btl.Sqlite;

import java.util.ArrayList;

import model.Product;

public class ListProduct extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "App Electronics Devices Sale", null, 1);
    ArrayList<Product> products = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        Cursor dataProduct = sqlite.getData("SELECT * FROM PRODUCT");
        while (dataProduct.moveToNext()){

        }
    }
}