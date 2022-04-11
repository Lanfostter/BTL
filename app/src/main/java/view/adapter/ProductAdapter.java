package view.adapter;


import android.content.Context;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;


import com.example.btl.MainActivity;
import com.example.btl.R;


import java.util.ArrayList;

import model.Product;
import view.product.ListProduct;

public class ProductAdapter extends BaseAdapter {
    ListProduct context;
    int layout;
    ArrayList<Product> products;
//    LayoutInflater inflater;
//    Button delete;


    public ProductAdapter(ListProduct context, int layout, ArrayList<Product> products) {
        this.context = context;
        this.layout = layout;
        this.products = products;
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

    private class ViewHolder {
        TextView txt_id, txt_name, txt_quantity, txt_price;
        ImageView iv_product;
        Button delete, edit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txt_id = (TextView) view.findViewById(R.id.idproduct);
            holder.txt_name= (TextView) view.findViewById(R.id.nameproduct);
            holder.iv_product = (ImageView) view.findViewById(R.id.iv_product16);
            holder.txt_quantity = (TextView) view.findViewById(R.id.quantityproduct);
            holder.txt_price= (TextView) view.findViewById(R.id.priceproduct);
            holder.delete= (Button) view.findViewById(R.id.bt_delete);
            holder.edit= (Button) view.findViewById(R.id.bt_update);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        Product product = (Product) getItem(i);
        holder.txt_id.setText(product.getId());
        holder.txt_name.setText(product.getName());
        holder.txt_price.setText(product.getPrice());
        holder.txt_quantity.setText(String.valueOf(product.getQuantity()));
        holder.iv_product.setImageURI(Uri.parse(product.getImage()));
//        Bắt sự kiện sửa và xoá
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.EditProduct(product.getId());
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogDeleteProduct(product.getId());
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
