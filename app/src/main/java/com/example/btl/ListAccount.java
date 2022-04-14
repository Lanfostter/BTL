package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Product;
import sqlite.Sqlite;
import view.account.RegisterActivity;
import view.adapter.AccountAdapter;
import view.adapter.ProductAdapter;
import view.product.ListProduct;

public class ListAccount extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);
    ListView lvaccount;
    ArrayList<Account> accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_account);
        lvaccount = findViewById(R.id.lv_account);
        accounts = sqlite.getAllAccount();
        lvaccount.setAdapter(new AccountAdapter(ListAccount.this, R.layout.acount_row, accounts));
        ImageView addnewaccount = (ImageView) findViewById(R.id.img_add_accound);
        addnewaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListAccount.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public void editAccount(int id) {
        Account.takeid = id;
        
    }

    public void dialogDeleteAccount(int id) {
        Account.takeid = id;

    }
}