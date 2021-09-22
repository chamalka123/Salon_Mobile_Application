package com.example.salonalrisha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class Services_lists extends AppCompatActivity {

    RecyclerView recyclerView;
    ServiceAdapter serviceAdapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_lists);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);


        FirebaseRecyclerOptions<Services> options =
                new FirebaseRecyclerOptions.Builder<Services>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Services"), Services.class)
                        .build();

        serviceAdapter = new ServiceAdapter(options);
        recyclerView.setAdapter(serviceAdapter);

    floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),AddService.class));
        }
    });
}
    @Override
    protected void onStart() {
        super.onStart();
        serviceAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        serviceAdapter.stopListening();
    }
}