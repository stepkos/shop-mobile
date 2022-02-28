package com.example.shopapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class CartItemModel {

    public static final String TABLE_NAME = "cart_items";

    public static String getTableSchema() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "products_id INTEGER," +
                    "amount INTEGER" +
                ");";
    }

    public static void addProducts(Context context, int product_id, int amount) {
        SQLiteDatabase db = new DBConnector(context).getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("products_id", product_id);
        cv.put("amount", amount);
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public static ArrayList<HashMap<String, Object>> getAllProductsFromCart(Context context) {
        SQLiteDatabase db = new DBConnector(context).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " INNER JOIN products ON products.id = cart_items.products_id", new String[] {});
        ArrayList<HashMap<String, Object>> result = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                result.add(new HashMap<String, Object>() {{
                    put("image_id", cursor.getInt(cursor.getColumnIndexOrThrow("image_id")));
                    put("name", cursor.getString(cursor.getColumnIndexOrThrow("name")));
                    put("description", cursor.getString(cursor.getColumnIndexOrThrow("description")));
                    put("price", cursor.getInt(cursor.getColumnIndexOrThrow("price")));
                    put("amount", cursor.getInt(cursor.getColumnIndexOrThrow("amount")));
                }});
            } while(cursor.moveToNext());
        }
        db.close();

        return result;
    }

    public static void cleanCart(Context context) {
        SQLiteDatabase db = new DBConnector(context).getReadableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
    }

}
