package com.example.salonalrisha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmployeeMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main);

        final Button btnRegister = (Button)findViewById(R.id.btnEmpRegister);
        btnRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterEmployeeActivity.class);
                startActivity(intent);
            }
        });
        final Button btnLogin = (Button)findViewById(R.id.btnEmpLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EmployeeLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}