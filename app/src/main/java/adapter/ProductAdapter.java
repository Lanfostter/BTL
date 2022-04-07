package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import model.Product;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Product> products;
    private String img;

    public ProductAdapter(Context context, int layout, List<Product> products, String img) {
        this.context = context;
        this.layout = layout;
        this.products = products;
        this.img = img;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView textView;
        ImageView imgDelete, imgEdit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
//            viewHolder.textView = (TextView) view.findViewById(R.id.);
        }
        return null;
    }
}
