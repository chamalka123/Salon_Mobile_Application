package com.example.salonalrisha;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SingleProduct extends AppCompatActivity {
    private static final String TAG = "SingleProduct";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_product);
        Log.d(TAG, "onCreate: started.");
    }

}
