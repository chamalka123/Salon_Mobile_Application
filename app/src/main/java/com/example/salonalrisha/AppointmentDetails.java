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


public class AppointmentDetails extends AppCompatActivity {

    EditText name, address, email, password;
    Button registerUser;
    boolean isALLFieldsChecked =false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);

        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.et_password);
        registerUser= (Button) findViewById(R.id.registerUser);

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isALLFieldsChecked = CheckAllFields();
                if (isALLFieldsChecked) {
                    insertData();
                    clearAll();
                }
            }
        });
    }

    private void insertData() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("address", address.getText().toString());
        map.put("email", email.getText().toString());
        map.put("password", password.getText().toString());


        FirebaseDatabase.getInstance().getReference().child("Users").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AppointmentDetails.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AppointmentDetails.this, "Error Occured", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void clearAll() {
        name.setText("");
        address.setText("");
        email.setText("");
        password.setText("");

    }

    private boolean CheckAllFields(){
        if(name.length()==0){
            name.setError("This field is required");
            return false;
        }
        if(address.length()==0){
            address.setError("This field is required");
            return false;
        }
        if(email.length()==0){
            email.setError("This field is required");
            return false;
        }
        if(password.length()==0){
            password.setError("This field is required");
            return false;
        }
        if(password.length()<6){
            password.setError("Min Password Lenght should be 6 character");
            password.requestFocus();
            return false;
        }
        return  true;
    }


    }
