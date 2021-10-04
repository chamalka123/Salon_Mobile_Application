package com.example.salonalrisha;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.salonalrisha.databinding.ActivityEditAppointmentBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class EditAppointment extends  AppCompatActivity{

    ActivityEditAppointmentBinding binding;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date=binding.dates.getText().toString();
                String time=binding.time.getText().toString();
                String service=binding.etService.getText().toString();
                String name=binding.name.getText().toString();
                String mobile=binding.mobile.getText().toString();

                updateData(date,time,service,name,mobile);
            }
        });

        }

    private void updateData(String date, String time, String service, String name, String mobile) {

        HashMap User =new HashMap();
        User.put("date",date);
        User.put("time",time);
        User.put("service",service);
        User.put("name",name);
        User.put("mobile",mobile);

        databaseReference= FirebaseDatabase.getInstance().getReference("Appointments");
        databaseReference.child(name).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()) {

                    binding.dates.setText("");
                    binding.time.setText("");
                    binding.etService.setText("");
                    binding.name.setText("");
                    binding.mobile.setText("");
                    Toast.makeText(EditAppointment.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditAppointment.this, "Failed to updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}







