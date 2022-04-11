package view.product;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.btl.R;

import sqlite.Sqlite;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import model.Product;
import view.adapter.ProductAdapter;

public class ListProduct extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);
    ListView lvproduct;
    ArrayList<Product> products;
    ImageView imageView;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        lvproduct = findViewById(R.id.lv_product);
        products = sqlite.getAllProduct();
        lvproduct.setAdapter(new ProductAdapter(ListProduct.this, products));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            products = sqlite.getAllProduct();
            Uri uri = Uri.parse(products.get(0).getImage());
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}