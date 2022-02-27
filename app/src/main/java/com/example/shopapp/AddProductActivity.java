package com.example.shopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class AddProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<HashMap<String, Object>> products;
    CustomProductsAdapter customProductsAdapter;
    Spinner spinner;
    TextView description;
    TextView price;
    int chosenId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        setInitialStuff();
        setListeners();
    }

    protected void setInitialStuff() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price_for_one);
        products = ProductModel.getAll(getBaseContext());
        customProductsAdapter = new CustomProductsAdapter(getApplicationContext(), products);
        spinner.setAdapter(customProductsAdapter);
    }

    protected void setListeners() {

        findViewById(R.id.go_to_cart).setOnClickListener(x -> {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.add_to_cart).setOnClickListener(x -> {
            String amount = ((EditText) findViewById(R.id.amount)).getText().toString();
            String productId = String.valueOf(chosenId);
            Log.i("click", amount + " " + productId);

            SQLiteDatabase db = new DBConnector(getBaseContext()).getWritableDatabase();
//            db.execSQL("INSERT INTO products VALUES (null, 2, kuba, opis, 5.5)");
            db.execSQL(String.format("INSERT INTO cart VALUES (null, %s, %s)", productId, amount));
            db.close();

            Toast.makeText(this, "Added to cart: " + amount + " " + productId, Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        chosenId = position;
        Log.i("click", String.valueOf(position));

        description.setText((String) products.get(position).get("description"));
        price.setText(String.valueOf(products.get(position).get("price")));

        Toast.makeText(getApplicationContext(),"Chosen position: " + chosenId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}