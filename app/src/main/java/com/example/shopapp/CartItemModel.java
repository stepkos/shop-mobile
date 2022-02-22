package com.example.shopapp;

public class CartItemModel {

    public static final String TABLE_NAME = "cart_items";

    public static String getTableSchema() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "products_id INTEGER," +
                    "amount INTEGER" +
                ");";
    }

}
