package com.example.salonalrisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddAppointment extends AppCompatActivity {

    EditText date, time, service, name, mobile;



    Button book;
    boolean isALLFieldsChecked =false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        date = (EditText) findViewById(R.id.dates);
        time = (EditText) findViewById(R.id.time);
        service = (EditText) findViewById(R.id.etService);
        name = (EditText) findViewById(R.id.name);
        mobile = (EditText) findViewById(R.id.mobile);

        book = (Button) findViewById(R.id.book);

        book.setOnClickListener(new View.OnClickListener() {
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

    public void openEdit(View view) {
        Intent intent = new Intent(this, EditAppointment.class);
        startActivity(intent);
    }


    public void openUpcome(View view) {
        Intent intent = new Intent(this, UpcomingActivity.class);
        startActivity(intent);
    }

    private void insertData() {
        Map<String, Object> map = new HashMap<>();
        map.put("date", date.getText().toString());
        map.put("time", time.getText().toString());
        map.put("service", service.getText().toString());
        map.put("name", name.getText().toString());
        map.put("mobile", mobile.getText().toString());


        FirebaseDatabase.getInstance().getReference().child("Appointments").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddAppointment.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddAppointment.this, "Error Occured", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearAll() {
        date.setText("");
        time.setText("");
        service.setText("");
        name.setText("");
        mobile.setText("");
    }

    private boolean CheckAllFields() {
        if (date.length() == 0) {
            date.setError("This field is required");
            return false;
        }
        if (time.length() == 0) {
            time.setError("This field is required");
            return false;
        }
        if (service.length() == 0) {
            service.setError("This field is required");
            return false;
        }
        if (name.length() == 0) {
            name.setError("This field is required");
            return false;
        }
        if (mobile.length() == 0) {
            mobile.setError("This field is required");
            return false;
        }
        return true;

    }

}

