package view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.example.btl.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import model.Product;
import sqlite.Sqlite;
import view.adapter.ListPhone;
import view.adapter.ListProductAdapter;
import view.adapter.MenuAdapter;

public class PhoneList extends AppCompatActivity {

    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);
    List<Product> phone;
    ListView listView;
    Product product = new Product();
    ListPhone listPhone;
    Toolbar tbphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_list);
        Anhxa();
   }

    private void Anhxa() {
        tbphone= (Toolbar) findViewById(R.id.tb_listphone);
        listView = (ListView) findViewById(R.id.lv_phone);
        phone = new ArrayList<>();
        listPhone = new ListPhone(getApplicationContext(), (ArrayList<Product>) phone);
        // dang lam do

    }
}