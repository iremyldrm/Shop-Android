package com.example.fetchshopdata;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.List;


public class CustomAdapter extends BaseAdapter {
    private LayoutInflater userInflater;
    private List<Item> itemList;
    private Context context;



    public CustomAdapter( Context context,List<Item> itemList) {
        super();

        userInflater = LayoutInflater.from(context);
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View lineView;
        lineView = userInflater.inflate(R.layout.custom_layout, null);
        TextView textViewName = (TextView) lineView.findViewById(R.id.textViewName);
        ImageView imageViewUserPicture = (ImageView) lineView.findViewById(R.id.imageViewUserPicture);
        TextView textVievPrice = (TextView) lineView.findViewById(R.id.textViewPrice);


        Item item = itemList.get(i);
        String url = "https://shopapp2.azurewebsites.net" + item.getImgPath();
        textViewName.setText(item.getName());
        textVievPrice.setText(String.valueOf(item.getPrice()) + " ₺");
        Picasso.with(context).load(url).into(imageViewUserPicture);



        return lineView;
    }
}