package com.example.fetchshopdata;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView nameText = findViewById(R.id.itemName);
        TextView shortDesText = findViewById(R.id.itemShortDes);
        TextView priceText = findViewById(R.id.itemPrice);
        final ImageView imgPath = findViewById(R.id.imgView);




        Bundle bundle = getIntent().getExtras();
        final Item item = bundle.getParcelable("com.example.fetchshopdata.Item");
        setTitle(item.name + " Detay");


        nameText.setText(item.getName());
        shortDesText.setText(item.getShortDescription());
        priceText.setText(String.valueOf(item.getPrice()) + " ₺");
        final String url = "https://shopapp2.azurewebsites.net" + item.getImgPath();

        Picasso.with(getApplicationContext()).load(url).into(imgPath);

        final ImagePopup imagePopup = new ImagePopup(this);
        imagePopup.setWindowHeight(800); // Optional
        imagePopup.setWindowWidth(800); // Optional
        imagePopup.setBackgroundColor(Color.BLACK);  // Optional
        imagePopup.setFullScreen(true); // Optional
        imagePopup.setHideCloseIcon(true);  // Optional
        imagePopup.setImageOnClickClose(true);  // Optional

        imagePopup.initiatePopup(imgPath.getDrawable());



        imgPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePopup.initiatePopupWithPicasso(url);
                imagePopup.viewPopup();


            }
        });
        Button fab = findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sepete Eklendi!", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });






    }

}
