package com.example.willproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
    private Button datebtn,display;
    Button schedulebtn;
    RadioGroup rg;
    RadioButton r1,r2,r3,r4,r5;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
        openHelper = new DataBaseHelper(this);

        datebtn = findViewById(R.id.datebtn);
        datetxt = findViewById(R.id.datetxt);
        display = findViewById(R.id.display);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DescActivity.this,ShowAppointments.class);
                startActivity(intent);

            }
        });
        r1 = findViewById(R.id.one);
        r2 = findViewById(R.id.two);
        r3 = findViewById(R.id.three);
        r4 = findViewById(R.id.four);
        r5 = findViewById(R.id.five);
        rg = findViewById(R.id.radio);

        schedulebtn  = findViewById(R.id.schedule);
        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        schedulebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                int i = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) rg.findViewById(i);
               // Toast.makeText(getApplicationContext(),"you " + rb.getText().toString(),Toast.LENGTH_LONG).show();

                //writable
                db = openHelper.getWritableDatabase();
                try {
                String date = datetxt.getText().toString();
                String radio1 = rb.getText().toString().trim();

                    if (date.isEmpty() && radio1.isEmpty()) {
                        Toast.makeText(DescActivity.this, "Please select all the details", Toast.LENGTH_LONG).show();
                    } else {
                        insertDateTime(date, radio1);
                        Toast.makeText(DescActivity.this, "Appointment Scheduled", Toast.LENGTH_LONG).show();
                    }
            }catch (Exception e){

                Toast.makeText(DescActivity.this, "Please select all the details", Toast.LENGTH_LONG).show();
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
    public void insertDateTime(String date ,String radio1){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.COL_7,date);
        contentValues.put(DataBaseHelper.COL_8,radio1);

        long id = db.insert(DataBaseHelper.TABLE_NAME,null,contentValues);
    }
}
