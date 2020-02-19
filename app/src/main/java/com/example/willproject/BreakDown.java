package com.example.willproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BreakDown extends AppCompatActivity {
    EditText entertext;
    TextView renttext,food,utility,tax,save,retire,insurance;
    Button btn,breakdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_down);

        entertext= findViewById(R.id.input);
        renttext = findViewById(R.id.rent);
        food = findViewById(R.id.food);
        utility = findViewById(R.id.utilities);
        tax = findViewById(R.id.tax);
        save = findViewById(R.id.save);
        retire = findViewById(R.id.retire);
        insurance = findViewById(R.id.insurance);
        btn = findViewById(R.id.cal);
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




            }
        });

    }
}
