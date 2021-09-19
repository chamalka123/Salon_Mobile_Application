package com.example.salonalrisha;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductAdapter extends FirebaseRecyclerAdapter<Products,ProductAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductAdapter(@NonNull  FirebaseRecyclerOptions<Products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductAdapter.myViewHolder holder, int position, @NonNull  Products model) {
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.brand.setText(model.getBrand());
        holder.category.setText(model.getCategory());

        Glide.with(holder.img.getContext())
                .load(model.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
    }

    @NonNull

    @Override
    public myViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_lists,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name,price,brand,category;

        public myViewHolder( View itemView) {
            super(itemView);
        img=(CircleImageView)itemView.findViewById(R.id.img1);
        name = (TextView)itemView.findViewById(R.id.product_name);
        price=(TextView)itemView.findViewById(R.id.product_price);
        brand=(TextView)itemView.findViewById(R.id.product_brand);
        category=(TextView)itemView.findViewById(R.id.product_category);

        }
    }



}
