package com.example.salonalrisha;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
public class FeedbackAdapter extends FirebaseRecyclerAdapter<Feedback, FeedbackAdapter.feedbackViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FeedbackAdapter(@NonNull FirebaseRecyclerOptions<Feedback> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FeedbackAdapter.feedbackViewHolder holder, final int position, @NonNull Feedback model) {
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.review.setText(model.getComment());
        holder.rating.setRating(model.getRating());
        Glide.with(holder.image.getContext())
                .load(model.getUrl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.image);

        holder.btnEditFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.image.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_feedback))
                        .setExpanded(true, 1200)
                        .create();

                //dialogPlus.show();
                View view = dialogPlus.getHolderView();

                EditText name = view.findViewById(R.id.Review_Person_name);
                EditText email = view.findViewById(R.id.feedback_email);
                EditText review = view.findViewById(R.id.feedback_review);

                Button btnUpdateFeedback = view.findViewById(R.id.btnUpdateFeedback);

                name.setText(model.getName());
                email.setText(model.getEmail());
                review.setText(model.getComment());

                dialogPlus.show(); //data fetched and display popup

                btnUpdateFeedback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("name", name.getText().toString());
                        map.put("email", email.getText().toString());
                        map.put("comment", review.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Feedbacks")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Error with updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });
        holder.btnDeleteFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted Feedback can't be undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Feedbacks")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.name.getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public feedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_lists, parent, false);
        return new feedbackViewHolder(v);
    }

    class feedbackViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView name, email, review;
        RatingBar rating;

        Button btnEditFeedback, btnDeleteFeedback;
        public feedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.image_name);
            email = (TextView) itemView.findViewById(R.id.feedback_email);
            review = (TextView) itemView.findViewById(R.id.feedback_review);
            image = (CircleImageView) itemView.findViewById(R.id.image_feedback);
            rating = (RatingBar) itemView.findViewById(R.id.ratingBar);
            btnEditFeedback = (Button) itemView.findViewById(R.id.btnEditFeedback);
            btnDeleteFeedback = (Button) itemView.findViewById(R.id.btnDeleteFeedback);
        }

    }
}