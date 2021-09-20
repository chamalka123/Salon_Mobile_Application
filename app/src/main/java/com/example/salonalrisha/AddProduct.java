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

public class AddProduct extends AppCompatActivity {

    EditText name,price,brand,category,image;
    Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        name=(EditText)findViewById(R.id.product_name);
        price=(EditText)findViewById(R.id.product_price);
        brand=(EditText)findViewById(R.id.product_brand);
        category=(EditText)findViewById(R.id.product_category);
        image=(EditText)findViewById(R.id.image);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack=(Button)findViewById(R.id.btnBack);

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
    private  void  insertData()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("price", price.getText().toString());
        map.put("brand", brand.getText().toString());
        map.put("category", category.getText().toString());
        map.put("image", image.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Products").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddProduct.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddProduct.this, "Error Occured", Toast.LENGTH_SHORT).show();
                    }
                });



    }
private  void clearAll(){
        name.setText("");
        price.setText("");
        brand.setText("");
        category.setText("");
        image.setText("");
}
}