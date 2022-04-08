package com.example.btl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import model.Product;

public class Sqlite extends SQLiteOpenHelper {
    String allColumns[] = {"p_id, p_name, p_quantity, p_price, p_img"};

    public Sqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "AppElectronicsDevicesSale.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS PRODUCT(p_id INTEGER PRIMARY KEY AUTOINCREMENT, p_name VARCHAR(50)," +
                "p_quantity INTEGER, p_price DOUBLE, p_img VARCHAR(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PRODUCT");
    }

    // ko tra ket qua
    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    // tra ket qua
    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public boolean insertProduct(Product product) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("p_id", product.getId());
        contentValues.put("p_name", product.getName());
        contentValues.put("p_quantity", product.getQuantity());
        contentValues.put("p_price", product.getPrice());
        contentValues.put("p_img", product.getImage());
        database.insert("product", null, contentValues);
        database.close();
        return true;
    }

    public boolean updateProduct(Product product) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("p_name", product.getName());
        contentValues.put("p_quantity", product.getQuantity());
        contentValues.put("p_price", product.getPrice());
        contentValues.put("p_img", product.getImage());
        database.update("product", contentValues,
                "where p_id = " + product.getId(), null);
        database.close();
        return true;
    }

    public List<Product> getAllProduct() {
        SQLiteDatabase database = getReadableDatabase();
        List<Product> products = new ArrayList<>();
        products.clear();
        Cursor cursor = database.query("product", allColumns, null,
                null, null, null, null);
        Log.e("Size", cursor.getCount() + " ");
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
