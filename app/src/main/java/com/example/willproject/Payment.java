package com.example.willproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Payment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

EditText name,number,cvv,expiry;
Button save;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        openHelper = new DataBaseHelper(this);

        name = findViewById(R.id.etname);
        number = findViewById(R.id.etnumber);
        cvv = findViewById(R.id.etcvv);
        expiry = findViewById(R.id.etexpiry);
        save = findViewById(R.id.etsave);


        expiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //writable
                db = openHelper.getWritableDatabase();
                try {
                    String cardname = name.getText().toString().trim();
                    String cardnumber = number.getText().toString().trim();
                    String cardcvv = cvv.getText().toString();
                    String cardexpiry = expiry.getText().toString().trim();

                    if (cardname.isEmpty() && cardnumber.isEmpty() && cardcvv.isEmpty() && cardexpiry.isEmpty()) {
                        Toast.makeText(Payment.this, "Please select all the details", Toast.LENGTH_LONG).show();
                    } else {
                        insertDateTime(cardname, cardnumber,cardcvv,cardexpiry);
                        Toast.makeText(Payment.this, "Payment Info Updated", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){

                    Toast.makeText(Payment.this, "Please select all the details", Toast.LENGTH_LONG).show();
                }

            }
        });






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
        expiry.setText(date);

    }
    public void insertDateTime(String cardname ,String cardnumber,String cardcvv,String cardexpiry){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.COL_16,cardname);
        contentValues.put(DataBaseHelper.COL_17,cardnumber);
        contentValues.put(DataBaseHelper.COL_18,cardcvv);
        contentValues.put(DataBaseHelper.COL_19,cardexpiry);

        long id = db.insert(DataBaseHelper.TABLE_NAME,null,contentValues);
    }
}
