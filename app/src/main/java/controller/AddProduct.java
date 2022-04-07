package controller;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.btl.R;
import com.example.btl.Sqlite;

import java.io.File;

import model.Product;

public class AddProduct extends AppCompatActivity {
    Button add, gallery;
    Product product = new Product();
    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        String strResult = intent.getStringExtra("data_result");
                    }
                }
            });

    // hello
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        gallery = (Button) findViewById(R.id.btn_add_upload_image);
        add = (Button) findViewById(R.id.btn_add_new_product);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertProduct();
            }
        });
        insertProduct();
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                mActivityResultLauncher.launch(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri selectImage = data.getData();
            ImageView imageView = (ImageView) findViewById(R.id.img_product);
            imageView.setImageURI(selectImage);
            File file = new File(selectImage.getPath());
            product.setImage(file.getPath());
        }

    }

    private void insertProduct() {
        Sqlite sqlite = new Sqlite(this, "App Electronics Devices Sale", null, 1);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText id = (EditText) findViewById(R.id.edit_txt_id);
                EditText name = (EditText) findViewById(R.id.edit_txt_name);
                EditText quantity = (EditText) findViewById(R.id.edit_txt_quantity);
                EditText price = (EditText) findViewById(R.id.edit_txt_price);
                product.setId(Integer.parseInt(id.getText().toString()));
                product.setName(name.getText().toString());
                product.setQuantity(Integer.parseInt(quantity.getText().toString()));
                product.setPrice(Double.parseDouble(price.getText().toString()));
                sqlite.QueryData("INSERT INTO PRODUCT VALUES(" + product.getId() + ",'" + product.getName() + "'," +
                        product.getQuantity() + "," + product.getPrice() + "," + product.getImage() + ")");

            }
        });
    }
}