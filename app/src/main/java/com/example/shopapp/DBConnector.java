package com.example.shopapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnector extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "shop.db";
    public static final int DATABASE_VERSION = 2;

    public DBConnector(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProductModel.getTableSchema());
        db.execSQL(CartItemModel.getTableSchema());

        for (ContentValues cv : ProductModel.getInitialData())
            db.insert(ProductModel.TABLE_NAME, null, cv);

//        try {

//            for(ProductModel product : initialProducts)
//                db.insert(ProductModel.TABLE, null, product.constructCV());
//
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
