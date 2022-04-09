package sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Product;

public class Sqlite extends SQLiteOpenHelper {
    String allColumns[] = {"p_id, p_name, p_quantity, p_price, p_img"};
    String allColumnsA[] = {"u_id, u_username, u_password, u_role"};

    public Sqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "AppElectronicsDevicesSale.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS PRODUCT(p_id VARCHAR(50) PRIMARY KEY, p_name VARCHAR(50)," +
                "p_quantity INTEGER, p_price VARCHAR(50), p_img VARCHAR(255))");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ACCOUNT(u_id INTEGER PRIMARY KEY, u_username VARCHAR(50), " +
                "u_password VARCHAR(50), u_role VARCHAR(50))");
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
        database.insert("PRODUCT", null, contentValues);
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
                product.getId(), null);
        database.close();
        return true;
    }

    public boolean deleteProduct(Product product) {
        SQLiteDatabase database = getReadableDatabase();
        database.delete("Product", product.getId(), null);
        return true;
    }

    public List<Product> getAllProduct() {
        SQLiteDatabase database = getReadableDatabase();
        List<Product> products = new ArrayList<>();
        products.clear();
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
        product.setId(cursor.getString(0));
        product.setName(cursor.getString(1));
        product.setQuantity(cursor.getInt(2));
        product.setPrice(cursor.getString(3));
        product.setImage(cursor.getString(4));
        return product;
    }

    public boolean insertAccount(Account account) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("u_id", account.getId());
        contentValues.put("u_username", account.getUsername());
        contentValues.put("u_password", account.getPassword());
        contentValues.put("u_role", account.getRole());
        database.insert("ACCOUNT", null, contentValues);
        database.close();
        return true;
    }

    public boolean updateAccount(Account account) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("u_username", account.getUsername());
        contentValues.put("u_password", account.getPassword());
        contentValues.put("u_role", account.getRole());
        database.update("ACCOUNT", contentValues, String.valueOf(account.getId()), null);
        database.close();
        return true;
    }

    public List<Account> getAllAccount() {
        SQLiteDatabase database = getReadableDatabase();
        List<Account> accounts = new ArrayList<>();
        accounts.clear();
        Cursor cursor = database.query("account", allColumnsA, null,
                null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Account account = cursorToAccount(cursor);
            accounts.add(account);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return accounts;
    }

    private boolean deleteAccount(Account account) {
        SQLiteDatabase database = getReadableDatabase();
        database.delete("ACCOUNT", String.valueOf(account.getId()), null);
        return true;
    }

    private Account cursorToAccount(Cursor cursor) {
        Account account = new Account();
        account.setId(cursor.getInt(0));
        account.setUsername(cursor.getString(1));
        account.setPassword(cursor.getString(2));
        account.setRole(cursor.getString(3));
        return account;
    }

}
