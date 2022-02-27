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

public class CartActivity extends AppCompatActivity {

    ListView cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        findViewById(R.id.go_to_shop).setOnClickListener(x -> {
            Intent intent = new Intent(this, AddProductActivity.class);
            startActivity(intent);
        });

        String [] descriptions = {
                "Game X G100, Core i5-10400F, 16 GB, GTX 1060, 512 GB M.2 PCIe Windows 10 Home", // 3645 zl
                "Game X G300, Core i5-10400F, 16 GB, RTX 3060, 1 TB M.2 PCIe", // 5349 zl
                "Game X Powered By NZXT G700 White, Core i9-10850K, 16 GB, RTX 3080 Ti, 1 TB M.2 PCIe 1 TB HDD Windows 11 Home" // 15499
        };
        int [] pcs = {
                R.drawable.set1,
                R.drawable.set2,
                R.drawable.set3
        };

        ArrayList<ArrayList<Integer>> items = new ArrayList<>();

        SQLiteDatabase db = new DBConnector(getBaseContext()).getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM cart", new String[] {});
        if (c.moveToFirst()) {
            do {
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.add(c.getInt(0));
                tmp.add(c.getInt(1));
                tmp.add(c.getInt(2));
                items.add(tmp);
                Log.i("from_db", "0: " + String.valueOf(c.getInt(0)));
                Log.i("from_db", "1: " + String.valueOf(c.getInt(1)));
                Log.i("from_db", "2: " + String.valueOf(c.getInt(2)));
                Log.i("from_db", "------------------------------");
            } while(c.moveToNext());
        }
        db.close();

        ArrayList<Integer> ids = new ArrayList<>();
        for (ArrayList<Integer> e: items) {
            ids.add(e.get(1));
        }

        int [] myPcs = new int[ids.size()];
        String [] myDesc = new String[ids.size()];

        for (int i=0; i<ids.size(); i++) {
            myPcs[i] = pcs[ids.get(i)];
            myDesc[i] = descriptions[ids.get(i)];
        }

//        CustomProductsAdapter customProductsAdapter = new CustomProductsAdapter(getApplicationContext(), myPcs, myDesc);
        cartItems = findViewById(R.id.cart_items);
//        cartItems.setAdapter(customProductsAdapter);
    }
}