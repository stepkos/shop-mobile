package com.example.shopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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

    int orderTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, descriptions);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        MyAdapter myAdapter = new MyAdapter(getApplicationContext(), pcs ,descriptions);
        spinner.setAdapter(myAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        switch (position) {
            case 0: orderTotal = 3824; break;
            case 1: orderTotal = 5832; break;
            case 2: orderTotal = 7764; break;
            default:;
        }
        Toast.makeText(getApplicationContext(),descriptions[position]+" "+orderTotal,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}