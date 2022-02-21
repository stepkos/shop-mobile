package com.example.shopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String [] descriptions = {
            "Game X G100, Core i5-10400F, 16 GB, GTX 1060, 512 GB M.2 PCIe Windows 10 Home", // 3645 zl
            "Game X G300, Core i5-10400F, 16 GB, RTX 3060, 1 TB M.2 PCIe", // 5349 zl
            "Game X Powered By NZXT G700 White, Core i9-10850K, 16 GB, RTX 3080 Ti, 1 TB M.2 PCIe 1 TB HDD Windows 11 Home" // 15499
    };

    Spinner spinner;

    int [] pcs = {
            R.drawable.set1,
            R.drawable.set2,
            R.drawable.set3
    };

    int chosenId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, descriptions);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        CustomProductsAdapter customProductsAdapter = new CustomProductsAdapter(getApplicationContext(), pcs ,descriptions);
        spinner.setAdapter(customProductsAdapter);

        findViewById(R.id.go_to_cart).setOnClickListener(x -> {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.add_to_cart).setOnClickListener(x -> {
            String amount = ((EditText) findViewById(R.id.amount)).getText().toString();
            String productId = String.valueOf(chosenId);

            Log.i("click", amount + " " + productId);
            Toast.makeText(this, amount + " " + productId, Toast.LENGTH_SHORT).show();

            SQLiteDatabase db = new DBConnector(getBaseContext()).getWritableDatabase();
            db.execSQL(String.format("INSERT INTO cart VALUES (null, %s, %s)", productId, amount));
            db.close();

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        chosenId = position;
        Log.i("click", String.valueOf(position));
        Toast.makeText(getApplicationContext(),"chosen position: " + chosenId,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}