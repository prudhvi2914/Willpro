

package com.example.willproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.willproject.DataBaseHelper.TABLE_NAME;

public class ProfileUpdate extends AppCompatActivity {


    EditText upname,upemail,upphone,uppassword;
    Button updatebtn;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        upname = findViewById(R.id.upname);
        upemail = findViewById(R.id.upemail);
        upphone = findViewById(R.id.upphone);
        uppassword = findViewById(R.id.uppassword);
        updatebtn = findViewById(R.id.update);

        openHelper = new DataBaseHelper(this);

updatebtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String name = upname.getText().toString();
        String email = upemail.getText().toString();
        String phone = upphone.getText().toString();
        String password = uppassword.getText().toString();
        db = openHelper.getWritableDatabase();
        readFromDataBase();
        updateData(name,email,phone,password);
        Toast.makeText(ProfileUpdate.this,"ProfileUpadted",Toast.LENGTH_LONG).show();

    }
});

    }
    public void updateData(String name,String email,String phone,String password){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.COL_2,name);
        contentValues.put(DataBaseHelper.COL_4,email);
        contentValues.put(DataBaseHelper.COL_3,phone);
        contentValues.put(DataBaseHelper.COL_5,password);
        long id = db.insert(DataBaseHelper.TABLE_NAME,null,contentValues);


    }
    public void readFromDataBase(){


        db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM registration",null);
        while (cursor.moveToNext()){
            upname.setText(cursor.getString(cursor.getColumnIndex("Name")));
            upemail.setText(cursor.getString(cursor.getColumnIndex("Phone")));
            upphone.setText(cursor.getString(cursor.getColumnIndex("Gmail")));
            uppassword.setText(cursor.getColumnIndex(""));

          //  txtDOB.setText(cursor.getString(cursor.getColumnIndex("dob")));
            break;

        }

    }

}
