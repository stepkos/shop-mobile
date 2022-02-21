package com.example.shopapp;

public class CartItemModel {

    public static String getTableSchema() {
        return "CREATE TABLE cart_items (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "products_id INTEGER," +
                    "amount INTEGER" +
                ");";
    }

}
