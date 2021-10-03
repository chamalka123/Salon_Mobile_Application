package com.example.salonalrisha;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    protected void onBindViewHolder(@NonNull ProductAdapter.myViewHolder holder, final int position, @NonNull  Products model) {

        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice() + " LKR");
        //holder.brand.setText(model.getBrand());
        holder.category.setText("Category : "+model.getCategory());
       //int total= ((Integer.valueOf(model.getPrice())))*Integer.valueOf(model.getQty());

        Glide.with(holder.image.getContext())
                .load(model.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.image);

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.image.getContext())
                        .setContentHolder(new ViewHolder(R.layout.single_product))
                        .setExpanded(true,1200)
                        .create();
                View view=dialogPlus.getHolderView();

                TextView name = view.findViewById(R.id.product_name);
                TextView price = view.findViewById(R.id.product_price);
               TextView brand = view.findViewById(R.id.product_brand);
                final TextView[] qty = {view.findViewById(R.id.product_qty)};

                final int[] count = {0};

                ImageView imageView8,imageView9;
                 imageView8=view.findViewById(R.id.imageView8);
                imageView9=view.findViewById(R.id.imageView9);
                name.setText(model.getName());
                price.setText("Price : " +model.getPrice()+ "LKR");
                brand.setText("Brand : "+model.getBrand());
                qty[0].setText(""+model.getQty());

                dialogPlus.show();
                imageView9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(count[0] <10){
                            count[0]++;
                            qty[0].setText(Integer.toString(count[0]));
                        }
                    }
                });
                dialogPlus.show();
                imageView8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(count[0] > 1){
                            count[0]--;
                            qty[0].setText(Integer.toString(count[0]));
                        }
                    }
                });
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.image.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_product))
                        .setExpanded(true,1100)
                        .create();
                //dialogPlus.show();
                View view=dialogPlus.getHolderView();
                EditText name = view.findViewById(R.id.product_name);
                EditText price = view.findViewById(R.id.product_price);
                EditText brand = view.findViewById(R.id.product_brand);
                EditText category = view.findViewById(R.id.product_category);

                Button btnEdit = view.findViewById(R.id.btnEdit);
                name.setText(model.getName());
                price.setText(model.getPrice());
                brand.setText(model.getBrand());
                category.setText(model.getCategory());


                dialogPlus.show();
                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("price",price.getText().toString());
                        map.put("brand",brand.getText().toString());
                        map.put("category",category.getText().toString());
                       // map.put("image",image.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Products")
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
;
            }
        });


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Products")
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
    public myViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productlist,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView name,price,brand,category;
        TextView  qty, total;
        Button btnEdit,btnDelete, btnView;

        public myViewHolder(View itemView) {
            super(itemView);
        image=(CircleImageView)itemView.findViewById(R.id.img1);
        name = (TextView)itemView.findViewById(R.id.product_name);
        price=(TextView)itemView.findViewById(R.id.product_price);
        brand=(TextView)itemView.findViewById(R.id.product_brand);
        category=(TextView)itemView.findViewById(R.id.product_category);
        qty=(TextView)itemView.findViewById(R.id.product_qty);
        total=(TextView)itemView.findViewById(R.id.total_price);
        btnEdit=(Button)itemView.findViewById(R.id.btnEdit);
        btnDelete=(Button)itemView.findViewById(R.id.btnDelete);
        btnView=(Button)itemView.findViewById(R.id.btnView);

        }
    }

}
