package view.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.btl.MainActivity;
import com.example.btl.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import model.Product;
import sqlite.Sqlite;
import view.product.ListProduct;

public class ProductAdapter extends BaseAdapter {
    ListProduct context;
    Context mycontext;
    ArrayList<Product> products;
    LayoutInflater inflater;
    Button delete;

    public ProductAdapter(Context mycontext, ArrayList<Product> products) {
        this.mycontext = mycontext;
        this.products = products;
        inflater = LayoutInflater.from(mycontext);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Integer.parseInt(products.get(i).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_contact, parent, false);
        }
        //Bind sữ liệu phần tử vào View
        Product product = (Product) getItem(position);
        ((TextView) convertView.findViewById(R.id.idproduct)).setText("ID: " + product.getId());
        ((TextView) convertView.findViewById(R.id.nameproduct)).setText("Name: " + product.getName());
        ((TextView) convertView.findViewById(R.id.priceproduct)).setText("Price: " + product.getPrice());
        ((TextView) convertView.findViewById(R.id.quantity)).setText("Quantity: " + String.valueOf(product.getQuantity()));
        delete = (Button) convertView.findViewById(R.id.bt_delete);
        Log.e("hello" + convertView, "đâ");

        delete.setOnClickListener(view -> {
            context.DialogDeleteProduct(product.getId());
            notifyDataSetChanged();
        });
//        Uri uri = Uri.parse(product.getImage());
//        Glide
//                .with(viewProduct)
//                .load(uri.getPath())
//                .into((ImageView) viewProduct.findViewById(R.id.iv_product));
        return convertView;


    }

}
