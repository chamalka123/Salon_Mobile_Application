package com.example.salonalrisha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button)findViewById(R.id.home_products);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Product_lists.class);
                startActivity(intent);
            }
        });
        final Button button1 = (Button)findViewById(R.id.home_services);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Services_lists.class);
                startActivity(intent);
            }
        });

        final Button feedback = (Button)findViewById(R.id.home_ratings);
        feedback.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddFeedback.class);
                startActivity(intent);
            }
        });
    }
}