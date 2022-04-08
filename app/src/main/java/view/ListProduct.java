package view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.btl.R;
import sqlite.Sqlite;

import java.util.List;

import model.Product;

public class ListProduct extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);
    ListView lvproduct;
    List<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        lvproduct = findViewById(R.id.lv_product);
        products = sqlite.getAllProduct();
            ArrayAdapter adapter = new ArrayAdapter(ListProduct.this, android.R.layout.simple_list_item_1, products);
            lvproduct.setAdapter(adapter);
            adapter.notifyDataSetChanged();

    }
}