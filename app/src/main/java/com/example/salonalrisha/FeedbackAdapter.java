package com.example.salonalrisha;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

    public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.feedbackViewHolder> {

        Context context;
        ArrayList<Feedback> list;

        public FeedbackAdapter(Context context, ArrayList<Feedback> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public feedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.feedback_lists, parent, false);
            return new feedbackViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull FeedbackAdapter.feedbackViewHolder holder, int position) {
            Feedback feedback = list.get(position);
            holder.name.setText(feedback.getName());
            holder.email.setText(feedback.getEmail());
            holder.review.setText(feedback.getComment());
            Glide.with(context)
                    .asBitmap().load(feedback.getImage())
                    .into(holder.image);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public static class feedbackViewHolder extends RecyclerView.ViewHolder{
            CircleImageView image;
            TextView name, email, review;
            RelativeLayout parentLayout;

            public feedbackViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.image_name);
                email = itemView.findViewById(R.id.feedback_email);
                review = itemView.findViewById(R.id.feedback_review);
                image = itemView.findViewById(R.id.image_feedback);
                parentLayout = itemView.findViewById(R.id.parent_layout);
            }
        }
    }


