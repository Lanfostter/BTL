package dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.widget.EditText;

import com.example.btl.MainActivity;
import com.example.btl.R;
import com.example.btl.Sqlite;

import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDaoImpl implements ProductDao {
    Sqlite sqlite = new Sqlite(null, "App Electronics Devices Sale", null, 1);
    String allColumns[] = {"p_id, p_name, p_quantity, p_price, p_img"};

    @Override
    public boolean insertProduct(Product product) {
        SQLiteDatabase database = sqlite.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("p_id", product.getId());
        contentValues.put("p_name", product.getName());
        contentValues.put("p_quantity", product.getQuantity());
        contentValues.put("p_price", product.getPrice());
        contentValues.put("p_img", product.getImage());
        database.insert("product", null, contentValues);
        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        SQLiteDatabase database = sqlite.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("p_name", product.getName());
        contentValues.put("p_quantity", product.getQuantity());
        contentValues.put("p_price", product.getPrice());
        contentValues.put("p_img", product.getImage());
        database.update("product", contentValues,
                "where p_id = " + product.getId(), null);
        return true;
    }

    @Override
    public boolean deleteProduct(Product product) {
        return false;
    }

    @Override
    public List<Product> getAllProduct() {
        SQLiteDatabase database = sqlite.getReadableDatabase();
        List<Product> products = new ArrayList<>();
        Cursor cursor = database.query("product", allColumns, null,
                null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product product = cursorToProduct(cursor);
            products.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return products;
    }

    private Product cursorToProduct(Cursor cursor) {
        Product product = new Product();
        product.setId(cursor.getInt(0));
        product.setName(cursor.getString(1));
        product.setQuantity(cursor.getInt(2));
        product.setPrice(cursor.getDouble(3));
        product.setImage(cursor.getString(4));
        return product;
    }
}
