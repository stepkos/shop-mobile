package com.example.shopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {

    ArrayList<HashMap<String, Object>> products;
    CustomProductsAdapter customProductsAdapter;
    ListView cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        setInitialStuff();
        setListeners();
    }

    protected void setInitialStuff() {
        products = ProductModel.getAll(getBaseContext());
        cartItems = findViewById(R.id.cart_items);
        customProductsAdapter = new CustomProductsAdapter(getApplicationContext(), products);
        cartItems.setAdapter(customProductsAdapter);
    }

    protected void setListeners() {

        findViewById(R.id.go_to_shop).setOnClickListener(x -> {
            Intent intent = new Intent(this, AddProductActivity.class);
            startActivity(intent);
        });

    }
}