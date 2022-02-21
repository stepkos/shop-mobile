package com.example.shopapp;

public class ProductModel {

    public static String getTableSchema() {
        return "CREATE TABLE products (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "image_id INTEGER," +
                    "name TEXT," +
                    "description TEXT," +
                    "price FLOAT" +
                ");";
    }

}
