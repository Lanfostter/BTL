package view.adapter;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.btl.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import model.Product;
import view.product.ListProduct;

public class ProductAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> products;
    LayoutInflater inflater;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
        inflater = LayoutInflater.from(context);
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

        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.item_contact, null);
        } else viewProduct = convertView;
        //Bind sữ liệu phần tử vào View
        Product product = (Product) getItem(position);
        ((TextView) viewProduct.findViewById(R.id.idproduct)).setText("ID: " +product.getId());
        ((TextView) viewProduct.findViewById(R.id.nameproduct)).setText("Name: " + product.getName());
        ((TextView) viewProduct.findViewById(R.id.priceproduct)).setText("Price: "+product.getPrice());
        ((TextView) viewProduct.findViewById(R.id.quantity)).setText("Quantity: " + String.valueOf(product.getQuantity()));

//        Uri uri = Uri.parse(product.getImage());
//        Glide
//                .with(viewProduct)
//                .load(uri.getPath())
//                .into((ImageView) viewProduct.findViewById(R.id.iv_product));
        return viewProduct;
    }

}
