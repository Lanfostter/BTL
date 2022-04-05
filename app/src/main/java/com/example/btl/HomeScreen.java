package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

public class HomeScreen extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewTrangChu;
    NavigationView navigationView;
    ListView listViewTrangChu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Anhxa();
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbartrangchu);
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerViewTrangChu = findViewById(R.id.recyclerview);
        listViewTrangChu = findViewById(R.id.listviewtrangchu);
        navigationView = findViewById(R.id.navigationview);

    }
}