package com.example.willproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="registration";
    public static final String Table_Name1 = "User_image";
    public static final String COL_1="ID";
    public static final String COL_2="Name";
    public static final String COL_3="Phone";
    public static final String COL_4="Gmail";
    public static final String COL_5="Password";
    public static final String C_IMAGE = "IMAGE";
    public static final String C_ADDED_TIMESTAMP = "ADDED_TIME_STAMP";
    public static final String C_UPDATED_TIMESTAMP = "UPDATED_TIME_STAMP";
    public static final String COL_7="date";
    public static final String COL_8="radio";
    //------------------------------------------
    public static final String COL_9="rent";
    public static final String COL_10="food";
    public static final String COL_11="utility";
    public static final String COL_12="savings";
    public static final String COL_13="taxes";
    public static final String COL_14="retirement";
    public static final String COL_15="insurance";
    //-------------------------------------------
    public static final String COL_16="cardname";
    public static final String COL_17="cardnumber";
    public static final String COL_18="cardcvv";
    public static final String COL_19="cardexpiry";
    public static final String column0 = "UserImage";




    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Phone TEXT,Gmail TEXT UNIQUE,Password TEXT,date TEXT, radio TEXT,Last Text, rent Text,food Text,utility Text,savings Text,taxes Text,retirement Text,insurance Text, cardname Text,cardnumber Text,cardcvv Text,cardexpiry Text,ADDED_TIME_STAMP Text,UPDATED_TIME_STAMP Text,IMAGE TEXT)");
      //  db.execSQL("CREATE TABLE " + TBName_Book + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Date TEXT,Radio TEXT)");
        String User_image = "CREATE TABLE " + Table_Name1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,userImage image) ";
        db.execSQL(User_image);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
//        db.execSQL("DROP TABLE IF EXISTS " +TBName_Book);
//        onCreate(db);
    }

    public Cursor getlistofbookings(SQLiteDatabase db){


        String[] proj = {DataBaseHelper.COL_2,DataBaseHelper.COL_7,DataBaseHelper.COL_8};
        Cursor cursor = db.query(DataBaseHelper.TABLE_NAME,proj,null,null,null,null,null);

        return cursor;
    }
    //create insert image method
    public  boolean insertImage(byte[] userImage){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues0 = new ContentValues();
        contentValues0.put(column0,userImage);
        db.insert(Table_Name1,null,contentValues0);
        return true;
    }
    // Create a method to retrieve image from database
    public Bitmap getImage(ImageView id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap bt = null;
        Cursor cursor = db.rawQuery("select * from " + Table_Name1 + " where id = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            byte[] image = cursor.getBlob(1);
            bt = BitmapFactory.decodeByteArray(image, 0, image.length);
        }
        return bt;
    }

}
