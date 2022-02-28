package com.example.shopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {

    ArrayList<HashMap<String, Object>> cartProducts;
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
        cartProducts = CartItemModel.getAllProductsFromCart(getBaseContext());
        cartItems = findViewById(R.id.cart_items);
        customProductsAdapter = new CustomProductsAdapter(getApplicationContext(), cartProducts);
        cartItems.setAdapter(customProductsAdapter);
    }

    protected void setListeners() {

        findViewById(R.id.go_to_shop).setOnClickListener(x -> {
            Intent intent = new Intent(this, AddProductActivity.class);
            startActivity(intent);
        });

    }
}