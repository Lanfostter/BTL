package controller;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.btl.R;
import com.example.btl.Sqlite;

import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ListProduct extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);
    List<Product> products;
    ListView lvproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        lvproduct = findViewById(R.id.lv_product);
        ArrayAdapter adapter = new ArrayAdapter(ListProduct.this, android.R.layout.simple_list_item_1, sqlite.getAllProduct());
        lvproduct.setAdapter(adapter);

    }
}