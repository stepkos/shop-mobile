package com.example.shopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {

    ArrayList<HashMap<String, Object>> cartItems;
    CustomCartItemsAdapter customCartItemsAdapter;
    ListView listView;
    TextView cartSum;

    String cartSumBaseText = "Koszt calkowity: %d PLN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        setInitialStuff();
        updateSum();
        setListeners();
    }

    protected void setInitialStuff() {
        cartSum = findViewById(R.id.cart_sum);
        cartItems = CartItemModel.getAllProductsFromCart(getBaseContext());
        listView = findViewById(R.id.cart_items);
        customCartItemsAdapter = new CustomCartItemsAdapter(getApplicationContext(), cartItems);
        listView.setAdapter(customCartItemsAdapter);
    }

    protected void setListeners() {

        findViewById(R.id.go_to_shop).setOnClickListener(x -> {
            Intent intent = new Intent(this, AddProductActivity.class);
            startActivity(intent);
        });

    }

    protected void updateSum() {
        int sum = 0;
        int price, amount;

        for (HashMap<String, Object> cartItem : cartItems) {
            price = (int) cartItem.get("price");
            amount = (int) cartItem.get("amount");
            sum += price * amount;
        }

        cartSum.setText(String.format(cartSumBaseText, sum));
    }
}