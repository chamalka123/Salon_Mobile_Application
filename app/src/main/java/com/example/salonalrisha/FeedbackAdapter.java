package com.example.salonalrisha;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.ViewHolder;

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
                    .asBitmap().load(feedback.getUrl())
                    .into(holder.image);


            holder.btnEditFeedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final DialogPlus dialogPlus = DialogPlus.newDialog(holder.image.getContext())
                            .setContentHolder(new ViewHolder(R.layout.update_feedback))
                            .setExpanded(true, 1200)
                            .create();

                    dialogPlus.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public static class feedbackViewHolder extends RecyclerView.ViewHolder{
            CircleImageView image;
            TextView name, email, review;
            RelativeLayout parentLayout;
            Button btnEditFeedback;

            public feedbackViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.image_name);
                email = itemView.findViewById(R.id.feedback_email);
                review = itemView.findViewById(R.id.feedback_review);
                image = itemView.findViewById(R.id.image_feedback);
                btnEditFeedback = itemView.findViewById(R.id.btnEditFeedback);
                parentLayout = itemView.findViewById(R.id.parent_layout);
            }
        }
    }


