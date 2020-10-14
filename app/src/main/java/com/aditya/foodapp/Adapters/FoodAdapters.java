package com.aditya.foodapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.foodapp.Database.foodmodel;
import com.aditya.foodapp.DetailActivity;
import com.aditya.foodapp.Models.FoodModels;
import com.aditya.foodapp.R;

import java.util.ArrayList;

public class FoodAdapters extends RecyclerView.Adapter<FoodAdapters.viewholder>{
    Context context;
    ArrayList<FoodModels> list;

    public FoodAdapters(Context context, ArrayList<FoodModels> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_food_list,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final FoodModels model=list.get(position);
        holder.fimage.setImageResource(model.getImages());
        holder.fname.setText(model.getName());
        holder.fprice.setText(model.getPrice());
        holder.fdescription.setText(model.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, DetailActivity.class);
                i.putExtra("name",model.getName());
                i.putExtra("image",model.getImages());
                i.putExtra("price",model.getPrice());
                i.putExtra("description",model.getDescription());
                i.putExtra("type",1);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  viewholder extends  RecyclerView.ViewHolder{
        TextView fname,fprice,fdescription;
        ImageView fimage;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            fimage=itemView.findViewById(R.id.foodimage);
            fname=itemView.findViewById(R.id.foodname);
            fprice=itemView.findViewById(R.id.price);
            fdescription=itemView.findViewById(R.id.description);


        }
    }
}
