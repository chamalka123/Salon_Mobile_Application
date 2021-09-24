package com.example.salonalrisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feedbacks extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference db;
    FeedbackAdapter feedbackAdapter;
    ArrayList<Feedback> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacks);

        recyclerView = findViewById(R.id.recycler_view);
        db = FirebaseDatabase.getInstance().getReference("Feedbacks");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        feedbackAdapter = new FeedbackAdapter(this, list);
        recyclerView.setAdapter(feedbackAdapter);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Feedback feedback = dataSnapshot.getValue(Feedback.class);
                    list.add(feedback);
                }
                feedbackAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}