package com.example.salonalrisha;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddService extends AppCompatActivity {

    EditText name, price, duration, category, image;
    Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        name = (EditText) findViewById(R.id.service_name);
        price = (EditText) findViewById(R.id.service_price);
        duration = (EditText) findViewById(R.id.service_duration);
        category = (EditText) findViewById(R.id.service_category);
        image = (EditText) findViewById(R.id.image);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void insertData() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("price", price.getText().toString());
        map.put("duration", duration.getText().toString());
        map.put("category", category.getText().toString());
        map.put("image", image.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Services").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddService.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddService.this, "Error Occured", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void clearAll() {
        name.setText("");
        price.setText("");
        duration.setText("");
        category.setText("");
        image.setText("");
    }
}