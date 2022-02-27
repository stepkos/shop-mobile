package com.example.shopapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

}
