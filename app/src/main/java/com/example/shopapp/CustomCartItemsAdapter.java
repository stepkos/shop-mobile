package com.example.shopapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomCartItemsAdapter extends BaseAdapter {

    Context context;
    ArrayList<HashMap<String, Object>> products;

    LayoutInflater layoutInflater;
    ImageView imageView;
    TextView name;
    TextView price;
    TextView amount;
    ImageButton deleteCartItem;

    public CustomCartItemsAdapter(Context context, ArrayList<HashMap<String, Object>> products) {
        super();
        this.context = context;
        this.products = products;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int p) {
        return null;
    }

    @Override
    public long getItemId(int p) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.cart_item,null);
        imageView = convertView.findViewById(R.id.image_view_cart_item);
        name = convertView.findViewById(R.id.text_view_cart_item);
        price = convertView.findViewById(R.id.cart_item_price);
        amount = convertView.findViewById(R.id.cart_item_amount);
        deleteCartItem = convertView.findViewById(R.id.delete_item);

        imageView.setImageResource((int) products.get(position).get("image_id"));
        name.setText((String) products.get(position).get("name"));
        price.setText(products.get(position).get("price") + " PLN");
        amount.setText("X" + products.get(position).get("amount"));

        setClickListener(deleteCartItem, position, parent);
        return convertView;
    }

    private void setClickListener(View view, final int position, final ViewGroup parent) {
        view.setOnClickListener(v -> {
            ((ListView) parent).performItemClick(v, position, 0);
        });
    }
}
