package com.example.shopapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomProductsAdapter extends BaseAdapter {

    Context context;
    int[] pcs;
    String[] descriptions;
    LayoutInflater layoutInflater;
    ImageView imageView;
    TextView textView;

    public CustomProductsAdapter(Context context, int pcs[], String[] descriptions) {
        super();
        this.context = context;
        this.pcs = pcs;
        this.descriptions = descriptions;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return pcs.length;
    }

    @Override
    public Object getItem(int p) {
        return null;
    }

    @Override
    public long getItemId(int p) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.spinner_item,null);
        imageView = convertView.findViewById(R.id.image_view);
        textView = convertView.findViewById(R.id.textView);
        imageView.setImageResource(pcs[position]);
        textView.setText(descriptions[position]);
        return convertView;
    }
}
