package com.example.salonalrisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddFeedback extends AppCompatActivity {

    //register components
    EditText txtName, txtEmail, txtReview;
    Button btnPost;
    RatingBar ratingBar;
    Feedback obFeedback;
    DatabaseReference db;

    //method to clear user input after submit
    private void clearControls(){
        txtName.setText("");
        txtEmail.setText("");
        txtReview.setText("");
        ratingBar.setRating((float) 0.0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

        //binding
        txtName = (EditText) findViewById(R.id.Review_Person_name);
        txtEmail = (EditText) findViewById(R.id.email);
        txtReview = (EditText) findViewById(R.id.review_message);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        btnPost = (Button) findViewById(R.id.btnPost);

        obFeedback = new Feedback();

        final Button avgCalc = (Button)findViewById(R.id.CalculateAverageFeedback);
        avgCalc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AverageCalcRating.class);
                startActivity(intent);
            }
        });
    }

    public  void Post(View view){
        db = FirebaseDatabase.getInstance().getReference().child("Feedbacks");

        try{
            if(TextUtils.isEmpty(txtName.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Please Enter Your Name", Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(txtEmail.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Please Enter Your Email", Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(txtReview.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"Please Give your Review", Toast.LENGTH_LONG).show();
            }else{
                obFeedback.setName(txtName.getText().toString().trim());
                obFeedback.setEmail(txtEmail.getText().toString().trim());
                obFeedback.setComment(txtReview.getText().toString().trim());
                obFeedback.setRating(ratingBar.getRating());
                db.push().setValue(obFeedback);

                Toast.makeText(getApplicationContext(), "Your Review Successfully Recorded", Toast.LENGTH_LONG).show();
                clearControls();
            }
        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Number Format Exception",Toast.LENGTH_LONG).show();
        }

    }
}