package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.btl.MainActivity;
import com.example.btl.R;

import java.util.ArrayList;

import model.Product;
import security.SessionManager;

public class ShoppingCart extends AppCompatActivity {
    Button btnmuahang;
    ListView products;
    ArrayList<Product> productArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        btnmuahang = (Button) findViewById(R.id.btnmuahang);
        ClickMuaHang();
    }

    private void ClickMuaHang() {
        btnmuahang.setOnClickListener(v -> startActivity(new Intent(ShoppingCart.this, payment.class)));// chuyen trang Cart
    }


}