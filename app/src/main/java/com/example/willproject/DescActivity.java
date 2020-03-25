package com.example.willproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class DescActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);




        // Recieve data

        String name  = getIntent().getExtras().getString("name");
        String rating  = getIntent().getExtras().getString("rating");
        String image  = getIntent().getExtras().getString("image");


        // ini views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);


        TextView tv_name = findViewById(R.id.aa_anime_name);
        TextView tv_rating = findViewById(R.id.aa_rating);
        ImageView img = findViewById(R.id.aa_thumbnail);
        // setting values to each view

        tv_name.setText(name);
        tv_rating.setText(rating);
        collapsingToolbarLayout.setTitle(name);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        // set image using Glide
        Glide.with(this).load(image).apply(requestOptions).into(img);



    }
}
