

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
    DataBaseHelper dataBaseHelper;
    SQLiteDatabase db;
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

        dataBaseHelper = new DataBaseHelper(this);

updatebtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String name = upname.getText().toString();
        String email = upemail.getText().toString();
        String phone = upphone.getText().toString();
        String password = uppassword.getText().toString();
        db = dataBaseHelper.getWritableDatabase();
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


        db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(("SELECT * FROM TABLE_NAME"), null);
try {


    while (cursor.moveToNext()) {
        upname.setText(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_2)));

        upemail.setText(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_4)));
        upphone.setText(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_3)));
        uppassword.setText(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_5)));


        //  txtDOB.setText(cursor.getString(cursor.getColumnIndex("dob")));
        break;

    }
}catch (Exception e){}

    }



}

//
//    private void fillProfileFromDatabase() {
//        try {
//
//            TourBuddyDB = dbHelper.getReadableDatabase();
//            Cursor cursor = TourBuddyDB.rawQuery(("SELECT email, phone, password, name, dob " +
//                    "FROM "+ TBName_User +" WHERE email LIKE '"+ userEmail +"'"), null);
//            while (cursor.moveToNext()){
//                edtName.setText(cursor.getString(cursor.getColumnIndex("name")));
//                edtPhone.setText(cursor.getString(cursor.getColumnIndex("phone")));
//                edtEmail.setText(cursor.getString(cursor.getColumnIndex("email")));
//                edtPassword.setText(cursor.getString(cursor.getColumnIndex("password")));
//
//                txtDOB.setText(cursor.getString(cursor.getColumnIndex("dob")));
//                break;
//
//            }
//
//        }catch (Exception e){
//            Log.e("ProfileActivity", e.getMessage());
//        }finally{
//            TourBuddyDB.close();
//        }
//    }
