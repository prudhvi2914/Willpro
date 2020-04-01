package com.example.willproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BreakDown extends AppCompatActivity {
    EditText entertext;
    TextView renttext,food,utility,tax,save,retire,insurance;
    Button btn,breakdown;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_down);
        openHelper = new DataBaseHelper(this);


        entertext= findViewById(R.id.input);
        renttext = findViewById(R.id.rent);
        food = findViewById(R.id.food);
        utility = findViewById(R.id.utilities);
        tax = findViewById(R.id.tax);
        save = findViewById(R.id.save);
        retire = findViewById(R.id.retire);
        insurance = findViewById(R.id.insurance);
        btn = findViewById(R.id.cal);
        breakdown = findViewById(R.id.showbrkdwn);
        breakdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(BreakDown.this,MapActivtiy.class);
                startActivity(i);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int amount = Integer.parseInt(entertext.getText().toString());
                //rent
                double rent = 0.3 * amount;

                renttext.setText(String.valueOf(rent));
                //food
                double foods = 0.2 * amount;
                food.setText(String.valueOf(foods));
                //utilities
                double utilites = 0.15 * amount;
                utility.setText(String.valueOf(utilites));
                //saving
                double saving = 0.05 * amount;
                save.setText(String.valueOf(saving));
                //taxes
                double taxes = 0.15 * amount;
                tax.setText(String.valueOf(taxes));
                //retirement
                double retirement = 0.05 * amount;
                retire.setText(String.valueOf(retirement));
                //Insurance
                double insurances = 0.3 * amount;
                insurance.setText(String.valueOf(insurances));

                try {
                //writable
                db = openHelper.getWritableDatabase();
                    String rent1 = renttext.getText().toString();
                    String food1 = food.getText().toString().trim();
                    String utility1 = utility.getText().toString();
                    String savings1 = save.getText().toString().trim();
                    String taxes1 = tax.getText().toString();
                    String retirement1 = retire.getText().toString().trim();
                    String insurance1 = insurance.getText().toString().trim();


                    if (rent1.isEmpty() && food1.isEmpty() && utility1.isEmpty() && savings1.isEmpty()
                            && taxes1.isEmpty()&& retirement1.isEmpty() && insurance1.isEmpty()) {
                        Toast.makeText(BreakDown.this, "Please select all the details", Toast.LENGTH_LONG).show();
                    } else {
                        insertDateTime(rent1, food1,utility1,savings1,taxes1,retirement1,insurance1);
                        Toast.makeText(BreakDown.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){

                    Toast.makeText(BreakDown.this, "Please select all the details", Toast.LENGTH_LONG).show();
                }




            }
        });

    }
    public void insertDateTime(String rent1 ,String food1,String utility1,String savings1,String taxes1,String retirement1,String insurance1){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.COL_9,rent1);
        contentValues.put(DataBaseHelper.COL_10,food1);
        contentValues.put(DataBaseHelper.COL_11,utility1);
        contentValues.put(DataBaseHelper.COL_12,savings1);
        contentValues.put(DataBaseHelper.COL_13,taxes1);
        contentValues.put(DataBaseHelper.COL_14,retirement1);
        contentValues.put(DataBaseHelper.COL_15,insurance1);


        long id = db.insert(DataBaseHelper.TABLE_NAME,null,contentValues);
    }
}
