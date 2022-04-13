package view.adapter;

import android.content.Context;
import model.Product;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import model.Product;
import view.PhoneList;
import view.product.ListProduct;

public class ListPhone extends BaseAdapter {
    Context context;
    ArrayList<Product> phone;

    public ListPhone(Context context, ArrayList<Product> phone) {
        this.context = context;
        this.phone = phone;
    }

    @Override
    public int getCount() {
        return phone.size();
    }

    @Override
    public Object getItem(int i) {
        return phone.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Integer.parseInt(phone.get(i).getId());
    }
    public class ViewHolder{
        public  TextView txtName, txtPrice, txtSub;
        public ImageView iv_product;
        public Button addtocart;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_phone_list,null);
            viewHolder.txtName = view.findViewById(R.id.itemdt_ten);
            viewHolder.txtPrice = view.findViewById(R.id.itemdt_gia);
            viewHolder.txtSub = view.findViewById(R.id.itemdt_mota);
            viewHolder.iv_product = (ImageView) view.findViewById(R.id.itemdt_image);
            view.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Product phone= (Product) getItem(i);
        viewHolder.txtName.setText(phone.getName());
        viewHolder.txtPrice.setText(phone.getPrice());
        Bitmap bitmap = BitmapFactory.decodeByteArray(phone.getImage(),0, phone.getImage().length);
        viewHolder.iv_product.setImageBitmap(bitmap);
        return view;
    }
}
