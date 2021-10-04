package com.example.salonalrisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button logIn;
    boolean isALLFieldsChecked =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.et_password);

        logIn = (Button) findViewById(R.id.logIn);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isALLFieldsChecked = CheckAllFields();
                if (isALLFieldsChecked) {
                    insertData();
                    clearAll();
                }
            }
           /* btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   startActivity(new Intent(getApplicationContext(),RegisterUser.class));
                }*/


        });
    }

        public void openRegisters(View view) {
            Intent intent = new Intent(this, AppointmentDetails.class);
            startActivity(intent);
        }


    private void insertData() {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email.getText().toString());
        map.put("password", password.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Login").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(LoginActivity.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(LoginActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearAll() {
        email.setText("");
        password.setText("");
    }

    private boolean CheckAllFields() {
        if (email.length() == 0) {
            email.setError("This field is required");
            return false;
        }
        if (password.length() == 0) {
            password.setError("This field is required");
            return false;
        }
        if (password.length() < 6) {
            password.setError("Invalid Password ");
            password.requestFocus();
            return false;
        }
        return true;
    }


}