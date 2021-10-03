package com.example.salonalrisha;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AverageCalcRating extends AppCompatActivity {
    private EditText total;
    private EditText count;
    private Button calculate;
    private TextView average;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average_calc_rating);

        average = (TextView) findViewById(R.id.averageRate);
        calculate = (Button) findViewById(R.id.calculateAvgRating);
        total = (EditText) findViewById(R.id.editTextTotal);
        count = (EditText) findViewById(R.id.editTextCount);
    }

    @Override
    protected void onResume() {
        super.onResume();
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcAverageRate();
            }
        });
    }
    private void calcAverageRate(){
        float totalStars = Float.parseFloat(total.getText().toString());
        float userRates = Float.parseFloat(count.getText().toString());
        float averageRate = (userRates / totalStars)*100;
        average.setText(String.valueOf(averageRate));
    }
}