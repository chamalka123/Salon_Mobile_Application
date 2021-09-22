package com.example.salonalrisha;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class ServiceAdapter extends FirebaseRecyclerAdapter<Services,ServiceAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ServiceAdapter(@NonNull  FirebaseRecyclerOptions<Services> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ServiceAdapter.myViewHolder holder, final int position, @NonNull  Services model) {
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.duration.setText(model.getDuration());
       // holder.category.setText(model.getCategory());

        Glide.with(holder.image.getContext())
                .load(model.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.image);

    }
        @NonNull

        @Override
        public ServiceAdapter.myViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.servicelist,parent,false);
            return new ServiceAdapter.myViewHolder(view);
        }

        class myViewHolder extends RecyclerView.ViewHolder {
            ImageView image;
            TextView name, price, duration ;


            public myViewHolder(View itemView) {
                super(itemView);
                image = (ImageView) itemView.findViewById(R.id.img2);
                name = (TextView) itemView.findViewById(R.id.service_name);
                price = (TextView) itemView.findViewById(R.id.service_price);
                duration = (TextView) itemView.findViewById(R.id.service_duration);
                //category = (TextView) itemView.findViewById(R.id.service_category);
            }
        }
}