package com.example.shopapp;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductModel {

    public static final String TABLE_NAME = "products";

    public static String getTableSchema() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "image_id INTEGER," +
                    "name TEXT," +
                    "description TEXT," +
                    "price INTEGER" +
                ");";
    }

    public static ContentValues getCVFromData(int image_id, String name, String description, int price) {
        ContentValues cv = new ContentValues();
        cv.put("image_id", image_id);
        cv.put("name", name);
        cv.put("description", description);
        cv.put("price", price);
        return cv;
    }

    public static ArrayList<ContentValues> getInitialData() {
        return new ArrayList<>(Arrays.asList(
                getCVFromData(R.drawable.set1, "Komputer 1", "opis 1", 3999),
                getCVFromData(R.drawable.set2, "Komputer 2", "opis 2", 7999),
                getCVFromData(R.drawable.set3, "Komputer 3", "opis 3", 11999)
        ));
    }

}
