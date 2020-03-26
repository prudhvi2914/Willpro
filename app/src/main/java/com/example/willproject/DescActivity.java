package com.example.willproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Calendar;

public class DescActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    private TextView datetxt;
    private Button datebtn;
    Button schedulebtn;
    RadioGroup rg;
    RadioButton r1,r2,r3,r4,r5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        datebtn = findViewById(R.id.datebtn);
        datetxt = findViewById(R.id.datetxt);
        r1 = findViewById(R.id.one);
        r2 = findViewById(R.id.two);
        r3 = findViewById(R.id.three);
        r4 = findViewById(R.id.four);
        r5 = findViewById(R.id.five);
        rg = findViewById(R.id.radio);

        schedulebtn  = findViewById(R.id.schedule);
        schedulebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                datebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDatePickerDialog();
                    }
                });

//                int i = rg.getCheckedRadioButtonId();
//                RadioButton rb = (RadioButton) rg.findViewById(i);
//                Toast.makeText(getApplicationContext(),"you " + rb.getText().toString(),Toast.LENGTH_LONG).show();
                if(r1.isChecked()){
                 // textview.settext("fdvf");
                    
                }


            }
        });







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


    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //this gets the date that was selected
        String date = ""+ month +"/"+dayOfMonth + "/"+year;
        datetxt.setText(date);

    }
}
