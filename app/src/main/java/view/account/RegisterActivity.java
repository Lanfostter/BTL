package view.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btl.R;

import model.Account;
import sqlite.Sqlite;
import view.HomeScreen;

public class RegisterActivity extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button addAccount;
        addAccount = (Button) findViewById(R.id.btn_add_new_account);
        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText id = (EditText) findViewById(R.id.editText_id);
                EditText username = (EditText) findViewById(R.id.editText_username);
                EditText password = (EditText) findViewById(R.id.editText_password);
                EditText role = (EditText) findViewById(R.id.editText_role);
                Account newaccound = new Account(Integer.parseInt(id.getText().toString()), username.getText().toString(),
                        password.getText().toString(), role.getText().toString());
                sqlite.insertAccount(newaccound);
                if (sqlite.insertAccount(newaccound) == true) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "Thêm thành công", Toast.LENGTH_LONG);
                    toast.show();
                    Intent intent = new Intent(RegisterActivity.this, HomeScreen.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(RegisterActivity.this, "Thêm thất bại", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

}