package com.example.shopapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
                getCVFromData(R.drawable.set1, "Komputer Game X G100", "Core i5-10400F, 16 GB, GTX 1060, 512 GB M.2 PCIe", 3699),
                getCVFromData(R.drawable.set2, "Komputer Game X G300", "Core i5-10400F, 16 GB, RTX 3060, 1 TB M.2 PCIe", 5349),
                getCVFromData(R.drawable.set3, "Komputer Game X G700", "Core i9-10850K, 16 GB, RTX 3080 Ti, 1 TB M.2 PCIe", 15499),
                getCVFromData(R.drawable.keyboard1, "Klawiatura SPC Gear GK630K", "Przelaczniki mechaniczne - Kailh Blue, Podswietlenie RGB", 279),
                getCVFromData(R.drawable.mouse1, "Mysz SteelSeries Rival 600", "12000 DPI, 7 Przyciskow, TrueMove3+ Dual Sensor System", 349)
        ));
    }

    public static ArrayList<HashMap<String, Object>> getAll(Context context) {
        SQLiteDatabase db = new DBConnector(context).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, new String[] {});
        ArrayList<HashMap<String, Object>> result = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                result.add(new HashMap<String, Object>() {{
                    put("image_id (Integer)", cursor.getInt(0));
                    put("name (String)", cursor.getString(1));
                    put("description (String)", cursor.getString(2));
                    put("price (Integer)", cursor.getInt(3));
                }});
            } while(cursor.moveToNext());
        }
        db.close();

        return result;
    }

}
