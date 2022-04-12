package view.product;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.btl.R;

import sqlite.Sqlite;

import java.io.File;
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
    Product product = new Product();
    Button upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        lvproduct = findViewById(R.id.lv_product);
        products = sqlite.getAllProduct();
        lvproduct.setAdapter(new ProductAdapter(ListProduct.this, R.layout.item_contact, products));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            products = sqlite.getAllProduct();
                Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
                imageView.setImageBitmap(bitmap);
        }
//        Uri selectImage = data.getData();
//        ImageView imageView1 = (ImageView) findViewById(R.id.img_product);
//        imageView1.setImageURI(selectImage);
//        // lấy địa chỉ url
//        File file = new File(selectImage.getPath());
//        product.setImage(file.getPath());
    }
    public void DialogDeleteProduct(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to delete this product ?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            sqlite.queryData("DELETE FROM PRODUCT WHERE P_ID LIKE '" + id + "'");
            sqlite.getAllProduct();
            startActivity(new Intent(ListProduct.this, ListProduct.class));
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {
        });
        builder.show();
    }
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
    public void EditProduct(String id) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_update_product);
        dialog.show();
        EditText pid = (EditText) dialog.findViewById(R.id.update_id);
        pid.setText(id);
        EditText name = (EditText) dialog.findViewById(R.id.update_name);
        EditText quantity = (EditText) dialog.findViewById(R.id.update_quantity);
        EditText price = (EditText) dialog.findViewById(R.id.update_price);

        upload = (Button) dialog.findViewById(R.id.btn_update_upload_image);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                mActivityResultLauncher.launch(intent);
            }
        });
        Button update = (Button) dialog.findViewById(R.id.btn_update_product);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product updateproduct = new Product(pid.getText().toString(), name.getText().toString()
                        , Integer.parseInt(quantity.getText().toString()), price.getText().toString(), product.getImage());
                sqlite.updateProduct(updateproduct, id);
            }
        });
    }
}
