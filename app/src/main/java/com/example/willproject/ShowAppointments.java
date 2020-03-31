package com.example.willproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowAppointments extends AppCompatActivity {


    TextView t1,t2,t3;
  DataBaseHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_appointments);
        t1 = findViewById(R.id.tt1);
        t1 = findViewById(R.id.tt2);
        t1 = findViewById(R.id.tt3);

        dataBaseHelper = new DataBaseHelper(this);
        fillProfileFromDatabase();


    }

    private void fillProfileFromDatabase() {

try {


    sqLiteDatabase = dataBaseHelper.getReadableDatabase();
    Cursor cursor = dataBaseHelper.getlistofbookings(sqLiteDatabase);

    while (cursor.moveToNext()) {
        t1.setText(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_2)));
        t2.setText(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_7)));
        t3.setText(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_8)));


        break;

    }

}catch (Exception e){

}


    }
}
